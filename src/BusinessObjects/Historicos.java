/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class Historicos {
    
    private String codigoCie10;
    private String numeroHistoriaClinica;
    private Date fechaIngreso;
    private String estadoPaciente;

    public void setCodigoCie10(String codigoCie10) {
        this.codigoCie10 = codigoCie10;
    }

    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public String getCodigoCie10() {
        return codigoCie10;
    }

    public String getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }    
}
