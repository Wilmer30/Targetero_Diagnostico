/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.Cama;
import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * Contiene los métodos de persistencia para la tabla: Camas.
 * @author Erick
 */
public class CamasDAL {
    
    /**
     * Recupera los registros de la tabla: Camas, según el
     * parámetro especificado.
     * @param cama parámetro de la consulta.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel SelectCode(Cama cama){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();        
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CAM FROM Camas WHERE COD_SAL=? AND EST_CAM=? AND USER_CAM=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, cama.getCodigoSala());
                comando.setString(2, cama.getEstado());
                comando.setString(3, cama.getTipoUsuario());
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    String codigo=lector.getString("COD_CAM");
                    model.addElement(codigo);
                }                
                return model;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Recupera los registros de la tabla: Camas, según el
     * código de la sala y el tipo de usuario especificado.
     * @param codigoSala parámetro de la consulta.
     * @param tipoUsuario parámetro de la consulta.
     * @return DefaultTableModel con los datos de la consulta.
     */
    public DefaultTableModel Select(String codigoSala,String tipoUsuario) {
        DefaultTableModel model = new DefaultTableModel();
        String[] fila = new String[2];
        model.addColumn("Código");
        model.addColumn("Estado");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CAM,EST_CAM FROM Camas WHERE COD_SAL=? AND USER_CAM=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoSala);
                comando.setString(2, tipoUsuario);
                ResultSet lector = comando.executeQuery();
                while (lector.next()) {
                    fila[0] = lector.getString("COD_CAM");
                    fila[1] = lector.getString("EST_CAM");
                    model.addRow(fila);
                }
                connection.close();
                return model;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Inserta un registro en la tabla: Camas.
     * @param cama parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Cama cama) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Camas(COD_CAM,USER_CAM,EST_CAM,COD_SAL) "
                        + "VALUES(?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, cama.getCodigo());
                comando.setString(2, cama.getTipoUsuario());
                comando.setString(3, cama.getEstado());
                comando.setString(4, cama.getCodigoSala());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido insertar";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    /**
     * Actualiza el estado de una Cama.
     * @param cama parámetro para la actualización.
     * @return cadena con el resultado de la actualización.
     */
    public String UpdateEstado(Cama cama) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();        
        if (connection != null) {
            try {
                String sentencia = "UPDATE Camas SET EST_CAM=? "
                        + "WHERE COD_CAM=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, cama.getEstado());
                comando.setString(2, cama.getCodigo());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar la cama";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
}
