package Clases;


import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Usuario {

    int idSexo;
    
    public void establecerIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }
    
    public void MostrarSexoCombo(JComboBox comboSexo){
        
        Clases.Conexion objetoConexion = new Clases.Conexion();
    
        String sql = "select * from sexo";
        
        Statement st;
        
        try {
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            comboSexo.removeAllItems();
            
            while (rs.next()) {
                String nombreSexo = rs.getString("sexo");
                this.establecerIdSexo(rs.getInt("id"));
                
                comboSexo.addItem(nombreSexo);
                comboSexo.putClientProperty(nombreSexo, idSexo);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Sexo: "+e.toString());
        }
        
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    
    
    public void AgregarUsuario(JTextField nombres, JTextField apellidos, JComboBox combosexo, 
                               JTextField edad, JDateChooser fnacimiento, File foto){
        
        
        Conexion objetConexion = new Conexion();
        
        String consulta = "insert into usuarios (nombres, apellidos, fksexo, edad, fnacimiento, foto) Values (?,?,?,?,?,?);";

        try {
            FileInputStream fis = new FileInputStream(foto);

            CallableStatement cs = objetConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, nombres.getText());
            cs.setString(2, apellidos.getText()); 
            
            int idSexo = (int) combosexo.getClientProperty(combosexo.getSelectedItem());

            cs.setInt(3, idSexo);
            cs.setInt(4, Integer.parseInt(edad.getText()));

            Date fechaSeleccionada = fnacimiento.getDate();

            java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
            
            cs.setDate(5, fechaSQL);
            //Guardar fotos
            cs.setBinaryStream(6, fis, (int)foto.length());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se guardo el usuario correctamente");



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar, error: " + e.toString());
        }
    
        
    }
    
}
