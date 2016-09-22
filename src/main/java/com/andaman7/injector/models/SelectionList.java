package com.andaman7.injector.models;

import java.util.Map;

/**
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 18/02/2015.
 */
public class SelectionList {

    /**
     * The key of the selection list
     */
    private String key;

    /**
     * The items of the selection list.
     */
    private Map<String, SelectionListItem> items;

    /**
     * Builds a selection list from a key, a display name and some items.
     *
     * @param key the key of the selection list
     * @param items the items of the selection list
     */
    public SelectionList(String key, Map<String, SelectionListItem> items) {
        this.key = key;
        this.items = items;
    }

    /**
     * Returns the key of the selection list.
     *
     * @return the key of the selection list
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the selection list.
     *
     * @param key the key of the selection list
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the items of the selection list.
     * @return
     */
    public Map<String, SelectionListItem> getItems() {
        return items;
    }

    /**
     * Sets the items of the selection list.
     *
     * @param items the items of the selection list
     */
    public void setItems(Map<String, SelectionListItem> items) {
        this.items = items;
    }
    
    public SelectionListItem getItem(String key) {
        return this.items.get(key);
    }
}
