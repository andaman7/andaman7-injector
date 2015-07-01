package biz.manex.andaman7.injector.dtos.users;
import biz.manex.andaman7.injector.dtos.devices.DeviceDTO;
import java.util.Date;
import java.util.List;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class AuthenticatedUserDTO extends UserDTO {

    private String mail;
    private String password;
    private List<DeviceDTO> devices;
    private Boolean privateProfile;
    private Boolean localizationAccepted;
    private Date validationDate;
    private String preferredLanguage;

    
    public AuthenticatedUserDTO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DeviceDTO> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDTO> devices) {
        this.devices = devices;
    }

    public Boolean getPrivateProfile() {
        return privateProfile;
    }

    public void setPrivateProfile(Boolean privateProfile) {
        this.privateProfile = privateProfile;
    }

    public Boolean getLocalizationAccepted() {
        return localizationAccepted;
    }

    public void setLocalizationAccepted(Boolean localizationAccepted) {
        this.localizationAccepted = localizationAccepted;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
}
