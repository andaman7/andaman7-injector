package biz.manex.andaman7.injector.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AbstractSyncContentDTO extends IdentifiedDataModelObjectDTO {

    // used to send from device to server
    private List<AmiContainerDTO> amiContainerDTOs;
    private Map<String, String> fileUuidToFileContent;

    // used to send from server to device
    private String ehrsContent;
    private String fileUuidToFileContentString;

    private Date lastUploadDateFromDevice;


    public List<AmiContainerDTO> getAmiContainerDTOs() {
        return amiContainerDTOs;
    }

    public Date getLastUploadDateFromDevice() {
        return lastUploadDateFromDevice;
    }

    public void setLastUploadDateFromDevice(Date lastUploadDateFromDevice) {
        this.lastUploadDateFromDevice = lastUploadDateFromDevice;
    }

    public void setAmiContainerDTOs(List<AmiContainerDTO> amiContainerDTOs) {
        this.amiContainerDTOs = amiContainerDTOs;
    }

    public String getEhrsContent() {
        return ehrsContent;
    }

    public void setEhrsContent(String ehrsContent) {
        this.ehrsContent = ehrsContent;
    }

    public Map<String, String> getFileUuidToFileContent() {
        return fileUuidToFileContent;
    }

    public void setFileUuidToFileContent(Map<String, String> fileUuidToFileContent) {
        this.fileUuidToFileContent = fileUuidToFileContent;
    }

    public String getFileUuidToFileContentString() {
        return fileUuidToFileContentString;
    }

    public void setFileUuidToFileContentString(String fileUuidToFileContentString) {
        this.fileUuidToFileContentString = fileUuidToFileContentString;
    }
}
