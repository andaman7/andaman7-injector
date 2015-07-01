package biz.manex.andaman7.injector.dtos;

import java.net.URI;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 11/06/2015.
 */
public class ErrorDTO {

    private Integer status;
    private Integer code;
    private String message;
    private String developerMessage;
    private URI moreInfo;
    private String support;

    public ErrorDTO() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public URI getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(URI moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
