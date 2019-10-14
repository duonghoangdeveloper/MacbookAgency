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
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Duong
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessoryCategoryKeyword", propOrder = {
    "category",
    "keyword",
})
public class AccessoryCategoryKeywordDTO implements Serializable {
    @XmlElement(required = true)
    private String category;
    @XmlElement(required = true)
    private String keyword;

    public AccessoryCategoryKeywordDTO() {
    }

    public AccessoryCategoryKeywordDTO(String category, String keyword) {
        this.category = category;
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
