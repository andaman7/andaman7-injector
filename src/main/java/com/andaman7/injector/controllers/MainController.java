package com.andaman7.injector.controllers;

import com.andaman7.server.api.pub.dto.user.AuthenticatedUserDTO;
import com.andaman7.server.api.pub.dto.user.UserDTO;
import com.andaman7.server.api.pub.dto.ehr.ResultSyncContentDTO;
import com.andaman7.injector.exceptions.AndamanException;
import com.andaman7.injector.exceptions.AuthenticationException;
import com.andaman7.injector.exceptions.MissingTableModelException;
import com.andaman7.injector.models.*;
import com.andaman7.injector.models.types.MultivaluedTAMI;
import com.andaman7.injector.models.types.QualifierType;
import com.andaman7.injector.models.types.TAMI;
import com.andaman7.injector.utils.FileHelper;
import com.andaman7.injector.utils.XmlHelper;
import com.andaman7.injector.views.EditAmiDialog;
import com.andaman7.injector.views.LoginFrame;
import com.andaman7.injector.views.MainFrame;
import com.andaman7.injector.webservice.AndamanService;
import com.andaman7.server.api.pub.dto.device.DeviceDTO;
import com.andaman7.server.api.pub.dto.invitations.InvitationDTO;
import com.andaman7.server.api.pub.dto.translation.TranslationDTO;
import com.andaman7.server.api.pub.dto.translation.TranslationEntryDTO;
import com.andaman7.server.api.pub.dto.trusteduser.TrustedUserCreationDTO;
import com.andaman7.server.api.pub.dto.trusteduser.TrustedUserDTO;
import com.andaman7.server.api.pub.dto.trusteduser.TrustedUserModificationDTO;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The main controller that coordinates the views and performs all the business logic.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://www.a7-software.com)<br/>
 * Date: 19/01/2015.
 */
public class MainController {

    /**
     * The context web service.
     */
    private AndamanService andamanService;


    /**
     * The XML controller.
     */
    private XmlController xmlController;
    
    /**
     * The login GUI frame.
     */
    private LoginFrame loginFrame;

    /**
     * The main GUI frame.
     */
    private MainFrame mainFrame;
    
    /**
     * The current user that is logged in.
     */
    private AuthenticatedUserDTO currentUser;
    
    /**
     * The device of the current user.
     */
    private DeviceDTO currentDevice;

    /**
     * The current version of the XML file where the TAMIs are described.
     */
    private int currentTamiVersion;


    /**
     * Builds a main controller using a specific XML controller.
     * @param xmlController TODO
     */
    public MainController(XmlController xmlController) {
        this.xmlController = xmlController;
    }

    /**
     * Returns the current user.
     *
     * @return the current user
     */
    public AuthenticatedUserDTO getCurrentUser() {
        return currentUser;
    }

    /**
     * Starts the GUI.
     *
     * @param loginFrame the login GUI frame
     * @param mainFrame the main GUI frame
     */
    public void start(LoginFrame loginFrame, MainFrame mainFrame) {
        
        this.loginFrame = loginFrame;
        this.mainFrame = mainFrame;
        this.loginFrame.setVisible(true);
    }

    /**
     * Setup the settings.
     *
     * @param settings the settings
     */
    public void setSettings(Settings settings) {
        
        String serverURL = buildServerURL(settings);
        String contextServiceURL = serverURL + "public/v1/";
        String apiKey = settings.getApiKey();
        
        String username = settings.getUsername();
        String password = settings.getPassword();

        andamanService = AndamanService.getInstance(contextServiceURL, apiKey, username, password);
    }

    /**
     * Build the URL of the server.
     *
     * @param settings the settings
     * @return the URL of the server
     */
    private String buildServerURL(Settings settings) {
        StringBuilder url = new StringBuilder();
        url.append("http");
        
        // Use HTTPS if SSL is used
        if(settings.getServerPort().equals("443") || settings.isHttps())
            url.append("s");
        
        url.append("://").append(settings.getServerHostname());
        
        // Add explicitly the port if not the default HTTP or HTTPS one
        if(!settings.getServerPort().equals("80") &&
                !settings.getServerPort().equals("443"))
            url.append(":").append(settings.getServerPort());
        
        url.append("/");
        
        return url.toString();
    }

