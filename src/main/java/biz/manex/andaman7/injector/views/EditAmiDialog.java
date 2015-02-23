package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.exceptions.InjectorException;
import biz.manex.andaman7.injector.exceptions.MissingTableModelException;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.SelectionListItem;
import biz.manex.andaman7.injector.models.types.MultivaluedQualifierType;
import biz.manex.andaman7.injector.models.types.MultivaluedType;
import biz.manex.andaman7.injector.views.tablemodels.QualifiersTableModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Pierre-Yves
 */
public class EditAmiDialog extends javax.swing.JDialog {

    /**
     * The available status of the dialog.
     */
    public enum Status { VALIDATED, CANCELLED, UNKNOWN }
    
    /**
     * The main controller.
     */
    private MainController controller;
    
    /**
     * The AMI to edit.
     */
    private AMI ami;
    
    /**
     * The table model of the qualifiers.
     */
    private QualifiersTableModel tableModel;
    
    private JComboBox jComboBoxValues;
    
    /**
     * The status after the dialog has been closed.
     */
    private Status closeStatus;
    
    public EditAmiDialog(java.awt.Frame parent, boolean modal, MainController controller, AMI ami, HashMap<String, SelectionListItem> values) {
        this(parent, modal, controller, ami);
        
        DefaultComboBoxModel<SelectionListItem> model =
                new DefaultComboBoxModel<SelectionListItem>(values.values().toArray(new SelectionListItem[values.size()]));
        
        jComboBoxValues = new JComboBox<SelectionListItem>();
        jComboBoxValues.setModel(model);
        jComboBoxValues.setSelectedItem(values.get(ami.getValue()));
        
        switchAmiValueComponent(jTextFieldValue, jComboBoxValues);
    }
    
    
    /**
     * Creates new form QualifiersDialog
     * 
     * @param parent the parent frame
     * @param modal says if the dialog is modal or not
     * @param controller the main controller
     * @param ami the AMI to edit
     */
    public EditAmiDialog(java.awt.Frame parent, boolean modal, MainController controller, AMI ami) {
        super(parent, modal);
        
        initComponents();
        
        this.controller = controller;
        this.ami = ami;
        closeStatus = Status.UNKNOWN;
        
        jTextFieldValue.setText(ami.getValue());
        
        jButtonOk.setName("ok");
        jButtonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeStatus = Status.VALIDATED;
                setVisible(false);
            }
        });
        
        jButtonCancel.setName("cancel");
        jButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeStatus = Status.CANCELLED;
                setVisible(false);
            }
        });
        
        // Qualifiers table model
        tableModel = new QualifiersTableModel();
        
        for(Qualifier qualifier : ami.getQualifiers())
            tableModel.addItem(qualifier);
        
        // Initialize the manage qualifiers panel
        manageQualifiersPanel.setTableModel(tableModel);
        
        List<QualifierType> qualifierTypes = ami.getType().getQualifierTypes();
        manageQualifiersPanel.setTypes(qualifierTypes.toArray(new QualifierType[qualifierTypes.size()]));
        
        wireManageQualifiersPanel();

        
        setTitle(String.format("Andaman 7 - Edit '%s' AMI", ami.getType().getName()));
        setMinimumSize(new Dimension(425, 450));
    }
    
    private void wireManageQualifiersPanel() {
        
        // Add button
        manageQualifiersPanel.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addQualifier();
            }
        });
        
        // Value text field
        manageQualifiersPanel.getValueTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    addQualifier();
            }
        });
        
        // Values combo box
        manageQualifiersPanel.getValuesComboBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                 
               if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    addQualifier();
            }
        });
    }
    
    private void switchAmiValueComponent(JComponent oldComponent, JComponent newComponent) {
        
        jPanelAMI.remove(oldComponent);
        jPanelAMI.add(newComponent);
        
        javax.swing.GroupLayout jPanelAMILayout = new javax.swing.GroupLayout(jPanelAMI);
        jPanelAMI.setLayout(jPanelAMILayout);
        jPanelAMILayout.setHorizontalGroup(
            jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAMILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newComponent)
                .addContainerGap())
        );
        jPanelAMILayout.setVerticalGroup(
            jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAMILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValue)
                    .addComponent(newComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    private void addQualifier() {
        
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

        try {
            manageQualifiersPanel.addItem(qualifier);
        
        } catch(InjectorException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Returns all the qualifiers of the table.
     * 
     * @return a list of qualifiers
     * @throws MissingTableModelException if the table model has not been set
     */
    public List<Qualifier> getQualifiers() throws MissingTableModelException {
        return (List<Qualifier>) Collections.list(manageQualifiersPanel.getAllItems());
    }
    
    /**
     * Sets the qualifiers table.
     * 
     * @param qualifiers the qualifiers to add
     * @throws MissingTableModelException if the table model has not been set
     */
    public void setQualifiers(List<Qualifier> qualifiers) throws MissingTableModelException {
        manageQualifiersPanel.setItems(qualifiers);
    }

    /**
     * Returns the close status.
     * 
     * @return the close status
     */
    public Status getCloseStatus() {
        return closeStatus;
    }
    
    public AMI getEditedAmi() {
        
        String value;
        
        if(ami.getType() instanceof MultivaluedType)
            value = ((SelectionListItem) jComboBoxValues.getSelectedItem()).getKey();
        else
            value = jTextFieldValue.getText();
        
        ami.setValue(value);
        ami.setQualifiers(Collections.list(tableModel.elements()));
        
        return ami;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageQualifiersPanel = new biz.manex.andaman7.injector.views.ItemsManagementPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jPanelAMI = new javax.swing.JPanel();
        jLabelValue = new javax.swing.JLabel();
        jTextFieldValue = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        manageQualifiersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Qualifiers"));

        jButtonCancel.setText("Cancel");

        jButtonOk.setText("OK");

        jPanelAMI.setBorder(javax.swing.BorderFactory.createTitledBorder("AMI"));

        jLabelValue.setText("Value");

        javax.swing.GroupLayout jPanelAMILayout = new javax.swing.GroupLayout(jPanelAMI);
        jPanelAMI.setLayout(jPanelAMILayout);
        jPanelAMILayout.setHorizontalGroup(
            jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAMILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldValue)
                .addContainerGap())
        );
        jPanelAMILayout.setVerticalGroup(
            jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAMILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValue)
                    .addComponent(jTextFieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 367, Short.MAX_VALUE)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageQualifiersPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanelAMI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAMI, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageQualifiersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabelValue;
    private javax.swing.JPanel jPanelAMI;
    private javax.swing.JTextField jTextFieldValue;
    private biz.manex.andaman7.injector.views.ItemsManagementPanel manageQualifiersPanel;
    // End of variables declaration//GEN-END:variables
}
