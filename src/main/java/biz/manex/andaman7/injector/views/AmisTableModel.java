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

    private static final String[] columnNames = new String[] { "TAMI", "Value" };
    private List<AMI> data = new ArrayList<AMI>();


    public AmisTableModel() {
    }

    public AmisTableModel(List<AMI> data) {
        this.data = data;
    }

    public void addAmi(AMI registrar) {
        this.data.add(registrar);
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
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AMI ami = this.data.get(rowIndex);
        return this.getValueInAMI(ami, columnIndex);
    }

    public AMI getValueAt(int rowIndex) {
        return this.data.get(rowIndex);
    }

    public void removeElementAt(int rowIndex) {
        this.data.remove(rowIndex);
    }

    public int getSize() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public Enumeration<AMI> elements() {
        return Collections.enumeration(this.data);
    }

    public void clear() {
        this.data.clear();
    }
}
