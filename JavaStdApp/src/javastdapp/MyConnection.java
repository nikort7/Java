
package javastdapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;




public class MyConnection {
    
    public static Connection getConnection()
    {
        Connection con = null;
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "myuser1", "mypassword");
            
            /*
            Statement st = con.createStatement();
            String sql = "select * from Userclube";
            
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) );
                        
            con.close();
            */
            
            
        } catch( Exception ex){
            System.out.println(ex);
        }
        
        return con;
    }
}
