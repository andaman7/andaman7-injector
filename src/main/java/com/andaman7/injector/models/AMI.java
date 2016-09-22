package com.andaman7.injector.models;

import com.andaman7.injector.models.types.TAMI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Stores the TAMI and the value of an AMI.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 06/02/2015.
 */
public class AMI {

    /**
     * The UUID of the AMI.
     */
    String id;
    
    /**
     * The type of the AMI.
     * @see com.andaman7.injector.models.TAMI
     */
    private TAMI type;

    /**
     * The value of the AMI.
     */
    private String value;
    
    /**
     * The creation date of the AMI.
     */
    private Date creationDate;
    
    /**
     * The qualifiers.
     */
    private List<Qualifier> qualifiers;

    /**
     * Builds an empty AMI.
     */
    public AMI() {
        
        id = UUID.randomUUID().toString();
        qualifiers = new ArrayList<Qualifier>();
    }

    /**
     * Builds an AMI with a type and a value.
     *
     * @param type the type of the AMI
     * @param value the value of the AMI
     */
    public AMI(String id, TAMI type, String value, Date creationDate) {
        
        this.id = id;
        this.type = type;
        this.value = value;
        this.creationDate = creationDate;
        qualifiers = new ArrayList<Qualifier>();
    }

    /**
     * Returns the UUID of the AMI.
     * 
     * @return the UUID of the AMI
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the UUID of the AMI.
     * 
     * @param id the UUID of the AMI
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of the AMI.
     *
     * @return the type of the AMI
     * @see com.andaman7.injector.models.TAMI
     */
    public TAMI getType() {
        return type;
    }

    /**
     * Sets the type of the AMI.
     *
     * @param type the type of the AMI.
     * @see com.andaman7.injector.models.TAMI
     */
    public void setType(TAMI type) {
        this.type = type;
    }

    /**
     * Returns the value of the AMI.
     *
     * @return the value of the AMI
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the AMI.
     *
     * @param value the value of the AMI
     */
    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the qualifiers of the AMI.
     * 
     * @return the qualifiers of the AMI
     */
    public List<Qualifier> getQualifiers() {
        return qualifiers;
    }

    /**
     * Sets the qualifiers of the AMI.
     * 
     * @param qualifiers the qualifiers of the AMI
     */
    public void setQualifiers(List<Qualifier> qualifiers) {
        this.qualifiers = qualifiers;
    }

    /**
     * Returns the string representation of the AMI.
     *
     * @return the string representation of the AMI
     */
    @Override
    public String toString() {
        return type + " - " + value;
    }
}
