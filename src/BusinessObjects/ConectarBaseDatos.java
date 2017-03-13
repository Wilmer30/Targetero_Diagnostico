/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Erick
 */
public class ConectarBaseDatos {

    private Connection connect;
    private String cadenaConexion;
    private static  String usuario;

    public static String getUsuario() {
        return usuario;
    }
    private String clave;
    private String error;

    public ConectarBaseDatos() {
        connect = null;
        error = null;
    }

    public Connection conectar() {
        error = recuperarConfiguracion();
        if (error == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection(cadenaConexion, usuario, clave);
            } catch (PSQLException ex) {
                error = "La base de datos no existe";
            } catch (SQLException ex) {
                error = "Usuario o contraseña incorrecto";
            } catch (ClassNotFoundException ex) {
                error = "No se ha podido establecer la conexión";
            }
        }
        return connect;
    }

    public String getError() {
        return error;
    }

    private String recuperarConfiguracion() {
        File archivoConfiguracion = new File("config.properties");
        try {
            InputStream archivo = new FileInputStream(archivoConfiguracion);
            Properties propiedades = new Properties();
            propiedades.load(archivo);
            cadenaConexion = propiedades.getProperty("ConnectionString");
            usuario = propiedades.getProperty("User");
            clave = propiedades.getProperty("Password");
            return null;
        } catch (FileNotFoundException ex) {
            return "No se ha encontrado el archivo para cargar la configuración";
        } catch (IOException ex) {
            return "No se ha podido cargar la configuración";
        }        
    }
}
