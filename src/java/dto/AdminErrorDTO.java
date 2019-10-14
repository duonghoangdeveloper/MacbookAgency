/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Duong
 */
public class AdminErrorDTO implements Serializable {
    private String usernameErrorMessage;
    private String passwordErrorMessage;
    private String confirmPasswordErrorMessage;
    private String fullnameErrorMessage;
    private String emailErrorMessage;
    private String phoneErrorMessage;

    public AdminErrorDTO() {
    }

    public AdminErrorDTO(String usernameErrorMessage, String passwordErrorMessage, String confirmPasswordErrorMessage, String fullnameErrorMessage, String emailErrorMessage, String phoneErrorMessage) {
        this.usernameErrorMessage = usernameErrorMessage;
        this.passwordErrorMessage = passwordErrorMessage;
        this.confirmPasswordErrorMessage = confirmPasswordErrorMessage;
        this.fullnameErrorMessage = fullnameErrorMessage;
        this.emailErrorMessage = emailErrorMessage;
        this.phoneErrorMessage = phoneErrorMessage;
    }

    public String getUsernameErrorMessage() {
        return usernameErrorMessage;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public String getConfirmPasswordErrorMessage() {
        return confirmPasswordErrorMessage;
    }

    public String getFullnameErrorMessage() {
        return fullnameErrorMessage;
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public String getPhoneErrorMessage() {
        return phoneErrorMessage;
    }

    public void setUsernameErrorMessage(String usernameErrorMessage) {
        this.usernameErrorMessage = usernameErrorMessage;
    }

    public void setPasswordErrorMessage(String passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }
    
    public void setConfirmPasswordErrorMessage(String confirmPasswordErrorMessage) {
        this.confirmPasswordErrorMessage = confirmPasswordErrorMessage;
    }

    public void setFullnameErrorMessage(String fullnameErrorMessage) {
        this.fullnameErrorMessage = fullnameErrorMessage;
    }

    public void setEmailErrorMessage(String emailErrorMessage) {
        this.emailErrorMessage = emailErrorMessage;
    }

    public void setPhoneErrorMessage(String phoneErrorMessage) {
        this.phoneErrorMessage = phoneErrorMessage;
    }
}
