/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DomainDTO;
import dto.DomainListDTO;
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
public class DomainDAO implements Serializable {
    
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
    
    public DomainListDTO getDomainList() throws SQLException, ClassNotFoundException {
        DomainListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT domain, name, xslPath FROM Domain";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new DomainListDTO();
            while (rs.next()) {
                String domain = rs.getString("domain");
                String name = rs.getString("name");
                String xslPath = rs.getString("xslPath");
                
                PageDAO dao = new PageDAO();
                PageListDTO pageList = dao.getPageListByDomain(domain);
                
                DomainDTO dto = new DomainDTO(domain, name, xslPath, pageList);
                result.getDomain().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public DomainDTO getDomain(String domain) throws ClassNotFoundException, SQLException {
        DomainDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT name, xslPath FROM Domain WHERE domain = '" + domain + "'";
            preStm = conn.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String xslPath = rs.getString("xslPath");
                
                PageDAO dao = new PageDAO();
                PageListDTO pageList = dao.getPageListByDomain(domain);
                
                result = new DomainDTO(domain, name, xslPath,pageList);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean exists(String domain) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 domain FROM Domain WHERE domain = '" + domain + "'";
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
    
    public boolean createDomain(String domain, String name, String xslPath) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO Domain (domain, name, xslPath) VALUES (?, ?, ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, domain);
            preStm.setString(2, name);
            preStm.setString(3, xslPath);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean updateDomain(String domain, String name, String xslPath) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            DomainDAO dao = new DomainDAO();
            DomainDTO Domain = dao.getDomain(domain);
            if (Domain != null) {
                String newName = Domain.getName();
                String newXslPath = Domain.getXslPath();
                String sql = "UPDATE Domain SET name = ?, xslPath = ? WHERE domain = '" + domain + "'";
                preStm = conn.prepareStatement(sql);
                
                if (name != null && !name.isEmpty()) {
                    newName = name;
                }
                
                if (xslPath != null) {
                    newXslPath = xslPath;
                }
                
                preStm.setString(1, newName);
                preStm.setString(2, newXslPath);
                
                result = preStm.executeUpdate() == 1; 
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean deleteDomain(String domain) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Domain WHERE domain = '" + domain + "'";
            preStm = conn.prepareStatement(sql);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
