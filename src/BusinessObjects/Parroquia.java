/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Parroquias.
 * @author Erick
 */
public class Parroquia {
    
    private String _codigo;
    private String _nombre;
    private String _codigoCanton;

    /**
     * @return código de la parroquia.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return nombre de la parroquia.
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param codigo código de la parroquia a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param nombre nombre de la parroquia a ser seteado.
     */
    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    /**
     * @return código del cantón a la que pertenece la parroquia.
     */
    public String getCodigoCanton() {
        return _codigoCanton;
    }

    /**
     * @param codigoCanton código del cantón a la que pertenece la parroquia a ser seteado.
     */
    public void setCodigoCanton(String codigoCanton) {
        this._codigoCanton = codigoCanton;
    }
    
    @Override
    public String toString(){
        return _nombre;
    }
}
