package biz.manex.andaman7.injector.dto;

import java.util.List;

/**
 * User: Philippe Lodomez
 * Copyright A7 Software
 * Date: 11/12/12
 */
public class RegistrarSyncContentDTO extends AbstractSyncContentDTO {

    private List<String> destinationRegistrars;
    private String medicalRecordId;
    private String sourceDeviceId;
    private String sourceRegistrarId;

    public String getSourceRegistrarId() { return sourceRegistrarId; }

    public void setSourceRegistrarId(String sourceRegistrarId) { this.sourceRegistrarId = sourceRegistrarId; }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public RegistrarSyncContentDTO() {
        super();
    }

    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    public List<String> getReceiverRegistrars() {
        return destinationRegistrars;
    }

    public void setDestinationRegistrars(List<String> destinationRegistrars) {
        this.destinationRegistrars = destinationRegistrars;
    }

    public void setReceiverRegistrars(List<String> destinationRegistrars) {
        this.destinationRegistrars = destinationRegistrars;
    }
}
