/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.models;

import biz.manex.andaman7.injector.models.types.Type;
import java.util.List;

/**
 *
 * @author Pierre-Yves
 */
public abstract class AbstractGroup<T extends Type> extends KeyNameItem {

    public AbstractGroup() {
    }

    public AbstractGroup(String key, String name) {
        super(key, name);
    }

    public abstract List<T> getItems();
}
