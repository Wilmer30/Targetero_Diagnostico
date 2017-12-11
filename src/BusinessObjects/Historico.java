/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;

/**
 * Clase que representa la tabla: Históricos.
 * @author Wilmer Oñate
 */
public class Historico {
    
    private String _codigo;
    private String _historiaClinica;
    private Date _fechaIngreso;
    private String _estadoPaciente;        

    /**
     * @return código CIE10 de la enfermedad.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return número de historia clínica del paciente.
     */
    public String getHistoriaClinica() {
        return _historiaClinica;
    }

    /**
     * @return fecha de ingreso del paciente.
     */
    public Date getFechaIngreso() {
        return _fechaIngreso;
    }

    /**
     * @return estado del paciente al egreso.
     */
    public String getEstadoPaciente() {
        return _estadoPaciente;
    }

    /**
     * @param codigo código CIE10 de la enfermedad a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param historiaClinica número de historia clínica del paciente a ser seteado.
     */
    public void setHistoriaClinica(String historiaClinica) {
        this._historiaClinica = historiaClinica;
    }

    /**
     * @param fechaIngreso fecha de ingreso del paciente a ser seteado.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this._fechaIngreso = fechaIngreso;
    }

    /**
     * @param estadoPaciente estado del paciente al egreso a ser seteado.
     */
    public void setEstadoPaciente(String estadoPaciente) {
        this._estadoPaciente = estadoPaciente;
    }
}
