/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DomainDAO;
import dto.DomainErrorDTO;
import java.sql.SQLException;

/**
 *
 * @author Duong
 */
public class DomainValidator {
    private static final String EMPTY_DOMAIN_MESSAGE = "Domain must not be empty";
    private static final String EMPTY_NAME_MESSAGE = "Name must not be empty";
    private static final String EMPTY_XSL_PATH_MESSAGE = "XSLPath must not be empty";
    private static final String INVALID_DOMAIN_MESSAGE = "Invalid domain";
    private static final String INVALID_NAME_MESSAGE = "Invalid name";
    private static final String INVALID_XSL_PATH_MESSAGE = "Invalid xsl path";
    
    private static final String DOMAIN_REGEX = "^.{1,50}$";
    private static final String NAME_REGEX = "^.{1,50}$";
    private static final String XSL_PATH_REGEX = "^.{1,50}$";
    
    public static DomainErrorDTO validateCreateDomain(String domain, String name, String xslPath) {
        DomainErrorDTO result = null;
        
        String domainErrorMessage = null;
        String nameErrorMessage = null;
        String xslPathErrorMessage = null;
        
        if (domain == null || domain.isEmpty()) {
            domainErrorMessage = EMPTY_DOMAIN_MESSAGE;
        } else if (!domain.matches(DOMAIN_REGEX)) {
            domainErrorMessage = INVALID_DOMAIN_MESSAGE;
        }
        
        if (name == null || name.isEmpty()) {
            nameErrorMessage = EMPTY_NAME_MESSAGE;
        } else if (!name.matches(NAME_REGEX)) {
            nameErrorMessage = INVALID_NAME_MESSAGE;
        }
        
        if (xslPath == null || xslPath.isEmpty()) {
            xslPathErrorMessage = null;
        } else if (!xslPath.matches(XSL_PATH_REGEX)) {
            xslPathErrorMessage = INVALID_XSL_PATH_MESSAGE;
        }
        
        if (domainErrorMessage != null || nameErrorMessage != null || xslPathErrorMessage != null) {
            result = new DomainErrorDTO(domainErrorMessage, nameErrorMessage, xslPathErrorMessage);
        }
        
        return result;
    }
    
    public static DomainErrorDTO validateUpdateDomain(String domain, String name, String xslPath) {
        DomainErrorDTO result = null;
        
        String domainErrorMessage = null;
        String nameErrorMessage = null;
        String xslPathErrorMessage = null;
        
        if (domain == null || domain.isEmpty()) {
            domainErrorMessage = EMPTY_DOMAIN_MESSAGE;
        } else if (!domain.matches(DOMAIN_REGEX)) {
            domainErrorMessage = INVALID_DOMAIN_MESSAGE;
        }
        
        if (name == null || name.isEmpty()) {
            nameErrorMessage = EMPTY_NAME_MESSAGE;
        } else if (!name.matches(NAME_REGEX)) {
            nameErrorMessage = INVALID_NAME_MESSAGE;
        }
        
        if (xslPath == null || xslPath.isEmpty()) {
            xslPathErrorMessage = null;
        } else if (!xslPath.matches(XSL_PATH_REGEX)) {
            xslPathErrorMessage = INVALID_XSL_PATH_MESSAGE;
        }
        
        if (domainErrorMessage != null || nameErrorMessage != null || xslPathErrorMessage != null) {
            result = new DomainErrorDTO(domainErrorMessage, nameErrorMessage, xslPathErrorMessage);
        }
        
        return result;
    }
}
