package com.andaman7.injector.views.tablemodels;

import com.andaman7.server.api.pub.dto.user.UserDTO;

/**
 *
 * @author Pierre-Yves
 */
public class UsersTableModel extends AbstractTableModel<UserDTO> {

    /**
     * The columns of a qualifier.
     */
    public enum UserColumn {
        /**
         * The first name of the user.
         */
        FIRST_NAME("First name"),

        /**
         * The last name of the user.
         */
        LAST_NAME("Last name"),
        
        /**
         * The country of the user.
         */
        COUNTRY("Country");

        /**
         * The name of the column.
         */
        private final String name;

        /**
         * Builds an andaman user column.
         *
         * @param name the name of the column
         */
        UserColumn(String name) {
            this.name = name;
        }

        /**
         * Returns the name of the column.
         *
         * @return the name of the column
         */
        public String getName() {
            return name;
        }
    }

    /**
     * The names of the columns.
     */
    private static final String[] COLUMN_NAMES = {
            UserColumn.FIRST_NAME.getName(),
            UserColumn.LAST_NAME.getName(),
            UserColumn.COUNTRY.getName()
    };
    
    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object getValueInItem(UserDTO item, int columnIndex) {
        
        String result;

        switch(columnIndex) {
            case 0:
                result = item.getAdministrative().getFirstName();
                break;
            case 1:
                result = item.getAdministrative().getLastName();
                break;
            case 2:
                result = item.getAdministrative().getAddress().getCountry();
                break;
            default:
                result = "";
                break;
        }

        return result;
    }
}
