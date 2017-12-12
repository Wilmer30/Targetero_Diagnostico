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
 * Clase para gestionar la conexión con la base de datos.
 * @author Erick
 */
public class ConectarBaseDatos {

    private Connection connect;
    private String cadenaConexion;
    private String usuario;
    private String clave;
    private String error;

    /**
     * Constructor de la clase.
     */
    public ConectarBaseDatos() {
        connect = null;
        error = null;
    }

    /**
     * Método para la conexión a la base de datos.
     * @return estado de la conexión a la base de datos.
     */
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

    /**
     * Almacena los errores al conectar a la base de datos.
     * @return error ocurrido durante la conexión a la base de datos.
     */
    public String getError() {
        return error;
    }

    /**
     * Carga los parámetros usados para la cadena de conexión desde un archivo de configuración.
     * @return algún error producido al cargar los parámetros desde el archivo.
     */
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