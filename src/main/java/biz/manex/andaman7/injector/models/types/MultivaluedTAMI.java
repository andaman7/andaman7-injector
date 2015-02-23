package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.SelectionList;
import java.util.List;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 *         Copyright A7 Software (http://a7-software.com/)
 *         Date : 18/02/2015.
 */
public class MultivaluedTAMI extends TAMI implements MultivaluedType {

    /**
     * The values of the TAMI.
     */
    private SelectionList values;

    /**
     * Builds a multivalued TAMI from a key, a name, some values and some qualifier types.
     *
     * @param key the key of the TAMI
     * @param name the name of the TAMI
     * @param values the values of the TAMI
     * @param qualifierTypes the qualifier types of the TAMI
     */
    public MultivaluedTAMI(String key, String name, SelectionList values, List<QualifierType> qualifierTypes) {
        super(key, name, qualifierTypes);
        this.values = values;
    }

    public SelectionList getValues() {
        return values;
    }

    public void setValues(SelectionList values) {
        this.values = values;
    }
}
