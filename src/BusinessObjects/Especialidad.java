/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Especialidades.
 * @author Erick
 */
public class Especialidad {
    
    private String _codigo;
    private String _descripcion;

    /**
     * @return código de la especialidad.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return descripción de la especialidad.
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * @param codigo código de la especialidad a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param descripcion descripción de la especialidad a ser seteado.
     */
    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return _descripcion;
    }
}
