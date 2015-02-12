package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.models.AMI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://a7-software.com/)
 * Date : 11/02/2015.
 */
public class AmisTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = { "TAMI", "Value" };
    private List<AMI> data = new ArrayList<AMI>();


    public AmisTableModel() {
    }

    public AmisTableModel(List<AMI> data) {
        this.data = data;
    }

    public void addAmi(AMI registrar) {
        data.add(registrar);
    }

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

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AMI ami = data.get(rowIndex);
        return getValueInAMI(ami, columnIndex);
    }

    public AMI getValueAt(int rowIndex) {
        return data.get(rowIndex);
    }

    public void removeElementAt(int rowIndex) {
        data.remove(rowIndex);
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Enumeration<AMI> elements() {
        return Collections.enumeration(data);
    }

    public void clear() {
        data.clear();
    }
}
