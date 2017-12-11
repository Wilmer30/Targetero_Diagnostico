/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 * Clase que representa la tabla: Reportes.
 * @author Erick
 */
public class Reporte {
    
    private int _id;
    private String _descripcion;

    /**
     * @return id del reporte.
     */
    public int getId() {
        return _id;
    }

    /**
     * @return descripción del reporte.
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * @param id id del reporte a ser seteado.
     */
    public void setId(int id) {
        this._id = id;
    }

    /**
     * @param descripcion descripción del reporte a ser seteado.
     */
    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }
}
