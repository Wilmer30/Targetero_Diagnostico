/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Erick
 */
public class RolesDAL {
    
    public ArrayList SelectRoles(){
        ArrayList<String> roles=new ArrayList<>();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT RoleName FROM Roles";
                Statement comando = connection.createStatement();                
                ResultSet lector = comando.executeQuery(sentencia);
                while(lector.next()) {
                    roles.add(lector.getString("RoleName"));
                }
                return roles;
            } catch (Exception e) {
                return null;
            }
        }        
        return null;
    }
    
    public int SelectRolPorNombre(String rol){        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT RoleID FROM Roles WHERE RoleName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, rol);
                ResultSet lector = comando.executeQuery();
                if(lector.next()) {
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
