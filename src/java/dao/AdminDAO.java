/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AdminDTO;
import dto.AdminListDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtilities;

/**
 *
 * @author Duong
 */
public class AdminDAO implements Serializable {
    
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
    
    public AdminDTO login(String username, String password) throws SQLException, NamingException, ClassNotFoundException {
        AdminDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 fullname, email, phone FROM Admin WHERE username = ? AND password = ?";          
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                result = new AdminDTO(username, fullname, email, phone);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public AdminListDTO getAdminList() throws SQLException, ClassNotFoundException {
        AdminListDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "SELECT username, fullname, email, phone FROM Admin";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            
            result = new AdminListDTO();
            while (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                AdminDTO dto = new AdminDTO(username, fullname, email, phone);
                result.getAdmin().add(dto);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public AdminDTO getAdmin(String username) throws ClassNotFoundException, SQLException {
        AdminDTO result = null;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT fullname, email, phone FROM Admin WHERE username = '" + username + "'";
            preStm = conn.prepareStatement(sql);
            
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                result = new AdminDTO(username, fullname, email, phone);
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean exists(String username) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            
            String sql = "SELECT TOP 1 username FROM Admin WHERE username = '" + username + "'";
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
    
    public boolean createAdmin(String username, String password, String fullname, String email, String phone) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "INSERT INTO Admin (username, password, fullname, email, phone) VALUES (?, ?, ?, ? , ?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            preStm.setString(3, fullname);
            preStm.setString(4, email);
            preStm.setString(5, phone);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean updateAdmin(String username, String password, String fullname, String email, String phone) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            AdminDAO dao = new AdminDAO();
            AdminDTO admin = dao.getAdmin(username);
            if (admin != null) {
                String newFullname = admin.getFullname();
                String newEmail = admin.getEmail();
                String newPhone = admin.getPhone();
                String sql = null;
                
                if (password != null && !password.isEmpty()) {
                    sql = "UPDATE Admin SET fullname = ?, email = ?, phone = ?, password = ? WHERE username = '" + username + "'";
                    preStm = conn.prepareStatement(sql);
                    preStm.setString(4, password);
                } else {
                    sql = "UPDATE Admin SET fullname = ?, email = ?, phone = ? WHERE username = '" + username + "'";
                    preStm = conn.prepareStatement(sql);
                }
                
                if (fullname != null && !fullname.isEmpty()) {
                    newFullname = fullname;
                }
                
                if (email != null && !email.isEmpty()) {
                    newEmail = email;
                }
                
                if (phone != null && !phone.isEmpty()) {
                    newPhone = phone;
                }
                
                preStm.setString(1, newFullname);
                preStm.setString(2, newEmail);
                preStm.setString(3, newPhone);
                
                result = preStm.executeUpdate() == 1; 
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean deleteAdmin(String username) throws ClassNotFoundException, SQLException {
        boolean result = false;
        
        try {
            conn = DBUtilities.createConnection();
            String sql = "DELETE FROM Admin WHERE username = '" + username + "'";
            preStm = conn.prepareStatement(sql);
            result = preStm.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
