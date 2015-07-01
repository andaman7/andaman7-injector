package biz.manex.andaman7.injector.dtos.translations;

import biz.manex.andaman7.injector.dtos.TrackingDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 10/03/2015.
 */
public class TranslationDTO extends TrackingDTO {

    public static final String ALL_LANGUAGES = "*";

    protected String key;
    protected List<TranslationEntryDTO> translationEntries;

    public TranslationDTO() {
        translationEntries = new ArrayList<TranslationEntryDTO>();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<TranslationEntryDTO> getTranslationEntries() {
        return translationEntries;
    }

    public void setTranslationEntries(List<TranslationEntryDTO> translationEntries) {
        this.translationEntries = translationEntries;
    }
}
