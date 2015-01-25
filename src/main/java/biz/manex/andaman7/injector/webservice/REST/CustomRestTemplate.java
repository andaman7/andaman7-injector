package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.util.SecurityHelper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URISyntaxException;
import java.util.Base64;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 19/01/2015.
 */
public class CustomRestTemplate {

    private String urlServer;
    private String apiKey;

    private HttpClient httpClient;


    public CustomRestTemplate(String urlServer, String apiKey) {
        this.urlServer = urlServer;
        this.apiKey = apiKey;

        this.httpClient = HttpClientBuilder.create().build();
    }

    public HttpResponse get(String path) throws Exception {
        return this.get(path, null, null);
    }

    public HttpResponse get(String path, String username, String password)
            throws Exception {

        HttpGet request = new HttpGet(this.buildUrl(path));
        return this.executeRequest(request, username, password);
    }

    public HttpResponse post(String path, String body) throws Exception {
        return this.post(path, body, null, null);
    }

    public HttpResponse post(String path, String body, String username,
            String password) throws Exception {

        HttpPost request = new HttpPost(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, username, password);
    }

    public HttpResponse put(String path, String body) throws Exception {
        return this.put(path, body, null, null);
    }

    public HttpResponse put(String path, String body, String username,
            String password) throws Exception {

        HttpPut request = new HttpPut(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, username, password);
    }

    public HttpResponse delete(String path) throws Exception {
        return this.delete(path, null, null);
    }

    public HttpResponse delete(String path, String username, String password)
            throws Exception {

        HttpDelete request = new HttpDelete(this.buildUrl(path));
        return this.executeRequest(request, username, password);
    }

    private String buildUrl(String path) throws URISyntaxException {
        String url = this.urlServer;

        if (!this.urlServer.endsWith("/"))
            url += "/";

        return url + path;
    }

    public void setAuthorizationHeader(HttpRequestBase request, String username,
            String password){

        // Set the username and password for creating a Basic Auth request
        if (username != null && password != null) {
            String passwordHash = SecurityHelper.getSHA256Digest(password.trim());

            byte[] bytes = (username.trim() + ":" + passwordHash.trim()).getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            String auth = ("Basic " + base64).trim();
            request.addHeader("Authorization", auth);
        }
    }

    private HttpResponse executeRequest(HttpRequestBase request,
            String username, String password) throws Exception {

        request.addHeader("api-key", this.apiKey);
        setAuthorizationHeader(request, username, password);
        System.err.println("Request : " + request.getMethod() + " - " +
                request.getURI());
        return  this.httpClient.execute(request);
    }
}