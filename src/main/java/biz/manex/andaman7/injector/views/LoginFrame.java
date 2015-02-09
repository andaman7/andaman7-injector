/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.views;

import biz.manex.andaman7.injector.controllers.MainController;
import biz.manex.andaman7.injector.models.Settings;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Pierre-Yves
 */
public class LoginFrame extends javax.swing.JFrame {
    
    private final MainController mainController;
    private Settings settings;

    /**
     * Creates new form LoginFrame
     * @param mainController the controller that will control the frame
     * @param loginListener the listener that will listen for actions on the
     *                      login button
     */
    public LoginFrame(MainController mainController, ActionListener loginListener) {
        initComponents();
        
        this.mainController = mainController;
        this.jButtonLogin.addActionListener(loginListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSettingsUser = new javax.swing.JPanel();
        jLabelSettingsUsername = new javax.swing.JLabel();
        jTextFieldSettingsUsername = new javax.swing.JTextField();
        jLabelSettingsPassword = new javax.swing.JLabel();
        jPasswordFieldSettingsPassword = new javax.swing.JPasswordField();
        jLabelSettingTestConnection = new javax.swing.JLabel();
        jPanelSettingsServer = new javax.swing.JPanel();
        jLabelSettingsServerHostname = new javax.swing.JLabel();
        jTextFieldSettingsServerHostname = new javax.swing.JTextField();
        jLabelSettingsServerPort = new javax.swing.JLabel();
        jTextFieldSettingsServerPort = new javax.swing.JTextField();
        jLabelSettingsApiKey = new javax.swing.JLabel();
        jTextFieldSettingsApiKey = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Andaman7 - Login");

        jPanelSettingsUser.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));

        jLabelSettingsUsername.setText("Username");

        jTextFieldSettingsUsername.setText("pyderbaix@student.ulg.ac.be");
        jTextFieldSettingsUsername.setInputVerifier(new NoEmptyInputVerifier());
        jTextFieldSettingsUsername.setName("username"); // NOI18N
        jTextFieldSettingsUsername.setPreferredSize(new java.awt.Dimension(125, 22));

        jLabelSettingsPassword.setText("Password");

        jPasswordFieldSettingsPassword.setText("aaaaaa");
        jPasswordFieldSettingsPassword.setInputVerifier(new NoEmptyInputVerifier());
        jPasswordFieldSettingsPassword.setName("password"); // NOI18N
        jPasswordFieldSettingsPassword.setPreferredSize(new java.awt.Dimension(125, 22));

        javax.swing.GroupLayout jPanelSettingsUserLayout = new javax.swing.GroupLayout(jPanelSettingsUser);
        jPanelSettingsUser.setLayout(jPanelSettingsUserLayout);
        jPanelSettingsUserLayout.setHorizontalGroup(
            jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSettingsUserLayout.createSequentialGroup()
                        .addGroup(jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSettingsUsername)
                            .addComponent(jLabelSettingsPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSettingsUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldSettingsPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelSettingsUserLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabelSettingTestConnection)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSettingsUserLayout.setVerticalGroup(
            jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsUserLayout.createSequentialGroup()
                .addGroup(jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSettingsUsername)
                    .addComponent(jTextFieldSettingsUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSettingsUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSettingsPassword)
                    .addComponent(jPasswordFieldSettingsPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSettingTestConnection)
                .addContainerGap())
        );

        jPanelSettingsServer.setBorder(javax.swing.BorderFactory.createTitledBorder("Server"));

        jLabelSettingsServerHostname.setText("Server hostname");

        jTextFieldSettingsServerHostname.setText("localhost");
        jTextFieldSettingsServerHostname.setInputVerifier(new NoEmptyInputVerifier());
        jTextFieldSettingsServerHostname.setName("server hostname"); // NOI18N

        jLabelSettingsServerPort.setText("Server port");

        jTextFieldSettingsServerPort.setText("8080");
        jTextFieldSettingsServerPort.setInputVerifier(new NoEmptyInputVerifier());
        jTextFieldSettingsServerPort.setName("server port"); // NOI18N

        jLabelSettingsApiKey.setText("API key");

        jTextFieldSettingsApiKey.setText("2C949434-20FF-4636-BA96-B7C0CAD42612");
        jTextFieldSettingsApiKey.setInputVerifier(new NoEmptyInputVerifier());
        jTextFieldSettingsApiKey.setName("API key"); // NOI18N

        javax.swing.GroupLayout jPanelSettingsServerLayout = new javax.swing.GroupLayout(jPanelSettingsServer);
        jPanelSettingsServer.setLayout(jPanelSettingsServerLayout);
        jPanelSettingsServerLayout.setHorizontalGroup(
            jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSettingsServerHostname)
                    .addComponent(jLabelSettingsApiKey))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSettingsApiKey, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jTextFieldSettingsServerHostname, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabelSettingsServerPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSettingsServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelSettingsServerLayout.setVerticalGroup(
            jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsServerLayout.createSequentialGroup()
                .addGroup(jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSettingsServerHostname)
                    .addComponent(jTextFieldSettingsServerHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSettingsServerPort)
                    .addComponent(jTextFieldSettingsServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSettingsServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSettingsApiKey)
                    .addComponent(jTextFieldSettingsApiKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonLogin.setText("Login");
        jButtonLogin.setName("login"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSettingsUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSettingsServer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonLogin)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSettingsServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelSettingsUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jButtonLogin)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean verifySettings() {
        
        boolean state = verifyForm(jPanelSettingsServer);
        
        if(state)
            state = verifyForm(jPanelSettingsUser);
        
        return state;
    }
    
    private boolean verifyForm(JComponent form) {
        
        for(Component item : Arrays.asList(form.getComponents())) {
            
            JComponent component = (JComponent) item;
            InputVerifier iv = component.getInputVerifier();
            
            if(iv != null && !iv.verify(component)) {
                JOptionPane.showMessageDialog(this,
                        "The " + item.getName() + " must not be empty. Check the settings tab.",
                        "Missing setting", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    public Settings getSettings() {

        if(verifySettings()) {
            
            String apiKey = jTextFieldSettingsApiKey.getText();
            String serverHostname = jTextFieldSettingsServerHostname.getText();
            String serverPort = jTextFieldSettingsServerPort.getText();
            String username = jTextFieldSettingsUsername.getText();
            String password = new String(jPasswordFieldSettingsPassword.getPassword());
            
            Settings settings = new Settings();
            settings.setApiKey(apiKey);
            settings.setServerHostname(serverHostname);
            settings.setServerPort(serverPort);
            settings.setUsername(username);
            settings.setPassword(password);
            
            return settings;
        }
        
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelSettingTestConnection;
    private javax.swing.JLabel jLabelSettingsApiKey;
    private javax.swing.JLabel jLabelSettingsPassword;
    private javax.swing.JLabel jLabelSettingsServerHostname;
    private javax.swing.JLabel jLabelSettingsServerPort;
    private javax.swing.JLabel jLabelSettingsUsername;
    private javax.swing.JPanel jPanelSettingsServer;
    private javax.swing.JPanel jPanelSettingsUser;
    private javax.swing.JPasswordField jPasswordFieldSettingsPassword;
    private javax.swing.JTextField jTextFieldSettingsApiKey;
    private javax.swing.JTextField jTextFieldSettingsServerHostname;
    private javax.swing.JTextField jTextFieldSettingsServerPort;
    private javax.swing.JTextField jTextFieldSettingsUsername;
    // End of variables declaration//GEN-END:variables
}
