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
@XmlType(name = "domain", propOrder = {
    "domain",
    "name",
    "xslPath",
    "pageList"
})
public class DomainDTO implements Serializable {
    @XmlElement(required = true)
    private String domain;
    @XmlElement(required = true)
    private String name;
    @XmlElement()
    private String xslPath;
    @XmlElement(name = "pageList", required = true)
    private PageListDTO pageList;

    public DomainDTO() {
    }

    public DomainDTO(String domain, String name, String xslPath, PageListDTO pageList) {
        this.domain = domain;
        this.name = name;
        this.xslPath = xslPath;
        this.pageList = pageList;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public String getXslPath() {
        return xslPath;
    }

    public PageListDTO getPageList() {
        return pageList;
    }
    
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPageList(PageListDTO pageList) {
        this.pageList = pageList;
    }

    public void setXslPath(String xslPath) {
        this.xslPath = xslPath;
    }
    
}
