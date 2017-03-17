/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class UsuariosDAL {

    private String fechaHora() {
        Date now = new Date();
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return cambiarfecha.format(now);
    }

    public String verificarUsuario(String usuario) {        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT UserID FROM Usuarios WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario);                
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    return null;
                }
                return "El usuario no existe";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public boolean buscarUsuario(String usuario) {        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT UserID FROM Usuarios WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario);                
                ResultSet lector = comando.executeQuery();
                return lector.next();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public String recuperarContraseña(String usuario) {        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT Password FROM Usuarios WHERE UserName=? AND Approved=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario);
                comando.setBoolean(2, true);
                ResultSet lector = comando.executeQuery();
                if(lector.next()) {
                    return lector.getString("Password");
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }        
        return null;
    }

    public String insert(Usuarios usuario) {
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
                comando.setBoolean(5, true);
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
    
    public boolean ultimaActividad(String usuario){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET LastActivity=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setTimestamp(1, Timestamp.valueOf(fechaHora()));
                comando.setString(2, usuario);                
                int registrosAfectados = comando.executeUpdate();
                return registrosAfectados > 0;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
        
    public String updateEmail(String usuario,String email){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET Email=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, email);
                comando.setString(2, usuario);                
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar el email";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public String updatePassword(String usuario,String password){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET Password=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, password);
                comando.setString(2, usuario);                
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido cambiar el password";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public String updateEstado(String usuario,boolean estado){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET Approved=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setBoolean(1, estado);
                comando.setString(2, usuario);                
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar el estado del usuario";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public String ultimoLogin(String usuario){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET LastLogin=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setTimestamp(1, Timestamp.valueOf(fechaHora()));
                comando.setString(2, usuario);
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados>0) {
                    return null;
                }
                return "No se ha podido ingresar al sistema";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public String updateSeguridad(String usuario,String pregunta,String respuesta){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Usuarios SET PasswordQuestion=?,PasswordAnswer=? WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, pregunta);
                comando.setString(2, respuesta);
                comando.setString(3, usuario);                
                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar la pregunta y respuesta de seguridad";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    public String recuperarPregunta(String usuario) {        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT PasswordQuestion FROM Usuarios WHERE UserName=? AND Approved=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario);
                comando.setBoolean(2, true);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    return lector.getString("PasswordQuestion");
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    public String recuperarRespuesta(String usuario) {        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT PasswordAnswer FROM Usuarios WHERE UserName=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, usuario);                
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    return lector.getString("PasswordAnswer");
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
