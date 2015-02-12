package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.models.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.models.dto.DeviceDTO;
import biz.manex.andaman7.injector.models.dto.MessageDTO;
import biz.manex.andaman7.injector.models.dto.RegistrarDTO;
import biz.manex.andaman7.injector.utils.XmlHelper;
import org.apache.http.HttpResponse;
import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains methods to interact with the context service of Andaman7.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public class AndamanContextService extends CustomRestService {

    /**
     * Unique instance of the REST service.
     */
    private static final Map<String, AndamanContextService> instances =
            new HashMap<String, AndamanContextService>();


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

        AndamanContextService instance = AndamanContextService.instances.
                get(urlServer + "#" + login + "#" + password);

        if(instance == null) {
            instance = new AndamanContextService(urlServer, apiKey, login,
                    password);
            AndamanContextService.instances.put(
                    urlServer + "#" + login + "#" + password,
                    instance);
        }

        return instance;
    }

    /**
     * Returns data related to the authenticated registrar.
     *
     * @return the {@link biz.manex.andaman7.injector.models.dto.RegistrarDTO}
     * of the authenticated registrar
     */
    public RegistrarDTO login() {
        try {
            HttpResponse response = this.restTemplate.get("registrars/login/",
                    true);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    RegistrarDTO.class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Document getTamiXml(int currentVersion) {
        try {
            HttpResponse response = this.restTemplate.get("tami-xml/next/" +
                    currentVersion, true);

            if(response.getStatusLine().getStatusCode() == 204)
                return null;
            else
                return XmlHelper.getDocument(response.getEntity().getContent());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Document getGuiXml(int currentXmlVersion) {
        try {
            HttpResponse response = this.restTemplate.get("gui-xml/last/" +
                    currentXmlVersion, true);

            if(response.getStatusLine().getStatusCode() == 204)
                return null;
            else
                return XmlHelper.getDocument(response.getEntity().getContent());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public DeviceDTO[] getDevices() {

        try {
            HttpResponse response = this.restTemplate.get(
                    "devices/", true);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    DeviceDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Searches users based on a given keyword.
     *
     * @param keyword the keyword used to filter users
     * @return the list of {@link biz.manex.andaman7.injector.models.dto.RegistrarDTO}
     * found based on the keyword
     */
    public AndamanUserDTO[] searchUsers(String keyword) {

        try {
            HttpResponse response = this.restTemplate.get(
                    "andamanusers/search?keyword=" + keyword, true);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    AndamanUserDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * Sends a community request to other registrars.
     *
     * @param senderDeviceId the UUID of the source device
     * @param newCommunityMembers the list of registrars UUIDs to send the
     *                            request to
     * @return the list of {@link biz.manex.andaman7.injector.models.dto.RegistrarDTO}s
     * of the new community members
     */
    public RegistrarDTO[] sendCommunityRequest(String senderDeviceId,
            String[] newCommunityMembers) {

        try {
            String body = this.jsonMapper.writeValueAsString(
                    newCommunityMembers);
            HttpResponse response =  this.restTemplate.post("community/" +
                    senderDeviceId, body);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    RegistrarDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    
    public RegistrarDTO[] getCommunityMembers() {
        try {
            HttpResponse response =  this.restTemplate.get("community/");
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    RegistrarDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Sets the acceptance level of a community request.
     *
     * @param otherRegistrarId the UUID of the registrar that has sent the
     *                         request
     * @param isAccepted the boolean that says if the registrar is accepted or
     *                   not
     * @return the HTTP response to the request
     */
    public HttpResponse setCommunityAcceptance(String otherRegistrarId,
            boolean isAccepted) {

        try {
            return this.restTemplate.post("registrars/" + otherRegistrarId +
                    "/acceptance/" + isAccepted, "");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public MessageDTO[] getTranslations() {

        try {
            HttpResponse response = this.restTemplate.get("translations/");
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    MessageDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
