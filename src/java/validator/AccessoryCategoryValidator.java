/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.AccessoryCategoryErrorDTO;

/**
 *
 * @author Duong
 */
public class AccessoryCategoryValidator {
    private static final String EMPTY_CATEGORY_MESSAGE = "Category must not be empty";
    private static final String EMPTY_THUMBNAIL_MESSAGE = "Thumbnail must not be empty";
    private static final String INVALID_CATEGORY_MESSAGE = "Invalid category";
    private static final String INVALID_THUMBNAIL_MESSAGE = "Invalid thumbnail";
    
    private static final String CATEGORY_REGEX = "^.{1,50}$";
    private static final String THUMBNAIL_REGEX = "^.{1,500}$";
    
    public static AccessoryCategoryErrorDTO validateCreateAccessoryCategory(String category, String thumbnail) {
        AccessoryCategoryErrorDTO result = null;
        
        String categoryErrorMessage = null;
        String thumbnailErrorMessage = null;
        
        if (category == null || category.isEmpty()) {
            categoryErrorMessage = EMPTY_CATEGORY_MESSAGE;
        } else if (!category.matches(CATEGORY_REGEX)) {
            categoryErrorMessage = INVALID_CATEGORY_MESSAGE;
        }
        
        if (thumbnail == null || thumbnail.isEmpty()) {
            thumbnailErrorMessage = null;
        } else if (!thumbnail.matches(THUMBNAIL_REGEX)) {
            thumbnailErrorMessage = INVALID_THUMBNAIL_MESSAGE;
        }
        
        if (categoryErrorMessage != null || thumbnailErrorMessage != null) {
            result = new AccessoryCategoryErrorDTO(categoryErrorMessage, thumbnailErrorMessage);
        }
        
        return result;
    }
    
    public static AccessoryCategoryErrorDTO validateUpdateAccessoryCategory(String category, String thumbnail) {
        AccessoryCategoryErrorDTO result = null;
        
        String categoryErrorMessage = null;
        String thumbnailErrorMessage = null;
        
        if (category == null || category.isEmpty()) {
            categoryErrorMessage = EMPTY_CATEGORY_MESSAGE;
        } else if (!category.matches(CATEGORY_REGEX)) {
            categoryErrorMessage = INVALID_CATEGORY_MESSAGE;
        }
        
        if (thumbnail == null || thumbnail.isEmpty()) {
            thumbnailErrorMessage = null;
        } else if (!thumbnail.matches(THUMBNAIL_REGEX)) {
            thumbnailErrorMessage = INVALID_THUMBNAIL_MESSAGE;
        }
        
        if (categoryErrorMessage != null || thumbnailErrorMessage != null) {
            result = new AccessoryCategoryErrorDTO(categoryErrorMessage, thumbnailErrorMessage);
        }
        
        return result;
    }
}
