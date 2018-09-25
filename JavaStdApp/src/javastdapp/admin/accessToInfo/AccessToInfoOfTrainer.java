/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp.admin.accessToInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javastdapp.MyConnection;
import javastdapp.admin.MyFunction;
import javastdapp.admin.businessLayer.Trainer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Никита
 */
public class AccessToInfoOfTrainer {
    
    public void insertDeleteUpdate(char operation, Integer id_t, Trainer trainer)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        // i for insert
        if (operation == 'i')
        {
            try {
                CallableStatement cs = con.prepareCall("{CALL ADDTRAINER (?, ?, ?, ?, ?, ?, ?, ?)}");
                
//                cs.setString(1, userphone);
//                cs.setString(2, password);
//                cs.setString(3, FIO);
//                cs.setString(4, bdate);
//                cs.setString(5, sex);
//                cs.setInt(6, country);
//                cs.setInt(7, ranks);
//                cs.setInt(8, sport);
                cs.setString(1, trainer.getUserphone());
                cs.setString(2, trainer.getPassword());
                cs.setString(3, trainer.getFIO());
                cs.setString(4, trainer.getBDate());
                cs.setString(5, trainer.getSex());
                cs.setInt(6, trainer.getCountry());
                cs.setInt(7, trainer.getRank());
                cs.setInt(8, trainer.getSport());
                //Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.INFO, "{0} {1} {2}", new Object[]{userphone, sport, ranks});
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Новый тренер добавлен.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'u') //u for update
        {
            try {
                ps = con.prepareStatement("UPDATE Trainer SET FIO = ?, Date_Birthday = ?,  Sex = ?, id_country = ? WHERE id_t = ?");
                
                ps.setString(1, trainer.getFIO());
                ps.setString(2, trainer.getBDate());
                ps.setString(3, trainer.getSex());
                ps.setInt(4, trainer.getCountry());
                ps.setInt(5, id_t);
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Данные о тренере обновлены.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'd') //u for delete
        {
            try {
                
                CallableStatement cs = con.prepareCall("{CALL DELETETRAINER (?)}");
                                
                cs.setInt(1, id_t);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Данные о спортсмене удалены.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void fillTrainerJtable(JTable table, String valueToSearch)
    {
         Connection con = MyConnection.getConnection();
         PreparedStatement ps;
         ResultSet rs, rs1, rs2, rs3;
         try {
           
           ps = con.prepareStatement("SELECT user_id, FIO, userphone, Date_Birthday, Sex, id_country, id_sport, id_zv FROM Trainer, Userclube \n " + 
                                     "WHERE Userclube.user_id = Trainer.id_t \n " +
                                     "AND concat(concat(concat(concat(concat(concat(FIO, userphone), Date_Birthday), Sex), id_country), id_sport), id_zv) LIKE ? \n " + 
                                     "ORDER BY FIO");
           
            ps.setString(1, "%"+valueToSearch+"%");
                        
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[8];
                row[0] = rs.getInt(1);      //id_s
                row[1] = rs.getString(2);   //FIO
                row[2] = rs.getString(3);   //userphone
                row[3] = rs.getString(4);   //Date_Birthday
                row[4] = rs.getString(5);   //Sex
                
                int 
                        id_country = rs.getInt(6),  //country
                        id_sport = rs.getInt(7),    //id_sport
                        id_zv = rs.getInt(8);       //id_zv 
            
                Statement st;
                try {
                    st = con.createStatement();
                    rs1 = st.executeQuery("SELECT * FROM COUNTRY");

                    while (rs1.next()){
                        if ( id_country == rs1.getInt(1)){
                            row[5] = rs1.getString(2);   //id_country
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    st = con.createStatement();
                    rs2 = st.executeQuery("SELECT * FROM SPORT");

                    while (rs2.next()){
                        if ( id_sport == rs2.getInt(1)){
                            row[6] = rs2.getString(2);   //id_sport
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                st = con.createStatement();
                rs3 = st.executeQuery("SELECT * FROM RANKS");

                while (rs3.next()){
                    if ( id_zv == rs3.getInt(1)){
                        row[7] = rs3.getString(2);   // id_zv
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void fillSport (JComboBox jCombo)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM SPORT");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void fillRanks (JComboBox jCombo)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM RANKS");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void fillCountry (JComboBox jCombo)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM COUNTRY");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public void fillTrainer (JComboBox jCombo, int id_sport)
    {        
        Connection con = MyConnection.getConnection();
        jCombo.addItem("");
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT id_t, FIO, Sport.id_sport, Name_sport from Trainer, Sport\n" +
                                      "WHERE Trainer.id_sport = Sport.id_sport AND Sport.id_sport = ?");
            ps.setString(1, String.valueOf(id_sport));
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                if (id_sport == rs.getInt(3))
                    jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void fillSportsmenOfTrainerJtable(JTable table, int id_sport, int id_trainer)
    {
         Connection con = MyConnection.getConnection();
         PreparedStatement ps;
         ResultSet rs;
        try {
            
            
//            ps = con.prepareStatement("select AccessToInfoOfSportsmen.id_s, AccessToInfoOfSportsmen.FIO, AccessToInfoOfSportsmen.Date_Birthday, AccessToInfoOfSportsmen.sex from AccessToInfoOfSportsmen, Sport_Sportsmen, Sport \n" +
//                                     "where AccessToInfoOfSportsmen.id_s = Sport_Sportsmen.id_s and Sport_Sportsmen.id_sport = Sport.id_sport \n" + 
//                                     "and Sport_Sportsmen.id_sport = ?");
            
            ps = con.prepareStatement("select Sportsmen.id_s, Sportsmen.FIO, Sportsmen.Date_Birthday, Sportsmen.sex, Trainer.FIO from Sportsmen, Sport_Sportsmen, Sport, Trainer, Trainer_Sportsmen \n"
                + "where Sportsmen.id_s = Trainer_Sportsmen.id_s and Sportsmen.id_s = Sport_Sportsmen.id_s and Sport_Sportsmen.id_sport = Sport.id_sport \n"
                + "and Trainer.id_t = Trainer_Sportsmen.id_t \n"
                + "and Sport_Sportsmen.id_sport = " + id_sport + " and Trainer.id_t = " + id_trainer);
                                    
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[7];
                row[0] = rs.getInt(1);      //id_t
                row[1] = rs.getString(2);   //FIO
                row[2] = rs.getString(3);   //sex
                row[3] = rs.getString(4);   //Name_sport
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
     public void AddSportsmenToTrainer(int id_t, int id_s)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        
            try {
                ps = con.prepareStatement("INSERT INTO Trainer_Sportsmen (id_t, id_s) VALUES(?, ?)");
                
                ps.setInt(1, id_t);
                ps.setInt(2, id_s);
                
                ps.executeUpdate();
                
                String FIOtrainer = "";
                String FIOsportsmen = "";
                
                try {
                    Statement st = con.createStatement();
                    ResultSet rsT = st.executeQuery("SELECT * FROM TRAINER");

                    while (rsT.next()){
                        if ( id_t == rsT.getInt(1)){
                            FIOtrainer = rsT.getString(2);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
                  }
                
                
                try {
                    Statement st = con.createStatement();
                    ResultSet rsS = st.executeQuery("SELECT * FROM SPORTSMEN");

                    while (rsS.next()){
                        if ( id_s == rsS.getInt(1)){
                            FIOsportsmen = rsS.getString(2);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
                  }
                
                
                JOptionPane.showMessageDialog(null, "Спортсмен " + FIOsportsmen + " записан к тренеру " + FIOtrainer + ".");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
}
