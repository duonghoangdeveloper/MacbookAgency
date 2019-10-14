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
@XmlType(name = "accessoryCategoryList", propOrder = {
    "accessoryCategory"
})
@XmlRootElement(name = "accessoryCategoryList")
public class AccessoryCategoryListDTO implements Serializable {
    private List<AccessoryCategoryDTO> accessoryCategory;

    public AccessoryCategoryListDTO() {
    }

    public AccessoryCategoryListDTO(List<AccessoryCategoryDTO> accessoryCategory) {
        this.accessoryCategory = accessoryCategory;
    }
    
    public List<AccessoryCategoryDTO> getAccessoryCategory() {
        if (accessoryCategory == null) {
            accessoryCategory = new ArrayList<AccessoryCategoryDTO>();
        }
        return accessoryCategory;
    }

    public void setAccessoryCategory(List<AccessoryCategoryDTO> accessoryCategory) {
        this.accessoryCategory = accessoryCategory;
    }
}
