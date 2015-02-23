/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.models;

import biz.manex.andaman7.injector.models.types.TAMI;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the TAMI and the value of an AMI.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date : 06/02/2015.<br/>
 */
public class AMI implements QualifiableItem {

    /**
     * The type of the AMI.
     * @see biz.manex.andaman7.injector.models.TAMI
     */
    private TAMI type;

    /**
     * The value of the AMI.
     */
    private String value;
    
    /**
     * The qualifiers.
     */
    private List<Qualifier> qualifiers;

    /**
     * Builds an empty AMI.
     */
    public AMI() {
        
        qualifiers = new ArrayList<Qualifier>();
    }

    /**
     * Builds an AMI with a type and a value.
     *
     * @param type the type of the AMI
     * @param value the value of the AMI
     */
    public AMI(TAMI type, String value) {
        this.type = type;
        this.value = value;
        qualifiers = new ArrayList<Qualifier>();
    }

    /**
     * Returns the type of the AMI.
     *
     * @return the type of the AMI
     * @see biz.manex.andaman7.injector.models.TAMI
     */
    public TAMI getType() {
        return type;
    }

    /**
     * Sets the type of the AMI.
     *
     * @param type the type of the AMI.
     * @see biz.manex.andaman7.injector.models.TAMI
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
