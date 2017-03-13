/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class Usuarios {
    Integer codigoUsuario;
    String nombreUsuario;
    Date lastActivity;
    Date created;
    String email;
    String password;
    boolean approved;
    Date lastLogin;
    String passwordQuestion;
    String passwordAnswer;

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setEmail(String email){
        this.email=email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public Date getCreated() {
        return created;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword() {
        return password;
    }

    public boolean isApproved() {
        return approved;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }        
}
