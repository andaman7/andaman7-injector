package biz.manex.andaman7.injector.models;

import biz.manex.andaman7.injector.models.types.TAMI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 19/02/2015.
 */
public class TamiGroup extends AbstractGroup<TAMI> implements Comparable<TamiGroup> {

    private final List<TAMI> tamis;

    /**
     * Builds an empty TAMI group.
     */
    public TamiGroup() {
        tamis = new ArrayList<TAMI>();
    }

    /**
     * Builds a group of TAMIs from a key, a display name and some TAMIs.
     *
     * @param key   the key of the group of TAMI
     * @param value the display name of the group of TAMI
     * @param tamis the list of TAMIs of the group of TAMI
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
