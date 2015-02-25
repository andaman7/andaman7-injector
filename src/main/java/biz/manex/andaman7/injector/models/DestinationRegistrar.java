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
public class DestinationRegistrar {
    
    private String uuid;
    private AMIContainer amiContainer;

    
    public DestinationRegistrar(String uuid, AMIContainer amiContainer) {
        this.uuid = uuid;
        this.amiContainer = amiContainer;
    }

    public String getUuid() {
        return uuid;
    }
    
    public AMIContainer getAmiContainer() {
        return amiContainer;
    }

    public void setAmiContainer(AMIContainer amiContainer) {
        this.amiContainer = amiContainer;
    }
}
