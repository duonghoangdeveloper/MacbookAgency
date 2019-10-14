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
@XmlType(name = "accessoryCategoryKeywordList", propOrder = {
    "accessoryCategoryKeyword"
})
@XmlRootElement(name = "accessoryCategoryKeywordList")
public class AccessoryCategoryKeywordListDTO implements Serializable {
    private List<AccessoryCategoryKeywordDTO> accessoryCategoryKeyword;

    public AccessoryCategoryKeywordListDTO() {
    }

    public AccessoryCategoryKeywordListDTO(List<AccessoryCategoryKeywordDTO> accessoryCategoryKeyword) {
        this.accessoryCategoryKeyword = accessoryCategoryKeyword;
    }
    
    public List<AccessoryCategoryKeywordDTO> getAccessoryCategoryKeyword() {
        if (accessoryCategoryKeyword == null) {
            accessoryCategoryKeyword = new ArrayList<AccessoryCategoryKeywordDTO>();
        }
        return accessoryCategoryKeyword;
    }

    public void setAccessoryCategoryKeyword(List<AccessoryCategoryKeywordDTO> accessoryCategoryKeyword) {
        this.accessoryCategoryKeyword = accessoryCategoryKeyword;
    }
}
