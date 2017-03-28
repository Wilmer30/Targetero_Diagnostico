/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Wilmer Oñate
 */
public class EnfermedadesBL {
 
    public boolean VerificarEnfermedad(String codigo) {
        
        boolean res = false;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie from enfermedades where cod_cie =  ? and est_cie=?";
                //String sentencia = "SELECT  cod_cie from enfermedades ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo);
                comando.setString(2, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    res=true;
                }

            } catch (Exception e) {
            }
        }
        return res;
    }
}
