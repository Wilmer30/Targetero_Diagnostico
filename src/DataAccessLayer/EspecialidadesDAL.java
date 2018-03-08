/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Especialidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 * Contiene los métodos de persistencia para la tabla: Especialidades.
 * @author Erick
 */
public class EspecialidadesDAL {
    
    /**
     * Recupera todos los registros de la tabla: Especialidades.
     * @return DefaultComboBoxModel con los datos de la consulta.
     */
    public DefaultComboBoxModel Select(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Especialidad especialidad;
        if (connection != null) {
            try {
                String sentencia = "SELECT COD_ESP,DESC_ESP FROM Especialidades";
                Statement comando = connection.createStatement();
                ResultSet lector = comando.executeQuery(sentencia);
                connection.close();
                while (lector.next()) {
                    especialidad=new Especialidad();
                    especialidad.setCodigo(lector.getString("COD_ESP"));
                    especialidad.setDescripcion(lector.getString("DESC_ESP"));                                        
                    model.addElement(especialidad);
                }                               
                return model;                
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
