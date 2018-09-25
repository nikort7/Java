/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp.admin;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javastdapp.admin.MainForm;
import javastdapp.users.MainFormForUsers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javastdapp.MyConnection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Никита
 */
public class LoginForm extends javax.swing.JFrame {

    public static String username;
    public static String password;
    
    static LoginForm loginForm = new LoginForm();
    
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        lbl_U.setVisible(false);
        lbl_p.setVisible(false);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jButton_cancel = new javax.swing.JButton();
        jButton_LogIn = new javax.swing.JButton();
        lbl_U = new javax.swing.JLabel();
        lbl_p = new javax.swing.JLabel();
        jButtonRegistration = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Login Form");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Логин:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Пароль:");

        jTextField_Username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UsernameActionPerformed(evt);
            }
        });

        jPasswordField_Password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField_Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_PasswordKeyPressed(evt);
            }
        });

        jButton_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_cancel.setText("Отмена");
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        jButton_LogIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_LogIn.setText("Войти");
        jButton_LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogInActionPerformed(evt);
            }
        });

        lbl_U.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_U.setForeground(new java.awt.Color(255, 0, 0));
        lbl_U.setText("*");

        lbl_p.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_p.setForeground(new java.awt.Color(255, 0, 0));
        lbl_p.setText("*");

        jButtonRegistration.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonRegistration.setText("Зарегистрироваться");
        jButtonRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Username)
                            .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_U)
                            .addComponent(lbl_p))))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_U))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_p))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UsernameActionPerformed

    
    private void LogIn()
    {
        lbl_U.setVisible(false);
        lbl_p.setVisible(false);
        if(jTextField_Username.getText().equals(""))
        {
            lbl_U.setVisible(true);
        }
        if (String.valueOf(jPasswordField_Password.getPassword()).equals(""))
        {
            lbl_p.setVisible(true);
        }
        else {
            
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
            
            try {
                ps = con.prepareStatement("SELECT * FROM Userclube WHERE userphone = ? AND userpassword = ?");
                username = jTextField_Username.getText();
                password = String.valueOf(jPasswordField_Password.getPassword());
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if ("1".equals(username)){
                    if(rs.next())                
                    {
                        MainForm mf = new MainForm();
                        mf.setVisible(true);
                        mf.pack();
                        mf.setLocationRelativeTo(null);
                        mf.setExtendedState(JFrame.DISPOSE_ON_CLOSE/*.MAXIMIZED_BOTH*/);
                        mf.jLabel_Username.setText("Режим администратора.");

                        mf.jLabel_SportsmenCount.setText("Всего в базе спортсменов: " + Integer.toString(MyFunction.countData("Sportsmen"))+ " ");
                        mf.jLabel_TrainerCount.setText("Всего в базе тренеров: " + Integer.toString(MyFunction.countData("Trainer"))+ " ");
                        mf.jLabel_CompetitionCount.setText("Всего в базе соревнований: " + Integer.toString(MyFunction.countData("Competition"))+ " ");

                        this.dispose();

                    }else{
                        JOptionPane.showMessageDialog(null, "Неверный логин или пароль. Повторите ввод.");
                        //System.out.println("NO");
                    }
                }
                else {
                    if(rs.next())                
                    {
                        MainFormForUsers mf = new MainFormForUsers();
                        mf.setVisible(true);
                        mf.pack();
                        mf.setLocationRelativeTo(null);
                        mf.setExtendedState(JFrame.DISPOSE_ON_CLOSE/*.MAXIMIZED_BOTH*/);
                        mf.jLabel_Username.setText("Добро пожаловать в наш спортивный клуб, " + MyFunction.showFIO(username, password) + "");

                        mf.jLabel_SportsmenCount.setText("Всего в базе спортсменов: " + Integer.toString(MyFunction.countData("Sportsmen"))+ " ");
                        mf.jLabel_TrainerCount.setText("Всего в базе тренеров: " + Integer.toString(MyFunction.countData("Trainer"))+ " ");
                        mf.jLabel_CompetitionCount.setText("Всего в базе соревнований: " + Integer.toString(MyFunction.countData("Competition"))+ " ");


                        this.dispose();

                    }else{
                        JOptionPane.showMessageDialog(null, "Неверный логин или пароль. Повторите ввод.");
                        //System.out.println("NO");
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void jButton_LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogInActionPerformed
    
        LogIn();
        
    }//GEN-LAST:event_jButton_LogInActionPerformed

    
    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        
        System.exit(0);
        
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jButtonRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrationActionPerformed
        
        FormForAddUsers registr = new FormForAddUsers();
        this.dispose();
        
        registr.setVisible(true);
        registr.pack();
        //registr.setLocationRelativeTo(null);
        registr.setLocation(450, 50);
        registr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jButtonRegistrationActionPerformed

    private void jPasswordField_PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_PasswordKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            LogIn();
        
    }//GEN-LAST:event_jPasswordField_PasswordKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                loginForm.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistration;
    private javax.swing.JButton jButton_LogIn;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_Username;
    private javax.swing.JLabel lbl_U;
    private javax.swing.JLabel lbl_p;
    // End of variables declaration//GEN-END:variables
}