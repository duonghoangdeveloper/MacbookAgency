/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MacbookModelKeywordDTO;
import dto.MacbookModelKeywordListDTO;
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
public class MacbookModelKeywordDAO implements Serializable {
    
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
    
    public MacbookModelKeywordListDTO getMacbookModelKeywordList() throws SQLException, ClassNotFoundException {
        MacbookModelKeywordListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT modelID, keyword FROM MacbookModelKeyword";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new MacbookModelKeywordListDTO();
            while (rs.next()) {
                String modelID = rs.getString("modelID");
                String keyword = rs.getString("keyword").toLowerCase();
                MacbookModelKeywordDTO dto = new MacbookModelKeywordDTO(modelID, keyword);
                result.getMacbookModelKeyword().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public MacbookModelKeywordListDTO getMacbookModelKeywordListByModelID(String modelID) throws SQLException, ClassNotFoundException {
        MacbookModelKeywordListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT modelID, keyword FROM MacbookModelKeyword WHERE modelID = '" + modelID + "'";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new MacbookModelKeywordListDTO();
            while (rs.next()) {
                String keyword = rs.getString("keyword").toLowerCase();
                MacbookModelKeywordDTO dto = new MacbookModelKeywordDTO(modelID, keyword);
                result.getMacbookModelKeyword().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean exists(String modelID, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 modelID, keyword FROM MacbookModelKeyword WHERE modelID = ? AND keyword = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, modelID);
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
    
    public boolean createMacbookModelKeyword(String modelID, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO MacbookModelKeyword (modelID, keyword) VALUES (?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, modelID);
            preStm.setString(2, keyword.toLowerCase());
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean deleteMacbookModelKeyword(String modelID, String keyword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM MacbookModelKeyword WHERE modelID = ? AND keyword = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, modelID);
            preStm.setString(2, keyword.toLowerCase());
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
