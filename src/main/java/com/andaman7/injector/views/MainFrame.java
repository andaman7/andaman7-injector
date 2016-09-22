package com.andaman7.injector.views;

import com.andaman7.injector.controllers.CsvController;
import com.andaman7.injector.dtos.A7ItemDTO;
import com.andaman7.injector.exceptions.AuthenticationException;
import com.andaman7.injector.views.tablemodels.AmisTableModel;
import com.andaman7.injector.views.tablemodels.UsersTableModel;
import com.andaman7.injector.controllers.MainController;
import com.andaman7.server.api.pub.dto.user.AuthenticatedUserDTO;
import com.andaman7.server.api.pub.dto.user.UserDTO;
import com.andaman7.server.api.pub.dto.ehr.ResultSyncContentDTO;
import com.andaman7.injector.exceptions.AndamanException;
import com.andaman7.injector.exceptions.MissingTableModelException;
import com.andaman7.injector.exceptions.NoSelectedItemException;
import com.andaman7.injector.models.AMI;
import com.andaman7.injector.models.AMIContainer;
import com.andaman7.injector.models.Qualifier;
import com.andaman7.injector.models.types.MultivaluedTAMI;
import com.andaman7.injector.models.types.QualifierType;
import com.andaman7.injector.models.SelectionList;
import com.andaman7.injector.models.SelectionListItem;
import com.andaman7.injector.models.TamiGroup;
import com.andaman7.injector.models.types.TAMI;
import com.andaman7.injector.views.tablemodels.TableRowSelectionModel;
import com.andaman7.server.api.pub.dto.device.DeviceDTO;
import com.andaman7.server.api.pub.dto.invitations.InvitationDTO;
import com.andaman7.server.api.pub.dto.trusteduser.TrustedUserModificationDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.xml.sax.SAXException;


/**
 * The main GUI frame.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date: 02/02/2015.
 */
public class MainFrame extends JFrame implements ListSelectionListener {

    /**
     * The main controller.
     */
    private final MainController mainController;

    private final CsvController csvController;

    /**
     * The selected CSV file.
     */
    private File selectedCsvFile;
    
    /**
     * The model of the table containing the AMIs.
     */
    private final AmisTableModel amisTableModel;


