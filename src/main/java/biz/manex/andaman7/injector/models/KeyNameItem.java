package biz.manex.andaman7.injector.models;

/**
 * An item that has a name and is identified by a key.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 18/02/2015.
 */
public class KeyNameItem {
    
    /**
     * The key of the item.
     */
    protected String key;

    /**
     * The name of the item.
     */
    protected String name;


    public KeyNameItem() {
    }

    /**
     * Builds an item from a key and a display name.
     *
     * @param key the key of the item
     * @param name the display name of the item
     */
    public KeyNameItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Returns the key of the item.
     *
     * @return the key of the item
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the item.
     *
     * @param key the key of the item
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the display name of the item.
     *
     * @return the display name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of the item.
     *
     * @param name the display name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the item.
     *
     * @return the string representation of the item
     */
    @Override
    public String toString() {
        return name;
    }
}
