package com.andaman7.injector.dtos;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com)<br/>
 *         Date : 09/06/2015.
 */
public class A7ItemDTO extends TrackingDTO {
    
    private String creatorRegistrarId;
    private A7ItemTypeEnum type;
    private String key;
    private String value;
    private Integer version;
    private String uuidMulti;
    private String parentId;

    
    public A7ItemDTO(String id) {
        this.id = id;
    }

    public String getCreatorRegistrarId() {
        return creatorRegistrarId;
    }

    public void setCreatorRegistrarId(String creatorRegistrarId) {
        this.creatorRegistrarId = creatorRegistrarId;
    }

    public A7ItemTypeEnum getType() {
        return type;
    }

    public void setType(A7ItemTypeEnum type) {
        this.type = type;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUuidMulti() {
        return uuidMulti;
    }

    public void setUuidMulti(String uuidMulti) {
        this.uuidMulti = uuidMulti;
    }
    
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