    /**
     * Logs the user in.
     * 
     * @param settings the settings used to connect to the server and to login
     */
    public void login(Settings settings) {
        
        // Get the settings from the login frame and log the user in
        try {

            setSettings(settings);
            mainFrame.setTamiGroupsList();
            currentUser = andamanService.getAuthenticatedUser();

            if (currentUser != null) {
                List<DeviceDTO> devices = andamanService.getDevices();

                if (!devices.isEmpty()) {
                    currentDevice = devices.get(0);
                    mainFrame.setContextId(currentDevice.getId());
                } else {
                    throw new AndamanException("The logged user must have at least one device.");
                }

                loginFrame.setVisible(false);
                mainFrame.setVisible(true);
            }

        } catch(IOException | SAXException | ParserConfigurationException | AndamanException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(loginFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Logs the user out.
     */
    public void logout() {
        
        currentUser = null;
        mainFrame.clearForm();
        mainFrame.setVisible(false);
        loginFrame.setVisible(true);
    }

    /**
     * Returns the XML file where the TAMIs are described.
     *
     * @return the XML document
     * @throws IOException TODO
     * @throws SAXException TODO
     * @throws ParserConfigurationException TODO
     */
    private Document getTamiXml() throws IOException, SAXException, ParserConfigurationException, AndamanException {

        String filename = "tami-dict.xml";
        File file = FileHelper.getFileInCurrentDir(filename);
        Document doc = null;

        // Get the current version of the XML file
        if (file.canRead()) {
            try {
                doc = XmlHelper.getDocument(file);
                XPathExpression expr = XmlHelper.getXPathExpression("//AmiDictionary/@version");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                currentTamiVersion = Integer.parseInt(nodes.item(0).getNodeValue());
                
            } catch (XPathExpressionException e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
            }
        }

        Document docFromServer = andamanService.getTamiXml(currentTamiVersion);

        // If a new version is available, overwrite the file
        if(docFromServer != null) {
            XmlHelper.writeDocumentToFile(docFromServer, file);
            doc = docFromServer;
        }

        return doc;
    }

    /**
     * Returns the XML file where the GUI panels layouts are described.
     *
     * @return the XML document
     * @throws IOException TODO
     * @throws SAXException TODO
     * @throws ParserConfigurationException TODO
     */
    private Document getGuiXml() throws IOException, SAXException, ParserConfigurationException, AndamanException {

        String filename = "gui-dict.xml";
        int currentVersion = 0;
        File file = FileHelper.getFileInCurrentDir(filename);
        Document doc = null;

        // Get the current version of the XML file
        if (file.canRead()) {
            try {
                doc = XmlHelper.getDocument(file);
                XPathExpression expr = XmlHelper.getXPathExpression("//GuiDictionary/@version");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                currentVersion = Integer.parseInt(nodes.item(0).getNodeValue());
                
            } catch (XPathExpressionException e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
            }
        }

        Document docFromServer = andamanService.getGuiXml(currentVersion);

        // If a new version is available, overwrite the file
        if(docFromServer != null) {
            XmlHelper.writeDocumentToFile(docFromServer, file);
            doc = docFromServer;
        }

        return doc;
    }

    /**
     * Returns the TAMIs from the most recent XML file.
     *
     * @return a list of the TAMIs
     * @throws java.io.IOException TODO
     * @throws org.xml.sax.SAXException TODO
     * @throws javax.xml.parsers.ParserConfigurationException TODO
     * @throws AuthenticationException
     */
    public List<TamiGroup> getTamiGroups() throws IOException, SAXException, ParserConfigurationException, AndamanException {

        List<TranslationDTO> translations = andamanService.getTranslations();
        HashMap<String, String> translationMap = new HashMap<>();
        Map<String, TamiGroup> tamiGroups = new HashMap<>();

        for(TranslationDTO translation : translations)
            for(TranslationEntryDTO translationEntry : translation.getTranslationEntries())
            if(translationEntry.getLanguageCode().equals("EN"))
                translationMap.put(translation.getKey(), translationEntry.getValue());

        Document doc = getTamiXml();
        getGuiXml();

        // Get the selection lists
        Map<String, SelectionList> selectionLists = xmlController.getSelectionLists(doc, translationMap);

        // Get the default qualifier types
        List<QualifierType> defaultQualifierTypes = xmlController.getDefaultQualifierTypes(doc, translationMap);
        List<TAMI> tamis = xmlController.getAmis(doc, translationMap, defaultQualifierTypes, selectionLists, tamiGroups);


        return new ArrayList<>(tamiGroups.values());
    }

    /**
     * Searches some {@link com.andaman7.server.api.dto.registrar.AndamanUserDTO}
     * according to a specified keyword.
     *
     * The search is done on the first name, last name and email address.
     *
     * @param keyword the keyword on which the search is based
     * @return a list of found {@link com.andaman7.server.api.dto.registrar.AndamanUserDTO}
     * @throws java.io.IOException
     * @throws AuthenticationException
     */
    public List<UserDTO> searchUsers(String keyword) throws IOException, AndamanException {

        List<UserDTO> users = andamanService.searchUsers(keyword);
        Map<String, UserDTO> usersMap = new HashMap<>();

        for (UserDTO user : users)
            usersMap.put(user.getId(), user);

        usersMap.remove(currentUser.getId());

        List<UserDTO> trustedUsers = andamanService.getTrustedUsers();

        // Add only the members that correspond to
        // the specified keyword except ourselves
        for (UserDTO trustedUser : trustedUsers)
            if ((trustedUser.getAdministrative().getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    trustedUser.getAdministrative().getLastName().toLowerCase().contains(keyword.toLowerCase())) &&
                    !trustedUser.getId().equals(currentUser.getId()) &&
                    !usersMap.containsKey(trustedUser.getId()))
                usersMap.put(trustedUser.getId(), trustedUser);

        return new ArrayList<>(usersMap.values());
    }

    /**
     * Sends some community invitations.
     *
     * @return the {@link com.andaman7.server.api.dto.registrar.RegistrarDTO}s
     *         of the added community members.
     */
    private List<TrustedUserDTO> sendCommunityInvitation(TrustedUserCreationDTO trustedUserDTO) throws IOException, AndamanException {
        return andamanService.sendCommunityRequest(trustedUserDTO);
    }

    /**
     * Returns all received invitations.
     *
     * @return the received invitations
     * @throws IOException if there was an error with the connection to the server
     * @throws AuthenticationException
     */
    public List<InvitationDTO> getInvitations() throws IOException, AndamanException {
        return andamanService.getInvitations();
    }

    // TODO
    public void setCommunityInvitationAcceptance(String otherRegistrarUuid, TrustedUserModificationDTO trustedUser) throws IOException, AndamanException {
        andamanService.setAcceptance(otherRegistrarUuid, trustedUser);
    }

    /**
     * Sends some medical data into a specific AMI container of a specified registrar.
     *
     * @param amiContainersToSync the list of AMI containers to send
     * @return how many invitations were sent to registrars that weren't in the community of the sender.
     * @throws IOException TODO
     * @throws AuthenticationException
     */
    public int sendMedicalData(List<AMIContainer> amiContainersToSync) throws IOException, AndamanException {

        Set<String> idsToSendInvitation = new HashSet<>();
        
        for(AMIContainer amiContainerToSync : amiContainersToSync) {

            List<UserDTO> trustedUsers = andamanService.getTrustedUsers();
            List<String> destinationRegistrarsIds = amiContainerToSync.getDestinationRegistrarsIds();
            
            for(String destinationRegistrarId : destinationRegistrarsIds) {
            
                boolean alreadyMember = false;

                // Check if the destination registrar is already a community member
                for (UserDTO trustedUser : trustedUsers)
                    if (trustedUser.getId().equalsIgnoreCase(destinationRegistrarId))
                        alreadyMember = true;

                if(!alreadyMember)
                    idsToSendInvitation.add(destinationRegistrarId);
            }
            
            String[] newMembersIds = idsToSendInvitation.toArray(new String[idsToSendInvitation.size()]);

            for (String newMembersId : newMembersIds) {
                TrustedUserCreationDTO trustedUserDTO = new TrustedUserCreationDTO();
                trustedUserDTO.setSenderDeviceId(currentDevice.getId());
                trustedUserDTO.setMemberId(newMembersId);
                
                andamanService.sendCommunityRequest(trustedUserDTO);
            }
    
            for(String destinationRegistrarId : destinationRegistrarsIds) {
                // Send the data
                andamanService.sendAmiBasesToRegistrar(currentUser, amiContainerToSync, currentTamiVersion);
            }
        }
        
        return idsToSendInvitation.size();
    }

    // TODO
    public List<ResultSyncContentDTO> getMedicalDataInQueue(String userId, String deviceId) throws IOException, AndamanException {
        return andamanService.getMedicalDataInQueue(userId, deviceId);
    }

    // TODO
    public void acknowledgeMedicalData(String[] medicalRecordsIds) throws IOException, AndamanException {
        andamanService.acknowledgeMedicalData(medicalRecordsIds);
    }

    // TODO
    public AMI showEditAmiDialog(AMI ami) throws MissingTableModelException {
        
        EditAmiDialog editAmiDialog;
        
        if(ami.getType() instanceof MultivaluedTAMI) {
            MultivaluedTAMI multiTami = (MultivaluedTAMI) ami.getType();
            editAmiDialog = new EditAmiDialog(mainFrame, true, this, ami, multiTami.getValues().getItems());
        } else {
            editAmiDialog = new EditAmiDialog(mainFrame, true, this, ami);
        }
        editAmiDialog.setVisible(true);
        
        if(editAmiDialog.getCloseStatus() == EditAmiDialog.Status.VALIDATED)
            return editAmiDialog.getEditedAmi();
        else
            return ami;
    }

    public DeviceDTO getCurrentDevice() {
        return currentDevice;
    }
}
