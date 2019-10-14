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
@XmlType(name = "macbookModel", propOrder = {
    "modelID",
    "type",
    "year",
    "ssd",
    "screenSize",
    "touchbar",
    "thumbnail",
    "macbookModelKeywordList"
})
@XmlRootElement(name = "macbookModel")
public class MacbookModelDTO implements Serializable {
    @XmlElement(required = true)
    private String modelID;
    @XmlElement(required = true)
    private String type;
    @XmlElement(required = true)
    private int year;
    @XmlElement(required = true)
    private int ssd;
    @XmlElement(required = true)
    private float screenSize;
    @XmlElement(required = true)
    private boolean touchbar;
    @XmlElement(required = true)
    private String thumbnail;
    @XmlElement(name = "macbookModelKeywordList", required = true)
    private MacbookModelKeywordListDTO macbookModelKeywordList;

    public MacbookModelDTO() {
    }

    public MacbookModelDTO(String modelID, String type, int year, int ssd, float screenSize, boolean touchbar, String thumbnail, MacbookModelKeywordListDTO macbookModelKeywordList) {
        this.modelID = modelID;
        this.type = type;
        this.year = year;
        this.ssd = ssd;
        this.screenSize = screenSize;
        this.touchbar = touchbar;
        this.thumbnail = thumbnail;
        this.macbookModelKeywordList = macbookModelKeywordList;
    }

    public String getModelID() {
        return modelID;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public int getSsd() {
        return ssd;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public boolean isTouchbar() {
        return touchbar;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public MacbookModelKeywordListDTO getMacbookModelKeywordList() {
        return macbookModelKeywordList;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public void setTouchbar(boolean touchbar) {
        this.touchbar = touchbar;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setMacbookModelKeywordList(MacbookModelKeywordListDTO macbookModelKeywordList) {
        this.macbookModelKeywordList = macbookModelKeywordList;
    }
}
