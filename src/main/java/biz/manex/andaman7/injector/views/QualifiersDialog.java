/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.QualifiersController;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.injector.models.SelectionList;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.SelectionListItem;
import biz.manex.andaman7.injector.models.types.MultivaluedQualifierType;
import biz.manex.andaman7.injector.models.types.MultivaluedType;
import biz.manex.andaman7.injector.views.tablemodels.QualifiersTableModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Pierre-Yves
 */
public class QualifiersDialog extends javax.swing.JDialog {

    /**
     * The available status of the dialog.
     */
    public enum Status { VALIDATED, CANCELLED, UNKNOWN }
    
    /**
     * The qualifiers controller.
     */
    private QualifiersController controller;
    
    /**
     * The qualifier types.
     */
    private List<QualifierType> qualifierTypes;
    
    private QualifiersTableModel tableModel;
    
    /**
     * The status after the dialog has been closed.
     */
    private Status closeStatus;
    
    private JButton jButtonCancel;
    
    private JButton jButtonOk;
    
    private ItemsManagementPanel<Qualifier, SelectionListItem> manageQualifiersPanel;
    
    
    /**
     * Creates new form QualifiersDialog
     * @param parent the parent frame
     * @param modal says if the dialog is modal or not
     * @param controller the qualifiers controller
     */
    public QualifiersDialog(java.awt.Frame parent, boolean modal, QualifiersController controller) {
        super(parent, modal);
        
        this.controller = controller;
        tableModel = new QualifiersTableModel();
        
        // Initialize the manage qualifiers panel
        manageQualifiersPanel = new ItemsManagementPanel<Qualifier, SelectionListItem>(tableModel);
        wireManageQualifiersPanel();
        
        initializeGUI();

        closeStatus = Status.UNKNOWN;
    }

    /**
     * Initializes all the GUI components.
     */
    private void initializeGUI() {

        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setName("cancel");
        jButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeStatus = Status.CANCELLED;
                setVisible(false);
            }
        });

        jButtonOk.setText("OK");
        jButtonOk.setName("ok");
        jButtonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeStatus = Status.VALIDATED;
                setVisible(false);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 248, Short.MAX_VALUE)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addComponent(manageQualifiersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageQualifiersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );
        
        setMinimumSize(new Dimension(425, 450));
        
        pack();
    }
    
    private void wireManageQualifiersPanel() {
        
        // Add button
        manageQualifiersPanel.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                QualifierType type = (QualifierType) manageQualifiersPanel.getTypeComboBox().getSelectedItem();
                Qualifier qualifier;
                
                if(type instanceof MultivaluedQualifierType) {

                    MultivaluedQualifierType multiType = (MultivaluedQualifierType) type;
                    SelectionListItem value = (SelectionListItem) manageQualifiersPanel.getValuesComboBox().getSelectedItem();
                    
                    qualifier = new Qualifier(multiType, value.getKey());
                    
                } else {
                    String value = manageQualifiersPanel.getValueTextField().getText();
                    qualifier = new Qualifier(type, value);
                }
                
                tableModel.addItem(qualifier);
                tableModel.fireTableDataChanged();
            }
        });
    }
    
    public void setQualifiers(List<Qualifier> qualifiers) {
        manageQualifiersPanel.setItems(qualifiers);
    }
    
    public void setQualifierTypes(List<QualifierType> qualifierTypes) {
        
         this.qualifierTypes = qualifierTypes;
         manageQualifiersPanel.setTypes(this.qualifierTypes.toArray(new QualifierType[this.qualifierTypes.size()]));
    }

    /**
     * Returns the close status.
     * 
     * @return the close status
     */
    public Status getCloseStatus() {
        return closeStatus;
    }
    
    public List<Qualifier> getQualifiers() {
        return (List<Qualifier>) Collections.list(manageQualifiersPanel.getAllItems());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
