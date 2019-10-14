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
public class MacbookModelKeywordErrorDTO implements Serializable {
    private String modelIDErrorMessage;
    private String keywordErrorMessage;

    public MacbookModelKeywordErrorDTO() {
    }

    public MacbookModelKeywordErrorDTO(String modelIDErrorMessage, String keywordErrorMessage) {
        this.modelIDErrorMessage = modelIDErrorMessage;
        this.keywordErrorMessage = keywordErrorMessage;
    }

    public String getModelIDErrorMessage() {
        return modelIDErrorMessage;
    }

    public String getKeywordErrorMessage() {
        return keywordErrorMessage;
    }

    public void setModelIDErrorMessage(String modelIDErrorMessage) {
        this.modelIDErrorMessage = modelIDErrorMessage;
    }

    public void setKeywordErrorMessage(String keywordErrorMessage) {
        this.keywordErrorMessage = keywordErrorMessage;
    }
}
