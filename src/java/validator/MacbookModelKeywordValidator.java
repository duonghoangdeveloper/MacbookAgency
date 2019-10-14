/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.MacbookModelKeywordErrorDTO;

/**
 *
 * @author Duong
 */
public class MacbookModelKeywordValidator {
    private static final String EMPTY_MODEL_ID_MESSAGE = "ModelID must not be empty";
    private static final String EMPTY_KEYWORD_MESSAGE = "Keyword must not be empty";
    private static final String INVALID_MODEL_ID_MESSAGE = "Invalid modelID";
    private static final String INVALID_KEYWORD_MESSAGE = "Invalid keyword";
    
    private static final String MODEL_ID_REGEX = "^.{1,50}$";
    private static final String KEYWORD_REGEX = "^.{1,50}$";
    
    public static MacbookModelKeywordErrorDTO validateCreateMacbookModelKeyword(String modelID, String keyword) {
        MacbookModelKeywordErrorDTO result = null;
        
        String modelIDErrorMessage = null;
        String keywordErrorMessage = null;
        
        if (modelID == null || modelID.isEmpty()) {
            modelIDErrorMessage = EMPTY_MODEL_ID_MESSAGE;
        } else if (!modelID.matches(MODEL_ID_REGEX)) {
            modelIDErrorMessage = INVALID_MODEL_ID_MESSAGE;
        }
        
        if (keyword == null || keyword.isEmpty()) {
            keywordErrorMessage = EMPTY_KEYWORD_MESSAGE;
        } else if (!keyword.matches(KEYWORD_REGEX)) {
            keywordErrorMessage = INVALID_KEYWORD_MESSAGE;
        }
        
        if (modelIDErrorMessage != null || keywordErrorMessage != null) {
            result = new MacbookModelKeywordErrorDTO(modelIDErrorMessage, keywordErrorMessage);
        }
        
        return result;
    }
    
    public static MacbookModelKeywordErrorDTO validateUpdateMacbookModelKeyword(String modelID, String keyword) {
        MacbookModelKeywordErrorDTO result = null;
        
        String modelIDErrorMessage = null;
        String keywordErrorMessage = null;
        
        if (modelID == null || modelID.isEmpty()) {
            modelIDErrorMessage = EMPTY_MODEL_ID_MESSAGE;
        } else if (!modelID.matches(MODEL_ID_REGEX)) {
            modelIDErrorMessage = INVALID_MODEL_ID_MESSAGE;
        }
        
        if (keyword == null || keyword.isEmpty()) {
            keywordErrorMessage = EMPTY_KEYWORD_MESSAGE;
        } else if (!keyword.matches(KEYWORD_REGEX)) {
            keywordErrorMessage = INVALID_KEYWORD_MESSAGE;
        }
        
        if (modelIDErrorMessage != null || keywordErrorMessage != null) {
            result = new MacbookModelKeywordErrorDTO(modelIDErrorMessage, keywordErrorMessage);
        }
        
        return result;
    }
}
