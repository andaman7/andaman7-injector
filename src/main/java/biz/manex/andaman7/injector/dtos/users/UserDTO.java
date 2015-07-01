package biz.manex.andaman7.injector.dtos.users;

import biz.manex.andaman7.injector.dtos.TrackingDTO;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 09/03/2015.
 */
public class UserDTO extends TrackingDTO {

    protected String type;
    protected AdministrativeDTO administrative;


    public UserDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AdministrativeDTO getAdministrative() {
        return administrative;
    }

    public void setAdministrative(AdministrativeDTO administrative) {
        this.administrative = administrative;
    }
}
