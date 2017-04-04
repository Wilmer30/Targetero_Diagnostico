/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Erick
 */
public class Validaciones {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private final String patron_email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    // </editor-fold>

    public void soloNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }

    public void soloLetras(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < 'A' || c > 'Z')) {
            evt.consume();
        }
    }

    public boolean validarCedula(String cedula) {
        String cadena;
        int digito, verificador, residuo;
        int suma = 0;
        if (cedula.length() == 10) {
            for (int i = 0; i < 9; i++) {
                cadena = String.valueOf(cedula.charAt(i));
                digito = Integer.valueOf(cadena);
                if (i % 2 == 0) {
                    digito = digito * 2;
                    if (digito > 9) {
                        digito = digito - 9;
                    }
                }
                suma = suma + digito;
            }
            residuo = suma % 10;
            if (residuo != 0) {
                verificador = 10 - residuo;
            } else {
                verificador = 0;
            }
            return verificador == Integer.valueOf(String.valueOf(cedula.charAt(9)));
        }
        return false;
    }

    public boolean validarMail(String email) {
        Pattern patron = Pattern.compile(patron_email);
        Matcher comparar = patron.matcher(email);
        return comparar.matches();
    }
    
    public void soloNumerosLetras(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || Character.isDigit(c))) {
            evt.consume();
        }
    }

    public void longitudMaximoSeis(java.awt.event.KeyEvent evt, String texto) {
        if (!(texto.length() < 6)) {
            evt.consume();
        }

    }
    
    public void convertirMayusculas(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String texto=String.valueOf(c).toUpperCase();
            evt.setKeyChar(texto.charAt(0));
            
        }
    }
    
    public void longitudMaximoCuatro(java.awt.event.KeyEvent evt, String texto) {
        if ((texto.length() > 4)) {
            evt.consume();
        }

    }
}
