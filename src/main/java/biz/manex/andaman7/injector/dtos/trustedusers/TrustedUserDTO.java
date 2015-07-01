package biz.manex.andaman7.injector.dtos.trustedusers;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 19/03/2015.
 */
public class TrustedUserDTO {

    private String senderDeviceId;
    private String memberId;

    public TrustedUserDTO() {
    }

    public TrustedUserDTO(String senderDeviceId, String memberId) {
        this.senderDeviceId = senderDeviceId;
        this.memberId = memberId;
    }

    public String getSenderDeviceId() {
        return senderDeviceId;
    }

    public void setSenderDeviceId(String senderDeviceId) {
        this.senderDeviceId = senderDeviceId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
