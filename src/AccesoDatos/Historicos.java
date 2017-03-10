/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class Historicos {
    String codigoCie10;
    String numeroHistoriaClinica;
    Date fechaIngreso;
    String estadoPaciente;

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
