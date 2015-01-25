package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.dto.RegistrarDTO;
import org.apache.http.HttpResponse;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public class AndamanContextService extends CustomRestService {

    public AndamanContextService(String urlServer, String apiKey) {
        super(urlServer, apiKey);
    }

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
