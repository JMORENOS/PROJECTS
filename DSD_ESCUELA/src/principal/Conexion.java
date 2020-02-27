
package principal;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String servidor="jdbc:mysql://localhost/usuarios";
    private static final String user="root";
    private static final String pass="Luna77_13j";
    private static final String driver="com.mysql.jdbc.Driver";
    private static Connection conexion;
    
    public Conexion(){
        
        try{
            Class.forName(driver);
            conexion=(Connection) DriverManager.getConnection(servidor, user, pass);
            System.out.println("La Conexion fue exitosa...");    
            //System.out.println("ESCOM-IPN...");  
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("La Conexion fue fallida");
            
        }
    }
    public Connection getConnection(){
        return conexion;
    }
}
