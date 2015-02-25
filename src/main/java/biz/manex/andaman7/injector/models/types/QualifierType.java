package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.KeyNameItem;

/**
 * A qualifier type.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 18/02/2015.
 */
public class QualifierType extends KeyNameItem implements Type {
    
    /**
     * Builds a qualifier from a key and a display name.
     *
     * @param key the key of the TAMI
     * @param name the display name of the TAMI
     */
    public QualifierType(String key, String name) {
        super(key, name);
    }

    public QualifierType(String key) {
        super(key, null);
    }

    /**
     * Compares the current object to another object of same type.
     * @param o the other object to compare to
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    public int compareTo(Type o) {
        
        if(o instanceof QualifierType) {
            
            QualifierType qualifierType = (QualifierType) o;
            return name.compareTo(qualifierType.getName());
        }
        
        return -1;
    }
}
