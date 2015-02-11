package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.TAMI;
import biz.manex.andaman7.injector.models.dto.AndamanUserDTO;
import biz.manex.andaman7.injector.models.dto.DeviceDTO;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Pierre-Yves
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 02/02/2015.
 */
public class MainFrame extends javax.swing.JFrame {

    private final MainController mainController;
    private File selectedCsvFile;
    
    
    public MainFrame(MainController mainController, ActionListener logoutListener) {
        initComponents();
        
        this.mainController = mainController;
        
        this.jButtonLogout.addActionListener(logoutListener);

        this.jListRegistrars.setModel(new DefaultListModel<AndamanUserDTO>());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooserCsv = new javax.swing.JFileChooser();
        jPanelRegistrar = new javax.swing.JPanel();
        jLabelRegistrarKeyword = new javax.swing.JLabel();
        jTextFieldRegistrarKeyword = new javax.swing.JTextField();
        jButtonRegistrarSearch = new javax.swing.JButton();
        jScrollPanePatients = new javax.swing.JScrollPane();
        jListRegistrars = new javax.swing.JList();
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
        jPanelUpload = new javax.swing.JPanel();
        jLabelUploadFile = new javax.swing.JLabel();
        jTextFieldUploadFile = new javax.swing.JTextField();
        jButtonUploadBrowse = new javax.swing.JButton();
        jButtonUploadCsv = new javax.swing.JButton();
        jPanelContext = new javax.swing.JPanel();
        jLabelContextId = new javax.swing.JLabel();
        jTextFieldContextId = new javax.swing.JTextField();
        jLabelEhrId = new javax.swing.JLabel();
        jTextFieldEhrId = new javax.swing.JTextField();

        jFileChooserCsv.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jFileChooserCsv.setDialogTitle("Select CSV file");
        jFileChooserCsv.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelRegistrar.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar"));
        jPanelRegistrar.setPreferredSize(new java.awt.Dimension(424, 200));

        jLabelRegistrarKeyword.setText("Keyword");

        jTextFieldRegistrarKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRegistrarKeywordKeyPressed(evt);
            }
        });

        jButtonRegistrarSearch.setText("Search");
        jButtonRegistrarSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarSearchActionPerformed(evt);
            }
        });

        jListRegistrars.setModel(new DefaultListModel<AndamanUserDTO>()
        );
        jScrollPanePatients.setViewportView(jListRegistrars);

        javax.swing.GroupLayout jPanelRegistrarLayout = new javax.swing.GroupLayout(jPanelRegistrar);
        jPanelRegistrar.setLayout(jPanelRegistrarLayout);
        jPanelRegistrarLayout.setHorizontalGroup(
            jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPanePatients, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jPanelRegistrarLayout.createSequentialGroup()
                        .addComponent(jLabelRegistrarKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldRegistrarKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRegistrarSearch)))
                .addGap(17, 17, 17))
        );
        jPanelRegistrarLayout.setVerticalGroup(
            jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRegistrarKeyword)
                    .addComponent(jTextFieldRegistrarKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRegistrarSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanePatients)
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

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

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
                    .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDataRemove)
                    .addComponent(jButtonDataAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSend))
                    .addComponent(jScrollPaneData, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButtonLogout.setText("Logout");
        jButtonLogout.setName("logout"); // NOI18N
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jPanelUpload.setBorder(javax.swing.BorderFactory.createTitledBorder("Upload"));

        jLabelUploadFile.setText("File");

        jTextFieldUploadFile.setEditable(false);

        jButtonUploadBrowse.setText("Browse");
        jButtonUploadBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadBrowseActionPerformed(evt);
            }
        });

        jButtonUploadCsv.setText("Upload");
        jButtonUploadCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadCsvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUploadLayout = new javax.swing.GroupLayout(jPanelUpload);
        jPanelUpload.setLayout(jPanelUploadLayout);
        jPanelUploadLayout.setHorizontalGroup(
            jPanelUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldUploadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUploadBrowse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUploadCsv)
                .addContainerGap())
        );
        jPanelUploadLayout.setVerticalGroup(
            jPanelUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUploadFile)
                    .addComponent(jTextFieldUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUploadBrowse)
                    .addComponent(jButtonUploadCsv))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelContext.setBorder(javax.swing.BorderFactory.createTitledBorder("Context"));

        jLabelContextId.setText("Context ID");

        jLabelEhrId.setText("EHR ID");

        javax.swing.GroupLayout jPanelContextLayout = new javax.swing.GroupLayout(jPanelContext);
        jPanelContext.setLayout(jPanelContextLayout);
        jPanelContextLayout.setHorizontalGroup(
            jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelContextId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldContextId, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelEhrId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldEhrId, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelContextLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldContextId, jTextFieldEhrId});

        jPanelContextLayout.setVerticalGroup(
            jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContextId)
                    .addComponent(jTextFieldContextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEhrId)
                    .addComponent(jTextFieldEhrId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelData, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addComponent(jPanelUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanelContext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLogout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelContext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelData, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setContextId(String contextId) {
        this.jTextFieldContextId.setText(contextId);
    }

    private void searchPatient() {
        String keyword = jTextFieldRegistrarKeyword.getText();

        if(!keyword.isEmpty()) {
            AndamanUserDTO[] users = this.mainController.searchUsers(keyword);

            DefaultListModel<AndamanUserDTO> model = (DefaultListModel<AndamanUserDTO>) jListRegistrars.getModel();
            model.clear();

            for (AndamanUserDTO user : users)
                model.addElement(user);

            jListRegistrars.setModel(model);
            jListRegistrars.invalidate();
        }
    }

    private void addData() {
        TAMI type = (TAMI) jComboBoxDataType.getModel().getSelectedItem();
        String value = jTextFieldDataValue.getText();

        if(!value.isEmpty()) {
            AMI ami = new AMI(type, value);
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
            jListData.setSelectedIndex((index < model.getSize() - 1 ? index : model.getSize() - 1));
        }
    }

    private boolean verifyBeforSendingData() {

        int patientIndex = jListRegistrars.getSelectedIndex();
        DefaultListModel<AndamanUserDTO> patientsModel = (DefaultListModel<AndamanUserDTO>) jListRegistrars.getModel();
        DefaultListModel<AMI> dataModel = (DefaultListModel<AMI>) jListData.getModel();

        if(this.jTextFieldContextId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You need to specify a context id.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.jTextFieldEhrId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You need to specify an EHR id.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(patientIndex == -1) {
            JOptionPane.showMessageDialog(this, "You need to select a registrar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        List<DeviceDTO> devices = mainController.getCurrentUser().getDevices();
        if(devices != null && devices.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A device is required to inject data into an EHR.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }


    private void sendDataFromGui() {

        if(!verifyBeforSendingData())
            return;

        DefaultListModel<AMI> dataModel = (DefaultListModel<AMI>) jListData.getModel();

        if(dataModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "At least one data must be entered.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HashMap<String, String> amis = new HashMap<String, String>();
        Enumeration<AMI> enu = dataModel.elements();

        while(enu.hasMoreElements()) {
            AMI ami = enu.nextElement();
            amis.put(ami.getType().getKey(), ami.getValue());
        }

        this.sendData(amis);
    }

    private void sendDataFromCsvFile() {

        if(!verifyBeforSendingData())
            return;

        if(this.selectedCsvFile == null) {
            JOptionPane.showMessageDialog(this, "You need to select a CSV file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            HashMap<String, String> amis = this.mainController.getAmisFromCsvFile(this.selectedCsvFile);
            this.jFileChooserCsv.setSelectedFile(null);
            this.jTextFieldUploadFile.setText("");
            this.sendData(amis);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendData(HashMap<String, String> amis) {

        int patientIndex = jListRegistrars.getSelectedIndex();
        DefaultListModel<AndamanUserDTO> patientsModel = (DefaultListModel<AndamanUserDTO>) jListRegistrars.getModel();
        AndamanUserDTO patient = patientsModel.getElementAt(patientIndex);

        String contextId = this.jTextFieldContextId.getText();

        String amiContainerId = this.jTextFieldEhrId.getText();
        AMIContainer amiContainer = new AMIContainer(amiContainerId, amis);
        List<AMIContainer> amiContainers = new ArrayList<AMIContainer>();
        amiContainers.add(amiContainer);

        boolean alreadyMember = this.mainController.sendData(patient, amiContainers, contextId);

        if(!alreadyMember)
            JOptionPane.showMessageDialog(this,
                    "An invitation has been sent to the user.", "Info",
                    JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this,
                "The data has been successfuly sent !", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButtonRegistrarSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientSearchActionPerformed
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
        sendDataFromGui();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed

        // Clear all text fields, combo boxes and lists
        this.jTextFieldRegistrarKeyword.setText("");
        this.jTextFieldDataValue.setText("");
        this.jComboBoxDataType.setSelectedIndex(-1);

        DefaultListModel model;

        model = (DefaultListModel) this.jListRegistrars.getModel();
        model.clear();

        model = (DefaultListModel) this.jListData.getModel();
        model.clear();

    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jTextFieldRegistrarKeywordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchPatient();
    }//GEN-LAST:event_jTextFieldPatientNameKeyPressed

    private void jTextFieldDataValueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDataValueKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            addData();
    }//GEN-LAST:event_jTextFieldDataValueKeyPressed

    private void jButtonUploadBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadBrowseActionPerformed
        int result = this.jFileChooserCsv.showDialog(this, "Open");
        
        if(result == JFileChooser.APPROVE_OPTION && jFileChooserCsv.getSelectedFile() != null) {
            this.selectedCsvFile = this.jFileChooserCsv.getSelectedFile();
            this.jTextFieldUploadFile.setText(this.selectedCsvFile.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonUploadBrowseActionPerformed

    private void jButtonUploadCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadCsvActionPerformed
        sendDataFromCsvFile();
    }//GEN-LAST:event_jButtonUploadCsvActionPerformed

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
    private javax.swing.JButton jButtonRegistrarSearch;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonUploadBrowse;
    private javax.swing.JButton jButtonUploadCsv;
    private javax.swing.JComboBox jComboBoxDataType;
    private javax.swing.JFileChooser jFileChooserCsv;
    private javax.swing.JLabel jLabelContextId;
    private javax.swing.JLabel jLabelDataType;
    private javax.swing.JLabel jLabelDataValue;
    private javax.swing.JLabel jLabelEhrId;
    private javax.swing.JLabel jLabelRegistrarKeyword;
    private javax.swing.JLabel jLabelUploadFile;
    private javax.swing.JList jListData;
    private javax.swing.JList jListRegistrars;
    private javax.swing.JPanel jPanelContext;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelRegistrar;
    private javax.swing.JPanel jPanelUpload;
    private javax.swing.JScrollPane jScrollPaneData;
    private javax.swing.JScrollPane jScrollPanePatients;
    private javax.swing.JTextField jTextFieldContextId;
    private javax.swing.JTextField jTextFieldDataValue;
    private javax.swing.JTextField jTextFieldEhrId;
    private javax.swing.JTextField jTextFieldRegistrarKeyword;
    private javax.swing.JTextField jTextFieldUploadFile;
    // End of variables declaration//GEN-END:variables
}
