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
@XmlType(name = "macbookModelKeyword", propOrder = {
    "modelID",
    "keyword",
})
public class MacbookModelKeywordDTO implements Serializable {
    @XmlElement(required = true)
    private String modelID;
    @XmlElement(required = true)
    private String keyword;

    public MacbookModelKeywordDTO() {
    }

    public MacbookModelKeywordDTO(String modelID, String keyword) {
        this.modelID = modelID;
        this.keyword = keyword;
    }

    public String getModelID() {
        return modelID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
