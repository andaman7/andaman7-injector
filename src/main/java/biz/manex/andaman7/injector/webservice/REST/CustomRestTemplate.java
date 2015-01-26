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

    /**
     * The login used for authentication.
     */
    private String login;

    /**
     * The password used for authentication.
     */
    private String password;


    public CustomRestTemplate(String urlServer, String apiKey, String login,
            String password) {

        this.urlServer = urlServer;
        this.apiKey = apiKey;

        this.httpClient = HttpClientBuilder.create().build();

        this.login = login;
        this.password = password;
    }

    /**
     * Performs a GET request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse get(String path) throws Exception {
        return this.get(path, false);
    }

    /**
     * Performs a GET request.
     *
     * @param path the path to the resource
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse get(String path, boolean authenticationNeeded)
            throws Exception {

        HttpGet request = new HttpGet(this.buildUrl(path));
        return this.executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse post(String path, String body) throws Exception {
        return this.post(path, body, false);
    }

    /**
     * Performs a POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse post(String path, String body,
            boolean authenticationNeeded) throws Exception {

        HttpPost request = new HttpPost(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse put(String path, String body) throws Exception {
        return this.put(path, body, false);
    }

    /**
     * Performs a PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse put(String path, String body,
            boolean authenticationNeeded) throws Exception {

        HttpPut request = new HttpPut(this.buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return this.executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a DELETE request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse delete(String path) throws Exception {
        return this.delete(path, false);
    }

    /**
     * Performs a DELETE request.
     *
     * @param path the path to the resource
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws Exception
     */
    public HttpResponse delete(String path, boolean authenticationNeeded)
            throws Exception {

        HttpDelete request = new HttpDelete(this.buildUrl(path));
        return this.executeRequest(request, authenticationNeeded);
    }

    /**
     * Concatenates the server endpoint and the given path.
     *
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
     *
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
     *
     * @param request the HTTP request
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws Exception
     */
    private HttpResponse executeRequest(HttpRequestBase request,
            boolean authenticationNeeded) throws Exception {

        request.addHeader("api-key", this.apiKey);

        if(authenticationNeeded)
            setAuthorizationHeader(request, this.login, this.password);
        System.err.println("Request : " + request.getMethod() + " - " +
                request.getURI());
        return  this.httpClient.execute(request);
    }
}