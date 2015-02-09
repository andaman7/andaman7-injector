package biz.manex.andaman7.injector.models.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AmiContainerDTO extends IdentifiedDataModelObjectDTO {

    private String synchroState;
    private Set<AmiBaseDTO> amiBases;
    private String registrarUUID;
    private Map<String, String> contextMap;


    public AmiContainerDTO() {
        super();
    }

    protected AmiContainerDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId, String synchroState) {
        super(uuid, creationDate, creatorId, modificationDate, modificatorId);
        this.synchroState = synchroState;
    }

    protected AmiContainerDTO(String uuid, Date creationDate, String creatorId, Date modificationDate, String modificatorId, String synchroState, String registrarUUID) {
        super(uuid, creationDate, creatorId, modificationDate, modificatorId);
        this.synchroState = synchroState;
        this.registrarUUID = registrarUUID;
    }

    public Map<String, String> getContextMap() {
        return contextMap;
    }

    public void setContextMap(Map<String, String> contextMap) {
        this.contextMap = contextMap;
    }

    public String getSynchroState() {
        return synchroState;
    }

    public void setSynchroState(final String synchroState) {
        this.synchroState = synchroState;
    }

    public Set<AmiBaseDTO> getAmiBases() {
        if (amiBases == null) {
            amiBases = new HashSet<AmiBaseDTO>();
        }
        return amiBases;
    }

    public void setAmiBases(Set<AmiBaseDTO> amiBases) {
        this.amiBases = amiBases;
    }

    public String getRegistrarUUID() {
        return registrarUUID;
    }

    public void setRegistrarUUID(String registrarUUID) {
        this.registrarUUID = registrarUUID;
    }

    public void addAmiBaseDTO (AmiBaseDTO amiBaseDTO){
        if(this.amiBases == null){
            this.amiBases = new HashSet<AmiBaseDTO>();
        }
        this.amiBases.add(amiBaseDTO);
    }
}
