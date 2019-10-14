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
public class PageErrorDTO implements Serializable {
    private String domainErrorMessage;
    private String pathErrorMessage;

    public PageErrorDTO() {
    }

    public PageErrorDTO(String domainErrorMessage, String pathErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
        this.pathErrorMessage = pathErrorMessage;
    }

    public String getDomainErrorMessage() {
        return domainErrorMessage;
    }

    public String getPathErrorMessage() {
        return pathErrorMessage;
    }

    public void setDomainErrorMessage(String domainErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
    }

    public void setPathErrorMessage(String pathErrorMessage) {
        this.pathErrorMessage = pathErrorMessage;
    }
}
