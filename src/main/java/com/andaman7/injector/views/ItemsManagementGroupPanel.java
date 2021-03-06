package com.andaman7.injector.views;

import com.andaman7.injector.controllers.MainController;
import com.andaman7.injector.exceptions.MissingTableModelException;
import com.andaman7.injector.models.AbstractGroup;
import com.andaman7.injector.models.types.Type;
import com.andaman7.injector.views.tablemodels.AbstractTableModel;

import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Pierre-Yves
 * @param <I>
 * @param <V>
 * @param <T>
 */
public class ItemsManagementGroupPanel<I, V, T extends Type> extends AbstractItemsManagementPanel<I, V> {

    /**
     * Builds an items management panel.
     */
    public ItemsManagementGroupPanel() {
        super();
        
        initComponents();
        postInitComponents();
    }

    /**
     * Builds an items management panel using a table model.
     * 
     * @param mainController
     * @param tableModel the table model
     */
    public ItemsManagementGroupPanel(MainController mainController, AbstractTableModel<I> tableModel) {
        this();

        setMainController(mainController);
        setTableModel(tableModel);
    }
    
    public void setGroups(List<AbstractGroup<T>> groups) {
        
        DefaultComboBoxModel<AbstractGroup<T>> model = (DefaultComboBoxModel<AbstractGroup<T>>) jComboBoxGroup.getModel();
        
        for(AbstractGroup<T> group : groups)
            model.addElement(group);
        
        jComboBoxGroup.setSelectedIndex(-1);
        jComboBoxType.setSelectedIndex(-1);
    }

    protected void switchDataValueComponent(JComponent oldComponent, JComponent newComponent) {

        // Update the GUI
        remove(oldComponent);
        add(newComponent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGroup)
                .addGap(10, 10, 10)
                .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelType)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxType, 0, 180, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelValue)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(newComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGroup))
                .addContainerGap(358, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
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
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        invalidate();
    }

    private void selectedGroupUpdated() {
        AbstractGroup<T> group = (AbstractGroup<T>) jComboBoxGroup.getSelectedItem();
        
        if(group == null)
            return;
        
        List<T> items = group.getItems();
        
        Type[] itemsArray = items.toArray(new Type[items.size()]);
        Arrays.sort(itemsArray);
        setTypes(itemsArray);
    }
    
    @Override
    public void clearForm() {
        super.clearForm();
        
        jComboBoxGroup.setSelectedIndex(-1);
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
        jComboBoxGroup = new javax.swing.JComboBox();
        jLabelGroup = new javax.swing.JLabel();

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

        jComboBoxGroup.setModel(new DefaultComboBoxModel<AbstractGroup<T>>());
        jComboBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroupActionPerformed(evt);
            }
        });

        jLabelGroup.setText("Group");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGroup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxGroup, 0, 156, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addComponent(jLabelType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxType, 0, 155, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelValue)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGroup))
                .addContainerGap(358, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
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
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeActionPerformed
        selectedTypeUpdated();
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    protected void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        try {
            removeSelectedItem();
        } catch (MissingTableModelException ex) {

        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jComboBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroupActionPerformed
        selectedGroupUpdated();
    }//GEN-LAST:event_jComboBoxGroupActionPerformed

    public JButton getAddButton() {
        return jButtonAdd;
    }

    public JButton getEditButton() {
        return jButtonEdit;
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
    protected javax.swing.JButton jButtonAdd;
    protected javax.swing.JButton jButtonEdit;
    protected javax.swing.JButton jButtonRemove;
    private javax.swing.JComboBox jComboBoxGroup;
    protected javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelGroup;
    protected javax.swing.JLabel jLabelType;
    protected javax.swing.JLabel jLabelValue;
    protected javax.swing.JScrollPane jScrollPaneData;
    protected javax.swing.JTable jTable;
    protected javax.swing.JTextField jTextFieldValue;
    // End of variables declaration//GEN-END:variables
}
