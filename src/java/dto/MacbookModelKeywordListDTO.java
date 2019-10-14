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
@XmlType(name = "macbookModelKeywordList", propOrder = {
    "macbookModelKeyword"
})
@XmlRootElement(name = "macbookModelKeywordList")
public class MacbookModelKeywordListDTO implements Serializable {
    private List<MacbookModelKeywordDTO> macbookModelKeyword;

    public MacbookModelKeywordListDTO() {
    }

    public MacbookModelKeywordListDTO(List<MacbookModelKeywordDTO> macbookModelKeyword) {
        this.macbookModelKeyword = macbookModelKeyword;
    }
    
    public List<MacbookModelKeywordDTO> getMacbookModelKeyword() {
        if (macbookModelKeyword == null) {
            macbookModelKeyword = new ArrayList<MacbookModelKeywordDTO>();
        }
        return macbookModelKeyword;
    }

    public void setMacbookModelKeyword(List<MacbookModelKeywordDTO> macbookModelKeyword) {
        this.macbookModelKeyword = macbookModelKeyword;
    }
}
