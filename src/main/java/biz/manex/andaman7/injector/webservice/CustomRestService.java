package biz.manex.andaman7.injector.webservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * An base class for any implementation of a REST service.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.manex.biz)<br/>
 *         Date: 24/01/2015.
 */
public class CustomRestService {

    /**
     * The REST template.
     * @see biz.manex.andaman7.injector.webservice.CustomRestTemplate
     */
    protected CustomRestTemplate restTemplate;

    /**
     * The mapper used to convert JSON into objects and vice versa.
     * @see ObjectMapper
     */
    protected final ObjectMapper jsonMapper;


    /**
     * Builds a connection to a REST web service.
     *
     * @param urlServer the URL of the server
     * @param apiKey the API key
     * @param login the login used for the authentication
     * @param password the password used for the authentication
     */
    protected CustomRestService(String urlServer, String apiKey, String login, String password) {

        jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        restTemplate = new CustomRestTemplate(urlServer, apiKey, login, password, jsonMapper);
    }
}
