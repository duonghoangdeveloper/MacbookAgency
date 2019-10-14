/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.AccessoryCategoryKeywordErrorDTO;

/**
 *
 * @author Duong
 */
public class AccessoryCategoryKeywordValidator {
    private static final String EMPTY_CATEGORY_MESSAGE = "Category must not be empty";
    private static final String EMPTY_KEYWORD_MESSAGE = "Keyword must not be empty";
    private static final String INVALID_CATEGORY_MESSAGE = "Invalid category";
    private static final String INVALID_KEYWORD_MESSAGE = "Invalid keyword";
    
    private static final String CATEGORY_REGEX = "^.{1,50}$";
    private static final String KEYWORD_REGEX = "^.{1,50}$";
    
    public static AccessoryCategoryKeywordErrorDTO validateCreateAccessoryCategoryKeyword(String category, String keyword) {
        AccessoryCategoryKeywordErrorDTO result = null;
        
        String categoryErrorMessage = null;
        String keywordErrorMessage = null;
        
        if (category == null || category.isEmpty()) {
            categoryErrorMessage = EMPTY_CATEGORY_MESSAGE;
        } else if (!category.matches(CATEGORY_REGEX)) {
            categoryErrorMessage = INVALID_CATEGORY_MESSAGE;
        }
        
        if (keyword == null || keyword.isEmpty()) {
            keywordErrorMessage = EMPTY_KEYWORD_MESSAGE;
        } else if (!keyword.matches(KEYWORD_REGEX)) {
            keywordErrorMessage = INVALID_KEYWORD_MESSAGE;
        }
        
        if (categoryErrorMessage != null || keywordErrorMessage != null) {
            result = new AccessoryCategoryKeywordErrorDTO(categoryErrorMessage, keywordErrorMessage);
        }
        
        return result;
    }
    
    public static AccessoryCategoryKeywordErrorDTO validateUpdateAccessoryCategoryKeyword(String category, String keyword) {
        AccessoryCategoryKeywordErrorDTO result = null;
        
        String categoryErrorMessage = null;
        String keywordErrorMessage = null;
        
        if (category == null || category.isEmpty()) {
            categoryErrorMessage = EMPTY_CATEGORY_MESSAGE;
        } else if (!category.matches(CATEGORY_REGEX)) {
            categoryErrorMessage = INVALID_CATEGORY_MESSAGE;
        }
        
        if (keyword == null || keyword.isEmpty()) {
            keywordErrorMessage = EMPTY_KEYWORD_MESSAGE;
        } else if (!keyword.matches(KEYWORD_REGEX)) {
            keywordErrorMessage = INVALID_KEYWORD_MESSAGE;
        }
        
        if (categoryErrorMessage != null || keywordErrorMessage != null) {
            result = new AccessoryCategoryKeywordErrorDTO(categoryErrorMessage, keywordErrorMessage);
        }
        
        return result;
    }
}
