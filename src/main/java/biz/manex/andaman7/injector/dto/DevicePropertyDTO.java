package biz.manex.andaman7.injector.dto;

public class DevicePropertyDTO extends IdentifiedDataModelObjectDTO {

    private String key;
    private String value;

    public DevicePropertyDTO() {
    }

    public DevicePropertyDTO(final String value, final String key) {
        this.value = value;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
