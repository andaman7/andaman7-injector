package biz.manex.andaman7.injector.dtos.trustedusers;

import biz.manex.andaman7.injector.dtos.users.UserDTO;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/><br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 19/03/2015.
 */
public class TrustedUserDisplayDTO {

    private UserDTO user;
    private Integer acceptance;


    public TrustedUserDisplayDTO() {
        super();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Integer acceptance) {
        this.acceptance = acceptance;
    }
}
