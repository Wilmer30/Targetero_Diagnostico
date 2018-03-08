/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Canton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;

/**
 * Contiene los métodos de persistencia para la tabla: Cantones.
 * @author Erick
 */
public class CantonesDAL {
    
    /**
     * Recupera los registros de la tabla: Cantones, según el
     * código de la provincia especificado.
     * @param codigoProvincia parámetro de la consulta.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel Select(String codigoProvincia){                
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Canton canton;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_CAN,NOM_CAN FROM Cantones WHERE COD_PRO=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoProvincia);
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    canton=new Canton();
                    canton.setCodigo(lector.getString("COD_CAN"));
                    canton.setNombre(lector.getString("NOM_CAN"));                                        
                    model.addElement(canton);
                }                
                return model;                
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Recupera el código de la provincia al que pertenece el cantón.
     * @param codigoCanton parámetro de la consulta.
     * @return cadena con el código de la provincia.
     */
    public String SelectProvinceCode(String codigoCanton) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_PRO FROM Cantones WHERE COD_CAN=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigoCanton);
                ResultSet lector = comando.executeQuery();
                connection.close();
                if (lector.next()) {
                    return lector.getString("COD_PRO");
                }                
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
