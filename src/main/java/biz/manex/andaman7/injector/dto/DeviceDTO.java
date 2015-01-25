package biz.manex.andaman7.injector.dto;

import java.util.Date;
import java.util.List;

public class DeviceDTO extends IdentifiedDataModelObjectDTO {

    private Date lastSynchro;
    private boolean active;
    private List<DevicePropertyDTO> properties;
    private String deviceToken;
    private String macId;

    public DeviceDTO() {
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Date getLastSynchro() {
        return lastSynchro;
    }

    public void setLastSynchro(final Date lastSynchro) {
        this.lastSynchro = lastSynchro;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public List<DevicePropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(final List<DevicePropertyDTO> properties) {
        this.properties = properties;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String findModel() {
        if (properties != null) {
            for (DevicePropertyDTO deviceProperty : properties) {
                if ("DEVICE_MODEL".equals(deviceProperty.getKey())) {
                    return deviceProperty.getValue();
                }
            }
        }
        return null;
    }
}
