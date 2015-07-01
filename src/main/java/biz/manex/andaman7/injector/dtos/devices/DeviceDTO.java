package biz.manex.andaman7.injector.dtos.devices;

import biz.manex.andaman7.injector.dtos.TrackingDTO;
import java.util.Date;
import java.util.Set;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class DeviceDTO extends TrackingDTO {

    protected String name;
    protected Set<DevicePropertyDTO> properties;
    protected Date lastSynchro;
    protected boolean active;

    public DeviceDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastSynchro() {
        return lastSynchro;
    }

    public void setLastSynchro(Date lastSynchro) {
        this.lastSynchro = lastSynchro;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<DevicePropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(Set<DevicePropertyDTO> properties) {
        this.properties = properties;
    }
}
