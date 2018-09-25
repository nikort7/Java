
package javastdapp.admin.accessToInfo;

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
import javastdapp.admin.businessLayer.Sportsmen;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class AccessToInfoOfSportsmen {
    public void insertDeleteUpdate(char operation, Integer id_s, Sportsmen sportsmen)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        // i for insert
        if (operation == 'i')
        {
            try {
                //ps = con.prepareStatement("INSERT INTO AccessToInfoOfSportsmen (FIO, userphone, Date_Birthday, Sex, id_country) VALUES(?, ?, ?, ?, ?)");
                //ps = con.prepareStatement("CALL ADDSPORTSMEN (?, ?, ?, ?, ?, ?)");
                CallableStatement cs = con.prepareCall("{CALL ADDSPORTSMEN (?, ?, ?, ?, ?, ?, ?)}");
                
                cs.setString(1, sportsmen.getUserphone());
                cs.setString(2, sportsmen.getPassword());
                cs.setString(3, sportsmen.getFIO());
                cs.setString(4, sportsmen.getBDate());
                cs.setString(5, sportsmen.getSex());
                cs.setInt(6, sportsmen.getCountry());
                cs.setInt(7, sportsmen.getSport());
                
                cs.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Новый спортсмен добавлен.");
                JOptionPane.showMessageDialog(null, "Новый спортсмен добавлен.", "Добавление спортсменов.", JOptionPane.INFORMATION_MESSAGE);
                

                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'u') //u for update
        {
            try {
                ps = con.prepareStatement("UPDATE Sportsmen SET FIO = ?, Date_Birthday = ?,  Sex = ?, id_country = ? WHERE id_s = ?");
                //CallableStatement cs = con.prepareCall("{CALL UPDATESPORTSMEN (?, ?, ?, ?, ?, ?1)}");
                
                ps.setString(1, sportsmen.getFIO());
                ps.setString(2, sportsmen.getBDate());
                ps.setString(3, sportsmen.getSex());
                ps.setInt(4, sportsmen.getCountry());
                ps.setInt(5, id_s);
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Данные о спортсмене обновлены.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (operation == 'd') //u for delete
        {
            try {
                
                CallableStatement cs = con.prepareCall("{CALL DELETESPORTSMEN (?)}");
                                
                cs.setInt(1, id_s);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Данные о спортсмене удалены.");
                
            } catch (SQLException ex) {
                Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillSportsmenJtable(JTable table, String valueToSearch)
    {
         Connection con = MyConnection.getConnection();
         PreparedStatement ps;
         ResultSet rs, rs1;
        try {
           //ps = con.prepareStatement("SELECT * FROM SPORTSMEN WHERE concat(concat(concat(FIO, Date_Birthday), Sex), id_country) LIKE ?");
           ps = con.prepareStatement("SELECT user_id, FIO, userphone, Date_Birthday, Sex, id_country, id_sport FROM SPORTSMEN, Userclube, SPORT_SPORTSMEN \n " +
                   "WHERE Userclube.user_id = SPORTSMEN.id_s AND Userclube.user_id = SPORT_SPORTSMEN.id_s \n" +
                   "AND concat(concat(concat(concat(concat(FIO, userphone), Date_Birthday), Sex), id_country), id_sport) LIKE ? \n" + 
                   "ORDER BY FIO");
           
           
            ps.setString(1, "%"+valueToSearch+"%");
                        
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[7];
                row[0] = rs.getInt(1);      //id_s
                row[1] = rs.getString(2);   //FIO
                row[2] = rs.getString(3);   //userphone
                row[3] = rs.getString(4);   //Date_Birthday
                row[4] = rs.getString(5);   //Sex
                
                int 
                        id_country = rs.getInt(6),
                        id_sport = rs.getInt(7);
            
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
                    rs1 = st.executeQuery("SELECT * FROM SPORT");

                    while (rs1.next()){
                        if ( id_sport == rs1.getInt(1)){
                            row[6] = rs1.getString(2);   //id_sport
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void fillSportsmen (JComboBox jCombo)
    {
        Connection con = MyConnection.getConnection();
        jCombo.addItem("- Выберите -");
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM SPORTSMEN");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                jCombo.addItem(rs.getString(2));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccessToInfoOfSportsmen.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

}
