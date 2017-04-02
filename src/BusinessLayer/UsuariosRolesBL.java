/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.UsuariosRolesDAL;

/**
 *
 * @author Erick
 */
public class UsuariosRolesBL {
    
    // <editor-fold defaultstate="collapsed" desc="Datos">
    UsuariosRolesDAL userrolDAL;
    // </editor-fold>
    
    public UsuariosRolesBL(){
        userrolDAL=new UsuariosRolesDAL();
    }
    
    public String asignarRol(int userID,int roleID){
        String mensaje=userrolDAL.insert(userID, roleID);
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
    
    public String cambiarRol(int userID,int roleID){
        String mensaje=userrolDAL.updateRol(userID, roleID);
        if (mensaje!=null) {
            return mensaje;
        }
        return null;
    }
}
