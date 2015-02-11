package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.models.Settings;
import biz.manex.andaman7.injector.models.TAMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.models.dto.DeviceDTO;
import biz.manex.andaman7.injector.models.dto.MessageDTO;
import biz.manex.andaman7.injector.models.dto.RegistrarDTO;
import biz.manex.andaman7.injector.utils.FileHelper;
import biz.manex.andaman7.injector.utils.XmlHelper;
import biz.manex.andaman7.injector.views.LoginFrame;
import biz.manex.andaman7.injector.views.MainFrame;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
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
import javax.swing.JButton;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

/**
 *
 * @author Pierre-Yves
 */
public class MainController implements ActionListener {

    private RegistrarDTO currentUser;
    
    private AndamanContextService contextService;
    private AndamanEhrService ehrService;
    
    private LoginFrame loginFrame;
    private MainFrame mainFrame;

    private int currentTamiVersion = 0;
    
    
    public RegistrarDTO getCurrentUser() {
        return currentUser;
    }
    
    public void start(LoginFrame loginFrame, MainFrame mainFrame) {
        this.loginFrame = loginFrame;
        this.mainFrame = mainFrame;
        
        this.loginFrame.setVisible(true);
    }
    
    public void setSettings(Settings settings) {
        
        String serverURL = buildServerURL(settings);
        String contextServiceURL = serverURL + "context/v1/";
        String ehrServiceURL = serverURL + "ehr/v1/";
        String apiKey = settings.getApiKey();
        
        String username = settings.getUsername();
        String password = settings.getPassword();
        
        this.contextService = AndamanContextService.getInstance(
                contextServiceURL, apiKey, username, password);
        
        this.ehrService = AndamanEhrService.getInstance(ehrServiceURL, apiKey,
                username, password);
    }
    
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
    
    public RegistrarDTO login() {
        return this.contextService.login();
    }

