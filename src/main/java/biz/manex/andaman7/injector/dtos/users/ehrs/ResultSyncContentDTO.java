package biz.manex.andaman7.injector.dtos.users.ehrs;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 08/05/2015.
 */
public class ResultSyncContentDTO extends SyncContentDTO {

    private String medicalRecordId;

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }
}
