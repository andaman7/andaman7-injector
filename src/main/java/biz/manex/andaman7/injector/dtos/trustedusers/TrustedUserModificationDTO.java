/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.dtos.trustedusers;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 05/06/2015.
 */
public class TrustedUserModificationDTO {

    private Boolean accepted;

    public TrustedUserModificationDTO(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
