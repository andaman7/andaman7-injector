package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.KeyNameItem;
import java.util.List;

/**
 * A type of AMI.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 07/02/2015.
 */
public class TAMI extends KeyNameItem implements Type {

    /**
     * The types of the qualifiers supported by the TAMI
     */
    protected List<QualifierType> qualifierTypes;

    public TAMI() {
        super();
    }

    /**
     * Builds a TAMI from a key, a display name and some qualifier types.
     *
     * @param key the key of the TAMI
     * @param name the display name of the TAMI
     * @param qualifierTypes the qualifier types supported by the TAMI
     */
    public TAMI(String key, String name, List<QualifierType> qualifierTypes) {
        super(key, name);
        this.qualifierTypes = qualifierTypes;
    }

    /**
     * Returns the types of qualifiers supported by the TAMI.
     * 
     * @return the types of qualifiers supported by the TAMI
     */
    public List<QualifierType> getQualifierTypes() {
        return qualifierTypes;
    }

    /**
     * Sets the types of qualifiers supported by the TAMI.
     * 
     * @param qualifierTypes the types of qualifiers supported by the TAMI
     */
    public void setQualifierTypes(List<QualifierType> qualifierTypes) {
        this.qualifierTypes = qualifierTypes;
    }
    
    /**
     * Adds a type of qualifier to the list of supported types of qualifiers.
     * 
     * @param qualifierType the type of qualifier to add
     */
    public void addQualifierType(QualifierType qualifierType) {
        this.qualifierTypes.add(qualifierType);
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(Type o) {
        
        if(o instanceof TAMI) {
            
            TAMI tami = (TAMI) o;
            return name.compareTo(tami.getName());
        }
        
        return -1;
    }
}
