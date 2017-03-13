/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class UsuarioDAL {

    private String fechaHora() {
        Date now = new Date();
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("YYYY-MM-dd");//revisar
        return cambiarfecha.format(now);
    }

    public boolean VerificarUsuario(String cedula) {
        boolean res = false;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
            String sentencia = "SELECT  * from usuarios where username=? and APPROVED=?";
            PreparedStatement comando = connection.prepareStatement(sentencia);
            comando.setString(1, cedula);
            comando.setBoolean(2, true);
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                res = true;
            }
  
            } catch (Exception e) {
            }            
        }
        return res;
    }
    
    public boolean VerificarContraseña(String usuario, String password) {
        String clave=null;
        boolean res = false;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
            String sentencia = "SELECT  password from usuarios where username=? and APPROVED=?";
            PreparedStatement comando = connection.prepareStatement(sentencia);
            comando.setString(1, usuario);
            comando.setBoolean(2, true);
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                clave= rs.getString("password");
            }
  
            } catch (Exception e) {
            }            
        }
        if (password.equals(clave)) {
            res=true;
        }
        return res;
    }

    

    

    public String Insert(Usuarios usuario) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Usuarios(UserName,Created,Email,Password,Approved) "
                        + "VALUES(?,?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario.getNombreUsuario());
                comando.setTimestamp(2, Timestamp.valueOf(fechaHora()));
                comando.setString(3, usuario.getEmail());
                comando.setString(4, usuario.getPassword());
                comando.setBoolean(5, false);
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
