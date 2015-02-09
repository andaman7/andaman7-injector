package biz.manex.andaman7.injector.models.dto;

import java.util.Date;

/**
 * Created by Romain Gerardy (romain.gerardy@manex.biz)
 * Copyright A7 Software
 * Date: 23/01/12
 * Time: 09:45
 */
public class MessageDTO extends IdentifiedDataModelObjectDTO {

    private String key;

    private String value;

    private String languageCode;

    public MessageDTO() {
        super();
    }

    public MessageDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId, String key, String value, String languageCode) {
        super(uuid, creationDate, creatorId, modificationDate, modificatorId);
        this.key = key;
        this.languageCode = languageCode;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}