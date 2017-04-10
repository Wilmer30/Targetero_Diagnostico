/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Erick
 */
public class ReportesDAL {
    
    public int RecuperarID(){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT LAST_VALUE FROM ";//Falta nombre de la secuencia
                Statement comando = connection.createStatement();
                ResultSet lector = comando.executeQuery(sentencia);
                if(lector.next()) {
                    return lector.getInt("LAST_VALUE");
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        }        
        return -1;
    }
    
    public String Insert(String descripcion) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO REPORTS(Desc_Rep) "
                        + "VALUES(?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, descripcion);                
                int registrosAfectados = comando.executeUpdate();
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
