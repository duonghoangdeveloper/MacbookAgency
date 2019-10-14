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
public class AccessoryErrorDTO implements Serializable {
    private String domainErrorMessage;
    private String categoryErrorMessage;
    private String titleErrorMessage;
    private String priceErrorMessage;
    private String imageErrorMessage;
    private String urlErrorMessage;

    public AccessoryErrorDTO() {
    }

    public AccessoryErrorDTO(String domainErrorMessage, String categoryErrorMessage, String titleErrorMessage, String priceErrorMessage, String imageErrorMessage, String urlErrorMessage) {
        this.domainErrorMessage = domainErrorMessage;
        this.categoryErrorMessage = categoryErrorMessage;
        this.titleErrorMessage = titleErrorMessage;
        this.priceErrorMessage = priceErrorMessage;
        this.imageErrorMessage = imageErrorMessage;
        this.urlErrorMessage = urlErrorMessage;
    }

    public String getDomainErrorMessage() {
        return domainErrorMessage;
    }

    public String getCategoryErrorMessage() {
        return categoryErrorMessage;
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

    public void setCategoryErrorMessage(String categoryErrorMessage) {
        this.categoryErrorMessage = categoryErrorMessage;
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
        if (domainErrorMessage != null || categoryErrorMessage != null || titleErrorMessage != null || priceErrorMessage != null || imageErrorMessage != null || urlErrorMessage != null) {
            ArrayList<String> errorStringList = new ArrayList<String>();
            if (domainErrorMessage != null && !domainErrorMessage.isEmpty()) {
                errorStringList.add(domainErrorMessage);
            }
            if (categoryErrorMessage != null && !categoryErrorMessage.isEmpty()) {
                errorStringList.add(categoryErrorMessage);
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
