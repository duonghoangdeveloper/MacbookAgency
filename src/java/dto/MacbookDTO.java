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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Duong
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "macbook", propOrder = {
    "domain",
    "modelID",
    "title",
    "price",
    "image",
    "url"
})
@XmlRootElement(name = "macbook")
public class MacbookDTO implements Serializable {
    @XmlElement(required = true)
    protected String domain;
    @XmlElement(nillable = true)
    protected String modelID;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected int price;
    @XmlElement(required = true)
    protected String image;
    @XmlElement(required = true)
    protected String url;

    public MacbookDTO() {
    }

    public MacbookDTO(String domain, String modelID, String title, int price, String image, String url) {
        this.domain = domain;
        this.modelID = modelID;
        this.title = title;
        this.price = price;
        this.image = image;
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public String getModelID() {
        return modelID;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
