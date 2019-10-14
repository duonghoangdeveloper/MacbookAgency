/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.AdminDAO;
import dto.AdminErrorDTO;
import java.sql.SQLException;

/**
 *
 * @author Duong
 */
public class AdminValidator {
    private static final String EMPTY_USERNAME_MESSAGE = "Username must not be empty";
    private static final String EMPTY_PASSWORD_MESSAGE = "Password must not be empty";
    private static final String EMPTY_FULLNAME_MESSAGE = "Fullname must not be empty";
    private static final String EMPTY_EMAIL_MESSAGE = "Email must not be empty";
    private static final String EMPTY_PHONE_MESSAGE = "Phone must not be empty";
    private static final String INVALID_USERNAME_MESSAGE = "Invalid username";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";
    private static final String INVALID_FULLNAME_MESSAGE = "Invalid fullname";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid email";
    private static final String INVALID_PHONE_MESSAGE = "Invalid phone";
    private static final String INVALID_CONFIRM_PASSWORD_MESSAGE = "Confirm password does not match";
    
    private static final String USERNAME_REGEX = "^[A-Za-z0-9]{5,50}$";
    private static final String PASSWORD_REGEX = "^.{5,50}$";
    private static final String FULLNAME_REGEX = "^.{5,50}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._]{1,50}@[A-Za-z0-9._]{1,49}$";
    private static final String PHONE_REGEX = "^[0-9]{10,11}$";
    
    public static AdminErrorDTO validateCreateAdmin(String username, String password, String confirmPassword, String fullname, String email, String phone) {
        AdminErrorDTO result = null;
        
        String usernameErrorMessage = null;
        String passwordErrorMessage = null;
        String confirmPasswordErrorMessage = null;
        String fullnameErrorMessage = null;
        String emailErrorMessage = null;
        String phoneErrorMessage = null;
        
        if (username == null || username.isEmpty()) {
            usernameErrorMessage = EMPTY_USERNAME_MESSAGE;
        } else if (!username.matches(USERNAME_REGEX)) {
            usernameErrorMessage = INVALID_USERNAME_MESSAGE;
        }
        
        if (password == null || password.isEmpty()) {
            passwordErrorMessage = EMPTY_PASSWORD_MESSAGE;
        } else if (!password.matches(PASSWORD_REGEX)) {
            passwordErrorMessage = INVALID_PASSWORD_MESSAGE;
        }
        
        if (!password.equals(confirmPassword)) {
            confirmPasswordErrorMessage = INVALID_CONFIRM_PASSWORD_MESSAGE;
        }
        
        if (fullname == null || fullname.isEmpty()) {
            fullnameErrorMessage = EMPTY_FULLNAME_MESSAGE;
        } else if (!fullname.matches(FULLNAME_REGEX)) {
            fullnameErrorMessage = INVALID_FULLNAME_MESSAGE;
        }
        
        if (email == null || email.isEmpty()) {
            emailErrorMessage = null;
        } else if (!email.matches(EMAIL_REGEX)) {
            emailErrorMessage = INVALID_EMAIL_MESSAGE;
        }
        
        if (phone == null || phone.isEmpty()) {
            phoneErrorMessage = null;
        } else if (!phone.matches(PHONE_REGEX)) {
            phoneErrorMessage = INVALID_PHONE_MESSAGE;
        }
        
        if (usernameErrorMessage != null || passwordErrorMessage != null || fullnameErrorMessage != null || emailErrorMessage != null || phoneErrorMessage != null) {
            result = new AdminErrorDTO(usernameErrorMessage, passwordErrorMessage, confirmPasswordErrorMessage, fullnameErrorMessage, emailErrorMessage, phoneErrorMessage);
        }
        
        return result;
    }
    
    public static AdminErrorDTO validateUpdateAdmin(String username, String password, String confirmPassword, String fullname, String email, String phone) {
        AdminErrorDTO result = null;
        
        String usernameErrorMessage = null;
        String passwordErrorMessage = null;
        String confirmPasswordErrorMessage = null;
        String fullnameErrorMessage = null;
        String emailErrorMessage = null;
        String phoneErrorMessage = null;
        
        if (username == null || username.isEmpty()) {
            usernameErrorMessage = EMPTY_USERNAME_MESSAGE;
        } else if (!username.matches(USERNAME_REGEX)) {
            usernameErrorMessage = INVALID_USERNAME_MESSAGE;
        }
        
        if (password == null || password.isEmpty()) {
            passwordErrorMessage = null;
        } else if (!password.matches(PASSWORD_REGEX)) {
            passwordErrorMessage = INVALID_PASSWORD_MESSAGE;
        }
        
        if (!password.equals(confirmPassword)) {
            confirmPasswordErrorMessage = INVALID_CONFIRM_PASSWORD_MESSAGE;
        }
        
        if (fullname == null || fullname.isEmpty()) {
            fullnameErrorMessage = EMPTY_FULLNAME_MESSAGE;
        } else if (!fullname.matches(FULLNAME_REGEX)) {
            fullnameErrorMessage = INVALID_FULLNAME_MESSAGE;
        }
        
        if (email == null || email.isEmpty()) {
            emailErrorMessage = null;
        } else if (!email.matches(EMAIL_REGEX)) {
            emailErrorMessage = INVALID_EMAIL_MESSAGE;
        }
        
        if (phone == null || phone.isEmpty()) {
            phoneErrorMessage = null;
        } else if (!phone.matches(PHONE_REGEX)) {
            phoneErrorMessage = INVALID_PHONE_MESSAGE;
        }
        
        if (usernameErrorMessage != null || passwordErrorMessage != null || fullnameErrorMessage != null || emailErrorMessage != null || phoneErrorMessage != null) {
            result = new AdminErrorDTO(usernameErrorMessage, passwordErrorMessage, confirmPasswordErrorMessage, fullnameErrorMessage, emailErrorMessage, phoneErrorMessage);
        }
        
        return result;
    }
}
