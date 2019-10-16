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
@XmlType(name = "crawlPage", propOrder = {
    "crawlAt",
    "page",
    "createdMacbookList",
    "updatedMacbookList",
    "failedMacbookList",
    "invalidatedMacbookList",
    "unmatchedMacbookList",
    "createdAccessoryList",
    "updatedAccessoryList",
    "failedAccessoryList",
    "invalidatedAccessoryList",
    "unmatchedAccessoryList"
})
public class CrawlPageDTO implements Serializable {
    @XmlElement(required = true)
    private String crawlAt;
    @XmlElement(required = true)
    private PageDTO page;
    @XmlElement(required = true)
    private MacbookListDTO createdMacbookList;
    @XmlElement(required = true)
    private MacbookListDTO updatedMacbookList;
    @XmlElement(required = true)
    private MacbookListDTO failedMacbookList;
    @XmlElement(required = true)
    private MacbookListDTO invalidatedMacbookList;
    @XmlElement(required = true)
    private MacbookListDTO unmatchedMacbookList;
    @XmlElement(required = true)
    private AccessoryListDTO createdAccessoryList;
    @XmlElement(required = true)
    private AccessoryListDTO updatedAccessoryList;
    @XmlElement(required = true)
    private AccessoryListDTO failedAccessoryList;
    @XmlElement(required = true)
    private AccessoryListDTO invalidatedAccessoryList;
    @XmlElement(required = true)
    private AccessoryListDTO unmatchedAccessoryList;

    public CrawlPageDTO() {
    }

    public CrawlPageDTO(String crawlAt, PageDTO page, MacbookListDTO createdMacbookList, MacbookListDTO updatedMacbookList, MacbookListDTO failedMacbookList, MacbookListDTO invalidatedMacbookList, MacbookListDTO unmatchedMacbookList, AccessoryListDTO createdAccessoryList, AccessoryListDTO updatedAccessoryList, AccessoryListDTO failedAccessoryList, AccessoryListDTO invalidatedAccessoryList, AccessoryListDTO unmatchedAccessoryList) {
        this.crawlAt = crawlAt;
        this.page = page;
        this.createdMacbookList = createdMacbookList;
        this.updatedMacbookList = updatedMacbookList;
        this.failedMacbookList = failedMacbookList;
        this.invalidatedMacbookList = invalidatedMacbookList;
        this.unmatchedMacbookList = unmatchedMacbookList;
        this.createdAccessoryList = createdAccessoryList;
        this.updatedAccessoryList = updatedAccessoryList;
        this.failedAccessoryList = failedAccessoryList;
        this.invalidatedAccessoryList = invalidatedAccessoryList;
        this.unmatchedAccessoryList = unmatchedAccessoryList;
    }

    public String getCrawlAt() {
        return crawlAt;
    }

    public PageDTO getPage() {
        return page;
    }

    public MacbookListDTO getCreatedMacbookList() {
        return createdMacbookList;
    }

    public MacbookListDTO getUpdatedMacbookList() {
        return updatedMacbookList;
    }

    public MacbookListDTO getFailedMacbookList() {
        return failedMacbookList;
    }

    public MacbookListDTO getInvalidatedMacbookList() {
        return invalidatedMacbookList;
    }

    public MacbookListDTO getUnmatchedMacbookList() {
        return unmatchedMacbookList;
    }

    public AccessoryListDTO getCreatedAccessoryList() {
        return createdAccessoryList;
    }

    public AccessoryListDTO getUpdatedAccessoryList() {
        return updatedAccessoryList;
    }

    public AccessoryListDTO getFailedAccessoryList() {
        return failedAccessoryList;
    }

    public AccessoryListDTO getInvalidatedAccessoryList() {
        return invalidatedAccessoryList;
    }

    public AccessoryListDTO getUnmatchedAccessoryList() {
        return unmatchedAccessoryList;
    }

    public void setCrawlAt(String crawlAt) {
        this.crawlAt = crawlAt;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }

    public void setCreatedMacbookList(MacbookListDTO createdMacbookList) {
        this.createdMacbookList = createdMacbookList;
    }

    public void setUpdatedMacbookList(MacbookListDTO updatedMacbookList) {
        this.updatedMacbookList = updatedMacbookList;
    }

    public void setFailedMacbookList(MacbookListDTO failedMacbookList) {
        this.failedMacbookList = failedMacbookList;
    }

    public void setInvalidatedMacbookList(MacbookListDTO invalidatedMacbookList) {
        this.invalidatedMacbookList = invalidatedMacbookList;
    }

    public void setUnmatchedMacbookList(MacbookListDTO unmatchedMacbookList) {
        this.unmatchedMacbookList = unmatchedMacbookList;
    }

    public void setCreatedAccessoryList(AccessoryListDTO createdAccessoryList) {
        this.createdAccessoryList = createdAccessoryList;
    }

    public void setUpdatedAccessoryList(AccessoryListDTO updatedAccessoryList) {
        this.updatedAccessoryList = updatedAccessoryList;
    }

    public void setFailedAccessoryList(AccessoryListDTO failedAccessoryList) {
        this.failedAccessoryList = failedAccessoryList;
    }

    public void setInvalidatedAccessoryList(AccessoryListDTO invalidatedAccessoryList) {
        this.invalidatedAccessoryList = invalidatedAccessoryList;
    }

    public void setUnmatchedAccessoryList(AccessoryListDTO unmatchedAccessoryList) {
        this.unmatchedAccessoryList = unmatchedAccessoryList;
    }

    
}
