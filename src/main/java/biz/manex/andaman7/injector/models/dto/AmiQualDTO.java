package biz.manex.andaman7.injector.models.dto;

import java.util.Date;

/**
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 7/09/11
 * Time: 12:29
 */
public class AmiQualDTO extends AmiDTO {

    public AmiQualDTO() {
        super();
    }

    public AmiQualDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId, String metaItemId, String metaItemVersion, String value, String amiBaseId) {
        super(uuid, creationDate, creatorId, modificationDate, modificatorId, metaItemId, metaItemVersion, value);
    }
}
