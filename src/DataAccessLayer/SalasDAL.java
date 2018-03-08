/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 * Contiene los métodos de persistencia para la tabla: Salas.
 * @author Erick
 */
public class SalasDAL {
    
    /**
     * Recupera todos los registros de la tabla: Salas.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel Select(){                
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Sala sala;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_SAL,NOM_SAL FROM Salas";
                Statement comando = connection.createStatement();
                ResultSet lector = comando.executeQuery(sentencia);
                connection.close();
                while (lector.next()) {
                    sala=new Sala();
                    sala.setCodigo(lector.getString("COD_SAL"));
                    sala.setNombre(lector.getString("NOM_SAL"));                    
                    model.addElement(sala);
                }                
                return model;                
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Inserta un registro en la tabla: Salas.
     * @param sala parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Sala sala) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Salas(COD_SAL,NOM_SAL) "
                        + "VALUES(?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, sala.getCodigo());
                comando.setString(2, sala.getNombre());
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
}