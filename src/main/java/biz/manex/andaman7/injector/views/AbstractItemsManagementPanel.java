package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.exceptions.MissingTableModelException;
import biz.manex.andaman7.injector.exceptions.NoSelectedItemException;
import biz.manex.andaman7.injector.models.SelectionList;
import biz.manex.andaman7.injector.models.types.MultivaluedType;
import biz.manex.andaman7.injector.models.types.Type;
import biz.manex.andaman7.injector.views.tablemodels.AbstractTableModel;
import biz.manex.andaman7.injector.views.tablemodels.TableRowSelectionModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Pierre-Yves
 * @param <I>
 * @param <V>
 */
public abstract class AbstractItemsManagementPanel<I, V> extends JPanel implements ListSelectionListener {
    
    /**
     * The parent component.
     */
    protected JComponent parentComponent;
    
    /**
     * The qualifiers controller.
     */
    protected MainController mainController;
    
    /**
     * The combo box that contains predetermined values for a specific type.
     */
    protected JComboBox<V> jComboBoxDataValues;
    
    
    /**
     * Performs some initializations and updates of the GUI.
     */
    protected final void postInitComponents() {

        TableRowSelectionModel tableSelectionModel = new TableRowSelectionModel(getTable());
        tableSelectionModel.addListSelectionListener(this);
        getTable().setSelectionModel(tableSelectionModel);

        jComboBoxDataValues = new JComboBox<V>();
        jComboBoxDataValues.setModel(new DefaultComboBoxModel<V>());
        jComboBoxDataValues.setSelectedIndex(-1);

        getTable().getSelectionModel().addListSelectionListener(this);
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    /**
     * Sets the table model.
     * 
     * @param tableModel the table model
     */
    public final void setTableModel(AbstractTableModel<I> tableModel) {
        getTable().setModel(tableModel);
    }
    
    /**
     * Sets the parent component.
     * 
     * @param parentComponent the parent component.
     */
    public void setParentComponent(JComponent parentComponent) {
        this.parentComponent = parentComponent;
    }
    
    /**
     * Sets the types.
     * 
     * @param types the types
     */
    public void setTypes(Type[] types) {
        
        DefaultComboBoxModel<Type> model = (DefaultComboBoxModel<Type>) getTypeComboBox().getModel();
        model.removeAllElements();
        
        for(Type item : types)
            model.addElement(item);
        
        getTypeComboBox().setModel(model);
    }
    
    /**
     * Returns the selected type.
     * 
     * @return the selected type
     */
    public Type getSelectedType() {
        return (Type) getTypeComboBox().getSelectedItem();
    }
    
    /**
     * Sets the type to be selected.
     * 
     * @param type the type to be selected
     */
    public void setSelectedType(Object type) {
        getTypeComboBox().setSelectedItem(type);
    }
    
    /**
     * Returns the selected value.
     * 
     * @return the selected value
     */
    public Object getSelectedValue() {
        
        Type selectedType = getSelectedType();
        
        if(selectedType instanceof MultivaluedType) {
            return jComboBoxDataValues.getSelectedItem();
            
        } else {
            return getValueTextField().getText();
        }
    }
    
    /**
     * Sets the value to be selected.
     * 
     * @param multivalued says if the value is multivalued
     * @param value the value to be selected
     */
    public void setSelectedValue(boolean multivalued, Object value) {
        
        JComponent oldComponent;
        JComponent newComponent;
        
        if(multivalued) {
            jComboBoxDataValues.setSelectedItem(value);
            oldComponent = getValueTextField();
            newComponent = jComboBoxDataValues;
                
        } else {
            getValueTextField().setText(value.toString());
            oldComponent = jComboBoxDataValues;
            newComponent = getValueTextField();
        }
        
        switchDataValueComponent(oldComponent, newComponent);
    }
    
    /**
     * Toggles buttons related to the (de)selection of an AMI in the AMIs table.
     */
    protected void toggleButtons() {
        
        boolean enabled = getTable().getSelectedRow() != -1;
        
        getEditButton().setEnabled(enabled);
        getRemoveButton().setEnabled(enabled);
    }
    
    /**
     * Listens for a row of the table to be selected and then toggles the buttons.
     * 
     * @param e the list selection event
     */
    public void valueChanged(ListSelectionEvent e) {
        toggleButtons();
    }
    
    /**
     * Updates the data value component according to the type of TAMI.
     * If the TAMI is multivalued, the component is a combo box containing the values of the selection list of the TAMI.
     */
    protected void selectedTypeUpdated() {
        Type selectedType = (Type) getTypeComboBox().getSelectedItem();

        if(selectedType instanceof MultivaluedType) {

            MultivaluedType multiType = (MultivaluedType) selectedType;
            SelectionList selectionList = multiType.getValues();
            V[] items = (V[]) selectionList.getItems().values().toArray();
            Arrays.sort(items);
            
            DefaultComboBoxModel<V> model = new DefaultComboBoxModel<V>(items);
            jComboBoxDataValues.setModel(model);

            switchDataValueComponent(getValueTextField(), jComboBoxDataValues);
            
        } else {
            switchDataValueComponent(jComboBoxDataValues, getValueTextField());
            getValueTextField().setSize(getValueTextField().getHeight(), 150);
        }
    }
    
    /**
     * Switches the data value old GUI component by a new GUI component.
     * 
     * @param oldComponent the old data value GUI component
     * @param newComponent the new data value GUI component
     */
    protected abstract void switchDataValueComponent(JComponent oldComponent, JComponent newComponent);
    
    /**
     * Adds an item to the table.
     * 
     * @param item the item to add
     * @throws biz.manex.andaman7.injector.exceptions.MissingTableModelException
     */
    public void addItem(I item) throws MissingTableModelException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        model.addItem(item);
        model.fireTableDataChanged();
        
        clearForm();
    }
    
