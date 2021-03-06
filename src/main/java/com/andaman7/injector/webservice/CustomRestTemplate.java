package com.andaman7.injector.webservice;

import com.andaman7.injector.dtos.ErrorDTO;
import com.andaman7.injector.exceptions.AndamanException;
import com.andaman7.injector.exceptions.AuthenticationException;
import com.andaman7.injector.utils.SecurityHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Base64;

/**
 * Contains methods to interact with a RESTful server.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date: 19/01/2015.
 */
public class CustomRestTemplate {

    /**
     * The URL of the distant server.
     */
    private final String urlServer;

    /**
     * The API key.
     */
    private final String apiKey;

    /**
     * The HTTP client used to make the requests to the server.
     * @see org.apache.http.client.HttpClient
     */
    private final HttpClient httpClient;

    /**
     * The login used for authentication.
     */
    private final String login;

    /**
     * The password used for authentication.
     */
    private final String password;


    private final ObjectMapper jsonMapper;

    /**
     * Builds a connection to a REST web service.
     *
     * @param urlServer the URL of the server
     * @param apiKey the API key
     * @param login the login used for the authentication
     * @param password the password used for the authentication
     */
    public CustomRestTemplate(String urlServer, String apiKey, String login, String password, ObjectMapper jsonMapper) {

        this.urlServer = urlServer;
        this.apiKey = apiKey;

        httpClient = HttpClientBuilder.create().build();

        this.login = login;
        this.password = password;

        this.jsonMapper = jsonMapper;
    }

    /**
     * Performs a GET request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse get(String path) throws IOException, AndamanException {
        return get(path, false);
    }

    /**
     * Performs a GET request.
     *
     * @param path the path to the resource
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse get(String path, boolean authenticationNeeded) throws IOException, AndamanException {

        HttpGet request = new HttpGet(buildUrl(path));
        return executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse post(String path, String body) throws IOException, AndamanException {
        return post(path, body, false);
    }

    /**
     * Performs a POST request.
     *
     * @param path the path to the resource
     * @param body the body of the POST request
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse post(String path, String body, boolean authenticationNeeded) throws IOException, AndamanException {

        HttpPost request = new HttpPost(buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse put(String path, String body) throws IOException, AndamanException {
        return put(path, body, false);
    }

    /**
     * Performs a PUT request.
     *
     * @param path the path to the resource
     * @param body the body of the PUT request
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse put(String path, String body, boolean authenticationNeeded) throws IOException, AndamanException {

        HttpPut request = new HttpPut(buildUrl(path));
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(body));
        return executeRequest(request, authenticationNeeded);
    }

    /**
     * Performs a DELETE request.
     *
     * @param path the path to the resource
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse delete(String path) throws IOException, AndamanException {
        return delete(path, false);
    }

    /**
     * Performs a DELETE request.
     *
     * @param path the path to the resource
     * @param authenticationNeeded says if an authentication is needed or not
     * @return the HTTP response to the request
     * @throws IOException
     */
    public HttpResponse delete(String path, boolean authenticationNeeded) throws IOException, AndamanException {

        HttpDelete request = new HttpDelete(buildUrl(path));
        return executeRequest(request, authenticationNeeded);
    }

    /**
     * Concatenates the server endpoint and the given path.
     *
     * @param path the path to the resource
     * @return the built URL
     */
    private String buildUrl(String path) {

        String url = urlServer;

        if (!urlServer.endsWith("/") && !path.startsWith("/"))
            url += "/";

        if(urlServer.endsWith("/") && path.startsWith("/"))
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
    public void setAuthorizationHeader(HttpRequestBase request, String username, String password){

        // Set the username and password for creating a Basic Auth request
        if ((username != null) && (password != null)) {
            String passwordHash = SecurityHelper.getSHA256Digest(password.trim());

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
     * @throws IOException
     */
    private HttpResponse executeRequest(HttpRequestBase request, boolean authenticationNeeded) throws IOException, AndamanException {

        request.addHeader("api-key", apiKey);

        if(authenticationNeeded)
            setAuthorizationHeader(request, login, password);

        System.err.println(String.format("Request : %s - %s", request.getMethod(), request.getURI()));
        HttpResponse response = httpClient.execute(request);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");
        else if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK &&
                response.getStatusLine().getStatusCode() != HttpStatus.SC_ACCEPTED &&
                response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
            ErrorDTO error = jsonMapper.readValue(response.getEntity().getContent(), ErrorDTO.class);
            System.err.println(error.getDeveloperMessage());
            throw new AndamanException(error.getMessage());
        }

        return response;
    }
}