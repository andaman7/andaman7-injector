/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.exceptions.NoSelectedItemException;
import biz.manex.andaman7.injector.controllers.QualifiersController;
import biz.manex.andaman7.injector.models.QualifiableItem;
import biz.manex.andaman7.injector.models.types.MultivaluedType;
import biz.manex.andaman7.injector.models.SelectionList;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.types.Type;
import biz.manex.andaman7.injector.views.tablemodels.AbstractTableModel;
import biz.manex.andaman7.injector.views.tablemodels.TableRowSelectionModel;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Pierre-Yves
 */
public class ItemsManagementPanel<I, V> extends javax.swing.JPanel implements ListSelectionListener {

    private QualifiersController qualifiersController;
    
    /**
     * The combo box that contains predetermined values for a specific type.
     */
    private JComboBox<V> jComboBoxDataValues;
    
    
    public ItemsManagementPanel(boolean withQualifiers, AbstractTableModel<I> tableModel) {
        initComponents();
        
        TableRowSelectionModel tableSelectionModel = new TableRowSelectionModel(jTable);
        tableSelectionModel.addListSelectionListener(this);
        jTable.setSelectionModel(tableSelectionModel);
        
        jComboBoxDataValues = new JComboBox<V>();
        jComboBoxDataValues.setModel(new DefaultComboBoxModel<V>());
        jComboBoxDataValues.setPreferredSize(new Dimension(150, 22));

        jTable.setModel(tableModel);
        jTable.getSelectionModel().addListSelectionListener(this);
        
        jButtonQualifiers.setVisible(withQualifiers);
    }
    
    public ItemsManagementPanel(AbstractTableModel<I> tableModel) {
        this(false, tableModel);
    }
    
    public QualifiersController getQualifierController() {
        return qualifiersController;
    }
    
    public void setQualifierController(QualifiersController qualifiersController) {
        this.qualifiersController = qualifiersController;
    }
    
    /**
     * Sets the types.
     * 
     * @param types the types
     */
    public void setTypes(Type[] types) {
        
        DefaultComboBoxModel<Type> model = (DefaultComboBoxModel<Type>) jComboBoxType.getModel();
        
        for(Type item : types)
            model.addElement(item);
        
        jComboBoxType.setModel(model);
    }
    
    /**
     * Returns the selected type.
     * 
     * @return the selected type
     */
    public Type getSelectedType() {
        return (Type) jComboBoxType.getSelectedItem();
    }
    
