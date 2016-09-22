package com.andaman7.injector.views.tablemodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author Pierre-Yves
 * @param <T> the type of the items of the table model
 */
public abstract class AbstractTableModel<T> extends javax.swing.table.AbstractTableModel {
        
    /**
     * The data of the model.
     */
    protected List<T> data = new ArrayList<T>();


    /**
     * Builds an empty model.
     */
    public AbstractTableModel() {
    }

    /**
     * Builds a model filled with some data.
     *
     * @param data the data to be entered in the model
     */
    public AbstractTableModel(List<T> data) {
        this.data = data;
    }
    
    public abstract String[] getColumnNames();
    
    /**
     * Adds a new item to the model.
     *
     * @param item the new item to add
     */
    public void addItem(T item) {
        data.add(item);
    }

    /**
     * Returns a name of an item depending on the column index.
     *
     * @param item the item from which retrieving data
     * @param columnIndex the index of the column
     * @return the asked item name
     */
    protected abstract Object getValueInItem(T item, int columnIndex);

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
        return getColumnNames().length;
    }

    /**
     * Returns the column name according to its index.
     *
     * @param columnIndex the index of the column
     * @return the column name
     */
    @Override
    public String getColumnName(int columnIndex) {
        return getColumnNames()[columnIndex];
    }

    /**
     * Returns the name of a specific cell.
     *
     * @param rowIndex the index of the row
     * @param columnIndex the index of the column
     * @return the name
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T item = data.get(rowIndex);
        return getValueInItem(item, columnIndex);
    }

    /**
     * Returns the item of a specific row.
     *
     * @param rowIndex the index of the row
     * @return the item
     */
    public T getValueAt(int rowIndex) {
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
     * Returns an enumeration of the items contained in the table model.
     *
     * @return the items contained in the table model
     */
    public Enumeration<T> elements() {
        return Collections.enumeration(data);
    }

    /**
     * Clears the table model.
     */
    public void clear() {
        data.clear();
    }
}
