package biz.manex.andaman7.injector.webservice.REST;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public abstract class CustomRestService {

    protected CustomRestTemplate restTemplate;
    protected ObjectMapper jsonMapper;


    public CustomRestService(String urlServer, String apiKey) {
        this.restTemplate = new CustomRestTemplate(urlServer, apiKey);
        this.jsonMapper = new ObjectMapper();
    }
}
