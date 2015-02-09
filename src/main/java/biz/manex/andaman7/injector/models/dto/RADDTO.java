package biz.manex.andaman7.injector.models.dto;

public class RADDTO extends IdentifiedDataModelObjectDTO {

    private String value;
    private String tamiVersion;
    private String tamiId;
    private String registrar;

    public RADDTO() {
        super();
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTamiVersion() {
        return tamiVersion;
    }

    public void setTamiVersion(String tamiVersion) {
        this.tamiVersion = tamiVersion;
    }

    public String getTamiId() {
        return tamiId;
    }

    public void setTamiId(String tamiId) {
        this.tamiId = tamiId;
    }

}
