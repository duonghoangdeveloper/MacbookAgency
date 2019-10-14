/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccessoryDTO;
import dto.AccessoryListDTO;
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
public class AccessoryDAO implements Serializable {

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

    public AccessoryListDTO getAccessoryList() throws SQLException, ClassNotFoundException {
        AccessoryListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, category, title, price, image, url FROM Accessory";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new AccessoryListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String category = rs.getString("category");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String url = rs.getString("url");
                AccessoryDTO dto = new AccessoryDTO(domain, category, title, price, image, url);
                result.getAccessory().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public AccessoryListDTO getAccessoryListByCategory(String category) throws SQLException, ClassNotFoundException {
        AccessoryListDTO result = null;

        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, title, price, image, url FROM Accessory WHERE category = '" + category + "'";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();

            result = new AccessoryListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String url = rs.getString("url");
                AccessoryDTO dto = new AccessoryDTO(domain, category, title, price, image, url);
                result.getAccessory().add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean exists(String domain, String category, String title) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();

            String sql = "SELECT TOP 1 domain, category, title FROM Accessory WHERE domain = ? AND category = ? AND title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, category);
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

    public boolean createAccessory(String domain, String category, String title, int price, String image, String url) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO Accessory (domain, category, title, price, image, url) VALUES (?, ?, ?, ?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, category);
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
    
    public boolean updateAccessory(String domain, String category, String title, int price, String image, String url) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "UPDATE Accessory SET price = ?, image = ?, url = ? WHERE domain = ? AND category = ? AND title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(4, domain);
            preStm.setString(5, category);
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

    public boolean deleteAccessory(String domain, String category, String title) throws ClassNotFoundException, SQLException {
        boolean result = false;

        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Accessory WHERE category = ? AND keyword = ? ADN title = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, category);
            preStm.setString(3, title);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }

        return result;
    }
}
