/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccessoryCategoryDTO;
import dto.AccessoryCategoryListDTO;
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
public class AccessoryCategoryDAO implements Serializable {

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

    public AccessoryCategoryListDTO getAccessoryCategoryList() throws SQLException, ClassNotFoundException {
        AccessoryCategoryListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT category, thumbnail FROM AccessoryCategory";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new AccessoryCategoryListDTO();
            while (rs.next()) {
                String category = rs.getString("category");
                String thumbnail = rs.getString("thumbnail");

                AccessoryCategoryKeywordDAO dao = new AccessoryCategoryKeywordDAO();
                AccessoryCategoryKeywordListDTO accessoryCategoryKeywordList = dao.getAccessoryCategoryKeywordListByCategory(category);

                AccessoryCategoryDTO dto = new AccessoryCategoryDTO(category, thumbnail, accessoryCategoryKeywordList);
                result.getAccessoryCategory().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public AccessoryCategoryDTO getAccessoryCategory(String category) throws ClassNotFoundException, SQLException {
        AccessoryCategoryDTO result = null;

        try {
            conn = DBUtilities.createConnection();

            String sql = "SELECT thumbnail FROM AccessoryCategory WHERE category = '" + category + "'";
            preStm = conn.prepareStatement(sql);

            rs = preStm.executeQuery();
            if (rs.next()) {
                String thumbnail = rs.getString("thumbnail");

                AccessoryCategoryKeywordDAO dao = new AccessoryCategoryKeywordDAO();
                AccessoryCategoryKeywordListDTO accessoryCategoryKeywordList = dao.getAccessoryCategoryKeywordListByCategory(category);

                result = new AccessoryCategoryDTO(category, thumbnail, accessoryCategoryKeywordList);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public boolean exists(String category) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 category FROM AccessoryCategory WHERE category = '" + category + "'";
            preStm = conn.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }

    public boolean createAccessoryCategory(String category, String thumbnail) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO AccessoryCategory (category, thumbnail) VALUES (?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, category);
            preStm.setString(2, thumbnail);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean updateAccessoryCategory(String category, String thumbnail) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
            AccessoryCategoryDTO AccessoryCategory = dao.getAccessoryCategory(category);
            if (AccessoryCategory != null) {
                String newThumbnail = AccessoryCategory.getThumbnail();
                String sql = "UPDATE AccessoryCategory SET thumbnail = ? WHERE category = '" + category + "'";
                preStm = conn.prepareStatement(sql);

                if (thumbnail != null) {
                    newThumbnail = thumbnail;
                }
                
                preStm.setString(1, newThumbnail);

                result = preStm.executeUpdate() == 1;
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean deleteAccessoryCategory(String category) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM AccessoryCategory WHERE category = '" + category + "'";
            preStm = conn.prepareStatement(sql);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }
}
