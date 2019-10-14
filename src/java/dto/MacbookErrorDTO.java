/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import utils.Utilities;

/**
 *
 * @author Duong
 */
public class MacbookErrorDTO implements Serializable {
    private String domainErrorMessage;
    private String modelIDErrorMessage;
    private String titleErrorMessage;
    private String priceErrorMessage;
    private String imageErrorMessage;
    private String urlErrorMessage;

    public MacbookErrorDTO() {
    }

    public MacbookErrorDTO(String domainErrorMessage, String modelIDErrorMessage, String titleErrorMessage, String priceErrorMessage, String imageErrorMessage, String urlErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
        this.modelIDErrorMessage = modelIDErrorMessage;
        this.titleErrorMessage = titleErrorMessage;
        this.priceErrorMessage = priceErrorMessage;
        this.imageErrorMessage = imageErrorMessage;
        this.urlErrorMessage = urlErrorMessage;
    }

    public String getDomainErrorMessage() {
        return domainErrorMessage;
    }

    public String getModelIDErrorMessage() {
        return modelIDErrorMessage;
    }

    public String getTitleErrorMessage() {
        return titleErrorMessage;
    }

    public String getPriceErrorMessage() {
        return priceErrorMessage;
    }

    public String getImageErrorMessage() {
        return imageErrorMessage;
    }

    public String getUrlErrorMessage() {
        return urlErrorMessage;
    }

    public void setDomainErrorMessage(String domainErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
    }

    public void setModelIDErrorMessage(String modelIDErrorMessage) {
        this.modelIDErrorMessage = modelIDErrorMessage;
    }

    public void setTitleErrorMessage(String titleErrorMessage) {
        this.titleErrorMessage = titleErrorMessage;
    }

    public void setPriceErrorMessage(String priceErrorMessage) {
        this.priceErrorMessage = priceErrorMessage;
    }

    public void setImageErrorMessage(String imageErrorMessage) {
        this.imageErrorMessage = imageErrorMessage;
    }

    public void setUrlErrorMessage(String urlErrorMessage) {
        this.urlErrorMessage = urlErrorMessage;
    }

    @Override
    public String toString() {
        if (domainErrorMessage != null || modelIDErrorMessage != null || titleErrorMessage != null || priceErrorMessage != null || imageErrorMessage != null || urlErrorMessage != null) {
            ArrayList<String> errorStringList = new ArrayList<String>();
            if (domainErrorMessage != null && !domainErrorMessage.isEmpty()) {
                errorStringList.add(domainErrorMessage);
            }
            if (modelIDErrorMessage != null && !modelIDErrorMessage.isEmpty()) {
                errorStringList.add(modelIDErrorMessage);
            }
            if (titleErrorMessage != null && !titleErrorMessage.isEmpty()) {
                errorStringList.add(titleErrorMessage);
            }
            if (priceErrorMessage != null && !priceErrorMessage.isEmpty()) {
                errorStringList.add(priceErrorMessage);
            }
            if (imageErrorMessage != null && !imageErrorMessage.isEmpty()) {
                errorStringList.add(imageErrorMessage);
            }
            if (urlErrorMessage != null && !urlErrorMessage.isEmpty()) {
                errorStringList.add(urlErrorMessage);
            }
            return "Error: " + String.join(", ", errorStringList);
        }
        return "No error"; 
    }
    
    
}
