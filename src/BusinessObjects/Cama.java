/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Camas.
 * @author Erick
 */
public class Cama {
    
    private String _codigo;
    private String _tipoUsuario;
    private String _estado;
    private String _codigoSala;

    /**
     * @return código de la cama.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return tipo de usuario que ocupa la cama.
     */
    public String getTipoUsuario() {
        return _tipoUsuario;
    }

    /**
     * @return estado de la cama.
     */
    public String getEstado() {
        return _estado;
    }

    /**
     * @return código de la sala a la que pertenece la cama.
     */
    public String getCodigoSala() {
        return _codigoSala;
    }

    /**
     * @param codigo código de la cama a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param tipoUsuario tipo de usuario que ocupa la cama a ser seteado.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this._tipoUsuario = tipoUsuario;
    }

    /**
     * @param estado estado de la cama a ser seteado.
     */
    public void setEstado(String estado) {
        this._estado = estado;
    }

    /**
     * @param codigoSala código de la sala a la que pertenece la cama a ser seteado.
     */
    public void setCodigoSala(String codigoSala) {
        this._codigoSala = codigoSala;
    }
}
