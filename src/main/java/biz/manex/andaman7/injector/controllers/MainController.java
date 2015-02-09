package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.models.Settings;
import biz.manex.andaman7.injector.models.TAMI;
import biz.manex.andaman7.injector.models.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.models.dto.MessageDTO;
import biz.manex.andaman7.injector.models.dto.RegistrarDTO;
import biz.manex.andaman7.injector.views.LoginFrame;
import biz.manex.andaman7.injector.views.MainFrame;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

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
    
    
    public RegistrarDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(RegistrarDTO currentUser) {
        this.currentUser = currentUser;
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
        if(settings.getServerPort().equals("443"))
            url.append("s");
        
        url.append("://").append(settings.getServerHostname());
        
        // Add explicitely the port if not the default HTTP or HTTPS one
        if(!settings.getServerPort().equals("80") &&
                !settings.getServerPort().equals("443"))
            url.append(":").append(settings.getServerPort());
        
        url.append("/api/");
        
        return url.toString();
    }
    
    public RegistrarDTO login() {
        return this.contextService.login();
    }

    public TAMI[] getTamis() {
        try {
            MessageDTO[] messages = this.contextService.getTranslations();
            HashMap<String, String> translations = new HashMap<String, String>();
            ArrayList<TAMI> tamis = new ArrayList<TAMI>();

            for(MessageDTO message : messages)
                if(message.getLanguageCode().equals("EN"))
                    translations.put(message.getKey(), message.getValue());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(String.valueOf(
                    ClassLoader.getSystemClassLoader().getResource(
                            "tamidict.xml")));
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//tami/@id");
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
        
        // Add all user from search and add only the members that correspond to the specified keyword
        ArrayList<AndamanUserDTO> users = new ArrayList<AndamanUserDTO>();
        users.addAll(Arrays.asList(results));
        
        for(RegistrarDTO member : members)
            if(member.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                    member.getLastName().toLowerCase().contains(keyword.toLowerCase()))
                users.add(member);
        
        return users.toArray(new AndamanUserDTO[users.size()]);
    }
    
    public RegistrarDTO[] sendCommunityInvitation(String senderDeviceId,
            String[] newCommunityMembers) {
        return this.contextService.sendCommunityRequest(senderDeviceId,
                newCommunityMembers);
        
    }
    
    public boolean sendData(String sourceDeviceId, String sourceRegistrarId,
            AndamanUserDTO destinationRegistrar, HashMap<String, String> amis) {
        
        RegistrarDTO[] members = this.contextService.getCommunityMembers();
        boolean alreadyMember = false;
        
        // Check if the destination registrar is already a community member
        for(RegistrarDTO member : members)
            if(member.getUuid().equalsIgnoreCase(destinationRegistrar.getUuid()))
                alreadyMember = true;
        
        // If not, send an invitation
        if(!alreadyMember)
            this.sendCommunityInvitation(sourceDeviceId,
                    new String[] { destinationRegistrar.getUuid() });
        
        // Send the data
        this.ehrService.sendAmiBasesToRegistrar(sourceDeviceId,
                sourceRegistrarId, destinationRegistrar, amis);
        
        return alreadyMember;
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
