package biz.manex.andaman7.injector.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
@XmlRootElement
public abstract class AbstractDTO implements Serializable {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
