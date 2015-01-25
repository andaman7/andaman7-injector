package biz.manex.andaman7.injector.dto;

import java.util.Date;

public class IdentifiedDataModelObjectDTO implements DataModelObjectDTO {

    protected String uuid;
    protected Date creationDate;
    protected String creatorId;
    protected Date modificationDate;
    protected String modificatorId;
    protected Date invalidationDate;
    protected String invalidatorId;

    public IdentifiedDataModelObjectDTO(){

    }
    public IdentifiedDataModelObjectDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId){
        this.creationDate = creationDate;
        this.creatorId = creatorId;
        this.modificationDate = modificationDate;
        this.modificatorId = modificatorId;
        this.uuid = uuid;
    }

    public Date getInvalidationDate() {
        return invalidationDate;
    }

    public void setInvalidationDate(Date invalidationDate) {
        this.invalidationDate = invalidationDate;
    }

    public String getInvalidatorId() {
        return invalidatorId;
    }

    public void setInvalidatorId(String invalidatorId) {
        this.invalidatorId = invalidatorId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModificatorId() {
        return modificatorId;
    }

    public void setModificatorId(String modificatorId) {
        this.modificatorId = modificatorId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
