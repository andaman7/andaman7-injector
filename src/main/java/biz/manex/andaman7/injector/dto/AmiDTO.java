package biz.manex.andaman7.injector.dto;

import java.util.Date;

public class AmiDTO extends IdentifiedDataModelObjectDTO {

    protected String registrarCreatorId;

    protected String tamiId;

    protected String tamiVersion;

    protected String value;

    protected String synchroState;

    public AmiDTO() {
        super();
    }

    public AmiDTO(String uuid, Date creationDate, String creatorId,
            Date modificationDate, String modificatorId, String tamiId,
            String tamiVersion, String value) {

        super(uuid, creationDate, creatorId, modificationDate, modificatorId);
        this.tamiId = tamiId;
        this.tamiVersion = tamiVersion;
        this.value = value;
    }

    public String getRegistrarCreatorId() {
        return registrarCreatorId;
    }

    public void setRegistrarCreatorId(String registrarCreatorId) {
        this.registrarCreatorId = registrarCreatorId;
    }

    public String getSynchroState() {
        return synchroState;
    }

    public void setSynchroState(String synchroState) {
        this.synchroState = synchroState;
    }

    public String getTamiId() {
        return tamiId;
    }

    public void setTamiId(String tamiId) {
        this.tamiId = tamiId;
    }

    public String getTamiVersion() {
        return tamiVersion;
    }

    public void setTamiVersion(String tamiVersion) {
        this.tamiVersion = tamiVersion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
