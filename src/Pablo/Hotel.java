
package Pablo;
// @author Juan Pablo
import java.sql.*;
import javax.swing.JOptionPane;
public class Hotel {
    Connection cone=null;
    public Connection conexion(){
        try {
            //asdasdasd
            //puto el q lo lea
            //asdasdd
            //LISTO MIJO YA NO LO MOLESTO MAS :D
            // definitivamente puto el que lo lea
            //cargar nuestro driver
            Class.forName("com.mysql.jdbc.Driver");
            cone=DriverManager.getConnection ("jdbc:mysql://localhost/hotel_inter","root","");
            //JOptionPane.showMessageDialog(null, "conexion establecida");
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println("error de conexion");
            JOptionPane.showMessageDialog(null, "error de conexion "+e);
        }
        return cone;
   }
}
