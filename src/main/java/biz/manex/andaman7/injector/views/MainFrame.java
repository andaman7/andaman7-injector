package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.TAMI;
import biz.manex.andaman7.server.api.dto.device.DeviceDTO;
import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Pierre-Yves
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 02/02/2015.
 */
public class MainFrame extends JFrame {

    private final MainController mainController;
    private File selectedCsvFile;
    
    
    public MainFrame(MainController mainController, ActionListener logoutListener) {
        initComponents();
        
        this.mainController = mainController;

        jButtonLogout.addActionListener(logoutListener);

        jListRegistrars.setModel(new DefaultListModel<AndamanUserDTO>());
        jTableAmis.setModel(new AmisTableModel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooserCsv = new JFileChooser();
        jPanelRegistrar = new JPanel();
        jLabelRegistrarKeyword = new JLabel();
        jTextFieldRegistrarKeyword = new JTextField();
        jButtonRegistrarSearch = new JButton();
        jScrollPaneRegistrars = new JScrollPane();
        jListRegistrars = new JList<AndamanUserDTO>();
        jPanelData = new JPanel();
        jLabelDataType = new JLabel();
        jLabelDataValue = new JLabel();
        jTextFieldDataValue = new JTextField();
        jButtonDataAdd = new JButton();
        jButtonDataRemove = new JButton();
        jButtonDataEdit = new JButton();
        jComboBoxDataType = new JComboBox<TAMI>();
        jButtonSend = new JButton();
        jScrollPaneData = new JScrollPane();
        jTableAmis = new JTable();
        jButtonLogout = new JButton();
        jPanelUpload = new JPanel();
        jLabelUploadFile = new JLabel();
        jTextFieldUploadFile = new JTextField();
        jButtonUploadBrowse = new JButton();
        jButtonUploadCsv = new JButton();
        jPanelContext = new JPanel();
        jLabelContextId = new JLabel();
        jTextFieldContextId = new JTextField();
        jLabelEhrId = new JLabel();
        jTextFieldEhrId = new JTextField();

        jFileChooserCsv.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jFileChooserCsv.setDialogTitle("Select CSV file");
        jFileChooserCsv.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanelRegistrar.setBorder(BorderFactory.createTitledBorder("Registrar"));
        jPanelRegistrar.setPreferredSize(new Dimension(424, 200));

        jLabelRegistrarKeyword.setText("Keyword");

        jTextFieldRegistrarKeyword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jTextFieldRegistrarKeywordKeyPressed(evt);
            }
        });

        jButtonRegistrarSearch.setText("Search");
        jButtonRegistrarSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonRegistrarSearchActionPerformed(evt);
            }
        });

        jScrollPaneRegistrars.setViewportView(jListRegistrars);

        GroupLayout jPanelRegistrarLayout = new GroupLayout(jPanelRegistrar);
        jPanelRegistrar.setLayout(jPanelRegistrarLayout);
        jPanelRegistrarLayout.setHorizontalGroup(
            jPanelRegistrarLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(jScrollPaneRegistrars)
                        .addGroup(jPanelRegistrarLayout.createSequentialGroup()
                                .addComponent(jLabelRegistrarKeyword)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldRegistrarKeyword)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(jButtonRegistrarSearch)))
                .addGap(17, 17, 17))
        );
        jPanelRegistrarLayout.setVerticalGroup(
            jPanelRegistrarLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabelRegistrarKeyword)
                        .addComponent(jTextFieldRegistrarKeyword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRegistrarSearch))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jScrollPaneRegistrars)
                .addContainerGap())
        );

        jPanelData.setBorder(BorderFactory.createTitledBorder("Data"));
        jPanelData.setPreferredSize(new Dimension(12, 200));

        jLabelDataType.setText("Type");

        jLabelDataValue.setText("Value");

        jTextFieldDataValue.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jTextFieldDataValueKeyPressed(evt);
            }
        });

        jButtonDataAdd.setText("Add");
        jButtonDataAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonDataAddActionPerformed(evt);
            }
        });

        jButtonDataRemove.setText("Remove");
        jButtonDataRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonDataRemoveActionPerformed(evt);
            }
        });

        jButtonDataEdit.setText("Edit");
        jButtonDataEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonDataEditActionPerformed(evt);
            }
        });

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jScrollPaneData.setViewportView(jTableAmis);

        GroupLayout jPanelDataLayout = new GroupLayout(jPanelData);
        jPanelData.setLayout(jPanelDataLayout);
        jPanelDataLayout.setHorizontalGroup(
            jPanelDataLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createSequentialGroup()
                        .addComponent(jLabelDataType)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxDataType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataValue)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDataValue, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneData, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jButtonDataRemove)
                    .addComponent(jButtonDataAdd, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataEdit, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSend, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelDataLayout.setVerticalGroup(
            jPanelDataLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabelDataType)
                    .addComponent(jLabelDataValue)
                    .addComponent(jTextFieldDataValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataAdd)
                    .addComponent(jComboBoxDataType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createSequentialGroup()
                        .addComponent(jButtonDataRemove)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jButtonDataEdit)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSend))
                    .addComponent(jScrollPaneData, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButtonLogout.setText("Logout");
        jButtonLogout.setName("logout"); // NOI18N
        jButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jPanelUpload.setBorder(BorderFactory.createTitledBorder("Upload"));

        jLabelUploadFile.setText("File");

        jTextFieldUploadFile.setEditable(false);

        jButtonUploadBrowse.setText("Browse");
        jButtonUploadBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonUploadBrowseActionPerformed(evt);
            }
        });

        jButtonUploadCsv.setText("Upload");
        jButtonUploadCsv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonUploadCsvActionPerformed(evt);
            }
        });

        GroupLayout jPanelUploadLayout = new GroupLayout(jPanelUpload);
        jPanelUpload.setLayout(jPanelUploadLayout);
        jPanelUploadLayout.setHorizontalGroup(
            jPanelUploadLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploadFile)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldUploadFile)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButtonUploadBrowse)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButtonUploadCsv)
                .addContainerGap())
        );
        jPanelUploadLayout.setVerticalGroup(
            jPanelUploadLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUploadLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabelUploadFile)
                    .addComponent(jTextFieldUploadFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUploadBrowse)
                    .addComponent(jButtonUploadCsv))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelContext.setBorder(BorderFactory.createTitledBorder("Context"));

        jLabelContextId.setText("Context ID");

        jLabelEhrId.setText("EHR ID");

        GroupLayout jPanelContextLayout = new GroupLayout(jPanelContext);
        jPanelContext.setLayout(jPanelContextLayout);
        jPanelContextLayout.setHorizontalGroup(
            jPanelContextLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelContextId)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldContextId, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jLabelEhrId)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldEhrId, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelContextLayout.linkSize(SwingConstants.HORIZONTAL, jTextFieldContextId, jTextFieldEhrId);

        jPanelContextLayout.setVerticalGroup(
            jPanelContextLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContextLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabelContextId)
                    .addComponent(jTextFieldContextId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEhrId)
                    .addComponent(jTextFieldEhrId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelRegistrar, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(jPanelData, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                            .addComponent(jPanelUpload, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanelContext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLogout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelContext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelData, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jPanelUpload, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelRegistrar, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButtonLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setContextId(String contextId) {
        jTextFieldContextId.setText(contextId);
    }

    private void searchPatient() {
        String keyword = jTextFieldRegistrarKeyword.getText();

        if(!keyword.isEmpty()) {
            AndamanUserDTO[] users = mainController.searchUsers(keyword);

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
            AmisTableModel model = (AmisTableModel) jTableAmis.getModel();
            model.addAmi(ami);
            model.fireTableDataChanged();
            jTableAmis.setModel(model);

            jComboBoxDataType.setSelectedIndex(-1);
            jTextFieldDataValue.setText("");
        }
    }

    private void editData() {
        int index = jTableAmis.getSelectedRow();

        if(index != -1) {
            AmisTableModel model = (AmisTableModel) jTableAmis.getModel();
            AMI ami = model.getValueAt(index);

            jComboBoxDataType.setSelectedItem(ami.getType());
            jTextFieldDataValue.setText(ami.getValue());

            removeDataFromList();
        }
    }

    private void removeDataFromList() {

        int index = jTableAmis.getSelectedRow();

        if(index != -1) {
            AmisTableModel model = (AmisTableModel) jTableAmis.getModel();
            model.removeElementAt(index);
            model.fireTableDataChanged();
            jTableAmis.setModel(model);

            if(model.getSize() != 0) {
                int selectedIndex = index < model.getSize() - 1 ? index : model.getSize() - 1;
                jTableAmis.setRowSelectionInterval(selectedIndex, selectedIndex);
            }
        }
    }

    private boolean verifyBeforeSendingData() {

        int patientIndex = jListRegistrars.getSelectedIndex();

        if(jTextFieldContextId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You need to specify a context id.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(jTextFieldEhrId.getText().isEmpty()) {
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

        if(!verifyBeforeSendingData())
            return;

        AmisTableModel dataModel = (AmisTableModel) jTableAmis.getModel();

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

        sendData(amis);
    }

    private void sendDataFromCsvFile() {

        if(!verifyBeforeSendingData())
            return;

        if(selectedCsvFile == null) {
            JOptionPane.showMessageDialog(this, "You need to select a CSV file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            HashMap<String, String> amis = mainController.getAmisFromCsvFile(selectedCsvFile);
            jFileChooserCsv.setSelectedFile(null);
            jTextFieldUploadFile.setText("");
            sendData(amis);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendData(HashMap<String, String> amis) {

        int patientIndex = jListRegistrars.getSelectedIndex();
        DefaultListModel<AndamanUserDTO> patientsModel = (DefaultListModel<AndamanUserDTO>) jListRegistrars.getModel();
        AndamanUserDTO patient = patientsModel.getElementAt(patientIndex);

        String contextId = jTextFieldContextId.getText();

        String amiContainerId = jTextFieldEhrId.getText();
        AMIContainer amiContainer = new AMIContainer(amiContainerId, amis);
        List<AMIContainer> amiContainers = new ArrayList<AMIContainer>();
        amiContainers.add(amiContainer);

        boolean alreadyMember = mainController.sendMedicalData(patient, amiContainers, contextId);

        if(!alreadyMember)
            JOptionPane.showMessageDialog(this,
                    "An invitation has been sent to the user.", "Info",
                    JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this,
                "The data has been successfully sent !", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButtonRegistrarSearchActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonPatientSearchActionPerformed
        searchPatient();
    }//GEN-LAST:event_jButtonPatientSearchActionPerformed

    private void jButtonDataAddActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonDataAddActionPerformed
        addData();
    }//GEN-LAST:event_jButtonDataAddActionPerformed

    private void jButtonDataRemoveActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonDataRemoveActionPerformed
        removeDataFromList();
    }//GEN-LAST:event_jButtonDataRemoveActionPerformed

    private void jButtonDataEditActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonDataEditActionPerformed
        editData();
    }//GEN-LAST:event_jButtonDataEditActionPerformed

    private void jButtonSendActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        sendDataFromGui();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonLogoutActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed

        // Clear all text fields, combo boxes and lists
        jTextFieldRegistrarKeyword.setText("");
        jTextFieldDataValue.setText("");
        jComboBoxDataType.setSelectedIndex(-1);

        DefaultListModel listModel;
        listModel = (DefaultListModel) jListRegistrars.getModel();
        listModel.clear();

        AmisTableModel tableModel;
        tableModel = (AmisTableModel) jTableAmis.getModel();
        tableModel.clear();

    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jTextFieldRegistrarKeywordKeyPressed(KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchPatient();
    }//GEN-LAST:event_jTextFieldPatientNameKeyPressed

    private void jTextFieldDataValueKeyPressed(KeyEvent evt) {//GEN-FIRST:event_jTextFieldDataValueKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            addData();
    }//GEN-LAST:event_jTextFieldDataValueKeyPressed

    private void jButtonUploadBrowseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonUploadBrowseActionPerformed
        int result = jFileChooserCsv.showDialog(this, "Open");

        if(result == JFileChooser.APPROVE_OPTION && jFileChooserCsv.getSelectedFile() != null) {
            selectedCsvFile = jFileChooserCsv.getSelectedFile();
            jTextFieldUploadFile.setText(selectedCsvFile.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonUploadBrowseActionPerformed

    private void jButtonUploadCsvActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonUploadCsvActionPerformed
        sendDataFromCsvFile();
    }//GEN-LAST:event_jButtonUploadCsvActionPerformed

    public void setTamiList() {
        DefaultComboBoxModel<TAMI> model = new DefaultComboBoxModel<TAMI>();
        TAMI[] tamis = mainController.getTamis();

        for(TAMI tami : tamis)
            model.addElement(tami);

        jComboBoxDataType.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButtonDataAdd;
    private JButton jButtonDataEdit;
    private JButton jButtonDataRemove;
    private JButton jButtonLogout;
    private JButton jButtonRegistrarSearch;
    private JButton jButtonSend;
    private JButton jButtonUploadBrowse;
    private JButton jButtonUploadCsv;
    private JComboBox<TAMI> jComboBoxDataType;
    private JFileChooser jFileChooserCsv;
    private JLabel jLabelContextId;
    private JLabel jLabelDataType;
    private JLabel jLabelDataValue;
    private JLabel jLabelEhrId;
    private JLabel jLabelRegistrarKeyword;
    private JLabel jLabelUploadFile;
    private JList<AndamanUserDTO> jListRegistrars;
    private JPanel jPanelContext;
    private JPanel jPanelData;
    private JPanel jPanelRegistrar;
    private JPanel jPanelUpload;
    private JScrollPane jScrollPaneData;
    private JScrollPane jScrollPaneRegistrars;
    private JTable jTableAmis;
    private JTextField jTextFieldContextId;
    private JTextField jTextFieldDataValue;
    private JTextField jTextFieldEhrId;
    private JTextField jTextFieldRegistrarKeyword;
    private JTextField jTextFieldUploadFile;
    // End of variables declaration//GEN-END:variables
}
