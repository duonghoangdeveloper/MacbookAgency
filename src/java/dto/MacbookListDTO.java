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
@XmlType(name = "macbookList", propOrder = {
    "macbook"
})
@XmlRootElement(name = "macbookList")
public class MacbookListDTO implements Serializable {
    private List<MacbookDTO> macbook;

    public MacbookListDTO() {
    }

    public MacbookListDTO(List<MacbookDTO> macbook) {
        this.macbook = macbook;
    }
    
    public List<MacbookDTO> getMacbook() {
        if (macbook == null) {
            macbook = new ArrayList<MacbookDTO>();
        }
        return macbook;
    }

    public void setMacbook(List<MacbookDTO> macbook) {
        this.macbook = macbook;
    }
}
