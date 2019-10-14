/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MacbookModelDTO;
import dto.MacbookModelListDTO;
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
public class MacbookModelDAO implements Serializable {

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

    public MacbookModelListDTO getMacbookModelList() throws SQLException, ClassNotFoundException {
        MacbookModelListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT modelID, type, year, ssd, screenSize, touchbar, thumbnail FROM MacbookModel";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new MacbookModelListDTO();
            while (rs.next()) {
                String modelID = rs.getString("modelID");
                String type = rs.getString("type");
                int year = rs.getInt("year");
                int ssd = rs.getInt("ssd");
                float screenSize = rs.getFloat("screenSize");
                boolean touchbar = rs.getBoolean("touchbar");
                String thumbnail = rs.getString("thumbnail");

                MacbookModelKeywordDAO dao = new MacbookModelKeywordDAO();
                MacbookModelKeywordListDTO macbookModelKeywordList = dao.getMacbookModelKeywordListByModelID(modelID);

                MacbookModelDTO dto = new MacbookModelDTO(modelID, type, year, ssd, screenSize, touchbar, thumbnail, macbookModelKeywordList);
                result.getMacbookModel().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public MacbookModelDTO getMacbookModel(String modelID) throws ClassNotFoundException, SQLException {
        MacbookModelDTO result = null;

        try {
            conn = DBUtilities.createConnection();

            String sql = "SELECT type, year, ssd, screenSize, touchbar, thumbnail FROM MacbookModel WHERE modelID = '" + modelID + "'";
            preStm = conn.prepareStatement(sql);

            rs = preStm.executeQuery();
            if (rs.next()) {
                String type = rs.getString("type");
                int year = rs.getInt("year");
                int ssd = rs.getInt("ssd");
                float screenSize = rs.getFloat("screenSize");
                boolean touchbar = rs.getBoolean("touchbar");
                String thumbnail = rs.getString("thumbnail");

                MacbookModelKeywordDAO dao = new MacbookModelKeywordDAO();
                MacbookModelKeywordListDTO macbookModelKeywordList = dao.getMacbookModelKeywordListByModelID(modelID);

                result = new MacbookModelDTO(modelID, type, year, ssd, screenSize, touchbar, thumbnail, macbookModelKeywordList);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public boolean exists(String modelID) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 modelID FROM MacbookModel WHERE modelID = '" + modelID + "'";
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

    public boolean createMacbookModel(String modelID, String type, int year, int ssd, float screenSize, boolean touchbar, String thumbnail) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO MacbookModel (modelID, type, year, ssd, screenSize, touchbar, thumbnail) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, modelID);
            preStm.setString(2, type);
            preStm.setInt(3, year);
            preStm.setInt(4, ssd);
            preStm.setFloat(5, screenSize);
            preStm.setBoolean(6, touchbar);
            preStm.setString(7, thumbnail);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean updateMacbookModel(String modelID, String thumbnail) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            MacbookModelDAO dao = new MacbookModelDAO();
            MacbookModelDTO MacbookModel = dao.getMacbookModel(modelID);
            if (MacbookModel != null) {
                String newThumbnail = MacbookModel.getThumbnail();
                String sql = "UPDATE MacbookModel SET thumbnail = ? WHERE modelID = '" + modelID + "'";
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

    public boolean deleteMacbookModel(String modelID) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM MacbookModel WHERE modelID = '" + modelID + "'";
            preStm = conn.prepareStatement(sql);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }
}
