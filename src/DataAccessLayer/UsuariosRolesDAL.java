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

/**
 *
 * @author Erick
 */
public class UsuariosRolesDAL {
    
    public String insert(int userID,int roleID){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Users_Roles(UserID,RoleID) "
                        + "VALUES(?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setInt(1, userID);
                comando.setInt(2, roleID);
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
    
    public String updateRol(int userID,int rolID){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Users_Roles SET RoleID=? WHERE UserID=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setInt(1, rolID);
                comando.setInt(2, userID);                
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar el rol del usuario";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public int selectRolPorUsuario(int userID){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT RoleID FROM Users_Roles WHERE UserID=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setInt(1, userID);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    return lector.getInt("RoleID");
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }
}
