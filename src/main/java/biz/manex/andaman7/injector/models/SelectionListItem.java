package biz.manex.andaman7.injector.models;

/**
 * A selection list item.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 18/02/2015.
 */
public class SelectionListItem extends KeyNameItem implements Comparable<SelectionListItem> {
    
    /**
     * Builds a selection list item from a key and a display name.
     *
     * @param key the key of the TAMI
     * @param name the display name of the TAMI
     */
    public SelectionListItem(String key, String name) {
        super(key, name);
    }

    @Override
    public String toString() {
        return name;
    }

    public int compareTo(SelectionListItem o) {
        return name.compareTo(o.getName());
    }
}