    /**
     * Builds a main frame.
     *
     * @param mainController the main controller
     */
    public MainFrame(MainController mainController, CsvController csvController) {
        
        this.mainController = mainController;
        this.csvController = csvController;

        amisTableModel = new AmisTableModel();
        
        initComponents();

        // Wire the events sent by the AMIs management panel
        wireAmisManagementPanel();
        
        // Initialize the registrars table
        TableRowSelectionModel registrarsSelectionModel = new TableRowSelectionModel(jTableRegistrars);
        registrarsSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                
                TableRowSelectionModel model = (TableRowSelectionModel) e.getSource();
            
                if(!model.getValueIsAdjusting())
                    toggleRegistrarsRelatedButtons();
            }
        });
        jTableRegistrars.setSelectionModel(registrarsSelectionModel);

        invalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooserOpenCsv = new javax.swing.JFileChooser();
        jFileChooserSaveCsv = new javax.swing.JFileChooser();
        jButtonLogout = new javax.swing.JButton();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelForm = new javax.swing.JPanel();
        jPanelContext = new javax.swing.JPanel();
        jLabelContextId = new javax.swing.JLabel();
        jTextFieldContextId = new javax.swing.JTextField();
        jLabelEhrId = new javax.swing.JLabel();
        jTextFieldEhrId = new javax.swing.JTextField();
        jButtonContextNewEHR = new javax.swing.JButton();
        jButtonContextRegistrarsEHR = new javax.swing.JButton();
        jPanelRegistrar = new javax.swing.JPanel();
        jLabelRegistrarKeyword = new javax.swing.JLabel();
        jTextFieldRegistrarKeyword = new javax.swing.JTextField();
        jButtonRegistrarSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRegistrars = new javax.swing.JTable();
        manageAmisPanel = new ItemsManagementGroupPanel<AMI, SelectionListItem, TAMI>(mainController, amisTableModel);
        jButtonSend = new javax.swing.JButton();
        jPanelFile = new javax.swing.JPanel();
        jPanelFileUpload = new javax.swing.JPanel();
        jComboBoxUploadFormat = new javax.swing.JComboBox();
        jLabelUploadFormat = new javax.swing.JLabel();
        jButtonUploadBrowse = new javax.swing.JButton();
        jTextFieldUploadFile = new javax.swing.JTextField();
        jLabelUploadFile = new javax.swing.JLabel();
        jButtonUpload = new javax.swing.JButton();
        jPanelDownload = new javax.swing.JPanel();
        jButtonDownloadCheck = new javax.swing.JButton();

        jFileChooserOpenCsv.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jFileChooserOpenCsv.setDialogTitle("Select CSV file");
        jFileChooserOpenCsv.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        jFileChooserSaveCsv.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooserSaveCsv.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jFileChooserSaveCsv.setDialogTitle("Save CSV file");
        jFileChooserSaveCsv.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Andaman 7 - Injector");
        setMinimumSize(new java.awt.Dimension(1050, 625));
        setName(""); // NOI18N

        jButtonLogout.setText("Logout");
        jButtonLogout.setName("logout"); // NOI18N
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jPanelForm.setName("form"); // NOI18N

        jPanelContext.setBorder(javax.swing.BorderFactory.createTitledBorder("Context"));

        jLabelContextId.setText("Context ID");

        jTextFieldContextId.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabelEhrId.setText("EHR ID");

        jTextFieldEhrId.setPreferredSize(new java.awt.Dimension(300, 22));

        jButtonContextNewEHR.setText("New EHR");
        jButtonContextNewEHR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContextNewEHRActionPerformed(evt);
            }
        });

        jButtonContextRegistrarsEHR.setText("Registrar's EHR");
        jButtonContextRegistrarsEHR.setEnabled(false);
        jButtonContextRegistrarsEHR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContextRegistrarsEHRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelContextLayout = new javax.swing.GroupLayout(jPanelContext);
        jPanelContext.setLayout(jPanelContextLayout);
        jPanelContextLayout.setHorizontalGroup(
            jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelContextId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldContextId, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addComponent(jLabelEhrId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldEhrId, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonContextRegistrarsEHR, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonContextNewEHR)
                .addContainerGap())
        );
        jPanelContextLayout.setVerticalGroup(
            jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContextLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContextId)
                    .addComponent(jTextFieldContextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEhrId)
                    .addComponent(jTextFieldEhrId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonContextNewEHR)
                    .addComponent(jButtonContextRegistrarsEHR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelRegistrar.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar"));

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

        jTableRegistrars.setModel(new UsersTableModel());
        jTableRegistrars.setName("registrars"); // NOI18N
        jScrollPane1.setViewportView(jTableRegistrars);

        javax.swing.GroupLayout jPanelRegistrarLayout = new javax.swing.GroupLayout(jPanelRegistrar);
        jPanelRegistrar.setLayout(jPanelRegistrarLayout);
        jPanelRegistrarLayout.setHorizontalGroup(
            jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarLayout.createSequentialGroup()
                        .addComponent(jLabelRegistrarKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldRegistrarKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRegistrarSearch))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        manageAmisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("AMIs"));

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelContext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addComponent(jPanelRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageAmisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSend)))
                .addContainerGap())
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelContext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageAmisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(jPanelRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jButtonSend)
                .addContainerGap())
        );

        jTabbedPaneData.addTab("Form", jPanelForm);

        jPanelFile.setName("upload"); // NOI18N

        jPanelFileUpload.setBorder(javax.swing.BorderFactory.createTitledBorder("Upload"));

        jComboBoxUploadFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CSV" }));

        jLabelUploadFormat.setText("Format");

        jButtonUploadBrowse.setText("Browse");
        jButtonUploadBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadBrowseActionPerformed(evt);
            }
        });

        jTextFieldUploadFile.setEditable(false);
        jTextFieldUploadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldUploadFileMouseClicked(evt);
            }
        });

        jLabelUploadFile.setText("File");

        jButtonUpload.setText("Upload");
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFileUploadLayout = new javax.swing.GroupLayout(jPanelFileUpload);
        jPanelFileUpload.setLayout(jPanelFileUploadLayout);
        jPanelFileUploadLayout.setHorizontalGroup(
            jPanelFileUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUploadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldUploadFile, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUploadBrowse)
                .addGap(18, 18, 18)
                .addComponent(jLabelUploadFormat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxUploadFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButtonUpload)
                .addContainerGap())
        );
        jPanelFileUploadLayout.setVerticalGroup(
            jPanelFileUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileUploadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFileUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUploadFile)
                    .addComponent(jTextFieldUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUploadBrowse)
                    .addComponent(jLabelUploadFormat)
                    .addComponent(jComboBoxUploadFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpload))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDownload.setBorder(javax.swing.BorderFactory.createTitledBorder("Download"));

        jButtonDownloadCheck.setText("Check");
        jButtonDownloadCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDownloadCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDownloadLayout = new javax.swing.GroupLayout(jPanelDownload);
        jPanelDownload.setLayout(jPanelDownloadLayout);
        jPanelDownloadLayout.setHorizontalGroup(
            jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDownloadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDownloadCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDownloadLayout.setVerticalGroup(
            jPanelDownloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDownloadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDownloadCheck)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelFileLayout = new javax.swing.GroupLayout(jPanelFile);
        jPanelFile.setLayout(jPanelFileLayout);
        jPanelFileLayout.setHorizontalGroup(
            jPanelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFileUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelFileLayout.setVerticalGroup(
            jPanelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFileUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(379, Short.MAX_VALUE))
        );

        jTabbedPaneData.addTab("File", jPanelFile);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneData)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLogout)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Subscribe to all needed components of the panel that manages the AMIs.
     */
    private void wireAmisManagementPanel() {
        
        // Add button
        manageAmisPanel.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAmi();
            }
        });
        
        // Edit button
        manageAmisPanel.getEditButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                try {
                    AMI selectedAMI = (AMI) manageAmisPanel.getSelectedItem();
                    
                    List<Qualifier> qualifiers = selectedAMI.getQualifiers();
                    List<QualifierType> qualifierTypes = selectedAMI.getType().getQualifierTypes();
                    
                    mainController.showEditAmiDialog(selectedAMI);
                    manageAmisPanel.updateTable();
                    
                } catch (AndamanException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace(System.err);
                    JOptionPane.showMessageDialog(MainFrame.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Value text field
        manageAmisPanel.getValueTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    addAmi();
            }
        });
        
        // Values combo box
        manageAmisPanel.getValuesComboBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                 
               if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    addAmi();
            }
        });
    }
    
    /**
     * Sets the context id.
     *
     * @param contextId the context id
     */
    public void setContextId(String contextId) {
        jTextFieldContextId.setText(contextId);
    }
    
    /**
     * Initializes the combobox of TAMI groups with those that come from the XML file.
     * 
     * @throws IOException if there was an error with the connection to the server
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws AuthenticationException
     */
    public void setTamiGroupsList() throws IOException, SAXException, ParserConfigurationException, AndamanException {

        DefaultComboBoxModel<TAMI> model = new DefaultComboBoxModel<>();
        List<TamiGroup> tamiGroups = mainController.getTamiGroups();

        tamiGroups.sort(new Comparator<TamiGroup>() {

            @Override
            public int compare(TamiGroup group1, TamiGroup group2) {
                return group1.getName().compareTo(group2.getName());
            }
        });

        manageAmisPanel.setGroups(tamiGroups);
    }

    /**
     * Searches some Andaman users.
     */
    private void searchAndamanUsers() {
        
        String keyword = jTextFieldRegistrarKeyword.getText();

        if(!keyword.isEmpty()) {
            try {
                List<UserDTO> users = mainController.searchUsers(keyword);

                UsersTableModel model = (UsersTableModel) jTableRegistrars.getModel();
                model.clear();

                for (UserDTO user : users)
                    model.addItem(user);

                jTableRegistrars.setModel(model);
                model.fireTableDataChanged();
                
            } catch(Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Adds an AMI to the AMIs list.
     */
    private void addAmi() {

        TAMI type = (TAMI) manageAmisPanel.getSelectedType();
        Object value = manageAmisPanel.getSelectedValue();
        String strValue;
        
        if(type instanceof MultivaluedTAMI) {
            
            MultivaluedTAMI multiTAMI = (MultivaluedTAMI) type;
            SelectionListItem item = (SelectionListItem) value;
            strValue = item.getKey();
            
        } else {
            strValue = value.toString();
        }
        
        String id = UUID.randomUUID().toString();

        if(!strValue.isEmpty()) {
            try {
                AMI ami = new AMI(id, type, strValue, new Date());
                manageAmisPanel.addItem(ami);
                
            } catch (AndamanException e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Edits a selected AMI.
     */
    private void editAmi() throws NoSelectedItemException {
        
        try {
            AMI ami = (AMI) manageAmisPanel.getSelectedItem();
            manageAmisPanel.setSelectedType(ami.getType());
            
            if(ami.getType() instanceof MultivaluedTAMI) {
                
                MultivaluedTAMI multiTAMI = (MultivaluedTAMI) ami.getType();
                SelectionList selectionList = multiTAMI.getValues();
                SelectionListItem item = selectionList.getItem(ami.getValue());
                manageAmisPanel.setSelectedValue(true, item);
                
            } else {
                manageAmisPanel.setSelectedValue(false, ami.getValue());
            }
            
            manageAmisPanel.removeSelectedItem();
            
        } catch (MissingTableModelException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Verify the form before sending the AMIs.
     *
     * @return {@code true} if the data of the form are coherent, {@code false} otherwise
     */
    private boolean verifyBeforeSendingAmis() {

        int patientIndex = jTableRegistrars.getSelectedRow();

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

        DeviceDTO device = mainController.getCurrentDevice();
        if(device == null) {
            JOptionPane.showMessageDialog(this, "A device is required to inject data into an EHR.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Sends some AMIs from the list of the GUI.
     */
    private void sendAmisFromGui() {

        try {
            if(!verifyBeforeSendingAmis())
                return;
            
            if(amisTableModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "At least one data must be entered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Map<String, AMI> amis = new HashMap<String, AMI>();
            List<AMI> amisList = manageAmisPanel.getAllItems();
            
            for(AMI ami : amisList)
                amis.put(ami.getId(), ami);
            
            int patientIndex = jTableRegistrars.getSelectedRow();
            UsersTableModel destinationUserModel = (UsersTableModel) jTableRegistrars.getModel();
            UserDTO destinationUser = destinationUserModel.getValueAt(patientIndex);
            
            String contextId = jTextFieldContextId.getText();
            String amiContainerId = jTextFieldEhrId.getText();

            HashMap<String, String> contextMap = new HashMap<String, String>();
            contextMap.put(contextId, amiContainerId);
            
            List<String> destinationRegistrarsIds = new ArrayList<String>();
            destinationRegistrarsIds.add(destinationUser.getId());
            
            List<AMIContainer> amiContainersToSync = new ArrayList<AMIContainer>();
            AMIContainer amiContainer = new AMIContainer(amiContainerId, amis, contextMap, destinationRegistrarsIds);
            amiContainersToSync.add(amiContainer);
            
            sendAmis(amiContainersToSync);
            
        } catch (MissingTableModelException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sends some AMIs from a CSV file.
     */
    private void sendAmisFromCsvFile() {

        if(selectedCsvFile == null) {
            JOptionPane.showMessageDialog(this, "You need to select a CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<AMIContainer> amiContainersToSync = csvController.getDataFromCsvFile(selectedCsvFile);
            jFileChooserOpenCsv.setSelectedFile(null);
            jTextFieldUploadFile.setText("");
            sendAmis(amiContainersToSync);

        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sends some AMIs to the server.
     */
    private void sendAmis(List<AMIContainer> amiContainersToSync) {

        try {
            int invitationsSent = mainController.sendMedicalData(amiContainersToSync);

            if (invitationsSent != 0) {
                
                JOptionPane.showMessageDialog(this,
                        String.format("%d invitation(s) has been sent to the user(s).", invitationsSent),
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "The data has been successfully sent !", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch(Exception e) {
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Toggles buttons related to the (de)selection of a registrar in the registrars table.
     */
    private void toggleRegistrarsRelatedButtons() {
        
        boolean enabled = jTableRegistrars.getSelectedRow() != -1;
        
        jButtonContextRegistrarsEHR.setEnabled(enabled);
        
        if(!enabled)
            jTextFieldEhrId.setText("");
    }
    
    /**
     * Listens for a row to be (de)selected in a table.
     * 
     * @param e the list selection event
     */
    public void valueChanged(ListSelectionEvent e) {
        
        if(e.getSource() instanceof TableRowSelectionModel) {
            
            TableRowSelectionModel model = (TableRowSelectionModel) e.getSource();
            
            if(!model.getValueIsAdjusting()) {
                JTable table = model.getParent();
            
                if(table.getName().equals("registrars"))
                    toggleRegistrarsRelatedButtons();
            }
        }
    }
    
    public void clearForm() {
        
        // Clear all text fields, combo boxes and lists
        jTextFieldRegistrarKeyword.setText("");
        
        jTextFieldContextId.setText("");
        jTextFieldEhrId.setText("");
        jButtonContextRegistrarsEHR.setEnabled(false);
       
        UsersTableModel andamanUsersTableModel;
        andamanUsersTableModel = (UsersTableModel) jTableRegistrars.getModel();
        andamanUsersTableModel.clear();
        
        try {
            manageAmisPanel.clearData();
            
        } catch (MissingTableModelException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void browseFileToUpload() {
        
        int result = jFileChooserOpenCsv.showDialog(this, "Open");

        if(result == JFileChooser.APPROVE_OPTION && jFileChooserOpenCsv.getSelectedFile() != null) {
            selectedCsvFile = jFileChooserOpenCsv.getSelectedFile();
            jTextFieldUploadFile.setText(selectedCsvFile.getAbsolutePath());
        }
    }

    /**
     * Listens for the search registrars button to be clicked.
     *
     * @param evt the action event
     */
    private void jButtonRegistrarSearchActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonPatientSearchActionPerformed
        searchAndamanUsers();
    }//GEN-LAST:event_jButtonPatientSearchActionPerformed

    /**
     * Listens for the logout button to be clicked.
     *
     * @param evt the action event
     */
    private void jButtonLogoutActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        
        mainController.logout();
        clearForm();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    /**
     * Listens for the enter key to be pressed while focusing the registrar keyword text field.
     *
     * @param evt the key event
     */
    private void jTextFieldRegistrarKeywordKeyPressed(KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientNameKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchAndamanUsers();
    }//GEN-LAST:event_jTextFieldPatientNameKeyPressed

    /**
     * Listens for the browse button to be clicked.
     *
     * @param evt the key event
     */
    private void jButtonUploadBrowseActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonUploadBrowseActionPerformed
        browseFileToUpload();
    }//GEN-LAST:event_jButtonUploadBrowseActionPerformed

    private void jButtonContextNewEHRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContextNewEHRActionPerformed
        jTextFieldEhrId.setText(UUID.randomUUID().toString());
    }//GEN-LAST:event_jButtonContextNewEHRActionPerformed

    /**
     * Listens for the send button to be clicked.
     *
     * @param evt the action event
     */
    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
        sendAmisFromCsvFile();
    }//GEN-LAST:event_jButtonUploadActionPerformed

    private void jButtonContextRegistrarsEHRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContextRegistrarsEHRActionPerformed
        
        int rowIndex = jTableRegistrars.getSelectedRow();
        
        if(rowIndex != -1) {
            
            UsersTableModel model = (UsersTableModel) jTableRegistrars.getModel();
            UserDTO user = model.getValueAt(rowIndex);
            jTextFieldEhrId.setText(user.getId());
        }
    }//GEN-LAST:event_jButtonContextRegistrarsEHRActionPerformed

    private void jTextFieldUploadFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldUploadFileMouseClicked
        browseFileToUpload();
    }//GEN-LAST:event_jTextFieldUploadFileMouseClicked

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        sendAmisFromGui();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonDownloadCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDownloadCheckActionPerformed
        
        try {
            // Check for new invitations
            List<InvitationDTO> invitations = mainController.getInvitations();
            
            if(!invitations.isEmpty()) {
                for(InvitationDTO invitation : invitations) {

                    int result = JOptionPane.showConfirmDialog(
                            this,
                            String.format("Accept the invitation from %s %s (%s)", invitation.getFirstName(), invitation.getLastName(), invitation.getCountry()),
                            "New invitation",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    
                    Boolean response = null;
                    
                    switch(result) {
                        case JOptionPane.YES_OPTION:
                            response = true;
                            break;
                        case JOptionPane.NO_OPTION:
                            response = false;
                            break;
                    }
                    
                    if(response != null)
                        mainController.setCommunityInvitationAcceptance(invitation.getId(), new TrustedUserModificationDTO(response));
                }
            }
            
            // Check for new data
            AuthenticatedUserDTO currentUser = mainController.getCurrentUser();
            String deviceId = mainController.getCurrentDevice().getId();
            List<ResultSyncContentDTO> medicalRecords = mainController.getMedicalDataInQueue(currentUser.getId(), deviceId);

            if(medicalRecords.isEmpty())
                JOptionPane.showMessageDialog(this, "No medical record in queue.");

            ObjectMapper mapper = new ObjectMapper();
            List<String> medicalRecordIds  = new ArrayList<>();

            for(ResultSyncContentDTO medicalRecord : medicalRecords) {

                List<A7ItemDTO> amiContainerDTOs = Arrays.asList(mapper.readValue(medicalRecord.getA7Items(), A7ItemDTO[].class));
                JOptionPane.showMessageDialog(this, mapper.writeValueAsString(amiContainerDTOs));

                int result = jFileChooserSaveCsv.showDialog(this, "Save");

                if(result == JFileChooser.APPROVE_OPTION && jFileChooserSaveCsv.getSelectedFile() != null) {

                    File selectedFile = jFileChooserSaveCsv.getSelectedFile();
                    csvController.generateCsvFile(medicalRecord, selectedFile);
                    medicalRecordIds.add(medicalRecord.getMedicalRecordId());
                }
            }

            mainController.acknowledgeMedicalData(medicalRecordIds.toArray(new String[medicalRecordIds.size()]));
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, String.format("[%s] %s", e.getClass().getName(), e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDownloadCheckActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContextNewEHR;
    private javax.swing.JButton jButtonContextRegistrarsEHR;
    private javax.swing.JButton jButtonDownloadCheck;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonRegistrarSearch;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JButton jButtonUploadBrowse;
    private javax.swing.JComboBox jComboBoxUploadFormat;
    private javax.swing.JFileChooser jFileChooserOpenCsv;
    private javax.swing.JFileChooser jFileChooserSaveCsv;
    private javax.swing.JLabel jLabelContextId;
    private javax.swing.JLabel jLabelEhrId;
    private javax.swing.JLabel jLabelRegistrarKeyword;
    private javax.swing.JLabel jLabelUploadFile;
    private javax.swing.JLabel jLabelUploadFormat;
    private javax.swing.JPanel jPanelContext;
    private javax.swing.JPanel jPanelDownload;
    private javax.swing.JPanel jPanelFile;
    private javax.swing.JPanel jPanelFileUpload;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPanel jPanelRegistrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private javax.swing.JTable jTableRegistrars;
    private javax.swing.JTextField jTextFieldContextId;
    private javax.swing.JTextField jTextFieldEhrId;
    private javax.swing.JTextField jTextFieldRegistrarKeyword;
    private javax.swing.JTextField jTextFieldUploadFile;
    private ItemsManagementGroupPanel manageAmisPanel;
    // End of variables declaration//GEN-END:variables
}
