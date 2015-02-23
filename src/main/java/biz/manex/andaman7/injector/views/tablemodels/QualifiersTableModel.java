package biz.manex.andaman7.injector.views.tablemodels;

import biz.manex.andaman7.injector.models.Qualifier;

/**
 *
 * @author Pierre-Yves
 */
public class QualifiersTableModel  extends AbstractTableModel<Qualifier> {

    /**
     * The columns of a qualifier.
     */
    public enum QualifierColumn {
        /**
         * The type.
         */
        TYPE("Type"),

        /**
         * The name.
         */
        VALUE("Value");

        /**
         * The name of the column.
         */
        private final String name;

        /**
         * Builds a qualifier column.
         *
         * @param name the name of the column
         */
        QualifierColumn(String name) {
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
            QualifierColumn.TYPE.getName(),
            QualifierColumn.VALUE.getName()
    };
    
    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object getValueInItem(Qualifier item, int columnIndex) {
        
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
