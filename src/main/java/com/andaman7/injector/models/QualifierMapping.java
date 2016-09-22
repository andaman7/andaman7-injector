package com.andaman7.injector.models;

/**
 *
 * @author Pierre-Yves
 */
public class QualifierMapping {
    
    private Qualifier qualifier;
    private String parentId;
    private AMIContainer amiContainer;

    public QualifierMapping(Qualifier qualifier, String parentId, AMIContainer amiContainer) {
        this.qualifier = qualifier;
        this.parentId = parentId;
        this.amiContainer = amiContainer;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public AMIContainer getAmiContainer() {
        return amiContainer;
    }

    public void setAmiContainer(AMIContainer amiContainer) {
        this.amiContainer = amiContainer;
    }
    
    
}
