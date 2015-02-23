package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.SelectionList;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 *         Copyright A7 Software (http://a7-software.com/)
 *         Date : 18/02/2015.
 */
public class MultivaluedQualifierType extends QualifierType implements MultivaluedType {

    /**
     * The values supported by the qualifier type.
     */
    private SelectionList values;

    /**
     * Builds a multivalued qualifier type from a key, a display name and some values.
     * @param key the key of the qualifier type
     * @param name the display name of the qualifier type
     * @param values the values supported by the qualifier type
     */
    public MultivaluedQualifierType(String key, String name, SelectionList values) {
        super(key, name);
        this.values = values;
    }

    public SelectionList getValues() {
        return values;
    }

    public void setValues(SelectionList values) {
        this.values = values;
    }

    public int compareTo(Type o) {
        
        if(o instanceof MultivaluedQualifierType) {
            
            MultivaluedQualifierType multiQualifierType = (MultivaluedQualifierType) o;
            return name.compareTo(multiQualifierType.getName());
        }
        
        return -1;
    }
}
