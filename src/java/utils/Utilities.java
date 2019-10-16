/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dto.AccessoryCategoryDTO;
import dto.AccessoryCategoryKeywordDTO;
import dto.AccessoryCategoryListDTO;
import dto.MacbookModelDTO;
import dto.MacbookModelKeywordDTO;
import dto.MacbookModelListDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Duong
 */
public class Utilities {

    static private final String ERROR_PAGE = "error.jsp";
    static private final String CRAWL_SERVLET = "CrawlServlet";

    // Admin
    static private final String LOGIN_SERVLET = "LoginServlet";
    static private final String LOGOUT_SERVLET = "LogoutServlet";
    static private final String GET_ADMIN_LIST_SERVLET = "GetAdminListServlet";
    static private final String CREATE_ADMIN_SERVLET = "CreateAdminServlet";
    static private final String UPDATE_ADMIN_SERVLET = "UpdateAdminServlet";
    static private final String DELETE_ADMIN_SERVLET = "DeleteAdminServlet";

    // Domain
    static private final String GET_DOMAIN_LIST_SERVLET = "GetDomainListServlet";
    static private final String CREATE_DOMAIN_SERVLET = "CreateDomainServlet";
    static private final String UPDATE_DOMAIN_SERVLET = "UpdateDomainServlet";
    static private final String DELETE_DOMAIN_SERVLET = "DeleteDomainServlet";

    // Page
    static private final String CREATE_PAGE_SERVLET = "CreatePageServlet";
    static private final String DELETE_PAGE_SERVLET = "DeletePageServlet";

    // MacbookModel
    static private final String FILTER_MACBOOK_MODEL_LIST_SERVLET = "FilterMacbookModelListServlet";
    static private final String GET_MACBOOK_MODEL_LIST_SERVLET = "GetMacbookModelListServlet";
    static private final String GET_MACBOOK_MODEL_LIST_XML_STRING_SERVLET = "GetMacbookModelListXMLStringServlet";
    static private final String GET_MACBOOK_MODEL_XML_STRING_SERVLET = "GetMacbookModelXMLStringServlet";
    static private final String CREATE_MACBOOK_MODEL_SERVLET = "CreateMacbookModelServlet";
    static private final String UPDATE_MACBOOK_MODEL_SERVLET = "UpdateMacbookModelServlet";
    static private final String DELETE_MACBOOK_MODEL_SERVLET = "DeleteMacbookModelServlet";

    // MacbookModelKeyword
    static private final String CREATE_MACBOOK_MODEL_KEYWORD_SERVLET = "CreateMacbookModelKeywordServlet";
    static private final String DELETE_MACBOOK_MODEL_KEYWORD_SERVLET = "DeleteMacbookModelKeywordServlet";

    // MacbookModel
    static private final String GET_CATEGORY_LIST_SERVLET = "GetCategoryListServlet";
    static private final String GET_ACCESSORY_CATEGORY_LIST_SERVLET = "GetAccessoryCategoryListServlet";
    static private final String CREATE_ACCESSORY_CATEGORY_SERVLET = "CreateAccessoryCategoryServlet";
    static private final String UPDATE_ACCESSORY_CATEGORY_SERVLET = "UpdateAccessoryCategoryServlet";
    static private final String DELETE_ACCESSORY_CATEGORY_SERVLET = "DeleteAccessoryCategoryServlet";

    // AccessoryCategoryKeyword
    static private final String CREATE_ACCESSORY_CATEGORY_KEYWORD_SERVLET = "CreateAccessoryCategoryKeywordServlet";
    static private final String DELETE_ACCESSORY_CATEGORY_KEYWORD_SERVLET = "DeleteAccessoryCategoryKeywordServlet";
    
    // Crawl
    static private final String CRAWL_DOMAIN_SERVLET = "CrawlDomainServlet";
    
    // Accessory
    static private final String GET_ACCESSORY_LIST_XML_STRING_SERVLET = "GetAccessoryListXMLStringServlet";
    static private final String COUNT_ACCESSORY_SERVLET = "CountAccessoryServlet";
    static private final String CLICK_ACCESSORY_SERVLET = "ClickAccessoryServlet";
    static private final String DELETE_ACCESSORY_SERVLET = "DeleteAccessoryServlet";

