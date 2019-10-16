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
@XmlType(name = "crawlPageList", propOrder = {
    "crawlPage"
})
@XmlRootElement(name = "crawlPageList")
public class CrawlPageListDTO implements Serializable {
    private List<CrawlPageDTO> crawlPage;

    public CrawlPageListDTO() {
    }

    public CrawlPageListDTO(List<CrawlPageDTO> crawlPage) {
        this.crawlPage = crawlPage;
    }
    
    public List<CrawlPageDTO> getCrawlPage() {
        if (crawlPage == null) {
            crawlPage = new ArrayList<CrawlPageDTO>();
        }
        return crawlPage;
    }

    public void setCrawlPage(List<CrawlPageDTO> crawlPage) {
        this.crawlPage = crawlPage;
    }
}
