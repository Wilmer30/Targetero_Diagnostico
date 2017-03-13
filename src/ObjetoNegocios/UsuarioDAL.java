/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetoNegocios;

import AccesoDatos.Usuarios;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class UsuarioDAL {

    public String SelectCedula(String cedula) throws SQLException {
        boolean res = false;
        String password = null;
        Connection conexion = ConectarBaseDatos.conectar();

        PreparedStatement stmt = null;
        stmt = conexion.prepareStatement("SELECT  password from usuarios where username=? and APPROVED=?");
        stmt.setString(1, cedula);
        stmt.setBoolean(2, true);
        try {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                password = rs.getString("password");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido realizar el SELECT" + e);
        } finally {
            conexion.close();
        }

        return password;

    }

    public boolean Insert(Usuarios usuario) throws SQLException {
        boolean res = false;
        Connection conexion = ConectarBaseDatos.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexion.prepareStatement("INSERT INTO usuarios ("
                    + "USERNAME,"
                    + "LASTACTIVITY ,"
                    + "CREATED ,"
                    + "EMAIL,"
                    + "PASSWORD,"
                    + "APPROVED,"
                    + "LASTLOGIN,"
                    + "PASSWORDQUESTION,"
                    + "PASSWORDANSWER,"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setDate(2, (Date) usuario.getLastActivity());
            stmt.setDate(3, (Date) usuario.getCreated());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPassword());
            stmt.setBoolean(6, usuario.isApproved());
            stmt.setDate(7, (Date) usuario.getLastLogin());
            stmt.setString(8, usuario.getPasswordQuestion());
            stmt.setString(9, usuario.getPasswordAnswer());
            res = stmt.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido realizar el INSERT" + e);
        } finally {
            conexion.close();
        }

        return res;
    }
}
