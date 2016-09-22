package com.andaman7.injector.models;

import com.andaman7.injector.models.types.QualifierType;

/**
 * A qualifier.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 18/02/2015.
 */
public class Qualifier {
    
    /**
     * The identifier of the qualifier.
     */
    private String id;
    
    /**
     * The type of the qualifier.
     */
    private QualifierType type;
    
    /**
     * The value of the qualifier.
     */
    private String value;

    
    public Qualifier(String id, QualifierType type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of the qualifier.
     * 
     * @return the type of the qualifier
     */
    public QualifierType getType() {
        return type;
    }

    /**
     * Sets the type of the qualifier.
     * 
     * @param type the type of the qualifier
     */
    public void setType(QualifierType type) {
        this.type = type;
    }

    /**
     * Returns the value of the qualifier.
     * 
     * @return the value of the qualifier
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the qualifier.
     * 
     * @param value the value of the qualifier
     */
    public void setValue(String value) {
        this.value = value;
    }
}
