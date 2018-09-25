/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp.admin;

import javastdapp.admin.accessToInfo.AccessToInfoOfTrainer;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javastdapp.MyConnection;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Никита
 */
public class Competition {
    
    public void insertDeleteUpdate(char operation, Integer id_comp, Integer id_sport, String nameCompetition, Integer id_country, String placeCompetition, String dateComp)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps, psC;
        
        // i for insert
        if (operation == 'i')
        {
            try {
                ps = con.prepareStatement("INSERT INTO Competition (Name_comp, Place_comp, Date_comp, id_sport) VALUES(?, ?, ?, ?)");
                
                ps.setString(1, nameCompetition);
                ps.setString(2, placeCompetition);
                ps.setString(3, dateComp);
                ps.setInt(4, id_sport);
                //Logger.getLogger(Competition.class.getName()).log(Level.INFO, "{0} {1} {2}", new Object[]{nameCompetition, placeCompetition, dateComp});
                ps.executeUpdate();
                
                int lastID = 0;
                
                ps = con.prepareStatement("SELECT MAX(id_comp) AS lastID FROM Competition");
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()){
                    lastID = rs.getInt(1);
                }
                
                psC = con.prepareStatement("INSERT INTO Competition_Country (id_comp, id_country) VALUES(?, ?)");
                
                psC.setInt(1, lastID);
                psC.setInt(2, id_country);
                
                psC.executeUpdate();
                
                con.close();
                JOptionPane.showMessageDialog(null, "Информация добавлена.");
                
            } catch (SQLException ex) {
                Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'u') //u for update
        {
            try {
//                ps = con.prepareStatement("UPDATE AccessToInfoOfTrainer SET FIO = ?, Date_Birthday = ?,  Sex = ?, id_country = ? WHERE id_t = ?");
//                
//                ps.setString(1, FIO);
//                ps.setString(2, bdate);
//                ps.setString(3, sex);
//                ps.setInt(4, country);
//                ps.setInt(5, id_t);

                int lastID = 0;
                
                ps = con.prepareStatement("SELECT MAX(id_comp) AS lastID FROM Competition");
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()){
                    lastID = rs.getInt(1);
                }

                ps = con.prepareStatement("UPDATE Competition SET Name_comp = ?, Place_comp = ?, Date_comp = ?, id_sport = ? WHERE id_comp = ?");
                
                ps.setString(1, nameCompetition);
                ps.setString(2, placeCompetition);
                ps.setString(3, dateComp);
                ps.setInt(4, id_sport);
                ps.setInt(5, lastID);
                
                ps.executeUpdate();
                
                psC = con.prepareStatement("UPDATE Competition_Country SET id_country = ? WHERE id_comp = ?");
                
                psC.setInt(1, id_country);
                psC.setInt(2, lastID);
                
                psC.executeUpdate();
                
                con.close();
                JOptionPane.showMessageDialog(null, "Информация обновлена.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'd') //u for delete
        {
            try {
                
                ps = con.prepareStatement("DELETE FROM COMPETITION WHERE id_comp = " + id_comp);
                ps.executeUpdate();
                
                ps = con.prepareStatement("DELETE FROM Competition_Country WHERE id_comp = " + id_comp);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Информация удалена.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillCompetitionJtable(JTable table, String valueToSearch)
    {
         Connection con = MyConnection.getConnection();
         PreparedStatement ps;
         ResultSet rs, rs1, rs2, rs3;
         try {
           
            ps = con.prepareStatement("SELECT Comp.id_comp, Comp.id_sport, Comp.Name_comp, CC.id_country, Comp.Place_comp, Comp.Date_comp \n" +
                                      "FROM Competition Comp, Competition_Country CC\n" +
                                      "WHERE Comp.id_comp = CC.id_comp\n" +
                                      "AND concat(concat(concat(concat(Comp.Name_comp, Comp.Place_comp), Comp.Date_comp), Comp.id_sport), CC.id_country) LIKE ? \n" + 
                                      "ORDER BY Comp.Name_comp");
           
            ps.setString(1, "%"+valueToSearch+"%");
                        
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[6];
                row[0] = rs.getInt(1);      //id_comp
                row[2] = rs.getString(3);   //Name_comp
                row[4] = rs.getString(5);   //Place_comp
                row[5] = rs.getString(6);   //Date_comp
                
                int 
                        id_sport = rs.getInt(2),      //id_sport
                        id_country = rs.getInt(4);    //id_country;
            
                Statement st;
                                
                try {
                    st = con.createStatement();
                    rs2 = st.executeQuery("SELECT * FROM SPORT");

                    while (rs2.next()){
                        if ( id_sport == rs2.getInt(1)){
                            row[1] = rs2.getString(2);   //id_sport
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    st = con.createStatement();
                    rs1 = st.executeQuery("SELECT * FROM COUNTRY");

                    while (rs1.next()){
                        if ( id_country == rs1.getInt(1)){
                            row[3] = rs1.getString(2);   //id_country
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void fillNameCompetition (JComboBox jCombo)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM COMPETITION");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public int fillIDforDelete (String Name_comp)
    {
        int id_comp = 0;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT id_comp FROM COMPETITION WHERE Name_comp = ?");
            ps.setString(1, Name_comp);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                id_comp = rs.getInt(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Competition.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_comp;
    }
    
}
