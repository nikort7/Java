/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp.admin;

import javastdapp.admin.MyFunction;
import javastdapp.admin.accessToInfo.AccessToInfoOfSportsmen;
import javastdapp.admin.accessToInfo.AccessToInfoOfTrainer;
import javastdapp.admin.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javastdapp.MyConnection;
import javastdapp.admin.MainForm;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Никита
 */
public class ToRegistrationToTrainer extends javax.swing.JFrame {

    
    LoginForm user = new LoginForm();
    AccessToInfoOfTrainer trn = new AccessToInfoOfTrainer();
    AccessToInfoOfSportsmen spt = new AccessToInfoOfSportsmen();
    int id_sport = 0;
    String trainer = "";
    //static String username;
    //static String password;
    
    /**
     * Creates new form ToRegistrationToTrainer
     */
    public ToRegistrationToTrainer() {
        initComponents();
        jComboBoxSportsmen.removeAllItems();

        String
                sport = "",
                sportsmen = "";

            
            spt.fillSportsmen(jComboBoxSportsmen);
            
            sportsmen = (String)jComboBoxSportsmen.getSelectedItem();
            
            jComboBoxSportsmen.addActionListener (new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    
                    String
                            sport = "",
                            sportsmen = "";

                    Connection con = MyConnection.getConnection();
                    Statement st;

                    try {
                       
                        st = con.createStatement();
                        ResultSet rsS = st.executeQuery("SELECT SPORT_SPORTSMEN.id_s, SPORTSMEN.FIO, SPORT.id_sport, SPORT.Name_sport\n" +
                                                        "FROM SPORTSMEN, SPORT, SPORT_SPORTSMEN \n" +
                                                        "WHERE SPORTSMEN.id_s = SPORT_SPORTSMEN.id_s AND SPORT_SPORTSMEN.id_sport = SPORT.id_sport");

                        sportsmen = (String)jComboBoxSportsmen.getSelectedItem();

                        while (rsS.next()){
                            if (sportsmen.equals(rsS.getString(2))){
                                sport = rsS.getString(4);
                                id_sport = rsS.getInt(3);
                            }
                        }
                        
                        jTextFieldSport.setText(sport);
                        for( ActionListener al : jComboBoxTrainer.getActionListeners() ) {
                            jComboBoxTrainer.removeActionListener( al );
                        }
                        jComboBoxTrainer.removeAllItems();
                        
                        trn.fillTrainer(jComboBoxTrainer, id_sport);
                        
                        jComboBoxTrainer.addActionListener (new ActionListener () {
                            public void actionPerformed(ActionEvent t) {
                                int id_trainer = 0;
                                try {
                                    
                                    ResultSet rsT = st.executeQuery("SELECT Trainer.id_t, Trainer.FIO, Trainer.id_sport, SPORT.Name_sport \n"
                                                                        + "FROM Trainer, SPORT \n"
                                                                        + "WHERE Trainer.id_sport = SPORT.id_sport and Trainer.id_sport = " + id_sport);

                                    
                                    trainer = (String)jComboBoxTrainer.getSelectedItem();
                                    
                                    while (rsT.next()){
                                        if (trainer.equals(rsT.getString(2))){
                                                id_trainer = rsT.getInt(1);
                                        }
                                    }

                                    jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID", "Фамилия И.О.", "Дата рождения", "Пол"}));
                                    trn.fillSportsmenOfTrainerJtable(jTable1, id_sport, id_trainer);

                                    //trainer = "";
                                } catch (SQLException ex) {
                                    Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        });
                        
                        
                        
                        sportsmen = "";
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(Level.SEVERE, null, ex);
                      }
                }
            });
            
            
                        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButtonCancelRegistration = new javax.swing.JButton();
        jButtonRegistration = new javax.swing.JButton();
        jComboBoxTrainer = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSport = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxSportsmen = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setText("Запись к тренеру");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Фамилия И.О.", "Дата рождения", "Пол"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Записать к тренеру:");

        jButtonCancelRegistration.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonCancelRegistration.setText("Отмена");
        jButtonCancelRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelRegistrationActionPerformed(evt);
            }
        });

        jButtonRegistration.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonRegistration.setText("Записаться");
        jButtonRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrationActionPerformed(evt);
            }
        });

        jComboBoxTrainer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Выберите -" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Ваша секция:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Спортсмен:");

        jComboBoxSportsmen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Выберите -" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButtonCancelRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRegistration)
                .addGap(72, 72, 72))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldSport)
                            .addComponent(jComboBoxSportsmen, 0, 215, Short.MAX_VALUE)
                            .addComponent(jComboBoxTrainer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxSportsmen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTrainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelRegistrationActionPerformed

        this.dispose();
        MainForm mf = new MainForm();
        mf.setVisible(true);
        mf.pack();
        mf.setLocationRelativeTo(null);
        mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainForm.jLabel_SportsmenCount.setText("Всего в базе спортсменов: " + Integer.toString(MyFunction.countData("Sportsmen"))+ " ");
        MainForm.jLabel_TrainerCount.setText("Всего в базе тренеров: " + Integer.toString(MyFunction.countData("Trainer"))+ " ");
        MainForm.jLabel_CompetitionCount.setText("Всего в базе соревнований: " + Integer.toString(MyFunction.countData("Competition"))+ " ");

    }//GEN-LAST:event_jButtonCancelRegistrationActionPerformed

    private void jButtonRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrationActionPerformed

        String FIOtrainer = (String)jComboBoxTrainer.getSelectedItem();
        String FIOsportsmen = (String)jComboBoxSportsmen.getSelectedItem();
  
        int
            id_trainer = 0,
            id_sportsmen = 0;

            Connection con = MyConnection.getConnection();
            Statement st;
            
            try {
                st = con.createStatement();
                ResultSet rsT = st.executeQuery("SELECT * FROM TRAINER");

                while (rsT.next()){
                    if ( FIOtrainer.equals(rsT.getString(2))){
                        id_trainer = rsT.getInt(1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                st = con.createStatement();
                ResultSet rsS = st.executeQuery("SELECT * FROM SPORTSMEN");

                while (rsS.next()){
                    if ( FIOsportsmen.equals(rsS.getString(2))){
                        id_sportsmen = rsS.getInt(1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            trn.AddSportsmenToTrainer(id_trainer, id_sportsmen);
            
            jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID", "Фамилия И.О.", "Дата рождения", "Пол"}));
            trn.fillSportsmenOfTrainerJtable(jTable1, id_sport, id_trainer);

            //this.dispose();

    }//GEN-LAST:event_jButtonRegistrationActionPerformed
   
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
            java.util.logging.Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToRegistrationToTrainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToRegistrationToTrainer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelRegistration;
    public static javax.swing.JButton jButtonRegistration;
    public javax.swing.JComboBox<String> jComboBoxSportsmen;
    public static javax.swing.JComboBox<String> jComboBoxTrainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextFieldSport;
    // End of variables declaration//GEN-END:variables
}
