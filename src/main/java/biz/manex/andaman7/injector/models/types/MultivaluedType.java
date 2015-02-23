package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.SelectionList;

/**
 *
 * @author Pierre-Yves
 */
public interface MultivaluedType extends Type {
    
    SelectionList getValues();
}
