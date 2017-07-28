/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import BusinessObjects.Enfermedades;
import DataAccessLayer.EnfermedadesDAL;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Wilmer Oñate
 */
public class EnfermedadesBL {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    EnfermedadesDAL enfermedadesDAL;
    Enfermedades enfermedades;

    // </editor-fold>
    
    public EnfermedadesBL() {
        enfermedadesDAL = new EnfermedadesDAL();
        enfermedades= new Enfermedades();
    }

    public String validarEnfermedad(String enfermedad) {
        String mensaje = enfermedadesDAL.VerificarEnfermedad(enfermedad);
        if (mensaje == null) {
            return null;
        } else {
            return mensaje;
        }
    }

    public String buscarEnfermedad(String enfermedad) {
        String mensaje = enfermedadesDAL.buscarEnfermedad(enfermedad);
        return mensaje;

    }

    public DefaultTableModel SelectCIE10Inactivas() {
        return (enfermedadesDAL.SelelctEnfermedadesInactivas());
    }

    public DefaultTableModel SelectCIE10Activas() {        
        return (enfermedadesDAL.SelelctEnfermedadesActivas());
    }
    
    public DefaultComboBoxModel SelectCodigoCIE10(String codigo, String estado) {        
        return (enfermedadesDAL.SelectPrimaryKeyCombo(codigo, estado));
    }
    
    public String SelectDescripcionCIE10(String codigo, String estado) {        
         return (enfermedadesDAL.SelectPrimaryDesciprcion(codigo, estado));
    }

    public DefaultTableModel SelectCIE10PrimaryKeyActivas(String codigo) {
        //Se realiza la busqueda inteligente        
        return (enfermedadesDAL.SelectPrimaryKeyTablaActivas(codigo));

    }

    public DefaultTableModel SelectCIE10PrimaryKeyInactivas(String codigo) {
        //Se realiza la busqueda inteligente        
        return (enfermedadesDAL.SelelctPrimaryKeyTablaInactivas(codigo));

    }

    public String InsertEnfermedad(String codigo, String descripcion) {
        enfermedades.setCodigoCie10(codigo);
        enfermedades.setDescripcion(descripcion);
        enfermedades.setEstado("ACTIVO");
        String mensaje = enfermedadesDAL.Insert(enfermedades);
        return mensaje;
    }

    public String UpdateEstadoCIE10(String codigo, String estado) {
        String mensaje = enfermedadesDAL.UpdateEstado(codigo, estado); //Ejecutamos la actualización
        return mensaje;
    }

}
