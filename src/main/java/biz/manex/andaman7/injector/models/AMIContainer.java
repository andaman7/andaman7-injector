package biz.manex.andaman7.injector.models;

import java.util.HashMap;

/**
 *
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://a7-software.com/)
 * Date : 11/02/2015.
 */
public class AMIContainer {

    private String uuid;
    private HashMap<String, String> amis;


    public AMIContainer(String uuid, HashMap<String, String> amis) {
        this.uuid = uuid;
        this.amis = amis;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public HashMap<String, String> getAmis() {
        return amis;
    }

    public void setAmis(HashMap<String, String> amis) {
        this.amis = amis;
    }
}
