/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.UsuariosDAL;
import SecurityLayer.Encriptacion;

/**
 *
 * @author Erick
 */
public class UsuariosBL {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    UsuariosDAL usuarioDAL;
    Encriptacion encriptar;
    // </editor-fold>

    public UsuariosBL() {
        usuarioDAL = new UsuariosDAL();
        encriptar = new Encriptacion();
    }

    public String validarIngreso(String usuario, String clave) {
        String mensaje = usuarioDAL.verificarUsuario(usuario);
        if (mensaje == null) {
            String password = usuarioDAL.recuperarContraseña(usuario);
            if (password != null) {
                if (password.equals(encriptar.encriptar(clave))) {
               //if (password.equals((clave))) {
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
        if (answer.equals(encriptar.encriptar(respuesta))) {
            return null;
        }
        return "Respuesta incorrecta";
    }
    
    public String cambiarClave(String usuario,String nuevaClave){
        String mensaje=usuarioDAL.updatePassword(usuario, encriptar.encriptar(nuevaClave));
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
}
