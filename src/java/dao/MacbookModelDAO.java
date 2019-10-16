/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MacbookListDTO;
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

    public MacbookModelListDTO getMacbookModelList(boolean getKeyword, boolean getMacbook) throws SQLException, ClassNotFoundException {
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

                MacbookModelKeywordListDTO macbookModelKeywordList = null;
                MacbookListDTO macbookList = null;
                
                if (getKeyword) {
                    MacbookModelKeywordDAO dao = new MacbookModelKeywordDAO();
                    macbookModelKeywordList = dao.getMacbookModelKeywordListByModelID(modelID);
                }
                
                if (getMacbook) {
                    MacbookDAO dao = new MacbookDAO();
                    macbookList = dao.getMacbookListByModelID(modelID);
                }
                
                MacbookModelDTO dto = new MacbookModelDTO(modelID, type, year, ssd, screenSize, touchbar, thumbnail, macbookModelKeywordList, macbookList);
                result.getMacbookModel().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public MacbookModelListDTO filterMacbookModelList(String txtType, String txtYear, String txtSsd, String txtScreenSize, String txtTouchbar) throws SQLException, ClassNotFoundException {
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

                if (txtType != null && !txtType.equals(type)) {
                    continue;
                }

                if (txtYear != null && Integer.parseInt(txtYear) != year) {
                    continue;
                }

                if (txtSsd != null && Integer.parseInt(txtSsd) != ssd) {
                    continue;
                }

                if (txtScreenSize != null && Float.parseFloat(txtScreenSize) != screenSize) {
                    continue;
                }

                if (txtTouchbar != null && Boolean.parseBoolean(txtTouchbar) != touchbar) {
                    continue;
                }

                MacbookDAO dao = new MacbookDAO();
                MacbookListDTO macbookList = dao.getMacbookListByModelID(modelID);

                MacbookModelDTO dto = new MacbookModelDTO(modelID, type, year, ssd, screenSize, touchbar, thumbnail, null, macbookList);
                result.getMacbookModel().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public MacbookModelDTO getMacbookModel(String modelID, boolean getKeyword, boolean getMacbook) throws ClassNotFoundException, SQLException {
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

                MacbookModelKeywordListDTO macbookModelKeywordList = null;
                MacbookListDTO macbookList = null;
                
                if (getKeyword) {
                    MacbookModelKeywordDAO macbookModelKeywordDAO = new MacbookModelKeywordDAO();
                    macbookModelKeywordList = macbookModelKeywordDAO.getMacbookModelKeywordListByModelID(modelID);
                }

                if (getMacbook) {
                    MacbookDAO macbookDAO = new MacbookDAO();
                    macbookList = macbookDAO.getMacbookListByModelID(modelID);
                }
                
                result = new MacbookModelDTO(modelID, type, year, ssd, screenSize, touchbar, thumbnail, macbookModelKeywordList, macbookList);
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
            MacbookModelDTO MacbookModel = dao.getMacbookModel(modelID, false, false);
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
            // Delete all macbook by modelID
            MacbookDAO dao = new MacbookDAO();
            dao.deleteMacbookByModelID(modelID);

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
