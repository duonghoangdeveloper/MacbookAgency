/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccessoryCategoryKeywordDTO;
import dto.AccessoryCategoryKeywordListDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtilities;

/**
 *
 * @author Duong
 */
public class AccessoryCategoryKeywordDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    private void closeConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                
            }
        }
        
        if (preStm != null) {
            try {
                preStm.close();
            } catch (SQLException ex) {
                
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                
            }
        }
    }
    
    public AccessoryCategoryKeywordListDTO getAccessoryCategoryKeywordList() throws SQLException, ClassNotFoundException {
        AccessoryCategoryKeywordListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT category, keyword FROM AccessoryCategoryKeyword";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new AccessoryCategoryKeywordListDTO();
            while (rs.next()) {
                String category = rs.getString("category");
                String keyword = rs.getString("keyword").toLowerCase();
                AccessoryCategoryKeywordDTO dto = new AccessoryCategoryKeywordDTO(category, keyword);
                result.getAccessoryCategoryKeyword().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public AccessoryCategoryKeywordListDTO getAccessoryCategoryKeywordListByCategory(String category) throws SQLException, ClassNotFoundException {
        AccessoryCategoryKeywordListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT category, keyword FROM AccessoryCategoryKeyword WHERE category = '" + category + "'";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new AccessoryCategoryKeywordListDTO();
            while (rs.next()) {
                String keyword = rs.getString("keyword").toLowerCase();
                AccessoryCategoryKeywordDTO dto = new AccessoryCategoryKeywordDTO(category, keyword);
                result.getAccessoryCategoryKeyword().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean exists(String category, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 category, keyword FROM AccessoryCategoryKeyword WHERE category = ? AND keyword = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, category);
            preStm.setString(2, keyword.toLowerCase());
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean createAccessoryCategoryKeyword(String category, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO AccessoryCategoryKeyword (category, keyword) VALUES (?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, category);
            preStm.setString(2, keyword.toLowerCase());
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean deleteAccessoryCategoryKeyword(String category, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM AccessoryCategoryKeyword WHERE category = ? AND keyword = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, category);
            preStm.setString(2, keyword.toLowerCase());
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