    /**
     * Sets the type to be selected.
     * 
     * @param type the type to be selected
     */
    public void setSelectedType(Object type) {
        jComboBoxType.setSelectedItem(type);
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
            return jTextFieldValue.getText();
        }
    }
    
    /**
     * Sets the value to be selected.
     * 
     * @param multivalued says if the value is multivalued
     * @param value the value to be selected
     */
    public void setSelectedValue(boolean multivalued, Object value) {
        
        if(multivalued)
            jComboBoxDataValues.setSelectedItem(value);
        else
            jTextFieldValue.setText(value.toString());
    }
    
    /**
     * Toggles buttons related to the (de)selection of an AMI in the AMIs table.
     */
    private void toggleButtons() {
        
        boolean enabled = jTable.getSelectedRow() != -1;
        
        jButtonEdit.setEnabled(enabled);
        jButtonRemove.setEnabled(enabled);
        jButtonQualifiers.setEnabled(enabled);
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
    private void selectedTypeUpdated() {
        Type selectedType = (Type) jComboBoxType.getSelectedItem();

        if(selectedType instanceof MultivaluedType) {

            MultivaluedType multiType = (MultivaluedType) selectedType;
            SelectionList selectionList = multiType.getValues();
            V[] items = (V[]) selectionList.getItems().values().toArray();
            Arrays.sort(items);
            
            DefaultComboBoxModel<V> model = new DefaultComboBoxModel<V>(items);
            jComboBoxDataValues.setModel(model);

            switchDataValueComponent(jTextFieldValue, jComboBoxDataValues);
            
        } else {
            switchDataValueComponent(jComboBoxDataValues, jTextFieldValue);
            jTextFieldValue.setSize(jTextFieldValue.getHeight(), 150);
        }
    }
    
    /**
     * Switches the data value old GUI component by a new GUI component.
     * 
     * @param oldComponent the old data value GUI component
     * @param newComponent the new data value GUI component
     */
    private void switchDataValueComponent(JComponent oldComponent, JComponent newComponent) {
        
        // Update the GUI
        remove(oldComponent);
        add(newComponent);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelValue)
                                .addComponent(jLabelType))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonQualifiers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelType)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelValue)
                        .addComponent(newComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAdd))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonRemove)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonQualifiers)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        
        invalidate();
    }
    
    /**
     * Adds an item to the table.
     * 
     * @param item the item to add
     */
    public void addItem(I item) {
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        model.addItem(item);
        model.fireTableDataChanged();
        
        clearForm();
    }
    
    /**
     * Returns the selected item.
     * 
     * @return the selected item
     * @throws NoSelectedItemException if no item is selected
     */
    public I getSelectedItem() throws NoSelectedItemException {
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        int rowIndex = jTable.getSelectedRow();
        
        if(rowIndex != -1)
            return model.getValueAt(rowIndex);
        else
            throw new NoSelectedItemException("No item is selected.");
    }
    
    /**
     * Removes the selected item from the table.
     */
    public void removeSelectedItem() {
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        int rowIndex = jTable.getSelectedRow();
        
        if(rowIndex != -1) {
            
            model.removeElementAt(rowIndex);
            model.fireTableDataChanged();
            
            clearForm();
        }
    }
    
    /**
     * Returns all items of the table.
     * 
     * @return the items of the table
     */
    public Enumeration<I> getAllItems() {
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        return model.elements();
    }
    
    public void setItems(List<I> items) {
        
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        
        for(I item : items)
            model.addItem(item);
        
        model.fireTableDataChanged();
    }
    
    public QualifiableItem showQualifiersDialog(QualifiableItem item, List<QualifierType> qualifierTypes) {
        return qualifiersController.showQualifiersDialog(item, qualifierTypes);
    }
    
    public void updateItem(I item) {
        AbstractTableModel<I> model = (AbstractTableModel<I>) jTable.getModel();
        // TODO
    }
    
    /**
     * Clear the form : type and value(s).
     */
    public void clearForm() {
        jComboBoxType.setSelectedIndex(-1);
        jTextFieldValue.setText("");
        jComboBoxDataValues.setSelectedIndex(-1);
        jComboBoxDataValues.removeAllItems();
    }
    
    /**
     * Clear all data of the panel : form and table.
     */
    public void clearData() {
        
        clearForm();
        
        AbstractTableModel<V> model = (AbstractTableModel<V>) jTable.getModel();
        model.clear();
        model.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelType = new javax.swing.JLabel();
        jLabelValue = new javax.swing.JLabel();
        jTextFieldValue = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jComboBoxType = new javax.swing.JComboBox();
        jScrollPaneData = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonQualifiers = new javax.swing.JButton();

        jLabelType.setText("Type");

        jLabelValue.setText("Value");

        jTextFieldValue.setMinimumSize(new java.awt.Dimension(150, 22));
        jTextFieldValue.setPreferredSize(new java.awt.Dimension(150, 22));

        jButtonAdd.setText("Add");

        jButtonRemove.setText("Remove");
        jButtonRemove.setEnabled(false);
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit");
        jButtonEdit.setEnabled(false);

        jComboBoxType.setModel(new DefaultComboBoxModel<Type>());
        jComboBoxType.setMinimumSize(new java.awt.Dimension(150, 22));
        jComboBoxType.setPreferredSize(new java.awt.Dimension(150, 22));
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable.setName("amis"); // NOI18N
        jScrollPaneData.setViewportView(jTable);

        jButtonQualifiers.setText("Qualifiers");
        jButtonQualifiers.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelValue)
                                .addComponent(jLabelType))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonQualifiers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelType)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelValue)
                        .addComponent(jTextFieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAdd))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonRemove)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonQualifiers)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeActionPerformed
        selectedTypeUpdated();
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        removeSelectedItem();
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    public QualifiersController getQualifiersController() {
        return qualifiersController;
    }

    public JButton getAddButton() {
        return jButtonAdd;
    }

    public JButton getEditButton() {
        return jButtonEdit;
    }

    public JButton getQualifiersButton() {
        return jButtonQualifiers;
    }

    public JButton getRemoveButton() {
        return jButtonRemove;
    }

    public JComboBox getTypeComboBox() {
        return jComboBoxType;
    }

    public JLabel getTypeLabel() {
        return jLabelType;
    }

    public JLabel getValueLabel() {
        return jLabelValue;
    }

    public JScrollPane getDataScrollPane() {
        return jScrollPaneData;
    }

    public JTable getTable() {
        return jTable;
    }

    public JTextField getValueTextField() {
        return jTextFieldValue;
    }
    
    public JComboBox<V> getValuesComboBox() {
        return jComboBoxDataValues;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonQualifiers;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelValue;
    private javax.swing.JScrollPane jScrollPaneData;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldValue;
    // End of variables declaration//GEN-END:variables
}
