package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.models.types.MultivaluedTAMI;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.types.TAMI;
import biz.manex.andaman7.injector.models.types.MultivaluedQualifierType;
import biz.manex.andaman7.injector.models.*;
import biz.manex.andaman7.injector.utils.FileHelper;
import biz.manex.andaman7.injector.utils.XmlHelper;
import biz.manex.andaman7.injector.views.LoginFrame;
import biz.manex.andaman7.injector.views.MainFrame;
import biz.manex.andaman7.injector.views.QualifiersDialog;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
import biz.manex.andaman7.server.api.dto.device.DeviceDTO;
import biz.manex.andaman7.server.api.dto.others.MessageDTO;
import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;
import biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 * The main controller that coordinates the views and performs all the business
 * logic.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://www.manex.biz)<br/>
 * Date: 19/01/2015.<br/>
 */
public class MainController implements ActionListener {

    /**
     * The context web service.
     */
    private AndamanContextService contextService;

    /**
     * The EHR web service.
     */
    private AndamanEhrService ehrService;

    /**
     * The qualifiers controller.
     */
    private QualifiersController qualifiersController;
    
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
    
    
    public void setQualifiersController(QualifiersController qualifiersController) {
        this.qualifiersController = qualifiersController;
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
     * Returns the qualifiers controller.
     * 
     * @return the qualifiers controller
     */
    public QualifiersController getQualifiersController() {
        return qualifiersController;
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
        
        qualifiersController = new QualifiersController(this, contextService, ehrService);
        this.mainFrame.setQualifiersController(qualifiersController);
        
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
     * @return the {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO} of the logged user.
     */
    private RegistrarDTO login() throws IOException {
        return contextService.login();
    }

    /**
     * Returns the XML file where the TAMIs are described.
     *
     * @return the XML document
     */
    private Document getTamiXml() throws IOException, SAXException, ParserConfigurationException {

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
                e.printStackTrace();
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
     */
    private Document getGuiXml() throws IOException, SAXException, ParserConfigurationException {

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
                e.printStackTrace();
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
     * Returns a map of selection lists.
     *
     * @param xmlDoc the XML document from which to get the selection lists
     * @param translations the translations of the keys
     * @return a map of selection lists
     */
    private Map<String, SelectionList> getSelectionLists(Document xmlDoc, HashMap<String, String> translations) {

        HashMap<String, SelectionList> selectionLists = new HashMap<String, SelectionList>();

        try {
            XPathExpression selectionListsExpr = XmlHelper.getXPathExpression("//SelectionList");
            NodeList selectionListsNodes = (NodeList) selectionListsExpr.evaluate(xmlDoc, XPathConstants.NODESET);

            for (int i = 0; i < selectionListsNodes.getLength(); i++) {
                Node node = selectionListsNodes.item(i);

                String slKey = node.getAttributes().getNamedItem("id").getNodeValue();
                String slName = translations.get(slKey);
                HashMap<String, SelectionListItem> items = new HashMap<String, SelectionListItem>();

                // Get the items of the selection list
                for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                    Node childNode = node.getChildNodes().item(j);
                    if (childNode.getNodeName().equals("Item")) {

                        String itemKey = childNode.getAttributes().getNamedItem("id").getNodeValue();
                        String itemName = translations.get(itemKey);
                        SelectionListItem item = new SelectionListItem(itemKey, itemName);
                        items.put(itemKey, item);
                    }
                }

                selectionLists.put(slKey, new SelectionList(slKey, items));
            }

        } catch(XPathExpressionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return selectionLists;
    }

    /**
     * Returns a list of the default qualifier types.
     *
     * @param doc the XML document from which to get the default qualifier types
     * @param translations the translation of the keys
     * @return a list of the default qualifier types
     */
    private List<QualifierType> getDefaultQualifierTypes(Document doc, HashMap<String, String> translations) {

        ArrayList<QualifierType> defaultQualifiersTypes = new ArrayList<QualifierType>();

        try {
            XPathExpression defaultQualifierTypesExpr = XmlHelper.getXPathExpression("//DefaultQualifier");
            NodeList defaultQualifierTypesNodes = (NodeList) defaultQualifierTypesExpr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < defaultQualifierTypesNodes.getLength(); i++) {
                Node node = defaultQualifierTypesNodes.item(i);
                String key = node.getAttributes().getNamedItem("id").getNodeValue();
                String value = "";

                if (key.equals("default.note"))
                    value = "Note";
                else if (key.equals("default.stars"))
                    continue;
                else
                    value = translations.get(key);

                defaultQualifiersTypes.add(new QualifierType(key, value));
            }
        } catch(XPathExpressionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return defaultQualifiersTypes;
    }

    /**
     * Returns a list of the qualifier types of a TAMI.
     *
     * @param defaultQualifierTypes the list of all default qualifier types
     * @param translations the translation of the keys
     * @param tamiNode the XML node of a TAMI
     * @param selectionLists all the available selection lists
     * @return a list of the qualifier types of a TAMI
     */
    private List<QualifierType> getQualifierTypes(List<QualifierType> defaultQualifierTypes,
            HashMap<String, String> translations, Node tamiNode, Map<String, SelectionList> selectionLists) {

        List<QualifierType> qualifierTypes = new ArrayList<QualifierType>();
        qualifierTypes.addAll(defaultQualifierTypes);

        NodeList childNodes = tamiNode.getChildNodes();

        // Process all qualifiers
        for (int j = 0; j < childNodes.getLength(); j++) {

            Node qualifierNode = childNodes.item(j);

            if(!qualifierNode.getNodeName().equals("Qualifier"))
                continue;

            String key = qualifierNode.getAttributes().getNamedItem("id").getNodeValue();
            String name;

            // Use the key as name if the key is not found in the translations
            if(translations.containsKey(key))
                name = translations.get(key);
            else
                name = key;

            String type = qualifierNode.getAttributes().getNamedItem("type").getNodeValue();

            // If the qualifier is multivalued
            if (type.equals("oneSelection") || type.equals("multiSelection")) {
                String selectionListId = qualifierNode.getAttributes().getNamedItem("selectionListId").getNodeValue();
                SelectionList selectionList = selectionLists.get(selectionListId);

                MultivaluedQualifierType qualifierType = new MultivaluedQualifierType(key, name, selectionList);
                qualifierTypes.add(qualifierType);

            // Otherwise
            } else {
                QualifierType qualifierType = new QualifierType(key, name);
                qualifierTypes.add(qualifierType);
            }
        }

        return qualifierTypes;
    }

    /**
     * Returns the TAMIs from the most recent XML file.
     *
     * @return a list of the TAMIs
     * @throws java.io.IOException 
     */
    public TAMI[] getTamis() throws IOException, SAXException, ParserConfigurationException {

        MessageDTO[] messages = contextService.getTranslations();
        HashMap<String, String> translations = new HashMap<String, String>();
        ArrayList<TAMI> tamis = new ArrayList<TAMI>();

        for(MessageDTO message : messages)
            if(message.getLanguageCode().equals("EN"))
                translations.put(message.getKey(), message.getValue());

        Document doc = getTamiXml();
        getGuiXml();

        try {
            // Get the selection lists
            Map<String, SelectionList> selectionLists = getSelectionLists(doc, translations);

            // Get the default qualifier types
            List<QualifierType> defaultQualifierTypes = getDefaultQualifierTypes(doc, translations);

            // Get the TAMIs
            XPathExpression expr = XmlHelper.getXPathExpression("//Tami");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String key = node.getAttributes().getNamedItem("id").getNodeValue();
                String type = node.getAttributes().getNamedItem("type").getNodeValue();
                String name;

                if(translations.containsKey(key))
                    name = translations.get(key);
                else
                    name = key;

                List<QualifierType> qualifierTypes = getQualifierTypes(defaultQualifierTypes, translations, node, selectionLists);

                if(type.equals("oneSelection") ||type.equals("multiSelection")) {
                    String selectionListId = node.getAttributes().getNamedItem("selectionListId").getNodeValue();
                    SelectionList selectionList = selectionLists.get(selectionListId);

                    MultivaluedTAMI tami = new MultivaluedTAMI(key, name, selectionList, qualifierTypes);
                    tamis.add(tami);

                } else {
                    TAMI tami = new TAMI(key, name, qualifierTypes);
                    tamis.add(tami);
                }
            }

            return tamis.toArray(new TAMI[tamis.size()]);

        } catch(XPathExpressionException e) {
            return new TAMI[0];
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
    public AndamanUserDTO[] searchUsers(String keyword) throws IOException {

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
    private RegistrarDTO[] sendCommunityInvitation(String senderDeviceId, String[] newCommunityMembers) {

        try {
            return contextService.sendCommunityRequest(senderDeviceId, newCommunityMembers);

        } catch (IOException e) {
            return new RegistrarDTO[0];
        }
    }

    /**
     * Sends some medical data into a specific AMI container of a specified
     * registrar.
     *
     * @param destinationRegistrar the destination registrar
     * @param amiContainers the AMI container UUID and the AMIs to send
     * @param contextId the context identifier
     * @return {@code true} if the destination registrar is already a member of the source registrar's community,
     *         {@code false} otherwise
     * @throws java.io.IOException
     */
    public boolean sendMedicalData(AndamanUserDTO destinationRegistrar, List<AMIContainer> amiContainers,
            String contextId) throws IOException {

        RegistrarDTO[] members = contextService.getCommunityMembers();

        boolean alreadyMember = false;
        
        // Check if the destination registrar is already a community member
        for(RegistrarDTO member : members)
            if(member.getUuid().equalsIgnoreCase(destinationRegistrar.getUuid()))
                alreadyMember = true;
        
        // If not, send an invitation
        if(!alreadyMember)
            sendCommunityInvitation(currentUser.getUuid(), new String[] { destinationRegistrar.getUuid() });
        
        // Send the data
        ehrService.sendAmiBasesToRegistrar(currentUser, destinationRegistrar,
                amiContainers, contextId, currentTamiVersion);
        
        return alreadyMember;
    }

    /**
     * Returns a map of AMIs from an CSV file.
     *
     * @param file the CSV file
     * @return a list of AMIs
     * @throws IOException if the CSV file is not found
     */
    public List<AMI> getAmisFromCsvFile(File file) throws IOException {

        // Get the AMIs from the CSV file
        Reader in = new FileReader(file);
        CSVParser csvParser = new CSVParser(in, CSVFormat.EXCEL.withDelimiter(';').withHeader());
        Iterable<CSVRecord> records = csvParser.getRecords();

        List<AMI> amis = new ArrayList<AMI>();

        for (CSVRecord record : records) {
            String type = record.get("tami");
            String value = record.get("value");

            TAMI tami = new TAMI();
            tami.setKey(type);

            AMI ami = new AMI();
            ami.setType(tami);
            ami.setValue(value);
        }

        return amis;
    }

    /**
     * Listens to a login and logout button press.
     *
     * @param evt the event raised by the button press
     */
    public void actionPerformed(ActionEvent evt) {
        
        if(!(evt.getSource() instanceof JButton))
            return;

        JButton button = (JButton) evt.getSource();

        // Login action
        if(button.getName().equalsIgnoreCase("login")) {

            // Get the settings from the login frame and log the user in
            try {
            
                Settings settings = loginFrame.getSettings();
                setSettings(settings);
                mainFrame.setTamiList();
                currentUser = login();

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
                JOptionPane.showMessageDialog(button.getRootPane(), e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } 

        // Logout action
        } else if(button.getName().equalsIgnoreCase("logout")) {
            currentUser = null;
            mainFrame.clearForm();
            mainFrame.setVisible(false);
            loginFrame.setVisible(true);
        }
    }
}
