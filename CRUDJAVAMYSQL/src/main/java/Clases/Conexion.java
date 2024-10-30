package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar = null;
    
    String usuario="root";
    String contrasenia="GRRM@8398/*";
    String bd="bdusuarios";
    String ip="localhost";
    String puerto="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            System.out.println("Conexión Exitosa");
            //JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se conecto a la BD correctamente");
        }
    
        return conectar;
    }
    
    public void cerrarConexion(){
        try {
            if (conectar != null && !conectar.isClosed()){
                conectar.close();
                //JOptionPane.showMessageDialog(null, "Conexión Cerrada");
                System.out.println("Conexión Cerrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se pudo cerrar la Conexión");
        }
    
    }
    
}