    /**
     * Returns the selected item.
     * 
     * @return the selected item
     * @throws biz.manex.andaman7.injector.exceptions.MissingTableModelException
     * @throws NoSelectedItemException if no item is selected
     */
    public I getSelectedItem() throws MissingTableModelException, NoSelectedItemException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        int rowIndex = getTable().getSelectedRow();
        
        if(rowIndex != -1)
            return model.getValueAt(rowIndex);
        else
            throw new NoSelectedItemException("No item is selected.");
    }
    
    /**
     * Removes the selected item from the table.
     * @throws biz.manex.andaman7.injector.exceptions.MissingTableModelException
     */
    public void removeSelectedItem() throws MissingTableModelException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        int rowIndex = getTable().getSelectedRow();
        
        if(rowIndex != -1) {
            
            model.removeElementAt(rowIndex);
            model.fireTableDataChanged();
        }
    }
    
    /**
     * Returns all items of the table.
     * 
     * @return the items of the table
     * @throws biz.manex.andaman7.injector.exceptions.MissingTableModelException
     */
    public List<I> getAllItems() throws MissingTableModelException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        return Collections.list(model.elements());
    }
    
    public void setItems(List<I> items) throws MissingTableModelException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        
        for(I item : items)
            model.addItem(item);
        
        model.fireTableDataChanged();
    }
    
    public void updateTable() throws MissingTableModelException {
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) getTable().getModel();
        model.fireTableDataChanged();
    }
    
    /**
     * Clear the form : type and value(s).
     */
    public void clearForm() {
        getTypeComboBox().setSelectedIndex(-1);
        getValueTextField().setText("");
        jComboBoxDataValues.setSelectedIndex(-1);
        jComboBoxDataValues.removeAllItems();
    }
    
    /**
     * Clear all data of the panel : form and table.
     * @throws biz.manex.andaman7.injector.exceptions.MissingTableModelException
     */
    public void clearData() throws MissingTableModelException {
        
        clearForm();
        
        if(getTable().getModel() == null)
            throw new MissingTableModelException("A table model must be set for the items table.");
        
        AbstractTableModel<V> model = (AbstractTableModel<V>) getTable().getModel();
        model.clear();
        model.fireTableDataChanged();
    }
    
    public abstract JComboBox getTypeComboBox();
    public abstract JTextField getValueTextField();
    public abstract JComboBox<V> getValuesComboBox();
    public abstract JTable getTable();
    public abstract JButton getAddButton();
    public abstract JButton getRemoveButton();
    public abstract JButton getEditButton();
}
