package biz.manex.andaman7.injector.models;

import java.util.List;

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
    private List<AMI> amis;


    /**
     * Builds an AMI container with a UUID and some AMIs.
     *
     * @param uuid the UUID of the AMI container
     * @param amis the AMIs of the AMI container
     */
    public AMIContainer(String uuid, List<AMI> amis) {
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
    public List<AMI> getAmis() {
        return amis;
    }

    /**
     * Sets the AMIs.
     *
     * @param amis the AMIs
     */
    public void setAmis(List<AMI> amis) {
        this.amis = amis;
    }

    /**
     * Add an AMI to the AMI container.
     *
     * @param ami the AMI to add
     */
    public void addAmi(AMI ami) {
        amis.add(ami);
    }

    /**
     * Add AMIs to the AMI container.
     *
     * @param amis the AMIs to add
     */
    public void addAmis(List<AMI> amis) {
        this.amis.addAll(amis);
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
