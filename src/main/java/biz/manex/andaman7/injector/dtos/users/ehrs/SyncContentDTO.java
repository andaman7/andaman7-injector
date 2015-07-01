package biz.manex.andaman7.injector.dtos.users.ehrs;

import biz.manex.andaman7.injector.dtos.TrackingDTO;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 15/04/2015.
 */
public class SyncContentDTO extends TrackingDTO {

    private String sourceDeviceId;
    private String sourceRegistrarId;
    private String medicalRecords;
    private String fileMap;

    
    public SyncContentDTO() {
    }
    
    public SyncContentDTO(String sourceDeviceId, String sourceRegistrarId, String medicalRecords, String fileMap) {
        this.sourceDeviceId = sourceDeviceId;
        this.sourceRegistrarId = sourceRegistrarId;
        this.medicalRecords = medicalRecords;
        this.fileMap = fileMap;
    }

    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    public String getSourceRegistrarId() {
        return sourceRegistrarId;
    }

    public void setSourceRegistrarId(String sourceRegistrarId) {
        this.sourceRegistrarId = sourceRegistrarId;
    }

    public String getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(String medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public String getFileMap() {
        return fileMap;
    }

    public void setFileMap(String fileMap) {
        this.fileMap = fileMap;
    }
}
