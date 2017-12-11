/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;

/**
 * Clase que representa la tabla: Registros.
 * @author Erick
 */
public class Registro {
    
    private String _documentoIdentidad;
    private Date _fechaIngreso;
    private char _condicionEdad;
    private int _edad;
    private char _tipoDiscapacidad;
    private String _servicioSalaInternacion;
    private char _sala;
    private String _horaIngreso;
    private Date _fechaEgreso;
    private int _diasEstancia;
    private char _estado;
    private String _cieDefinitivo;
    private String _cieSecundario1;
    private String _cieSecundario2;
    private String _cieSecundario3;
    private String _cieSecundario4;
    private String _cieSecundario5;
    private String _cieExterno;
    private String _codigoEspecialidad;

    /**
     * @return documento de identidad del paciente.
     */
    public String getDocumentoIdentidad() {
        return _documentoIdentidad;
    }

    /**
     * @return fecha de ingreso del paciente.
     */
    public Date getFechaIngreso() {
        return _fechaIngreso;
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
    public String getHoraIngreso() {
        return _horaIngreso;
    }

    /**
     * @return fecha de egreso del paciente.
     */
    public Date getFechaEgreso() {
        return _fechaEgreso;
    }

    /**
     * @return días de estancia del paciente.
     */
    public int getDiasEstancia() {
        return _diasEstancia;
    }

    /**
     * @return estado del paciente al egreso.
     */
    public char getEstado() {
        return _estado;
    }

    /**
     * @return código CIE10 definitivo al agreso del paciente.
     */
    public String getCieDefinitivo() {
        return _cieDefinitivo;
    }

    /**
     * @return código CIE10 secundario1 al agreso del paciente.
     */
    public String getCieSecundario1() {
        return _cieSecundario1;
    }

    /**
     * @return código CIE10 secundario2 al agreso del paciente.
     */
    public String getCieSecundario2() {
        return _cieSecundario2;
    }

    /**
     * @return código CIE10 secundario3 al agreso del paciente.
     */
    public String getCieSecundario3() {
        return _cieSecundario3;
    }

    /**
     * @return código CIE10 secundario4 al agreso del paciente.
     */
    public String getCieSecundario4() {
        return _cieSecundario4;
    }

    /**
     * @return código CIE10 secundario5 al agreso del paciente.
     */
    public String getCieSecundario5() {
        return _cieSecundario5;
    }

    /**
     * @return código CIE10 externo al agreso del paciente.
     */
    public String getCieExterno() {
        return _cieExterno;
    }

    /**
     * @return código de la especialidad del egreso.
     */
    public String getCodigoEspecialidad() {
        return _codigoEspecialidad;
    }

    /**
     * @param documentoIdentidad documento de identidad del paciente a ser seteado.
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this._documentoIdentidad = documentoIdentidad;
    }

    /**
     * @param fechaIngreso fecha de ingreso del paciente a ser seteado.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this._fechaIngreso = fechaIngreso;
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
     * @param sala sala por la cual ingresó el paciente a ser seteado.
     */
    public void setSala(char sala) {
        this._sala = sala;
    }

    /**
     * @param horaIngreso hora de ingreso del paciente a ser seteado.
     */
    public void setHoraIngreso(String horaIngreso) {
        this._horaIngreso = horaIngreso;
    }

    /**
     * @param fechaEgreso fecha de egreso del paciente a ser seteado.
     */
    public void setFechaEgreso(Date fechaEgreso) {
        this._fechaEgreso = fechaEgreso;
    }

    /**
     * @param diasEstancia días de estancia del paciente a ser seteado.
     */
    public void setDiasEstancia(int diasEstancia) {
        this._diasEstancia = diasEstancia;
    }

    /**
     * @param estado estado del paciente al egreso a ser seteado.
     */
    public void setEstado(char estado) {
        this._estado = estado;
    }

    /**
     * @param cieDefinitivo código CIE10 definitivo al agreso del paciente a ser seteado.
     */
    public void setCieDefinitivo(String cieDefinitivo) {
        this._cieDefinitivo = cieDefinitivo;
    }

    /**
     * @param cieSecundario1 código CIE10 secundario1 al agreso del paciente a ser seteado.
     */
    public void setCieSecundario1(String cieSecundario1) {
        this._cieSecundario1 = cieSecundario1;
    }

    /**
     * @param cieSecundario2 código CIE10 secundario2 al agreso del paciente a ser seteado.
     */
    public void setCieSecundario2(String cieSecundario2) {
        this._cieSecundario2 = cieSecundario2;
    }

    /**
     * @param cieSecundario3 código CIE10 secundario3 al agreso del paciente a ser seteado.
     */
    public void setCieSecundario3(String cieSecundario3) {
        this._cieSecundario3 = cieSecundario3;
    }

    /**
     * @param cieSecundario4 código CIE10 secundario4 al agreso del paciente a ser seteado.
     */
    public void setCieSecundario4(String cieSecundario4) {
        this._cieSecundario4 = cieSecundario4;
    }

    /**
     * @param cieSecundario5 código CIE10 secundario5 al agreso del paciente a ser seteado.
     */
    public void setCieSecundario5(String cieSecundario5) {
        this._cieSecundario5 = cieSecundario5;
    }

    /**
     * @param cieExterno código CIE10 externo al agreso del paciente a ser seteado.
     */
    public void setCieExterno(String cieExterno) {
        this._cieExterno = cieExterno;
    }

    /**
     * @param codigoEspecialidad código de la especialidad del egreso a ser seteado.
     */
    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this._codigoEspecialidad = codigoEspecialidad;
    }    
}
