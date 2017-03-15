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
        String mensaje=usuarioDAL.verificarUsuario(usuario);
        if (mensaje==null) {
            String password = usuarioDAL.recuperarContraseña(usuario);
            if (password != null) {
                if (password.equals(encriptar.encriptar(clave))) {
                    return null;
                }
                return "Contraseña incorrecta";
            }
            return "El usuario no tiene acceso al sistema";
        }
        return mensaje;
    }
    
    public void ultimaConexion(String usuario){//Revisar
        usuarioDAL.ultimoLogin(usuario);
    }

}
