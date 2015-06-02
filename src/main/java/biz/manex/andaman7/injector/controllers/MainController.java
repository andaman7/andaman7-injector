package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.exceptions.AuthenticationException;
import biz.manex.andaman7.injector.exceptions.MissingTableModelException;
import biz.manex.andaman7.injector.models.*;
import biz.manex.andaman7.injector.models.types.MultivaluedTAMI;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.types.TAMI;
import biz.manex.andaman7.injector.utils.FileHelper;
import biz.manex.andaman7.injector.utils.XmlHelper;
import biz.manex.andaman7.injector.views.EditAmiDialog;
import biz.manex.andaman7.injector.views.LoginFrame;
import biz.manex.andaman7.injector.views.MainFrame;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
import biz.manex.andaman7.server.api.dto.device.DeviceDTO;
import biz.manex.andaman7.server.api.dto.ehrSynchro.RegistrarSyncContentDTO;
import biz.manex.andaman7.server.api.dto.others.FriendshipRequest;
import biz.manex.andaman7.server.api.dto.others.MessageDTO;
import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;
import biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
    private AndamanContextService contextService;

    /**
     * The EHR web service.
     */
    private AndamanEhrService ehrService;

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
    private RegistrarDTO currentUser;

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
    public RegistrarDTO getCurrentUser() {
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
        String contextServiceURL = serverURL + "context/v1/";
        String ehrServiceURL = serverURL + "ehr/v1/";
        String apiKey = settings.getApiKey();
        
        String username = settings.getUsername();
        String password = settings.getPassword();

        contextService = AndamanContextService.getInstance(
                contextServiceURL, apiKey, username, password);

        ehrService = AndamanEhrService.getInstance(ehrServiceURL, apiKey,
                username, password);
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
        
        url.append("/api/");
        
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
            mainFrame.setTamiGroupsList(); // TODO : Should build the main frame here
            currentUser = contextService.login();

            if (currentUser != null) {
                List<DeviceDTO> devices = currentUser.getDevices();

                if (devices == null) {
                    devices = new ArrayList<DeviceDTO>();
                    DeviceDTO[] devicesArray = contextService.getDevices();

                    if (devicesArray != null) {
                        devices.addAll(Arrays.asList(devicesArray));
                        currentUser.setDevices(devices);
                    }

                } else if (!devices.isEmpty()) {
                    mainFrame.setContextId(devices.get(0).getUuid());
                }

                loginFrame.setVisible(false);
                mainFrame.setVisible(true);
            }

        } catch(Exception e) {
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
    private Document getTamiXml() throws IOException, SAXException, ParserConfigurationException, AuthenticationException {

        String filename = "tami-dict.xml";
        File file = FileHelper.getFileInCurrentDir(filename);
        Document doc = null;

        // Get the current version of the XML file
        if (file.canRead()) {
            try {
                doc = XmlHelper.getDocument(file);
                XPathExpression expr = XmlHelper.getXPathExpression("//TamiDictionary/@version");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                currentTamiVersion = Integer.parseInt(nodes.item(0).getNodeValue());
                
            } catch (XPathExpressionException e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
            }
        }

        Document docFromServer = contextService.getTamiXml(currentTamiVersion);

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
    private Document getGuiXml() throws IOException, SAXException, ParserConfigurationException, AuthenticationException {

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

        Document docFromServer = contextService.getGuiXml(currentVersion);

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
     */
    public List<TamiGroup> getTamiGroups() throws IOException, SAXException, ParserConfigurationException, AuthenticationException {

        MessageDTO[] messages = contextService.getTranslations();
        HashMap<String, String> translations = new HashMap<String, String>();
        List<TamiGroup> tamiGroups = new ArrayList<TamiGroup>();

        for(MessageDTO message : messages)
            if(message.getLanguageCode().equals("EN"))
                translations.put(message.getKey(), message.getValue());

        Document doc = getTamiXml();
        getGuiXml();

        try {
            // Get the selection lists
            Map<String, SelectionList> selectionLists = xmlController.getSelectionLists(doc, translations);

            // Get the default qualifier types
            List<QualifierType> defaultQualifierTypes = xmlController.getDefaultQualifierTypes(doc, translations);

            // Get the TAMI groups
            XPathExpression expr = XmlHelper.getXPathExpression("//TamiGroup");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for(int i = 0; i < nodes.getLength(); i++) {

                Node tamiGroupNode = nodes.item(i);
                String key = tamiGroupNode.getAttributes().getNamedItem("id").getNodeValue();
                String name;

                if(translations.containsKey(key))
                    name = translations.get(key);
                else
                    name = key;

                List<TAMI> tamis = xmlController.getTamis(translations, defaultQualifierTypes, selectionLists, tamiGroupNode);

                TamiGroup tamiGroup = new TamiGroup(key, name, tamis);
                tamiGroups.add(tamiGroup);
            }

            return tamiGroups;

        } catch(XPathExpressionException e) {
            return new ArrayList<TamiGroup>();
        }
    }

    /**
     * Searches some {@link biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO}
     * according to a specified keyword.
     *
     * The search is done on the first name, last name and email address.
     *
     * @param keyword the keyword on which the search is based
     * @return a list of found {@link biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO}
     * @throws java.io.IOException
     */
    public AndamanUserDTO[] searchUsers(String keyword) throws IOException, AuthenticationException {

        AndamanUserDTO[] results = contextService.searchUsers(keyword);
        RegistrarDTO[] members = contextService.getCommunityMembers();

        // Add all user from search and add only the members that correspond to
        // the specified keyword except ourselves
        ArrayList<AndamanUserDTO> users = new ArrayList<AndamanUserDTO>();
        users.addAll(Arrays.asList(results));

        for (RegistrarDTO member : members)
            if ((member.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    member.getLastName().toLowerCase().contains(keyword.toLowerCase())) &&
                    !member.getUuid().equals(currentUser.getUuid()))
                users.add(member);

        return users.toArray(new AndamanUserDTO[users.size()]);
    }

    /**
     * Sends some community invitations.
     *
     * @param senderDeviceId the UUID of the sender device
     * @param newCommunityMembers the list of community members to add
     * @return the {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}s
     *         of the added community members.
     */
    private RegistrarDTO[] sendCommunityInvitation(String senderDeviceId, String[] newCommunityMembers) throws IOException, AuthenticationException {
        return contextService.sendCommunityRequest(senderDeviceId, newCommunityMembers);
    }

    /**
     * Returns all received invitations.
     *
     * @return the received invitations
     * @throws IOException if there was an error with the connection to the server
     */
    public FriendshipRequest[] getCommunityInvitations() throws IOException, AuthenticationException {
        return contextService.getInvitations();
    }

    // TODO
    public void setCommunityInvitationAcceptance(String otherRegistrarUuid, boolean acceptanceLevel) throws IOException, AuthenticationException {
        contextService.setAcceptance(otherRegistrarUuid, acceptanceLevel);
    }

    /**
     * Sends some medical data into a specific AMI container of a specified registrar.
     *
     * @param amiContainersToSync the list of AMI containers to send
     * @return how many invitations were sent to registrars that weren't in the community of the sender.
     * @throws IOException TODO
     */
    public int sendMedicalData(List<AMIContainer> amiContainersToSync) throws IOException, AuthenticationException {

        Set<String> idsToSendInvitation = new HashSet<String>();
        
        for(AMIContainer amiContainerToSync : amiContainersToSync) {

            RegistrarDTO[] members = contextService.getCommunityMembers();
            List<String> destinationRegistrarsIds = amiContainerToSync.getDestinationRegistrarsIds();
            
            for(String destinationRegistrarId : destinationRegistrarsIds) {
            
                boolean alreadyMember = false;

                // Check if the destination registrar is already a community member
                for (RegistrarDTO member : members)
                    if (member.getUuid().equalsIgnoreCase(destinationRegistrarId))
                        alreadyMember = true;

                if(!alreadyMember)
                    idsToSendInvitation.add(destinationRegistrarId);
            }
            
            String[] newMembersIds = idsToSendInvitation.toArray(new String[idsToSendInvitation.size()]);
            contextService.sendCommunityRequest(currentUser.getDevices().get(0).getUuid(), newMembersIds);
        }

        // Send the data
        ehrService.sendAmiBasesToRegistrar(currentUser, amiContainersToSync, currentTamiVersion);

        
        return idsToSendInvitation.size();
    }

    // TODO
    public RegistrarSyncContentDTO[] getMedicalDataInQueue(String deviceId) throws IOException {
        return ehrService.getMedicalDataInQueue(deviceId);
    }

    // TODO
    public void acknowledgeMedicalData(String deviceId, String[] medicalRecordsId) throws IOException {
        ehrService.acknowledgeMedicalData(deviceId, medicalRecordsId);
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
}
