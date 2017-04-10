/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import DataAccessLayer.ReportesDAL;

/**
 *
 * @author Erick
 */
public class ReportesBL {
    
    // <editor-fold defaultstate="collapsed" desc="Datos">
    ReportesDAL reporteDAL;
    // </editor-fold>
    
    public ReportesBL(){
        reporteDAL=new ReportesDAL();
    }
    
    public int numeroReporte(){
        return reporteDAL.RecuperarID();        
    }
    
    public String nuevoReporte(String descripcion){
        return reporteDAL.Insert(descripcion);        
    }
}
