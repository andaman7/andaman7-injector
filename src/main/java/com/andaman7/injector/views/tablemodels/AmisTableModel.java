package com.andaman7.injector.views.tablemodels;

import com.andaman7.injector.models.AMI;

/**
 * A table model that contains AMIs.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date : 11/02/2015.<br/>
 */
public class AmisTableModel extends AbstractTableModel<AMI> {

    /**
     * The columns of an AMI.
     */
    public enum AmiColumn {
        /**
         * The TAMI.
         */
        TAMI("TAMI"),

        /**
         * The name.
         */
        VALUE("Value");

        /**
         * The name of the column.
         */
        private final String name;

        /**
         * Builds an AMI column.
         *
         * @param name the name of the column
         */
        AmiColumn(String name) {
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
            AmiColumn.TAMI.getName(),
            AmiColumn.VALUE.getName()
    };

    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object getValueInItem(AMI item, int columnIndex) {
        
        String result;

        switch(columnIndex) {
            case 0:
                result = item.getType().getName();
                break;
            case 1:
                result = item.getValue();
                break;
            default:
                result = "";
                break;
        }

        return result;
    }
}
