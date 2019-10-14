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
@XmlType(name = "accessoryList", propOrder = {
    "accessory"
})
@XmlRootElement(name = "accessoryList")
public class AccessoryListDTO implements Serializable {
    private List<AccessoryDTO> accessory;

    public AccessoryListDTO() {
    }

    public AccessoryListDTO(List<AccessoryDTO> accessory) {
        this.accessory = accessory;
    }
    
    public List<AccessoryDTO> getAccessory() {
        if (accessory == null) {
            accessory = new ArrayList<AccessoryDTO>();
        }
        return accessory;
    }

    public void setAccessory(List<AccessoryDTO> accessory) {
        this.accessory = accessory;
    }
}
