package biz.manex.andaman7.injector.models.types;

import biz.manex.andaman7.injector.models.KeyNameItem;

/**
 * A qualifier type.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date : 18/02/2015.<br/>
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
}
