package biz.manex.andaman7.injector.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the identifier and the AMIs of an EHR.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 11/02/2015.
 */
public class AMIContainer {

    /**
     * The identifier.
     */
    private String uuid;

    /**
     * The AMIs.
     */
    private Map<String, AMI> amis;
    
    private Map<String, String> contextMap;


    /**
     * Builds an AMI container with a UUID and some AMIs.
     *
     * @param uuid the UUID of the AMI container
     * @param amis the AMIs of the AMI container
     * @param contextMap
     */
    public AMIContainer(String uuid, Map<String, AMI> amis, Map<String, String> contextMap) {
        this.uuid = uuid;
        this.amis = amis;
        this.contextMap = contextMap;
    }

    public AMIContainer(String uuid) {
        this.uuid = uuid;
        this.amis = new HashMap<String, AMI>();
        this.contextMap = new HashMap<String, String>();
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
    public Map<String, AMI> getAmis() {
        return amis;
    }

    /**
     * Sets the AMIs.
     *
     * @param amis the AMIs
     */
    public void setAmis(Map<String, AMI> amis) {
        this.amis = amis;
    }

    public Map<String, String> getContextMap() {
        return contextMap;
    }

    public void setContextMap(Map<String, String> contextMap) {
        this.contextMap = contextMap;
    }

    /**
     * Add an AMI to the AMI container.
     *
     * @param ami the AMI to add
     */
    public void addAmi(AMI ami) {
        amis.put(ami.getId(), ami);
    }

    /**
     * Add AMIs to the AMI container.
     *
     * @param amis the AMIs to add
     */
    public void addAmis(Map<String, AMI> amis) {
        this.amis.putAll(amis);
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
