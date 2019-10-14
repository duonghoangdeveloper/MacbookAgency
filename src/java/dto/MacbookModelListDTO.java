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
@XmlType(name = "macbookModelList", propOrder = {
    "macbookModel"
})
@XmlRootElement(name = "macbookModelList")
public class MacbookModelListDTO implements Serializable {
    private List<MacbookModelDTO> macbookModel;

    public MacbookModelListDTO() {
    }

    public MacbookModelListDTO(List<MacbookModelDTO> macbookModel) {
        this.macbookModel = macbookModel;
    }
    
    public List<MacbookModelDTO> getMacbookModel() {
        if (macbookModel == null) {
            macbookModel = new ArrayList<MacbookModelDTO>();
        }
        return macbookModel;
    }

    public void setMacbookModel(List<MacbookModelDTO> macbookModel) {
        this.macbookModel = macbookModel;
    }
}
