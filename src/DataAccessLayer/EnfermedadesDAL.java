/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Enfermedad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * Contiene los métodos de persistencia para la tabla: Enfermedades. 
 * @author Erick
 */
public class EnfermedadesDAL {

    /**
     * Recupera los registros de la tabla: Enfermedades, según el código de la
     * enfermedad especificado.     
     * @param codigoEnfermedad parámetro de la consulta.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel SelectIntelligentSearch(String codigoEnfermedad) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Enfermedad enfermedad;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CIE,DESC_CIE FROM Enfermedades WHERE COD_CIE LIKE ? AND EST_CIE=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoEnfermedad + "%");
                comando.setString(2, "1");
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    enfermedad = new Enfermedad();
                    enfermedad.setCodigo(lector.getString("COD_CIE"));
                    enfermedad.setDescripcion(lector.getString("DESC_CIE"));
                    model.addElement(enfermedad);
                }
                return model;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Recupera un registro de la tabla: Enfermedades, según el código de
     * enfermedad especificado.     
     * @param codigoEnfermedad parámetro de la consulta.
     * @return objeto Enfermedad con los datos de la consulta.
     */
    public Enfermedad SelectById(String codigoEnfermedad) {
        Enfermedad enfermedad = null;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT DESC_CIE,EST_CIE FROM Enfermedades "
                        + "WHERE COD_CIE=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoEnfermedad);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    enfermedad = new Enfermedad();
                    enfermedad.setCodigo(codigoEnfermedad);
                    enfermedad.setDescripcion(lector.getString("DESC_CIE"));
                    enfermedad.setEstado(lector.getString("EST_CIE"));
                }
                connection.close();
                return enfermedad;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Recupera los registros de la tabla: Enfermedades, según el código y el
     * estado de la enfermedad especificado.     
     * @param codigoEnfermedad parámetro de la consulta.
     * @param estadoEnfermedad parámetro de la consulta.
     * @return DefaultTableModel con los datos de la consulta.
     */
    public DefaultTableModel SelectIntelligentSearch(String codigoEnfermedad, String estadoEnfermedad) {
        DefaultTableModel model = new DefaultTableModel();
        String[] fila = new String[2];
        model.addColumn("Código");
        model.addColumn("Descripción");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CIE,DESC_CIE FROM Enfermedades "
                        + "WHERE COD_CIE LIKE ? AND EST_CIE=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoEnfermedad + "%");
                comando.setString(2, estadoEnfermedad);
                ResultSet lector = comando.executeQuery();
                while (lector.next()) {
                    fila[0] = lector.getString("COD_CIE");
                    fila[1] = lector.getString("DESC_CIE");
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
     * Recupera los registros de la tabla: Enfermedades, según el estado de la
     * enfermedad especificado.    
     * @param estadoEnfermedad parámetro de la consulta.
     * @return DefaultTableModel con los datos de la consulta.
     */
    public DefaultTableModel Select(String estadoEnfermedad) {
        DefaultTableModel model = new DefaultTableModel();
        String[] fila = new String[2];
        model.addColumn("Código");
        model.addColumn("Descripción");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CIE,DESC_CIE FROM Enfermedades WHERE EST_CIE=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, estadoEnfermedad);
                ResultSet lector = comando.executeQuery();
                while (lector.next()) {
                    fila[0] = lector.getString("COD_CIE");
                    fila[1] = lector.getString("DESC_CIE");
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
     * Inserta un registro en la tabla: Enfermedades.
     * @param enfermedad parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Enfermedad enfermedad) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Enfermedades(COD_CIE,DESC_CIE,EST_CIE) "
                        + "VALUES(?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, enfermedad.getCodigo());
                comando.setString(2, enfermedad.getDescripcion());
                comando.setString(3, enfermedad.getEstado());
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
     * Actualiza el estado de una Enfermedad.
     * @param enfermedad parámetro para la actualización.
     * @return cadena con el resultado de la actualización.
     */
    public String UpdateEstado(Enfermedad enfermedad) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();        
        if (connection != null) {
            try {
                String sentencia = "UPDATE Enfermedades SET EST_CIE=? "
                        + "WHERE COD_CIE=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, enfermedad.getEstado());
                comando.setString(2, enfermedad.getCodigo());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar la enfermedad";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
}
