/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

/**
 *
 * @author Wilmer Oñate
 */
public class Roles {

    Integer CodigoRol;
    String nombreRol;
    String DescripcionRol;

    public void setCodigoRol(Integer CodigoRol) {
        this.CodigoRol = CodigoRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public void setDescripcionRol(String DescripcionRol) {
        this.DescripcionRol = DescripcionRol;
    }

    public Integer getCodigoRol() {
        return CodigoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public String getDescripcionRol() {
        return DescripcionRol;
    }

}
