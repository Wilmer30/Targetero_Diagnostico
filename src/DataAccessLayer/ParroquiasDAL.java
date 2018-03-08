/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.Parroquia;
import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;

/**
 * Contiene los métodos de persistencia para la tabla: Parroquias. 
 * @author Erick
 */
public class ParroquiasDAL {

    /**
     * Recupera los registros de la tabla: Parroquias, según el código del
     * cantón especificado.     
     * @param codigoCanton parámetro de la consulta.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel Select(String codigoCanton) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Parroquia parroquia;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_PARR,NOM_PARR FROM Parroquias WHERE COD_CAN=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoCanton);
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    parroquia = new Parroquia();
                    parroquia.setCodigo(lector.getString("COD_PARR"));
                    parroquia.setNombre(lector.getString("NOM_PARR"));
                    model.addElement(parroquia);
                }                
                return model;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Recupera el código del cantón al que pertenece la parroquia.     
     * @param codigoParroquia parámetro de la consulta.
     * @return cadena con el código del cantón.
     */
    public String SelectCantonCode(String codigoParroquia) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CAN FROM Parroquias WHERE COD_PARR=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoParroquia);
                ResultSet lector = comando.executeQuery();
                connection.close();
                if (lector.next()) {
                    return lector.getString("COD_CAN");
                }                
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
