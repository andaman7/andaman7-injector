package com.andaman7.injector.models.types;

import com.andaman7.injector.models.SelectionList;

/**
 *
 * @author Pierre-Yves
 */
public interface MultivaluedType extends Type {
    
    SelectionList getValues();
}
