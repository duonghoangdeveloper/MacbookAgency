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
public class MacbookModelErrorDTO implements Serializable {
    private String modelIDErrorMessage;
    private String typeErrorMessage;
    private String yearErrorMessage;
    private String ssdErrorMessage;
    private String screenSizeErrorMessage;
    private String touchbarErrorMessage;
    private String thumbnailErrorMessage;

    public MacbookModelErrorDTO() {
    }

    public MacbookModelErrorDTO(String modelIDErrorMessage, String typeErrorMessage, String yearErrorMessage, String ssdErrorMessage, String screenSizeErrorMessage, String touchbarErrorMessage, String thumbnailErrorMessage) {
        this.modelIDErrorMessage = modelIDErrorMessage;
        this.typeErrorMessage = typeErrorMessage;
        this.yearErrorMessage = yearErrorMessage;
        this.ssdErrorMessage = ssdErrorMessage;
        this.screenSizeErrorMessage = screenSizeErrorMessage;
        this.touchbarErrorMessage = touchbarErrorMessage;
        this.thumbnailErrorMessage = thumbnailErrorMessage;
    }

    public String getModelIDErrorMessage() {
        return modelIDErrorMessage;
    }

    public String getTypeErrorMessage() {
        return typeErrorMessage;
    }

    public String getYearErrorMessage() {
        return yearErrorMessage;
    }

    public String getSsdErrorMessage() {
        return ssdErrorMessage;
    }

    public String getScreenSizeErrorMessage() {
        return screenSizeErrorMessage;
    }

    public String getTouchbarErrorMessage() {
        return touchbarErrorMessage;
    }

    public String getThumbnailErrorMessage() {
        return thumbnailErrorMessage;
    }

    public void setModelIDErrorMessage(String modelIDErrorMessage) {
        this.modelIDErrorMessage = modelIDErrorMessage;
    }

    public void setTypeErrorMessage(String typeErrorMessage) {
        this.typeErrorMessage = typeErrorMessage;
    }

    public void setYearErrorMessage(String yearErrorMessage) {
        this.yearErrorMessage = yearErrorMessage;
    }

    public void setSsdErrorMessage(String ssdErrorMessage) {
        this.ssdErrorMessage = ssdErrorMessage;
    }

    public void setScreenSizeErrorMessage(String screenSizeErrorMessage) {
        this.screenSizeErrorMessage = screenSizeErrorMessage;
    }

    public void setTouchbarErrorMessage(String touchbarErrorMessage) {
        this.touchbarErrorMessage = touchbarErrorMessage;
    }

    public void setThumbnailErrorMessage(String thumbnailErrorMessage) {
        this.thumbnailErrorMessage = thumbnailErrorMessage;
    }
}
