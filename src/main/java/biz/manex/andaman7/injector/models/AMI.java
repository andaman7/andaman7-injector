/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.models;

/**
 *
 * @author Pierre-Yves
 */
public class AMI {
    
    private TAMI type;
    private String value;

    public AMI() {
    }

    public AMI(TAMI type, String value) {
        this.type = type;
        this.value = value;
    }
    
    public TAMI getType() {
        return type;
    }

    public void setType(TAMI type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.type + " - " + this.value;
    }
}
