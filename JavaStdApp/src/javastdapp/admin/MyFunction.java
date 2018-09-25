package javastdapp.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javastdapp.MyConnection;


public class MyFunction {
    
    public static int countData(String tableName)
    {
        int total = 0;
        Connection con = MyConnection.getConnection();
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM " + tableName);
            while (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
    
    public static String showFIO (String userphone_, String password_) throws SQLException
    {
        String total = "";
        Connection con = MyConnection.getConnection();
        Statement st = con.createStatement();
        
        PreparedStatement ps = con.prepareStatement("SELECT user_id FROM Userclube WHERE userphone = ? and userpassword = ?");
        ps.setString(1, userphone_);
        ps.setString(2, password_);
        ResultSet
                id = ps.executeQuery(),
                rT,
                rS;
        
        
        try {
            st = con.createStatement();
            ps = con.prepareStatement("SELECT id_t, FIO FROM Trainer");
            rT = ps.executeQuery();
            
            
            ps = con.prepareStatement("SELECT id_s, FIO FROM Sportsmen");
            rS = ps.executeQuery();
            
            
            while (id.next()){
                
                while (rT.next()){
                    if (id.getInt(1) == rT.getInt(1))
                        total = rT.getString(2);
                }
                while (rS.next()){
                    if (id.getInt(1) == rS.getInt(1))
                        total = rS.getString(2);
                }
            }
            System.out.println(total);
        } catch (SQLException ex) {
            Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return total;
    }
}
