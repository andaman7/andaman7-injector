package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.dto.RegistrarDTO;
import org.apache.http.HttpResponse;

/**
 * This class contains methods to interact with the context service of Andaman7.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public class AndamanContextService extends CustomRestService {

    public AndamanContextService(String urlServer, String apiKey) {
        super(urlServer, apiKey);
    }

    /**
     * Returns data related to the authenticated registrar.
     *
     * @param login the login needed for authentication
     * @param password the password needed for authentication
     * @return the {@link biz.manex.andaman7.injector.dto.RegistrarDTO} of the
     * authenticated registrar
     */
    public RegistrarDTO login(String login, String password) {
        try {
            HttpResponse response = this.restTemplate.get("registrars/login/",
                    login, password);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    RegistrarDTO.class);

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
     * @param login the login needed for authentication
     * @param password the password needed for authentication
     * @return the list of {@link biz.manex.andaman7.injector.dto.RegistrarDTO}
     * found based on the keyword
     */
    public RegistrarDTO[] searchUsers(String keyword, String login,
            String password) {

        try {
            HttpResponse response = this.restTemplate.get(
                    "andamanusers/search?keyword=" + keyword, login, password);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    RegistrarDTO[].class);

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
     * @param login the login needed for authentication
     * @param password the password needed for authentication
     * @return the list of {@link biz.manex.andaman7.injector.dto.RegistrarDTO}s
     * of the new community members
     */
    public RegistrarDTO[] sendCommunityRequest(String senderDeviceId,
            String[] newCommunityMembers, String login, String password) {

        try {
            String body = this.jsonMapper.writeValueAsString(
                    newCommunityMembers);
            HttpResponse response =  this.restTemplate.post("community/" +
                    senderDeviceId, body, login, password);
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
     * @param login the login needed for authentication
     * @param password the password needed for authentication
     * @return the HTTP response to the request
     */
    public HttpResponse setCommunityAcceptance(String otherRegistrarId,
            boolean isAccepted, String login, String password) {

        try {
            return this.restTemplate.post("registrars/" + otherRegistrarId +
                    "/acceptance/" + isAccepted, "", login, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
