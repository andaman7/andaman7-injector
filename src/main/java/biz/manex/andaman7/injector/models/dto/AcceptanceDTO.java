package biz.manex.andaman7.injector.models.dto;

/**
 * Created by Antoine Smolders (smoldersan@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 12/27/12
 */
public enum AcceptanceDTO {

    ACCEPTED(0),
    REFUSED(1),
    UNDEFINED(2);

    private Integer value;

    private AcceptanceDTO(Integer v) {
        value = v;
    }

    public Integer getValue() {
        return value;
    }

    public static AcceptanceDTO getEnumFromValue(int value) {
        switch (value) {
            case 0:
                return ACCEPTED;
            case 1:
                return REFUSED;
            case 2:
                return UNDEFINED;
        }
        return UNDEFINED;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
