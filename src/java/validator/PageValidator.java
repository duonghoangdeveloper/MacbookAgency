/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.PageErrorDTO;

/**
 *
 * @author Duong
 */
public class PageValidator {
    private static final String EMPTY_DOMAIN_MESSAGE = "Domain must not be empty";
    private static final String EMPTY_PATH_MESSAGE = "Path must not be empty";
    private static final String INVALID_DOMAIN_MESSAGE = "Invalid domain";
    private static final String INVALID_PATH_MESSAGE = "Invalid path";
    
    private static final String DOMAIN_REGEX = "^.{1,50}$";
    private static final String PATH_REGEX = "^/.{1,49}$";
    
    public static PageErrorDTO validateCreatePage(String domain, String path) {
        PageErrorDTO result = null;
        
        String domainErrorMessage = null;
        String pathErrorMessage = null;
        
        if (domain == null || domain.isEmpty()) {
            domainErrorMessage = EMPTY_DOMAIN_MESSAGE;
        } else if (!domain.matches(DOMAIN_REGEX)) {
            domainErrorMessage = INVALID_DOMAIN_MESSAGE;
        }
        
        if (path == null || path.isEmpty()) {
            pathErrorMessage = EMPTY_PATH_MESSAGE;
        } else if (!path.matches(PATH_REGEX)) {
            pathErrorMessage = INVALID_PATH_MESSAGE;
        }
        
        if (domainErrorMessage != null || pathErrorMessage != null) {
            result = new PageErrorDTO(domainErrorMessage, pathErrorMessage);
        }
        
        return result;
    }
    
    public static PageErrorDTO validateUpdatePage(String domain, String path) {
        PageErrorDTO result = null;
        
        String domainErrorMessage = null;
        String pathErrorMessage = null;
        
        if (domain == null || domain.isEmpty()) {
            domainErrorMessage = EMPTY_DOMAIN_MESSAGE;
        } else if (!domain.matches(DOMAIN_REGEX)) {
            domainErrorMessage = INVALID_DOMAIN_MESSAGE;
        }
        
        if (path == null || path.isEmpty()) {
            pathErrorMessage = EMPTY_PATH_MESSAGE;
        } else if (!path.matches(PATH_REGEX)) {
            pathErrorMessage = INVALID_PATH_MESSAGE;
        }
        
        if (domainErrorMessage != null || pathErrorMessage != null) {
            result = new PageErrorDTO(domainErrorMessage, pathErrorMessage);
        }
        
        return result;
    }
}
