/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityLayer;

import org.apache.commons.codec.digest.DigestUtils;
import BusinessObjects.Enumeraciones;

/**
 * Clase que contiene los métodos de encriptación.
 * @author Erick
 */
public class Encriptacion {
    
    /**
     * Método usado para la encriptación.
     * @param cadena cadena que será encriptada.
     * @param tipo tipo de encriptación que se usará para encriptar la cadena.
     * @return cadena encriptada.
     */
    public String encriptar(String cadena,Enumeraciones.Encriptacion tipo){
        String cadenaEncriptada=null;
        switch(tipo){
            case Normal:
                cadenaEncriptada=DigestUtils.md5Hex(cadena);
                break;
            case Fuerte:
                cadenaEncriptada=DigestUtils.sha256Hex(cadena);
                break;
        }
        return cadenaEncriptada;
    }
}
