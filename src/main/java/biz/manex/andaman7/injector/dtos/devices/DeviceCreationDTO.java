package biz.manex.andaman7.injector.dtos.devices;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 15/05/2015.
 */
public class DeviceCreationDTO extends DeviceModificationDTO {

    protected String id;

    public DeviceCreationDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
