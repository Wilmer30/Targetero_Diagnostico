/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.sql.Date;

/**
 * Clase que representa la tabla: Pacientes.
 * @author Erick
 */
public class Paciente {
    
    private String _documentoIdentidad;
    private String _primerNombre;
    private String _segundoNombre;
    private String _primerApellido;
    private String _segundoApellido;
    private String _numeroHistoriaClinica;
    private String _nacionalidad;
    private String _paisNacionalidad;
    private String _genero;
    private Date _fechaNacimiento;
    private String _etnia;
    private String _direccion;
    private String _codigoParroquia;

    /**
     * @return documento de identidad del paciente.
     */
    public String getDocumentoIdentidad() {
        return _documentoIdentidad;
    }

    /**
     * @return primer nombre del paciente.
     */
    public String getPrimerNombre() {
        return _primerNombre;
    }

    /**
     * @return segundo nombre del paciente.
     */
    public String getSegundoNombre() {
        return _segundoNombre;
    }

    /**
     * @return primer apellido del paciente.
     */
    public String getPrimerApellido() {
        return _primerApellido;
    }

    /**
     * @return segundo apellido del paciente.
     */
    public String getSegundoApellido() {
        return _segundoApellido;
    }

    /**
     * @return número de historia clínica del paciente.
     */
    public String getNumeroHistoriaClinica() {
        return _numeroHistoriaClinica;
    }

    /**
     * @return nacionalidad del paciente.
     */
    public String getNacionalidad() {
        return _nacionalidad;
    }

    /**
     * @return país de nacionalidad del paciente.
     */
    public String getPaisNacionalidad() {
        return _paisNacionalidad;
    }

    /**
     * @return género del paciente.
     */
    public String getGenero() {
        return _genero;
    }

    /**
     * @return fecha de nacimiento del paciente.
     */
    public Date getFechaNacimiento() {
        return _fechaNacimiento;
    }

    /**
     * @return etnia del paciente.
     */
    public String getEtnia() {
        return _etnia;
    }

    /**
     * @return dirección del paciente.
     */
    public String getDireccion() {
        return _direccion;
    }

    /**
     * @param documentoIdentidad documento de identidad del paciente a ser seteado.
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this._documentoIdentidad = documentoIdentidad;
    }

    /**
     * @param primerNombre primer nombre del paciente a ser seteado.
     */
    public void setPrimerNombre(String primerNombre) {
        this._primerNombre = primerNombre;
    }

    /**
     * @param segundoNombre segundo nombre del paciente a ser seteado.
     */
    public void setSegundoNombre(String segundoNombre) {
        this._segundoNombre = segundoNombre;
    }

    /**
     * @param primerApellido primer apellido del paciente a ser seteado.
     */
    public void setPrimerApellido(String primerApellido) {
        this._primerApellido = primerApellido;
    }

    /**
     * @param segundoApellido segundo apellido del paciente a ser seteado.
     */
    public void setSegundoApellido(String segundoApellido) {
        this._segundoApellido = segundoApellido;
    }

    /**
     * @param numeroHistoriaClinica número de historia clínica del paciente a ser seteado.
     */
    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this._numeroHistoriaClinica = numeroHistoriaClinica;
    }

    /**
     * @param nacionalidad nacionalidad del paciente a ser seteado.
     */
    public void setNacionalidad(String nacionalidad) {
        this._nacionalidad = nacionalidad;
    }

    /**
     * @param paisNacionalidad país de nacionalidad del paciente a ser seteado
     */
    public void setPaisNacionalidad(String paisNacionalidad) {
        this._paisNacionalidad = paisNacionalidad;
    }

    /**
     * @param genero género del paciente a ser seteado
     */
    public void setGenero(String genero) {
        this._genero = genero;
    }

    /**
     * @param fechaNacimiento fecha de nacimiento del paciente a ser seteado.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this._fechaNacimiento = fechaNacimiento;
    }

    /**
     * @param etnia etnia del paciente a ser seteado.
     */
    public void setEtnia(String etnia) {
        this._etnia = etnia;
    }

    /**
     * @param direccion dirección del paciente a ser seteado.
     */
    public void setDireccion(String direccion) {
        this._direccion = direccion;
    }

    /**
     * @return código de la parroquia donde vive el paciente.
     */
    public String getCodigoParroquia() {
        return _codigoParroquia;
    }

    /**
     * @param codigoParroquia código de la parroquia donde vive el paciente a ser seteado.
     */
    public void setCodigoParroquia(String codigoParroquia) {
        this._codigoParroquia = codigoParroquia;
    }
}
