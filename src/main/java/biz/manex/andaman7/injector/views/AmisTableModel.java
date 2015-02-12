package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.models.AMI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * A table model that contains AMIs.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://a7-software.com/)
 * Date : 11/02/2015.
 */
public class AmisTableModel extends AbstractTableModel {

    /**
     * The columns of an AMI.
     */
    public enum AmiColumn {
        /**
         * The TAMI.
         */
        TAMI("TAMI"),

        /**
         * The value.
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

    /**
     * The AMIs present in the model.
     */
    private List<AMI> data = new ArrayList<AMI>();


    /**
     * Builds an empty model.
     */
    public AmisTableModel() {
    }

    /**
     * Builds a model filled with some data.
     *
     * @param data the data to be entered in the model
     */
    public AmisTableModel(List<AMI> data) {
        this.data = data;
    }

    /**
     * Adds a new AMI to the model.
     *
     * @param ami the new AMI to add
     */
    public void addAmi(AMI ami) {
        data.add(ami);
    }

    /**
     * Returns a value of an AMI depending on the column index.
     *
     * @param ami the AMI from which retrieving data
     * @param columnIndex the index of the column
     * @return the asked AMI value
     */
    private Object getValueInAMI(AMI ami, int columnIndex) {

        String result;

        switch(columnIndex) {
            case 0:
                result = ami.getType().getName();
                break;
            case 1:
                result = ami.getValue();
                break;
            default:
                result = "";
                break;
        }

        return result;
    }

    /**
     * Returns the number of rows of the table model.
     *
     * @return the number of rows of the table model
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Returns the number of columns of the table model.
     *
     * @return the number of columns of the table model
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * Returns the column name according to its index.
     *
     * @param columnIndex the index of the column
     * @return the column name
     */
    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    /**
     * Returns the value of a specific cell.
     *
     * @param rowIndex the index of the row
     * @param columnIndex the index of the column
     * @return the value
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AMI ami = data.get(rowIndex);
        return getValueInAMI(ami, columnIndex);
    }

    /**
     * Returns the AMI of a specific row.
     *
     * @param rowIndex the index of the row
     * @return the AMI
     * @see biz.manex.andaman7.injector.models.AMI
     */
    public AMI getValueAt(int rowIndex) {
        return data.get(rowIndex);
    }

    /**
     * Removes a row in the table model.
     *
     * @param rowIndex the index of the row
     */
    public void removeElementAt(int rowIndex) {
        data.remove(rowIndex);
    }

    /**
     * Returns the number of element in the table model.
     *
     * @return the number of element in the table model
     */
    public int getSize() {
        return data.size();
    }

    /**
     * Says if the table model is empty.
     *
     * @return {@code true} if the table model is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Returns an enumeration of the AMIs contained in the table model.
     *
     * @return the AMIs contained in the table model
     */
    public Enumeration<AMI> elements() {
        return Collections.enumeration(data);
    }

    /**
     * Clears the table model.
     */
    public void clear() {
        data.clear();
    }
}
