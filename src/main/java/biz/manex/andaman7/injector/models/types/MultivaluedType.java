/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.SelectionList;

/**
 *
 * @author Pierre-Yves
 */
public interface MultivaluedType extends Type {
    
    SelectionList getValues();
}
