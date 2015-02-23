package biz.manex.andaman7.injector.models;

/**
 * The settings needed to connect to the server.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 02/02/2015.
 */
public class Settings {

    /**
     * The username used for authentication.
     */
    private String username;

    /**
     * The password used for authentication.
     */
    private String password;

    /**
     * The hostname of the server.
     */
    private String serverHostname;

    /**
     * The port of the server.
     */
    private String serverPort;

    /**
     * Says if HTTPS is needed for the connection.
     */
    private boolean isHttps;

    /**
     * The API key used to identify the requests.
     */
    private String apiKey;


    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the hostname of the server.
     *
     * @return the hostname of the server
     */
    public String getServerHostname() {
        return serverHostname;
    }

    /**
     * Sets the hostname of the server.
     *
     * @param serverHostname the hostname of the server
     */
    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    /**
     * Returns the port of the server.
     *
     * @return the port of the server
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * Set the port of the server.
     *
     * @param serverPort the port of the server
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Says if HTTPS is needed for the connection.
     *
     * @return {@code true} if HTTPS is needed for the connection,
     *         {@code false} otherwise
     */
    public boolean isHttps() {
        return isHttps;
    }

    /**
     * Sets if HTTPS is needed for the connection.
     *
     * @param isHttps says if HTTPS is needed for the connection
     */
    public void setHttps(boolean isHttps) {
        this.isHttps = isHttps;
    }

    /**
     * Returns the API key.
     *
     * @return the API key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the API key.
     *
     * @param apiKey the API key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
