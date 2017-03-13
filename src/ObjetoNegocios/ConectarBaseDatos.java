/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetoNegocios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public  class ConectarBaseDatos {
    
        private static String _cadenaConexion;
        private static String _servidor;
        private static String _baseDatos;
        private static String _usuario;
        private static String _contraseña;
       static  Connection conec=null;
        
         public static void  SetCadenaConexion(String cadenaConexion)
        {
             _cadenaConexion = cadenaConexion;
        }
        public static  String GetCadenaConexion()
        {
            return _cadenaConexion;
        }
        
        public static void SetServidor(String servidor)
        {
             _servidor = servidor;             
        }
        
        
        public static String GetServidor()
        {           
             return _servidor; 
        }
        
       
        public  static void SetBaseDatos(String baseDatos)
        {
             _baseDatos = baseDatos;             
        }

        
         public static String GetBaseDatos()
        {
             return _baseDatos;
        }
       
        public static void SetUsuario(String usuario)
        {
            _usuario = usuario; 
            
        }
       
        public static String   GetUsuario()
        {
           return _usuario; 
        }
        
        public static void  SetContraseña(String contraseña)
        {
            _contraseña = contraseña;             
        }

        public static String  GetContraseña()
        {
            return _contraseña ;             
        }
        
         public static Connection conectar(){
       String url = "jdbc:postgresql://localhost:5432/Hospital";        
        try {
            Class.forName("org.postgresql.Driver");
            conec = DriverManager.getConnection(url, "postgres", "1234");    
               //JOptionPane.showMessageDialog(null, "Conexión Exitosa ");
        } catch (ClassNotFoundException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Conexión Erronea "+e);
        }
        return conec;
    }
        
        
}
