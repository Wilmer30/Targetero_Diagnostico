/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Provincia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
/**
 * Contiene los métodos de persistencia para la tabla: Provincias.
 * @author Erick
 */
public class ProvinciasDAL {
    
    /**
     * Recupera todos los registros de la tabla: Provincias.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel Select(){                
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Provincia provincia;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_PRO,NOM_PRO FROM Provincias";
                Statement comando = connection.createStatement();
                ResultSet lector = comando.executeQuery(sentencia);
                while (lector.next()) {
                    provincia=new Provincia();
                    provincia.setCodigo(lector.getString("COD_PRO"));
                    provincia.setNombre(lector.getString("NOM_PRO"));
                    model.addElement(provincia);
                }               
                connection.close();
                return model;                
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
