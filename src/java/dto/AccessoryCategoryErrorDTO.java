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
public class AccessoryCategoryErrorDTO implements Serializable {
    private String categoryErrorMessage;
    private String thumbnailErrorMessage;

    public AccessoryCategoryErrorDTO() {
    }

    public AccessoryCategoryErrorDTO(String categoryErrorMessage, String thumbnailErrorMessage) {
        this.categoryErrorMessage = categoryErrorMessage;
        this.thumbnailErrorMessage = thumbnailErrorMessage;
    }

    public String getCategoryErrorMessage() {
        return categoryErrorMessage;
    }

    public String getThumbnailErrorMessage() {
        return thumbnailErrorMessage;
    }

    public void setCategoryErrorMessage(String categoryErrorMessage) {
        this.categoryErrorMessage = categoryErrorMessage;
    }

    public void setThumbnailErrorMessage(String thumbnailErrorMessage) {
        this.thumbnailErrorMessage = thumbnailErrorMessage;
    }
}
