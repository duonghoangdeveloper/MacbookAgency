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
@XmlType(name = "pageList", propOrder = {
    "page"
})
@XmlRootElement(name = "pageList")
public class PageListDTO implements Serializable {
    private List<PageDTO> page;

    public PageListDTO() {
    }

    public PageListDTO(List<PageDTO> page) {
        this.page = page;
    }
    
    public List<PageDTO> getPage() {
        if (page == null) {
            page = new ArrayList<PageDTO>();
        }
        return page;
    }

    public void setPage(List<PageDTO> page) {
        this.page = page;
    }
}
