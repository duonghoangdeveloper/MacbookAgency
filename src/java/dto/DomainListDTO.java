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
@XmlType(name = "domainList", propOrder = {
    "domain"
})
@XmlRootElement(name = "domainList")
public class DomainListDTO implements Serializable {
    private List<DomainDTO> domain;

    public DomainListDTO() {
    }

    public DomainListDTO(List<DomainDTO> domain) {
        this.domain = domain;
    }
    
    public List<DomainDTO> getDomain() {
        if (domain == null) {
            domain = new ArrayList<DomainDTO>();
        }
        return domain;
    }

    public void setDomain(List<DomainDTO> domain) {
        this.domain = domain;
    }
}
