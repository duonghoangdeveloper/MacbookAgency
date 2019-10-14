/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Duong
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessoryCategory", propOrder = {
    "category",
    "thumbnail",
    "accessoryCategoryKeywordList"
})
@XmlRootElement(name = "accessoryCategory")
public class AccessoryCategoryDTO implements Serializable {
    @XmlElement(required = true)
    private String category;
    @XmlElement(required = true)
    private String thumbnail;
    @XmlElement(name = "accessoryCategoryKeywordList", required = true)
    private AccessoryCategoryKeywordListDTO accessoryCategoryKeywordList;

    public AccessoryCategoryDTO() {
    }

    public AccessoryCategoryDTO(String category, String thumbnail, AccessoryCategoryKeywordListDTO accessoryCategoryKeywordList) {
        this.category = category;
        this.thumbnail = thumbnail;
        this.accessoryCategoryKeywordList = accessoryCategoryKeywordList;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public AccessoryCategoryKeywordListDTO getAccessoryCategoryKeywordList() {
        return accessoryCategoryKeywordList;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setAccessoryCategoryKeywordList(AccessoryCategoryKeywordListDTO accessoryCategoryKeywordList) {
        this.accessoryCategoryKeywordList = accessoryCategoryKeywordList;
    }
}
