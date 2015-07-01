package biz.manex.andaman7.injector.dtos.users;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class AdministrativeDTO {

    private String firstName;
    private String lastName;
    private String picture;
    private AddressDTO address;
    private String phoneProfessional;
    private String mailProfessional;
    private String doctorFunction;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPhoneProfessional() {
        return phoneProfessional;
    }

    public void setPhoneProfessional(String phoneProfessional) {
        this.phoneProfessional = phoneProfessional;
    }

    public String getMailProfessional() {
        return mailProfessional;
    }

    public void setMailProfessional(String mailProfessional) {
        this.mailProfessional = mailProfessional;
    }

    public String getDoctorFunction() {
        return doctorFunction;
    }

    public void setDoctorFunction(String doctorFunction) {
        this.doctorFunction = doctorFunction;
    }
}
