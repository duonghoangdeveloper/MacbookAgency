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
public class DomainErrorDTO implements Serializable {
    private String domainErrorMessage;
    private String nameErrorMessage;
    private String xslPathErrorMessage;

    public DomainErrorDTO() {
    }

    public DomainErrorDTO(String domainErrorMessage, String nameErrorMessage, String xslPathErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
        this.nameErrorMessage = nameErrorMessage;
        this.xslPathErrorMessage = xslPathErrorMessage;
    }

    public String getDomainErrorMessage() {
        return domainErrorMessage;
    }

    public String getNameErrorMessage() {
        return nameErrorMessage;
    }

    public String getXslPathErrorMessage() {
        return xslPathErrorMessage;
    }

    public void setDomainErrorMessage(String domainErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
    }

    public void setNameErrorMessage(String nameErrorMessage) {
        this.nameErrorMessage = nameErrorMessage;
    }

    public void setXslPathErrorMessage(String xslPathErrorMessage) {
        this.xslPathErrorMessage = xslPathErrorMessage;
    }

    
}
