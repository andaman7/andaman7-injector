package com.andaman7.injector.dtos;

import java.util.Date;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 05/06/2015.
 */
public abstract class TrackingDTO extends AbstractDTO {

    protected Date creationDate;
    protected String creatorDeviceId;
    protected Date modificationDate;
    protected String modificatorDeviceId;
    protected Date invalidationDate;
    protected String invalidatorDeviceId;

    public TrackingDTO() {
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatorDeviceId() {
        return creatorDeviceId;
    }

    public void setCreatorDeviceId(String creatorDeviceId) {
        this.creatorDeviceId = creatorDeviceId;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModificatorDeviceId() {
        return modificatorDeviceId;
    }

    public void setModificatorDeviceId(String modificatorDeviceId) {
        this.modificatorDeviceId = modificatorDeviceId;
    }

    public Date getInvalidationDate() {
        return invalidationDate;
    }

    public void setInvalidationDate(Date invalidationDate) {
        this.invalidationDate = invalidationDate;
    }

    public String getInvalidatorDeviceId() {
        return invalidatorDeviceId;
    }

    public void setInvalidatorDeviceId(String invalidatorDeviceId) {
        this.invalidatorDeviceId = invalidatorDeviceId;
    }
}
