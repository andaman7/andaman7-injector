package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.dto.RegistrarDTO;
import org.apache.http.HttpResponse;

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
    private static Map<String, AndamanContextService> instances =
            new HashMap<String, AndamanContextService>();


    public AndamanContextService(String urlServer, String apiKey, String login,
            String password) {
        super(urlServer, apiKey, login, password);
    }

    public static AndamanContextService getInstance(String urlServer, String apiKey,
            String login, String password) {

        AndamanContextService instance = AndamanContextService.instances.get(urlServer +
                "#" + login);

        if(instance == null) {
            instance = new AndamanContextService(urlServer, apiKey, login,
                    password);
            AndamanContextService.instances.put(urlServer + "#" + login, instance);
        }

        return instance;
    }

    /**
     * Returns data related to the authenticated registrar.
     *
     * @return the {@link biz.manex.andaman7.injector.dto.RegistrarDTO} of the
     * authenticated registrar
     */
    public RegistrarDTO login() {
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
     * @return the list of {@link biz.manex.andaman7.injector.dto.RegistrarDTO}
     * found based on the keyword
     */
    public AndamanUserDTO[] searchUsers(String keyword) {

        try {
            HttpResponse response = this.restTemplate.get(
                    "andamanusers/search?keyword=" + keyword, login, password);
            return this.jsonMapper.readValue(response.getEntity().getContent(),
                    AndamanUserDTO[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
