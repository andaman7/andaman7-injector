/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.views.tablemodels;

import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;

/**
 *
 * @author Pierre-Yves
 */
public class AndamanUsersTableModel extends AbstractTableModel<AndamanUserDTO> {

    /**
     * The columns of a qualifier.
     */
    public enum AndamanUserColumn {
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
        AndamanUserColumn(String name) {
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
            AndamanUserColumn.FIRST_NAME.getName(),
            AndamanUserColumn.LAST_NAME.getName(),
            AndamanUserColumn.COUNTRY.getName()
    };
    
    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object getValueInItem(AndamanUserDTO item, int columnIndex) {
        
        String result;

        switch(columnIndex) {
            case 0:
                result = item.getFirstName();
                break;
            case 1:
                result = item.getLastName();
                break;
            case 2:
                result = item.getPatientAddressCountry();
                break;
            default:
                result = "";
                break;
        }

        return result;
    }
}
