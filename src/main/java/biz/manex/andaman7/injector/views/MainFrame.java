package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.TAMI;
import biz.manex.andaman7.injector.models.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.models.dto.DeviceDTO;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;


/**
 *
 * @author Pierre-Yves
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 02/02/2015.
 */
public class MainFrame extends javax.swing.JFrame {

    private final MainController mainController;
    
    
    public MainFrame(MainController mainController, ActionListener logoutListener) {
        initComponents();
        
        this.mainController = mainController;
        
        this.jButtonLogout.addActionListener(logoutListener);
        this.jListPatients.setModel(new DefaultListModel<AndamanUserDTO>());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPatient = new javax.swing.JPanel();
        jLabelPatientName = new javax.swing.JLabel();
        jTextFieldPatientName = new javax.swing.JTextField();
        jButtonPatientSearch = new javax.swing.JButton();
        jScrollPanePatients = new javax.swing.JScrollPane();
        jListPatients = new javax.swing.JList();
        jPanelData = new javax.swing.JPanel();
        jLabelDataType = new javax.swing.JLabel();
        jLabelDataValue = new javax.swing.JLabel();
        jTextFieldDataValue = new javax.swing.JTextField();
        jButtonDataAdd = new javax.swing.JButton();
        jButtonDataRemove = new javax.swing.JButton();
        jButtonDataEdit = new javax.swing.JButton();
        jScrollPaneData = new javax.swing.JScrollPane();
        jListData = new javax.swing.JList();
        jComboBoxDataType = new javax.swing.JComboBox();
        jButtonSend = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPatient.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient"));
        jPanelPatient.setPreferredSize(new java.awt.Dimension(424, 200));

        jLabelPatientName.setText("Patient name");

        jTextFieldPatientName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPatientNameKeyPressed(evt);
            }
        });

        jButtonPatientSearch.setText("Search");
        jButtonPatientSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientSearchActionPerformed(evt);
            }
        });

        jListPatients.setModel(new DefaultListModel<AndamanUserDTO>()
        );
        jScrollPanePatients.setViewportView(jListPatients);

        javax.swing.GroupLayout jPanelPatientLayout = new javax.swing.GroupLayout(jPanelPatient);
        jPanelPatient.setLayout(jPanelPatientLayout);
        jPanelPatientLayout.setHorizontalGroup(
            jPanelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPanePatients, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addGroup(jPanelPatientLayout.createSequentialGroup()
                        .addComponent(jLabelPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPatientSearch)))
                .addGap(17, 17, 17))
        );
        jPanelPatientLayout.setVerticalGroup(
            jPanelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPatientName)
                    .addComponent(jTextFieldPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPatientSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanePatients, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        jPanelData.setPreferredSize(new java.awt.Dimension(12, 200));

        jLabelDataType.setText("Type");

        jLabelDataValue.setText("Value");

        jTextFieldDataValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDataValueKeyPressed(evt);
            }
        });

        jButtonDataAdd.setText("Add");
        jButtonDataAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataAddActionPerformed(evt);
            }
        });

        jButtonDataRemove.setText("Remove");
        jButtonDataRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataRemoveActionPerformed(evt);
            }
        });

        jButtonDataEdit.setText("Edit");
        jButtonDataEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataEditActionPerformed(evt);
            }
        });

        jListData.setModel(new DefaultListModel<AMI>()
        );
        jScrollPaneData.setViewportView(jListData);

        javax.swing.GroupLayout jPanelDataLayout = new javax.swing.GroupLayout(jPanelData);
        jPanelData.setLayout(jPanelDataLayout);
        jPanelDataLayout.setHorizontalGroup(
            jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createSequentialGroup()
                        .addComponent(jLabelDataType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxDataType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataValue)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDataValue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDataRemove)
                    .addComponent(jButtonDataAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelDataLayout.setVerticalGroup(
            jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataType)
                    .addComponent(jLabelDataValue)
                    .addComponent(jTextFieldDataValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataAdd)
                    .addComponent(jComboBoxDataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createSequentialGroup()
                        .addComponent(jButtonDataRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDataEdit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPaneData))
                .addContainerGap())
        );

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jButtonLogout.setText("Logout");
        jButtonLogout.setName("logout"); // NOI18N
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelData, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jPanelData, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSend)
                    .addComponent(jButtonLogout))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchPatient() {
        String keyword = jTextFieldPatientName.getText();
        
        if(!keyword.isEmpty()) {
            AndamanUserDTO[] users = this.mainController.searchUsers(keyword);
            
            DefaultListModel<AndamanUserDTO> model = (DefaultListModel<AndamanUserDTO>) jListPatients.getModel();
            model.clear();
            
            for (AndamanUserDTO user : users)
                model.addElement(user);
            
            jListPatients.setModel(model);
            jListPatients.invalidate();
        }
    }
    
    private void addData() {
        TAMI type = (TAMI) jComboBoxDataType.getModel().getSelectedItem();
        String value = jTextFieldDataValue.getText();
        
        if(!value.isEmpty()) {
            AMI ami = new AMI(type.getKey(), value);
            DefaultListModel<AMI> model = (DefaultListModel<AMI>) jListData.getModel();
            model.addElement(ami);
            jListData.setModel(model);
            
            jComboBoxDataType.setSelectedIndex(-1);
            jTextFieldDataValue.setText("");
        }
    }
    
    private void editData() {
        int index = jListData.getSelectedIndex();
        
        if(index != -1) {
            DefaultListModel<AMI> model = (DefaultListModel<AMI>) jListData.getModel();
            AMI ami = model.getElementAt(index);

            jComboBoxDataType.setSelectedItem(ami.getType());
            jTextFieldDataValue.setText(ami.getValue());

            removeDataFromList();
        }
    }
    
    private void removeDataFromList() {
        
        int index = jListData.getSelectedIndex();
        
        if(index != -1) {
            DefaultListModel<AMI> model = (DefaultListModel<AMI>) jListData.getModel();
            model.removeElementAt(index);
            jListData.setModel(model);
            jListData.setSelectedIndex((index < model.getSize()-1 ? index : model.getSize()-1));
        }
    }
    
    private void sendData() {
        int patientIndex = jListPatients.getSelectedIndex();
        DefaultListModel<AndamanUserDTO> patientsModel = (DefaultListModel<AndamanUserDTO>) jListPatients.getModel();
        DefaultListModel<AMI> dataModel = (DefaultListModel<AMI>) jListData.getModel();
        
        if(patientIndex == -1) {
            JOptionPane.showMessageDialog(this,
                        "You must first select a patient.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(dataModel.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                        "At least one data must be entered.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String currentUserUUID = mainController.getCurrentUser().getUuid();

        List<DeviceDTO> devices = mainController.getCurrentUser().getDevices();
        if(devices.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "A device is required to inject data into an EHR.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }   

        String currentDeviceUUID = devices.get(0).getUuid();
        AndamanUserDTO patient = patientsModel.getElementAt(patientIndex);

        HashMap<String, String> amis = new HashMap<String, String>();

        Enumeration<AMI> enu = dataModel.elements();
        
        while(enu.hasMoreElements()) {
            AMI ami = enu.nextElement();
            amis.put(ami.getType(), ami.getValue());
        }

        boolean alreadyMember = this.mainController.sendData(currentDeviceUUID,
                currentUserUUID, patient, amis);
        
        if(!alreadyMember)
            JOptionPane.showMessageDialog(this,
                    "An invitation has been sent to the user.", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        
        JOptionPane.showMessageDialog(this,
                "The data has been successfuly sent !", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void jButtonPatientSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientSearchActionPerformed
        searchPatient();
    }//GEN-LAST:event_jButtonPatientSearchActionPerformed
    
    private void jButtonDataAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataAddActionPerformed
        addData();
    }//GEN-LAST:event_jButtonDataAddActionPerformed

    private void jButtonDataRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataRemoveActionPerformed
        removeDataFromList();
    }//GEN-LAST:event_jButtonDataRemoveActionPerformed

    private void jButtonDataEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataEditActionPerformed
        editData();
    }//GEN-LAST:event_jButtonDataEditActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        sendData();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        
        // Clear all text fields, combo boxes and lists
        this.jTextFieldPatientName.setText("");
        this.jTextFieldDataValue.setText("");
        this.jComboBoxDataType.setSelectedIndex(-1);

        DefaultListModel model;
        
        model = (DefaultListModel) this.jListPatients.getModel();
        model.clear();
        
        model = (DefaultListModel) this.jListData.getModel();
        model.clear();
        
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jTextFieldPatientNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchPatient();
    }//GEN-LAST:event_jTextFieldPatientNameKeyPressed

    private void jTextFieldDataValueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDataValueKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            addData();
    }//GEN-LAST:event_jTextFieldDataValueKeyPressed

    public void setTamiList() {
        DefaultComboBoxModel<TAMI> model = new DefaultComboBoxModel<TAMI>();
        TAMI[] tamis = this.mainController.getTamis();

        for(TAMI tami : tamis)
            model.addElement(tami);

        this.jComboBoxDataType.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDataAdd;
    private javax.swing.JButton jButtonDataEdit;
    private javax.swing.JButton jButtonDataRemove;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonPatientSearch;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JComboBox jComboBoxDataType;
    private javax.swing.JLabel jLabelDataType;
    private javax.swing.JLabel jLabelDataValue;
    private javax.swing.JLabel jLabelPatientName;
    private javax.swing.JList jListData;
    private javax.swing.JList jListPatients;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelPatient;
    private javax.swing.JScrollPane jScrollPaneData;
    private javax.swing.JScrollPane jScrollPanePatients;
    private javax.swing.JTextField jTextFieldDataValue;
    private javax.swing.JTextField jTextFieldPatientName;
    // End of variables declaration//GEN-END:variables
}
