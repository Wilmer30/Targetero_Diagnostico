/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;

/**
 * Clase que representa la tabla: Usuarios.
 * @author Wilmer Oñate
 */
public class Usuario {
    
    private int _id;
    private String _nombre;
    private Date _ultimaActividad;
    private Date _creacion;
    private String _clave;
    private boolean _aprobacion;
    private Date _ultimaConexion;
    private String _pregunta;
    private String _respuesta;
    private String _rol;

    /**
     * @return id del usuario.
     */
    public int getId() {
        return _id;
    }

    /**
     * @return nombre del usuario.
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @return fecha de la última actividad del usuario.
     */
    public Date getUltimaActividad() {
        return _ultimaActividad;
    }

    /**
     * @return fecha de creación del usuario.
     */
    public Date getCreacion() {
        return _creacion;
    }

    /**
     * @return contraeña del usuario.
     */
    public String getClave() {
        return _clave;
    }

    /**
     * @return true si el usuario está activo, false de lo contrario.
     */
    public boolean isApproved() {
        return _aprobacion;
    }

    /**
     * @return fecha de la última conexión del usuario.
     */
    public Date getUltimaConexion() {
        return _ultimaConexion;
    }

    /**
     * @return pregunta de seguridad del usuario.
     */
    public String getPregunta() {
        return _pregunta;
    }

    /**
     * @return respuesta de la pregunta de seguridad del usuario.
     */
    public String getRespuesta() {
        return _respuesta;
    }

    /**
     * @return rol del usuario.
     */
    public String getRol() {
        return _rol;
    }

    /**
     * @param id id del usuario a ser seteado.
     */
    public void setId(int id) {
        this._id = id;
    }

    /**
     * @param nombre nombre del usuario a ser seteado.
     */
    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    /**
     * @param ultimaActividad fecha de la última actividad del usuario a ser seteado.
     */
    public void setUltimaActividad(Date ultimaActividad) {
        this._ultimaActividad = ultimaActividad;
    }

    /**
     * @param creacion fecha de creación del usuario a ser seteado.
     */
    public void setCreacion(Date creacion) {
        this._creacion = creacion;
    }

    /**
     * @param clave contraeña del usuario a ser seteado.
     */
    public void setClave(String clave) {
        this._clave = clave;
    }

    /**
     * @param aprobacion true si el usuario está activo, false de lo contrario.
     */
    public void setAprobacion(boolean aprobacion) {
        this._aprobacion = aprobacion;
    }

    /**
     * @param ultimaConexion fecha de la última conexión del usuario a ser seteado.
     */
    public void setUltimaConexion(Date ultimaConexion) {
        this._ultimaConexion = ultimaConexion;
    }

    /**
     * @param pregunta pregunta de seguridad del usuario a ser seteado.
     */
    public void setPregunta(String pregunta) {
        this._pregunta = pregunta;
    }

    /**
     * @param respuesta respuesta de la pregunta de seguridad del usuario a ser seteado.
     */
    public void setRespuesta(String respuesta) {
        this._respuesta = respuesta;
    }

    /**
     * @param rol rol del usuario a ser seteado.
     */
    public void setRol(String rol) {
        this._rol = rol;
    }
}
