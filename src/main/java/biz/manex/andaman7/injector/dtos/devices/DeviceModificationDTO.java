package biz.manex.andaman7.injector.dtos.devices;


import biz.manex.andaman7.injector.dtos.AbstractDTO;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 15/05/2015.
 */
public class DeviceModificationDTO extends AbstractDTO {

    protected String name;
    protected Set<DevicePropertyDTO> properties;
    protected Date lastSynchro;
    protected boolean active;

    public DeviceModificationDTO() {
        properties = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DevicePropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(Set<DevicePropertyDTO> properties) {
        this.properties = properties;
    }

    public Date getLastSynchro() {
        return lastSynchro;
    }

    public void setLastSynchro(Date lastSynchro) {
        this.lastSynchro = lastSynchro;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
