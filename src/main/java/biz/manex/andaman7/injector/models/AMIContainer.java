package biz.manex.andaman7.injector.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Stores the identifier and the AMIs of an EHR.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date : 11/02/2015.<br/>
 */
public class AMIContainer {

    /**
     * The identifier.
     */
    private String uuid;

    /**
     * The AMIs.
     */
    private HashMap<String, String> amis;


    /**
     * Builds an AMI container with a UUID and some AMIs.
     *
     * @param uuid the UUID of the AMI container
     * @param amis the AMIs of the AMI container
     */
    public AMIContainer(String uuid, HashMap<String, String> amis) {
        this.uuid = uuid;
        this.amis = amis;
    }

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID.
     *
     * @param uuid the UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the AMIs.
     *
     * @return the AMIs
     */
    public HashMap<String, String> getAmis() {
        return amis;
    }

    /**
     * Sets the AMIs.
     *
     * @param amis the AMIs
     */
    public void setAmis(HashMap<String, String> amis) {
        this.amis = amis;
    }

    /**
     * Add an AMI to the AMI container.
     *
     * @param tamiKey the key of the AMI to add
     * @param value the value of the AMI to add
     */
    public void addAmi(String tamiKey, String value) {
        amis.put(tamiKey, value);
    }

    /**
     * Add AMIs to the AMI container.
     *
     * @param amis the AMIs to add
     */
    public void addAmis(HashMap<String, String> amis) {

        Set<Entry<String, String>> set = amis.entrySet();

        for(Entry<String, String> entry : set)
            this.amis.put(entry.getKey(), entry.getValue());
    }

    /**
     * Removes an AMI from the AMI container.
     *
     * @param tamiKey the key of the AMI to remove
     */
    public void removeAmi(String tamiKey) {
        amis.remove(tamiKey);
    }
}
