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
@XmlType(name = "page", propOrder = {
    "domain",
    "path",
})
public class PageDTO implements Serializable {
    @XmlElement(required = true)
    private String domain;
    @XmlElement(required = true)
    private String path;

    public PageDTO() {
    }

    public PageDTO(String domain, String path) {
        this.domain = domain;
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
