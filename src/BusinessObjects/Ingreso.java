/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;
/**
 * Clase que representa la tabla: Ingresos.
 * @author Erick
 */
public class Ingreso {
    
    private String _documentoIdentidad;
    private Date _fecha;
    private char _condicionEdad;
    private int _edad;
    private char _tipoDiscapacidad;
    private String _servicioSalaInternacion;
    private char _sala;
    private String _hora;
    private String _codigoCama;    

    /**
     * @return documento de identidad del paciente.
     */
    public String getDocumentoIdentidad() {
        return _documentoIdentidad;
    }

    /**
     * @return fecha de ingreso del paciente.
     */
    public Date getFecha() {
        return _fecha;
    }

    /**
     * @return condición de la edad al ingreso del paciente.
     */
    public char getCondicionEdad() {
        return _condicionEdad;
    }

    /**
     * @return edad del paciente al ingreso.
     */
    public int getEdad() {
        return _edad;
    }

    /**
     * @return tipo de discapacidad del paciente al ingreso.
     */
    public char getTipoDiscapacidad() {
        return _tipoDiscapacidad;
    }

    /**
     * @return servicio o sala de internación del paciente.
     */
    public String getServicioSalaInternacion() {
        return _servicioSalaInternacion;
    }

    /**
     * @return sala por la cual ingresó el paciente.
     */
    public char getSala() {
        return _sala;
    }

    /**
     * @return hora de ingreso del paciente.
     */
    public String getHora() {
        return _hora;
    }

    /**
     * @return código de la cama que usa el paciente.
     */
    public String getCodigoCama() {
        return _codigoCama;
    }

    /**
     * @param documentoIdentidad documento de identidad del paciente en el ingreso a ser seteado.
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this._documentoIdentidad = documentoIdentidad;
    }

    /**
     * @param fecha fecha de ingreso del paciente a ser seteado.
     */
    public void setFecha(Date fecha) {
        this._fecha = fecha;
    }

    /**
     * @param condicionEdad condición de la edad al ingreso del paciente a ser seteado.
     */
    public void setCondicionEdad(char condicionEdad) {
        this._condicionEdad = condicionEdad;
    }

    /**
     * @param edad edad del paciente al ingreso a ser seteado.
     */
    public void setEdad(int edad) {
        this._edad = edad;
    }

    /**
     * @param tipoDiscapacidad tipo de discapacidad del paciente al ingreso a ser seteado.
     */
    public void setTipoDiscapacidad(char tipoDiscapacidad) {
        this._tipoDiscapacidad = tipoDiscapacidad;
    }

    /**
     * @param servicioSalaInternacion servicio o sala de internación del paciente a ser seteado.
     */
    public void setServicioSalaInternacion(String servicioSalaInternacion) {
        this._servicioSalaInternacion = servicioSalaInternacion;
    }

    /**
     * @param sala sala por la cual ingresa el paciente a ser seteado.
     */
    public void setSala(char sala) {
        this._sala = sala;
    }

    /**
     * @param hora hora de ingreso del paciente a ser seteado.
     */
    public void setHora(String hora) {
        this._hora = hora;
    }

    /**
     * @param codigoCama código de la cama que usará el paciente a ser seteado.
     */
    public void setCodigoCama(String codigoCama) {
        this._codigoCama = codigoCama;
    }
}
