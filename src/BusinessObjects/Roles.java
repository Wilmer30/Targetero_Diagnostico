/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 *
 * @author Wilmer Oñate
 */
public class Roles {

    Integer codigoRol;
    String nombreRol;
    String descripcionRol;

    public void setCodigoRol(Integer codigoRol) {
        this.codigoRol = codigoRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Integer getCodigoRol() {
        return codigoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

}
