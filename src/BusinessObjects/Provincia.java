/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Provincias.
 * @author Erick
 */
public class Provincia {
    
    private String _codigo;
    private String _nombre;

    /**
     * @return código de la provincia.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return nombre de la provincia.
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param codigo código de la provincia a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param nombre nombre de la provincia a ser seteado.
     */
    public void setNombre(String nombre) {
        this._nombre = nombre;
    }
    
    @Override
    public String toString(){
        return _nombre;
    }
}