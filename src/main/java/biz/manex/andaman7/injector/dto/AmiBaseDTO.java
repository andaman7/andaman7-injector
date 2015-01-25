package biz.manex.andaman7.injector.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class AmiBaseDTO extends AmiDTO {

    private String amiContainerId;

    private Set<AmiQualDTO> qualifiers;

    private String uuidMulti;

    public AmiBaseDTO() {
        super();
    }
    public AmiBaseDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId, String metaItemId, String metaItemVersion, String value) {
        super(uuid, creationDate, creatorId, modificationDate, modificatorId, metaItemId, metaItemVersion, value);
    }

    public Set<AmiQualDTO> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Set<AmiQualDTO> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public void addQualifier(final AmiQualDTO qualifier) {
        if (qualifiers == null) {
            qualifiers = new HashSet<AmiQualDTO>();
        }
        qualifiers.add(qualifier);
    }

    public void removeQualifier(final AmiQualDTO qualifier) {
        if (qualifiers != null && qualifiers.contains(qualifier)) {
            qualifiers.remove(qualifier);
        }
    }

    public String getUuidMulti() {
        return uuidMulti;
    }

    public void setUuidMulti(String uuidMulti) {
        this.uuidMulti = uuidMulti;
    }

    public String getAmiContainerId() {
        return amiContainerId;
    }

    public void setAmiContainerId(final String amiContainerId) {
        this.amiContainerId = amiContainerId;
    }
}
