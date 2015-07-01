package biz.manex.andaman7.injector.dtos.translations;

import biz.manex.andaman7.injector.dtos.AbstractDTO;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class TranslationEntryDTO extends AbstractDTO {

    private String languageCode;
    private String value;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
