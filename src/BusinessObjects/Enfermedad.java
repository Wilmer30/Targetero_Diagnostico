/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Enfermedades.
 * @author Wilmer Oñate
 */
public class Enfermedad {

    private String _codigo;
    private String _descripcion;
    private String _estado;

    /**
     * @return código CIE10 de la enfermedad.
     */
    public String getCodigo() {
        return _codigo;
    }

    /**
     * @return descripción de la enfermedad.
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * @return estado de la enfermedad.
     */
    public String getEstado() {
        return _estado;
    }

    /**
     * @param codigo código CIE10 de la enfermedad a ser seteado.
     */
    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    /**
     * @param descripcion descripción de la enfermedad a ser seteado.
     */
    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    /**
     * @param estado estado de la enfermedad a ser seteado.
     */
    public void setEstado(String estado) {
        this._estado = estado;
    }
    
    @Override
    public String toString(){
        return _codigo;
    }
}
