/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.UsuariosDAL;
import SecurityLayer.Encriptacion;
import BusinessObjects.Usuario;
import BusinessObjects.Enumeraciones;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erick
 */
public class UsuariosBL {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    UsuariosDAL usuarioDAL;
    Encriptacion encriptar;
    Usuario nuevoUsuario;
    // </editor-fold>

    public UsuariosBL() {
        usuarioDAL = new UsuariosDAL();
        encriptar = new Encriptacion();
        nuevoUsuario=new Usuario();
    }

    public String validarIngreso(String usuario, String clave) {
        String mensaje = usuarioDAL.verificarUsuario(usuario);
        if (mensaje == null) {
            String password = usuarioDAL.recuperarContraseña(usuario);
            if (password != null) {
                if (password.equals(encriptar.encriptar(clave,Enumeraciones.Encriptacion.Fuerte))) {               
                    return null;
                }
                return "Contraseña incorrecta";
            }
            return "El usuario no tiene acceso al sistema";
        }
        return mensaje;
    }

    public String ultimaConexion(String usuario) {
        String mensaje = usuarioDAL.ultimoLogin(usuario);
        if (mensaje == null) {
            return null;
        }
        return mensaje;
    }

    public String seguridad(String usuario) {
        String mensaje = usuarioDAL.verificarUsuario(usuario);
        if (mensaje == null) {
            return null;
        }
        return mensaje;
    }

    public String pregunta(String usuario) {
        String pregunta = usuarioDAL.recuperarPregunta(usuario);
        if (pregunta != null) {
            return pregunta;
        }
        return null;
    }
    
    public String validarRecuperacion(String usuario,String respuesta){
        String answer=usuarioDAL.recuperarRespuesta(usuario);
        if (answer.equals(encriptar.encriptar(respuesta,Enumeraciones.Encriptacion.Normal))) {
            return null;
        }
        return "Respuesta incorrecta";
    }
    
    public String cambiarClave(String usuario,String nuevaClave){
        String mensaje=usuarioDAL.updatePassword(usuario, encriptar.encriptar(nuevaClave,Enumeraciones.Encriptacion.Normal));
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
    
    public String nuevoUsuario(String usuario,String email){
        nuevoUsuario.setNombre(usuario);        
        nuevoUsuario.setClave(encriptar.encriptar(usuario,Enumeraciones.Encriptacion.Fuerte));
        String mensaje=usuarioDAL.insert(nuevoUsuario);
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
    
    public boolean buscarUsuario(String usuario){
        String mensaje=usuarioDAL.buscarUsuario(usuario);
        return mensaje!=null;
    }
    
    public int idUsuario(String usuario){
        return usuarioDAL.idUsuario(usuario);        
    }
    
    public DefaultTableModel usuarios(){
        DefaultTableModel modelo=usuarioDAL.selectUsuarios();
        if (modelo==null) {
            return null;
        }
        return modelo;
    }
    
    public DefaultTableModel busquedaInteligenteUsuarios(String usuario){
        DefaultTableModel modelo=usuarioDAL.selectUsuarios(usuario);
        if (modelo==null) {
            return null;
        }
        return modelo;
    }
    
    public String cambiarEstado(String usuario,boolean estado){
        String mensaje=usuarioDAL.updateEstado(usuario, estado);
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
    
    public DefaultTableModel usuariosAlta(){
        DefaultTableModel modelo=usuarioDAL.selectUsuariosAlta();
        if (modelo==null) {
            return null;
        }
        return modelo;
    }
    
    public DefaultTableModel busquedaInteligente(String usuario){
        DefaultTableModel modelo=usuarioDAL.selectUsuariosAlta(usuario);
        if (modelo==null) {
            return null;
        }
        return modelo;
    }
    
    public String cambiarSeguridad(String usuario,String pregunta,String respuesta){
        String mensaje=usuarioDAL.updateSeguridad(usuario, pregunta, encriptar.encriptar(respuesta,Enumeraciones.Encriptacion.Fuerte));
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
    
    public String claveUsuario(String usuario) {
        String mensaje = usuarioDAL.verificarUsuario(usuario);
        if (mensaje == null) {
            String password = usuarioDAL.recuperarContraseña(usuario);
            if (password != null) {
                if (password.equals(encriptar.encriptar(usuario,Enumeraciones.Encriptacion.Fuerte))) {               
                    return null;
                }
                return "Primer ingreso";
            }
            return "El usuario no tiene acceso al sistema";
        }
        return mensaje;
    }
    
    public String ultimaActividad(String usuario) {
        String mensaje = usuarioDAL.ultimaActividad(usuario);
        if (mensaje == null) {
            return null;
        }
        return mensaje;
    }
    
    public String cambiarMail(String usuario,String nuevoMail){
        String mensaje=usuarioDAL.updateEmail(usuario, nuevoMail);
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
}
