/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.RolesDAL;
import DataAccessLayer.UsuariosDAL;
import DataAccessLayer.UsuariosRolesDAL;

/**
 *
 * @author Erick
 */
public class RolesBL {
    
    // <editor-fold defaultstate="collapsed" desc="Datos">
    UsuariosDAL usuarioDAL;
    RolesDAL rolDAL;
    UsuariosRolesDAL userrolDAL;
    // </editor-fold>
    
    public RolesBL(){
        usuarioDAL=new UsuariosDAL();
        rolDAL=new RolesDAL();
        userrolDAL=new UsuariosRolesDAL();
    }
        
    public String recuperarRol(String usuario){
        int userID=usuarioDAL.idUsuario(usuario);
        if (userID!=-1) {
            int rolID=userrolDAL.selectRolPorUsuario(userID);
            if (rolID!=-1) {
                String rol=rolDAL.SelectRolPorCodigo(rolID);
                if (rol!=null) {
                    return rol;
                }
                return null;
            }
            return null;
        }
        return null;
    }
    
}
