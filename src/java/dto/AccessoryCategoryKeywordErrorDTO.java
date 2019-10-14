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
public class AccessoryCategoryKeywordErrorDTO implements Serializable {
    private String categoryErrorMessage;
    private String keywordErrorMessage;

    public AccessoryCategoryKeywordErrorDTO() {
    }

    public AccessoryCategoryKeywordErrorDTO(String categoryErrorMessage, String keywordErrorMessage) {
        this.categoryErrorMessage = categoryErrorMessage;
        this.keywordErrorMessage = keywordErrorMessage;
    }

    public String getCategoryErrorMessage() {
        return categoryErrorMessage;
    }

    public String getKeywordErrorMessage() {
        return keywordErrorMessage;
    }

    public void setCategoryErrorMessage(String categoryErrorMessage) {
        this.categoryErrorMessage = categoryErrorMessage;
    }

    public void setKeywordErrorMessage(String keywordErrorMessage) {
        this.keywordErrorMessage = keywordErrorMessage;
    }
}
