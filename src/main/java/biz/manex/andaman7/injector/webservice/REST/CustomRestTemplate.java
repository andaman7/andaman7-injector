package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.util.SecurityHelper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Base64;

/**
 * This class contains methods to interact with a RESTful server.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 19/01/2015.
 */
public class CustomRestTemplate {

    /**
     * The URL of the distant server.
     */
    private String urlServer;

    /**
     * The API key.
     */
    private String apiKey;

    /**
     * The HTTP client used to make the requests to the server.
     * @see org.apache.http.client.HttpClient
     */
    private HttpClient httpClient;


    public CustomRestTemplate(String urlServer, String apiKey) {
        this.urlServer = urlServer;
        this.apiKey = apiKey;

        this.httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Makes an unauthenticated GET request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse get(String path) throws Exception {
        return this.get(path, null, null);
    }

    /**
     * Makes an authenticated GET request.
     *
     * @param path the path to the resource
     * @param username the username used for authentication
     * @param password the password used for authentication
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse get(String path, String username, String password)
            throws Exception {

        HttpGet request = new HttpGet(this.buildUrl(path));
        return this.executeRequest(request, username, password);
    }

    /**
     * Makes an unauthenticated POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse post(String path, String body) throws Exception {
        return this.post(path, body, null, null);
    }

    /**
     * Makes an authenticated POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @param username the username used for authentication
     * @param password the password used for authentication
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse post(String path, String body, String username,
            String password) throws Exception {

        HttpPost request = new HttpPost(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, username, password);
    }

    /**
     * Makes an unauthenticated PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse put(String path, String body) throws Exception {
        return this.put(path, body, null, null);
    }

    /**
     * Makes an authenticated PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @param username the username used for authentication
     * @param password the password used for authentication
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse put(String path, String body, String username,
            String password) throws Exception {

        HttpPut request = new HttpPut(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, username, password);
    }

    /**
     * Makes an unauthenticated DELETE request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse delete(String path) throws Exception {
        return this.delete(path, null, null);
    }

    /**
     * Makes an authenticated DELETE request.
     *
     * @param path the path to the resource
     * @param username the username used for authentication
     * @param password the password used for authentication
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse delete(String path, String username, String password)
            throws Exception {

        HttpDelete request = new HttpDelete(this.buildUrl(path));
        return this.executeRequest(request, username, password);
    }

    /**
     * Concatenate the server endpoint and the given path.
     * @param path the path to the resource
     * @return the built URL
     */
    private String buildUrl(String path) {
        String url = this.urlServer;

        if (!this.urlServer.endsWith("/") && !path.startsWith("/"))
            url += "/";

        if(this.urlServer.endsWith("/") && path.startsWith("/"))
            url += path.substring(1);
        else
            url += path;

        return url;
    }

    /**
     * Adds an authorization header to the HTTP request.
     * @param request the HTTP request
     * @param username the username needed for authentication
     * @param password the password needed for authentication
     */
    public void setAuthorizationHeader(HttpRequestBase request, String username,
            String password){

        // Set the username and password for creating a Basic Auth request
        if (username != null && password != null) {
            String passwordHash = SecurityHelper.getSHA256Digest(
                    password.trim());

            byte[] bytes = (username.trim() + ":" +
                    passwordHash.trim()).getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            String auth = ("Basic " + base64).trim();
            request.addHeader("Authorization", auth);
        }
    }

    /**
     * Executes an HTTP request.
     * @param request the HTTP request
     * @param username the username needed for authentication (can be null if no
     *                 authentication is needed)
     * @param password the password needed for authentication (can be null if no
     *                 authentication is needed)
     * @return the HTTP response to the request
     * @throws Exception
     */
    private HttpResponse executeRequest(HttpRequestBase request,
            String username, String password) throws Exception {

        request.addHeader("api-key", this.apiKey);
        setAuthorizationHeader(request, username, password);
        System.err.println("Request : " + request.getMethod() + " - " +
                request.getURI());
        return  this.httpClient.execute(request);
    }
}