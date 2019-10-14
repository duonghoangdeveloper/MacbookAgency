/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PageDTO;
import dto.PageListDTO;
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
public class PageDAO implements Serializable {
    
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
    
    public PageListDTO getPageList() throws SQLException, ClassNotFoundException {
        PageListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, path FROM Page";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new PageListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String path = rs.getString("path");
                PageDTO dto = new PageDTO(domain, path);
                result.getPage().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public PageListDTO getPageListByDomain(String domain) throws SQLException, ClassNotFoundException {
        PageListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, path FROM Page WHERE domain = '" + domain + "'";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new PageListDTO();
            while (rs.next()) {
                String path = rs.getString("path");
                PageDTO dto = new PageDTO(domain, path);
                result.getPage().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean exists(String domain, String path) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 domain, path FROM Page WHERE domain = ? AND path = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, path);
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean createPage(String domain, String path) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO Page (domain, path) VALUES (?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, path);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean deletePage(String domain, String path) throws ClassNotFoundException, SQLException {
        boolean result = false;
        System.out.println(domain);
        System.out.println(path);
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Page WHERE domain = ? AND path = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, path);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
