package biz.manex.andaman7.injector.models;

/**
 * Created by Pierre-Yves on 07/02/2015.
 */
public class TAMI {

    private String key;
    private String name;

    public TAMI(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
