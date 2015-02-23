package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.utils.XmlHelper;
import biz.manex.andaman7.server.api.dto.device.DeviceDTO;
import biz.manex.andaman7.server.api.dto.others.MessageDTO;
import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;
import biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Contains methods to interact with the context service of Andaman7.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://www.manex.biz)<br/>
 * Date: 24/01/2015.<br/>
 */
public class AndamanContextService extends CustomRestService {

    /**
     * Unique instance of the REST service.
     */
    private static final Map<String, AndamanContextService> INSTANCES =
            new HashMap<String, AndamanContextService>();


    /**
     * Builds a context service connection to the server.
     *
     * @param urlServer the URL of the server
     * @param apiKey the API key
     * @param login the login used for the authentication
     * @param password the password used for the authentication
     */
    public AndamanContextService(String urlServer, String apiKey, String login,
            String password) {
        super(urlServer, apiKey, login, password);
    }

    /**
     * Returns the unique instance of the EHR service corresponding to the URL
     * server and the login.
     *
     * @param urlServer the URL of the distant server
     * @param apiKey the API key
     * @param login the login needed to authenticate
     * @param password the password needed to authenticate
     * @return the unique instance of the EHR service
     */
    public static AndamanContextService getInstance(String urlServer,
            String apiKey, String login, String password) {

        AndamanContextService instance = INSTANCES.get(String.format("%s#%s#%s", urlServer, login, password));

        if(instance == null) {

            instance = new AndamanContextService(urlServer, apiKey, login, password);
            INSTANCES.put(String.format("%s#%s#%s", urlServer, login, password), instance);
        }

        return instance;
    }

    /**
     * Returns data related to the authenticated registrar.
     *
     * @return the {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}
     *         of the authenticated registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public RegistrarDTO login() throws IOException {

        HttpResponse response = restTemplate.get("registrars/login/", true);
        return jsonMapper.readValue(response.getEntity().getContent(), RegistrarDTO.class);
    }

    /**
     * Returns an XML file that describes the TAMIs according to the current
     * version of the file.
     *
     * @param currentXmlVersion the current version of the XML file
     * @return the XML document received from the server if a new version exists
     *         or null if no new version exists
     * @throws java.io.IOException if there was an error with the connection to the server
     * @throws org.xml.sax.SAXException if there was an error while parsing the XML document
     */
    public Document getTamiXml(int currentXmlVersion) throws IOException, SAXException, ParserConfigurationException {
        HttpResponse response = restTemplate.get("tami-xml/next/" + currentXmlVersion, true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT)
            return null;
        else
            return XmlHelper.getDocument(response.getEntity().getContent());
    }

    /**
     * Returns an XML file that describes the GUI panels layouts according to
     * the current version of the file.
     *
     * @param currentXmlVersion the current version of the XML file
     * @return the XML document received from the server if a new version exists
     *         or null if no new version exists
     * @throws java.io.IOException if there was an error with the connection to the server
     * @throws org.xml.sax.SAXException if there was an error while parsing the XML document
     */
    public Document getGuiXml(int currentXmlVersion) throws IOException, SAXException, ParserConfigurationException {
        HttpResponse response = restTemplate.get("gui-xml/last/" + currentXmlVersion, true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT)
            return null;
        else
            return XmlHelper.getDocument(response.getEntity().getContent());
    }

    /**
     * Returns the {@link biz.manex.andaman7.server.api.dto.device.DeviceDTO} of
     * the logged registrar.
     *
     * @return a list of the devices of the logged registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public DeviceDTO[] getDevices() throws IOException {

        HttpResponse response = restTemplate.get("devices/", true);
        return jsonMapper.readValue(response.getEntity().getContent(), DeviceDTO[].class);
    }

    /**
     * Searches users based on a given keyword.
     *
     * @param keyword the keyword used to filter users
     * @return the list of {@link biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO}
     *         found based on the keyword
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public AndamanUserDTO[] searchUsers(String keyword) throws IOException {

        HttpResponse response = restTemplate.get("andamanusers/search?keyword=" + keyword, true);
        return jsonMapper.readValue(response.getEntity().getContent(), AndamanUserDTO[].class);
    }
    
    /**
     * Sends a community request to other registrars.
     *
     * @param senderDeviceId the UUID of the source device
     * @param newCommunityMembers the list of registrars UUIDs to send the request to
     * @return the list of {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}s
     * of the new community members
     */
    public RegistrarDTO[] sendCommunityRequest(String senderDeviceId, String[] newCommunityMembers)
            throws IOException {

        String body = jsonMapper.writeValueAsString(newCommunityMembers);
        HttpResponse response = restTemplate.post("community/" + senderDeviceId, body);
        return jsonMapper.readValue(response.getEntity().getContent(), RegistrarDTO[].class);
    }

    /**
     * Returns the community members of the logged registrar.
     *
     * @return the community members of the logged registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public RegistrarDTO[] getCommunityMembers() throws IOException {

        HttpResponse response = restTemplate.get("community/");
        return jsonMapper.readValue(response.getEntity().getContent(), RegistrarDTO[].class);
    }

    /**
     * Sets the acceptance level of a community request.
     *
     * @param otherRegistrarId the UUID of the registrar that has sent the request
     * @param isAccepted the boolean that says if the registrar is accepted or not
     * @return the HTTP response to the request
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public HttpResponse setCommunityAcceptance(String otherRegistrarId, boolean isAccepted)
            throws IOException {

        return restTemplate.post("registrars/" + otherRegistrarId + "/acceptance/" + isAccepted, "");
    }

    /**
     * Returns the translation strings used to translate the TAMIs and the GUI strings.
     *
     * @return the translation strings used to translate the TAMIs and the GUI strings
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public MessageDTO[] getTranslations() throws IOException {

        HttpResponse response = restTemplate.get("translations/");
        return jsonMapper.readValue(response.getEntity().getContent(), MessageDTO[].class);
    }
}
