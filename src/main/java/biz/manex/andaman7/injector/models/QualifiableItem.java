package biz.manex.andaman7.injector.models;

import java.util.List;


public interface QualifiableItem {
    
    public List<Qualifier> getQualifiers();
    
    public void setQualifiers(List<Qualifier> qualifiers);
}
