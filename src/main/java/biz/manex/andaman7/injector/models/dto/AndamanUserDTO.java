package biz.manex.andaman7.injector.models.dto;

import java.util.List;

public class AndamanUserDTO extends IdentifiedDataModelObjectDTO {

    private String firstName;
    private String lastName;
    private String patientPicture;
    private String patientAddress;
    private String patientAddressNumber;
    private String patientAddressBox;
    private String patientAddressZip;
    private String patientAddressTown;
    private String patientAddressCountry;
    private String patientPhoneProfessional;
    private String patientMailProfessional;
    private String doctorFunction;
    private AcceptanceDTO acceptance;
    private List<DeviceDTO> devices;
    private String type;

    public AndamanUserDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientPicture() {
        return patientPicture;
    }

    public void setPatientPicture(String patientPicture) {
        this.patientPicture = patientPicture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<DeviceDTO> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDTO> devices) {
        this.devices = devices;
    }

    public Integer getAcceptance() {
        if (acceptance != null)
            return acceptance.getValue();
        return 0;
    }

    public void setAcceptance(AcceptanceDTO acceptance) {
        this.acceptance = acceptance;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientAddressNumber() {
        return patientAddressNumber;
    }

    public void setPatientAddressNumber(String patientAddressNumber) {
        this.patientAddressNumber = patientAddressNumber;
    }

    public String getPatientAddressBox() {
        return patientAddressBox;
    }

    public void setPatientAddressBox(String patientAddressBox) {
        this.patientAddressBox = patientAddressBox;
    }

    public String getPatientAddressZip() {
        return patientAddressZip;
    }

    public void setPatientAddressZip(String patientAddressZip) {
        this.patientAddressZip = patientAddressZip;
    }

    public String getPatientAddressTown() {
        return patientAddressTown;
    }

    public void setPatientAddressTown(String patientAddressTown) {
        this.patientAddressTown = patientAddressTown;
    }

    public String getPatientAddressCountry() {
        return patientAddressCountry;
    }

    public void setPatientAddressCountry(String patientAddressCountry) {
        this.patientAddressCountry = patientAddressCountry;
    }

    public String getPatientPhoneProfessional() {
        return patientPhoneProfessional;
    }

    public void setPatientPhoneProfessional(String patientPhoneProfessional) {
        this.patientPhoneProfessional = patientPhoneProfessional;
    }

    public String getPatientMailProfessional() {
        return patientMailProfessional;
    }

    public void setPatientMailProfessional(String patientMailProfessional) {
        this.patientMailProfessional = patientMailProfessional;
    }

    public String getDoctorFunction() {
        return doctorFunction;
    }

    public void setDoctorFunction(String doctorFunction) {
        this.doctorFunction = doctorFunction;
    }

    @Override
    public String toString() {

        String str = String.format("%s %s", this.firstName, this.lastName);

        if(this.getPatientAddressCountry() != null && !this.getPatientAddressCountry().isEmpty())
            str += String.format(" (%s)", this.patientAddressCountry);

        return str;
    }
}
