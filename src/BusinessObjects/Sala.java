/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Salas.
 * @author Erick
 */
public class Sala {
    
    private String _codigo;
    private String _nombre;

    /**
     * @return código de la sala.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return nombre de la sala.
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param codigo código de la sala a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param nombre nombre de la sala a ser seteado.
     */
    public void setNombre(String nombre) {
        this._nombre = nombre;
    }
}
