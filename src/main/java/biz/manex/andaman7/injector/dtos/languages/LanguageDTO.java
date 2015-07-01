package biz.manex.andaman7.injector.dtos.languages;

import biz.manex.andaman7.injector.dtos.TrackingDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class LanguageDTO extends TrackingDTO {

    private String code;
    private String name;
    private Boolean activated;

    public LanguageDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
