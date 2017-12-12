/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Cantones.
 * @author Erick
 */
public class Canton {
    
    private String _codigo;
    private String _nombre;
    private String _codigoProvincia;

    /**
     * @return código del cantón.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return nombre del cantón.
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param codigo código del cantón a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param nombre nombre del cantón a ser seteado.
     */
    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    /**
     * @return código de la provincia a la que pertenece el cantón.
     */
    public String getCodigoProvincia() {
        return _codigoProvincia;
    }

    /**
     * @param codigoProvincia código de la provincia a la que pertenece el cantón a ser seteado.
     */
    public void setCodigoProvincia(String codigoProvincia) {
        this._codigoProvincia = codigoProvincia;
    }
    
    @Override
    public String toString(){
        return _nombre;
    }
}
