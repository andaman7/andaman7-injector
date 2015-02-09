package biz.manex.andaman7.injector.models.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class RegistrarDTO extends AndamanUserDTO {

    private String mail;
    private String password;
    private List<RADDTO> rads;
    private Boolean privateProfile;
    private Boolean localizationAccepted;
    private Date validationDate;
    private String preferredLanguage;
    private String radSharingPolicy;
    private Set<RoleDTO> roles;

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public Boolean getLocalizationAccepted() {
        return localizationAccepted;
    }

    public void setLocalizationAccepted(Boolean localizationAccepted) {
        this.localizationAccepted = localizationAccepted;
    }

    public Boolean getPrivateProfile() {
        return privateProfile;
    }

    public void setPrivateProfile(Boolean privateProfile) {
        this.privateProfile = privateProfile;
    }

    public String getRadSharingPolicy() {
        return radSharingPolicy;
    }

    public void setRadSharingPolicy(String radSharingPolicy) {
        this.radSharingPolicy = radSharingPolicy;
    }

    public List<RADDTO> getRads() {
        return rads;
    }

    public void setRads(List<RADDTO> rads) {
        this.rads = rads;
    }

    public RegistrarDTO() {
        super();
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

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
