/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MacbookDTO;
import dto.MacbookListDTO;
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
public class MacbookDAO implements Serializable {

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

    public MacbookListDTO getMacbookList() throws SQLException, ClassNotFoundException {
        MacbookListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, modelID, title, price, image, url FROM Macbook";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new MacbookListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String modelID = rs.getString("modelID");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String url = rs.getString("url");
                MacbookDTO dto = new MacbookDTO(domain, modelID, title, price, image, url);
                result.getMacbook().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public MacbookListDTO getMacbookListByModelID(String modelID) throws SQLException, ClassNotFoundException {
        MacbookListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, title, price, image, url FROM Macbook WHERE modelID = '" + modelID + "'";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new MacbookListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String url = rs.getString("url");
                MacbookDTO dto = new MacbookDTO(domain, modelID, title, price, image, url);
                result.getMacbook().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean exists(String domain, String modelID, String title) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();

            String sql = "SELECT TOP 1 domain, modelID, title FROM Macbook WHERE domain = ? AND modelID = ? AND title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, modelID);
            preStm.setString(3, title);

            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean createMacbook(String domain, String modelID, String title, int price, String image, String url) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO Macbook (domain, modelID, title, price, image, url) VALUES (?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, modelID);
            preStm.setString(3, title);
            preStm.setInt(4, price);
            preStm.setString(5, image);
            preStm.setString(6, url);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public boolean updateMacbook(String domain, String modelID, String title, int price, String image, String url) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "UPDATE Macbook SET price = ?, image = ?, url = ? WHERE domain = ? AND modelID = ? AND title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(4, domain);
            preStm.setString(5, modelID);
            preStm.setString(6, title);
            preStm.setInt(1, price);
            preStm.setString(2, image);
            preStm.setString(3, url);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean deleteMacbook(String domain, String modelID, String title) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Macbook WHERE modelID = ? AND keyword = ? ADN title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, modelID);
            preStm.setString(3, title);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public int deleteMacbookByModelID(String modelID) throws ClassNotFoundException, SQLException {
        int result = 0;

        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Macbook WHERE modelID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, modelID);
            result = preStm.executeUpdate();
        } finally {
            closeConnection();
        }

        return result;
    }
}