    private Document getTamiXml() {

        String filename = "tamidict.xml";
        File file = FileHelper.getFileInCurrentDir(filename);
        Document doc = null;

        try {
            // Get the current version of the XML file
            if (file.canRead()) {
                doc = XmlHelper.getDocument(file);
                XPathExpression expr = XmlHelper.getXPathExpression("//itemDictionary/@version");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                this.currentTamiVersion = Integer.parseInt(nodes.item(0).getNodeValue());
            }

            Document docFromServer = this.contextService.getTamiXml(this.currentTamiVersion);

            // If a new version is available, overwrite the file
            if(docFromServer != null) {
                XmlHelper.writeDocumentToFile(docFromServer, file);
                doc = docFromServer;
            }

            return doc;

        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private Document getGuiXml() {

        String filename = "guipanels.xml";
        int currentVersion = 0;
        File file = FileHelper.getFileInCurrentDir(filename);
        Document doc = null;

        try {
            // Get the current version of the XML file
            if (file.canRead()) {
                doc = XmlHelper.getDocument(file);
                XPathExpression expr = XmlHelper.getXPathExpression("//guiDictionary/@version");
                NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                currentVersion = Integer.parseInt(nodes.item(0).getNodeValue());
            }

            Document docFromServer = this.contextService.getGuiXml(currentVersion);

            // If a new version is available, overwrite the file
            if(docFromServer != null) {
                XmlHelper.writeDocumentToFile(docFromServer, file);
                doc = docFromServer;
            }

            return doc;

        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public TAMI[] getTamis() {
        try {
            MessageDTO[] messages = this.contextService.getTranslations();
            HashMap<String, String> translations = new HashMap<String, String>();
            ArrayList<TAMI> tamis = new ArrayList<TAMI>();

            for(MessageDTO message : messages)
                if(message.getLanguageCode().equals("EN"))
                    translations.put(message.getKey(), message.getValue());

            Document doc = this.getTamiXml();
            this.getGuiXml();
            XPathExpression expr = XmlHelper.getXPathExpression("//tami/@id");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String key = node.getNodeValue();

                if(translations.containsKey(key)) {
                    TAMI tami = new TAMI(key, translations.get(key));
                    tamis.add(tami);
                }
            }

            return tamis.toArray(new TAMI[tamis.size()]);

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
    
    public AndamanUserDTO[] searchUsers(String keyword) {       
        AndamanUserDTO[] results = this.contextService.searchUsers(keyword);
        RegistrarDTO[] members = this.contextService.getCommunityMembers();
        
        // Add all user from search and add only the members that correspond to
        // the specified keyword except ourselves
        ArrayList<AndamanUserDTO> users = new ArrayList<AndamanUserDTO>();
        users.addAll(Arrays.asList(results));
        
        for(RegistrarDTO member : members)
            if((member.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    member.getLastName().toLowerCase().contains(keyword.toLowerCase())) &&
                    !member.getUuid().equals(this.currentUser.getUuid()))
                users.add(member);
        
        return users.toArray(new AndamanUserDTO[users.size()]);
    }
    
    public RegistrarDTO[] sendCommunityInvitation(String senderDeviceId,
            String[] newCommunityMembers) {
        return this.contextService.sendCommunityRequest(senderDeviceId,
                newCommunityMembers);
        
    }
    
    public boolean sendData(AndamanUserDTO destinationRegistrar,
            List<AMIContainer> amiContainers, String contextId) {
        
        RegistrarDTO[] members = this.contextService.getCommunityMembers();
        boolean alreadyMember = false;
        
        // Check if the destination registrar is already a community member
        for(RegistrarDTO member : members)
            if(member.getUuid().equalsIgnoreCase(destinationRegistrar.getUuid()))
                alreadyMember = true;
        
        // If not, send an invitation
        if(!alreadyMember)
            this.sendCommunityInvitation(this.currentUser.getUuid(), new String[] { destinationRegistrar.getUuid() });
        
        // Send the data
        this.ehrService.sendAmiBasesToRegistrar(this.currentUser, destinationRegistrar,
                amiContainers, contextId, this.currentTamiVersion);
        
        return alreadyMember;
    }
    
    public HashMap<String, String> getAmisFromCsvFile(File file) throws IOException {

        // Get the AMIs from the CSV file
        Reader in = new FileReader(file);
        CSVParser csvParser = new CSVParser(in, CSVFormat.EXCEL.withDelimiter(';').withHeader());
        Iterable<CSVRecord> records = csvParser.getRecords();

        HashMap<String, String> amis = new HashMap<String, String>();

        for (CSVRecord record : records) {
            String tami = record.get("tami");
            String value = record.get("value");

            amis.put(tami, value);
        }

        return amis;
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if(button.getName().equalsIgnoreCase("login")) {
                
                // Get the settings from the login frame and login the user
                Settings settings = this.loginFrame.getSettings();
                
                if(settings != null) {
                    this.setSettings(settings);
                    this.mainFrame.setTamiList();
                    this.currentUser = this.login();
                
                    if(this.currentUser != null) {
                        List<DeviceDTO> devices = currentUser.getDevices();

                        if(devices == null) {
                            devices = new ArrayList<DeviceDTO>();
                            DeviceDTO[] devicesArray = this.contextService.getDevices();

                            if(devicesArray != null) {
                                devices.addAll(Arrays.asList(devicesArray));
                                this.currentUser.setDevices(devices);
                            }
                        }

                        if(devices != null && devices.size() != 0)
                            this.mainFrame.setContextId(devices.get(0).getUuid());

                        this.loginFrame.setVisible(false);
                        this.mainFrame.setVisible(true);
                    }
                }
                
            } else if(button.getName().equalsIgnoreCase("logout")) {
                this.currentUser = null;
                this.mainFrame.setVisible(false);
                this.loginFrame.setVisible(true);
            }
        }
    }
}
