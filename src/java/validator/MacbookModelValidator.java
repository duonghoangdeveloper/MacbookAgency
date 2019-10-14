/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.MacbookModelErrorDTO;

/**
 *
 * @author Duong
 */
public class MacbookModelValidator {
    private static final String EMPTY_MODEL_ID_MESSAGE = "ModelID must not be empty";
    private static final String EMPTY_TYPE_MESSAGE = "Type must not be empty";
    private static final String EMPTY_YEAR_MESSAGE = "Year must not be empty";
    private static final String EMPTY_SSD_MESSAGE = "SSD must not be empty";
    private static final String EMPTY_SCREEN_SIZE_MESSAGE = "ScreenSize must not be empty";
    private static final String EMPTY_TOUCHBAR_MESSAGE = "Touchbar must not be empty";
    private static final String EMPTY_THUMBNAIL_MESSAGE = "Thumbnail must not be empty";
    private static final String INVALID_MODEL_ID_MESSAGE = "Invalid modelID";
    private static final String INVALID_TYPE_MESSAGE = "Invalid type";
    private static final String INVALID_YEAR_MESSAGE = "Invalid year";
    private static final String INVALID_SSD_MESSAGE = "Invalid SSD";
    private static final String INVALID_SCREEN_SIZE_MESSAGE = "Invalid screenSize";
    private static final String INVALID_TOUCHBAR_MESSAGE = "Invalid touchbar";
    private static final String INVALID_THUMBNAIL_MESSAGE = "Invalid thumbnail";
    
    private static final String MODEL_ID_REGEX = "^.{1,50}$";
    private static final String TYPE_REGEX = "^.{1,10}$";
    private static final String YEAR_REGEX = "^\\d+$";
    private static final String SSD_REGEX = "^\\d+$";
    private static final String SCREEN_SIZE_REGEX = "^\\d+(\\.\\d+)?$";
    private static final String TOUCHBAR_REGEX = "^(true|false|null)?$";
    private static final String THUMBNAIL_REGEX = "^.{1,500}$";
    
    public static MacbookModelErrorDTO validateCreateMacbookModel(String type, String year, String ssd, String screenSize, String touchbar, String thumbnail) {
        MacbookModelErrorDTO result = null;
        
        String typeErrorMessage = null;
        String yearErrorMessage = null;
        String ssdErrorMessage = null;
        String screenSizeErrorMessage = null;
        String touchbarErrorMessage = null;
        String thumbnailErrorMessage = null;
        
        if (type == null || type.isEmpty()) {
            typeErrorMessage = EMPTY_TYPE_MESSAGE;
        } else if (!type.matches(TYPE_REGEX)) {
            typeErrorMessage = INVALID_TYPE_MESSAGE;
        }
        
        if (year == null || year.isEmpty()) {
            yearErrorMessage = EMPTY_YEAR_MESSAGE;
        } else if (!year.matches(YEAR_REGEX)) {
            yearErrorMessage = INVALID_YEAR_MESSAGE;
        }
        
        if (ssd == null || ssd.isEmpty()) {
            ssdErrorMessage = EMPTY_SSD_MESSAGE;
        } else if (!ssd.matches(SSD_REGEX)) {
            ssdErrorMessage = INVALID_SSD_MESSAGE;
        }
        
        if (screenSize == null || screenSize.isEmpty()) {
            screenSizeErrorMessage = EMPTY_SCREEN_SIZE_MESSAGE;
        } else if (!screenSize.matches(SCREEN_SIZE_REGEX)) {
            screenSizeErrorMessage = INVALID_SCREEN_SIZE_MESSAGE;
        }
        
        if (touchbar == null || touchbar.isEmpty()) {
            touchbarErrorMessage = null;
        } else if (!touchbar.matches(TOUCHBAR_REGEX)) {
            touchbarErrorMessage = INVALID_TOUCHBAR_MESSAGE;
        }
        
        if (thumbnail == null || thumbnail.isEmpty()) {
            thumbnailErrorMessage = null;
        } else if (!thumbnail.matches(THUMBNAIL_REGEX)) {
            thumbnailErrorMessage = INVALID_THUMBNAIL_MESSAGE;
        }
        
        if (typeErrorMessage != null || yearErrorMessage != null ||  ssdErrorMessage != null ||  screenSizeErrorMessage != null ||  touchbarErrorMessage != null || thumbnailErrorMessage != null) {
            result = new MacbookModelErrorDTO(null, typeErrorMessage, yearErrorMessage, ssdErrorMessage, screenSizeErrorMessage, touchbarErrorMessage, thumbnailErrorMessage);
        }
        
        return result;
    }
    
    public static MacbookModelErrorDTO validateUpdateMacbookModel(String modelID, String thumbnail) {
        MacbookModelErrorDTO result = null;
        
        String modelIDErrorMessage = null;
        String thumbnailErrorMessage = null;
        
        if (modelID == null || modelID.isEmpty()) {
            modelIDErrorMessage = EMPTY_MODEL_ID_MESSAGE;
        } else if (!modelID.matches(MODEL_ID_REGEX)) {
            modelIDErrorMessage = INVALID_MODEL_ID_MESSAGE;
        }
        
        if (thumbnail == null || thumbnail.isEmpty()) {
            thumbnailErrorMessage = null;
        } else if (!thumbnail.matches(THUMBNAIL_REGEX)) {
            thumbnailErrorMessage = INVALID_THUMBNAIL_MESSAGE;
        }
        
        if (modelIDErrorMessage != null || thumbnailErrorMessage != null) {
            result = new MacbookModelErrorDTO(modelIDErrorMessage, null, null, null, null, null, thumbnailErrorMessage);
        }
        
        return result;
    }
}