    public static String getURLByAction(String action) {

        if ("Crawl".equals(action)) {
            return CRAWL_SERVLET;
        } else if ("Login".equals(action)) {
            return LOGIN_SERVLET;
        } else if ("Logout".equals(action)) {
            return LOGOUT_SERVLET;
        } else if ("Logout".equals(action)) {
            return LOGOUT_SERVLET;
        } else if ("GetAdminList".equals(action)) {
            return GET_ADMIN_LIST_SERVLET;
        } else if ("CreateAdmin".equals(action)) {
            return CREATE_ADMIN_SERVLET;
        } else if ("UpdateAdmin".equals(action)) {
            return UPDATE_ADMIN_SERVLET;
        } else if ("DeleteAdmin".equals(action)) {
            return DELETE_ADMIN_SERVLET;
        } else if ("GetDomainList".equals(action)) {
            return GET_DOMAIN_LIST_SERVLET;
        } else if ("CreateDomain".equals(action)) {
            return CREATE_DOMAIN_SERVLET;
        } else if ("UpdateDomain".equals(action)) {
            return UPDATE_DOMAIN_SERVLET;
        } else if ("DeleteDomain".equals(action)) {
            return DELETE_DOMAIN_SERVLET;
        } else if ("CreatePage".equals(action)) {
            return CREATE_PAGE_SERVLET;
        } else if ("DeletePage".equals(action)) {
            return DELETE_PAGE_SERVLET;
        } else if ("GetMacbookModelList".equals(action)) {
            return GET_MACBOOK_MODEL_LIST_SERVLET;
        } else if ("GetMacbookModelListXMLString".equals(action)) {
            return GET_MACBOOK_MODEL_LIST_XML_STRING_SERVLET;
        } else if ("GetMacbookModelXMLString".equals(action)) {
            return GET_MACBOOK_MODEL_XML_STRING_SERVLET;
        } else if ("FilterMacbookModelList".equals(action)) {
            return FILTER_MACBOOK_MODEL_LIST_SERVLET;
        } else if ("CreateMacbookModel".equals(action)) {
            return CREATE_MACBOOK_MODEL_SERVLET;
        } else if ("UpdateMacbookModel".equals(action)) {
            return UPDATE_MACBOOK_MODEL_SERVLET;
        } else if ("DeleteMacbookModel".equals(action)) {
            return DELETE_MACBOOK_MODEL_SERVLET;
        } else if ("CreateMacbookModelKeyword".equals(action)) {
            return CREATE_MACBOOK_MODEL_KEYWORD_SERVLET;
        } else if ("DeleteMacbookModelKeyword".equals(action)) {
            return DELETE_MACBOOK_MODEL_KEYWORD_SERVLET;
        } else if ("GetCategoryList".equals(action)) {
            return GET_CATEGORY_LIST_SERVLET;
        }  else if ("GetAccessoryCategoryList".equals(action)) {
            return GET_ACCESSORY_CATEGORY_LIST_SERVLET;
        } else if ("CreateAccessoryCategory".equals(action)) {
            return CREATE_ACCESSORY_CATEGORY_SERVLET;
        } else if ("UpdateAccessoryCategory".equals(action)) {
            return UPDATE_ACCESSORY_CATEGORY_SERVLET;
        } else if ("DeleteAccessoryCategory".equals(action)) {
            return DELETE_ACCESSORY_CATEGORY_SERVLET;
        } else if ("CreateAccessoryCategoryKeyword".equals(action)) {
            return CREATE_ACCESSORY_CATEGORY_KEYWORD_SERVLET;
        } else if ("DeleteAccessoryCategoryKeyword".equals(action)) {
            return DELETE_ACCESSORY_CATEGORY_KEYWORD_SERVLET;
        } else if ("CrawlDomain".equals(action)) {
            return CRAWL_DOMAIN_SERVLET;
        } else if ("GetAccessoryListXMLString".equals(action)) {
            return GET_ACCESSORY_LIST_XML_STRING_SERVLET;
        } else if ("CountAccessory".equals(action)) {
            return COUNT_ACCESSORY_SERVLET;
        } else if ("ClickAccessory".equals(action)) {
            return CLICK_ACCESSORY_SERVLET;
        } else if ("DeleteAccessory".equals(action)) {
            return DELETE_ACCESSORY_SERVLET;
        }

        return ERROR_PAGE;
    }

    public static String generateModelID(String type, int year, int ssd, float screenSize, boolean touchbar) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        return type + "-" + year + "-" + ssd + "-" + screenSize + (touchbar ? "-touchbar" : "");
    }

    public static void printStream(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String readLine;
        while (((readLine = br.readLine()) != null)) {
            System.out.println(readLine);
        }
    }
    
    public static String getCategoryFromTitle(String title, AccessoryCategoryListDTO accessoryCategoryList)  {
        String result = null;
        
        for (AccessoryCategoryDTO accessoryCategory : accessoryCategoryList.getAccessoryCategory()) {
            for (AccessoryCategoryKeywordDTO accessoryCategoryKeyword : accessoryCategory.getAccessoryCategoryKeywordList().getAccessoryCategoryKeyword()) {
                String keyword = accessoryCategoryKeyword.getKeyword();
                if (title.toLowerCase().contains(keyword)) {
                    return accessoryCategoryKeyword.getCategory();
                }
            }
        }
        
        return result;
    }
    
    public static String getModelIDFromTitle(String title, MacbookModelListDTO macbookModelList)  {
        String result = null;
        
        for (MacbookModelDTO macbookModel : macbookModelList.getMacbookModel()) {
            for (MacbookModelKeywordDTO macbookModelKeyword : macbookModel.getMacbookModelKeywordList().getMacbookModelKeyword()) {
                String keyword = macbookModelKeyword.getKeyword();
                if (title.toLowerCase().contains(keyword)) {
                    return macbookModelKeyword.getModelID();
                }
            }
        }
        
        return result;
    }
}
