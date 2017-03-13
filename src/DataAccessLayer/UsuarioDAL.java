/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class UsuarioDAL {
    
    private String fechaHora(){
        Date now = new Date();
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("YYYY-MM-dd");//revisar
        return cambiarfecha.format(now);        
    }
    
    public String Insert(Usuarios usuario){
        ConectarBaseDatos connect=new ConectarBaseDatos();
        Connection connection=connect.conectar();
        if (connection!=null) {
            try {
                String sentencia="INSERT INTO Usuarios(UserName,Created,Email,Password,Approved) "+
                                 "VALUES(?,?,?,?,?)";
                PreparedStatement comando=connection.prepareStatement(sentencia);
                comando.setString(1, usuario.getNombreUsuario());
                comando.setTimestamp(2, Timestamp.valueOf(fechaHora()));
                comando.setString(3, usuario.getEmail());
                comando.setString(4, usuario.getPassword());
                comando.setBoolean(5, false);
                int registrosAfectados=comando.executeUpdate();
                if (registrosAfectados>0) {
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
