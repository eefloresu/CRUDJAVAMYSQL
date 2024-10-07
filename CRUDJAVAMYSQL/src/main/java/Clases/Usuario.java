package Clases;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


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
    
}
