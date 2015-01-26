package biz.manex.andaman7.injector.webservice.REST;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * An abstract base class for any implementation of a REST service.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public abstract class CustomRestService {

    /**
     * The REST template.
     * @see biz.manex.andaman7.injector.webservice.REST.CustomRestTemplate
     */
    protected CustomRestTemplate restTemplate;

    /**
     * The mapper used to convert JSON into objects and vice versa.
     * @see org.codehaus.jackson.map.ObjectMapper
     */
    protected ObjectMapper jsonMapper;


    public CustomRestService(String urlServer, String apiKey) {
        this.restTemplate = new CustomRestTemplate(urlServer, apiKey);
        this.jsonMapper = new ObjectMapper();
    }
}
