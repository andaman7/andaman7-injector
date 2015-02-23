package biz.manex.andaman7.injector.models;

import biz.manex.andaman7.injector.models.types.TAMI;
import java.util.List;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 *         Copyright A7 Software (http://a7-software.com/)
 *         Date : 19/02/2015.
 */
public class TamiGroup extends KeyNameItem {

    private List<TAMI> tamis;

    /**
     * Builds an item from a key and a display name.
     *
     * @param key   the key of the item
     * @param value the display name of the item
     */
    public TamiGroup(String key, String value, List<TAMI> tamis) {
        super(key, value);
    }
}
