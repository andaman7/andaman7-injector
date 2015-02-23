package biz.manex.andaman7.injector.models;

import biz.manex.andaman7.injector.models.types.TAMI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 *         Copyright A7 Software (http://a7-software.com/)
 *         Date : 19/02/2015.
 */
public class TamiGroup extends AbstractGroup<TAMI> implements Comparable<TamiGroup> {

    private List<TAMI> tamis;

    /**
     * Builds an empty TAMI group.
     */
    public TamiGroup() {
        tamis = new ArrayList<TAMI>();
    }

    /**
     * Builds an item from a key and a display name.
     *
     * @param key   the key of the item
     * @param value the display name of the item
     */
    public TamiGroup(String key, String value, List<TAMI> tamis) {
        super(key, value);
        
        this.tamis = tamis;
    }

    public List<TAMI> getTamis() {
        return tamis;
    }
    
    public List<TAMI> getItems() {
        return tamis;
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(TamiGroup o) {
        return name.compareTo(o.getName());
    }
}
