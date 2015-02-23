package biz.manex.andaman7.injector.models;

/**
 * A type of AMI.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date : 07/02/2015.<br/>
 */
public class TAMI {

    /**
     * The key of the TAMI.
     */
    private String key;

    /**
     * The display name of the TAMI.
     */
    private String name;


    /**
     * Builds a TAMI from a key and a display name.
     *
     * @param key the key of the TAMI
     * @param name the display name of the TAMI
     */
    public TAMI(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Returns the key of the TAMI.
     *
     * @return the key of the TAMI
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the TAMI.
     *
     * @param key the key of the TAMI
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the display name of the TAMI.
     *
     * @return the display name of the TAMI
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of the TAMI.
     *
     * @param name the display name of the TAMI
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the TAMI.
     *
     * @return the string representation of the TAMI
     */
    @Override
    public String toString() {
        return name;
    }
}
