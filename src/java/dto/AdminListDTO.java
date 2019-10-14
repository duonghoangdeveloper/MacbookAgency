/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Duong
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adminList", propOrder = {
    "admin"
})
@XmlRootElement(name = "adminList")
public class AdminListDTO implements Serializable {
    private List<AdminDTO> admin;

    public AdminListDTO() {
    }

    public AdminListDTO(List<AdminDTO> admin) {
        this.admin = admin;
    }
    
    public List<AdminDTO> getAdmin() {
        if (admin == null) {
            admin = new ArrayList<AdminDTO>();
        }
        return admin;
    }

    public void setAdmin(List<AdminDTO> admin) {
        this.admin = admin;
    }
}
