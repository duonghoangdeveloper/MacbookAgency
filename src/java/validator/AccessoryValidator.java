/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dto.AccessoryErrorDTO;

/**
 *
 * @author Duong
 */
public class AccessoryValidator {
    private static final String EMPTY_DOMAIN_MESSAGE = "Domain must not be empty";
    private static final String EMPTY_CATEGORY_MESSAGE = "Category must not be empty";
    private static final String EMPTY_TITLE_MESSAGE = "Title must not be empty";
    private static final String EMPTY_PRICE_MESSAGE = "Price must not be empty";
    private static final String EMPTY_IMAGE_MESSAGE = "Image must not be empty";
    private static final String EMPTY_URL_MESSAGE = "URL must not be empty";
    private static final String INVALID_DOMAIN_MESSAGE = "Invalid domain";
    private static final String INVALID_CATEGORY_MESSAGE = "Invalid category";
    private static final String INVALID_TITLE_MESSAGE = "Invalid title";
    private static final String INVALID_PRICE_MESSAGE = "Invalid price";
    private static final String INVALID_IMAGE_MESSAGE = "Invalid image";
    private static final String INVALID_URL_MESSAGE = "Invalid url";
    
    private static final String DOMAIN_REGEX = "^.{1,50}$";
    private static final String CATEGORY_REGEX = "^.{1,50}$";
    private static final String TITLE_REGEX = "^.{1,250}$";
    private static final String PRICE_REGEX = "^\\d+$";
    private static final String IMAGE_REGEX = "^.{1,500}$";
    private static final String URL_REGEX = "^.{1,500}$";
    
    public static AccessoryErrorDTO validateCreateAccessory(String domain, String category, String title, String price, String image, String url) {
        AccessoryErrorDTO result = null;
        
        String domainErrorMessage = null;
        String categoryErrorMessage = null;
        String titleErrorMessage = null;
        String priceErrorMessage = null;
        String imageErrorMessage = null;
        String urlErrorMessage = null;
        
        if (domain == null || domain.isEmpty()) {
            domainErrorMessage = EMPTY_DOMAIN_MESSAGE;
        } else if (!domain.matches(DOMAIN_REGEX)) {
            domainErrorMessage = INVALID_DOMAIN_MESSAGE;
        }
        
        if (category == null || category.isEmpty()) {
            categoryErrorMessage = EMPTY_CATEGORY_MESSAGE;
        } else if (!category.matches(CATEGORY_REGEX)) {
            categoryErrorMessage = INVALID_CATEGORY_MESSAGE;
        }
        
        if (title == null || title.isEmpty()) {
            titleErrorMessage = EMPTY_TITLE_MESSAGE;
        } else if (!title.matches(TITLE_REGEX)) {
            titleErrorMessage = INVALID_TITLE_MESSAGE;
        }
        
        if (price == null || price.isEmpty()) {
            priceErrorMessage = EMPTY_PRICE_MESSAGE;
        } else if (!price.matches(PRICE_REGEX)) {
            priceErrorMessage = INVALID_PRICE_MESSAGE;
        }
        
        if (image == null || image.isEmpty()) {
            imageErrorMessage = EMPTY_IMAGE_MESSAGE;
        } else if (!image.matches(IMAGE_REGEX)) {
            imageErrorMessage = INVALID_IMAGE_MESSAGE;
        }
        
        if (url == null || url.isEmpty()) {
            urlErrorMessage = EMPTY_URL_MESSAGE;
        } else if (!url.matches(URL_REGEX)) {
            urlErrorMessage = INVALID_URL_MESSAGE;
        }
        
        if (domainErrorMessage != null || categoryErrorMessage != null || titleErrorMessage != null || priceErrorMessage != null || imageErrorMessage != null || urlErrorMessage != null) {
            result = new AccessoryErrorDTO(domainErrorMessage, categoryErrorMessage, titleErrorMessage, priceErrorMessage, imageErrorMessage, urlErrorMessage);
        }
        
        return result;
    }
}
