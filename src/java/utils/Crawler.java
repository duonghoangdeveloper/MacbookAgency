/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.AccessoryCategoryDAO;
import dao.AccessoryDAO;
import dao.MacbookDAO;
import dao.MacbookModelDAO;
import dto.AccessoryCategoryListDTO;
import dto.AccessoryDTO;
import dto.AccessoryErrorDTO;
import dto.AccessoryListDTO;
import dto.CrawlPageDTO;
import dto.MacbookDTO;
import dto.MacbookErrorDTO;
import dto.MacbookListDTO;
import dto.MacbookModelListDTO;
import dto.PageDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import validator.AccessoryValidator;
import validator.MacbookValidator;

/**
 *
 * @author Duong
 */
public class Crawler {

//    public static ArrayList<DOMResult> crawlAll(String realPath) {
//        DOMResult rs = Crawler.crawl(realPath + "\\WEB-INF\\config\\macstores-config.xml", realPath + "\\WEB-INF\\xsl\\macstores.xsl");
//        TransformerFactory factory = TransformerFactory.newInstance();
//        Transformer transformer = factory.newTransformer();
//        StreamResult sr = new StreamResult(new FileOutputStream(realPath + "\\WEB-INF\\macstores.xml"));
//
//        transformer.transform(new DOMSource(rs.getNode()), sr);
//    }
    // http://www.kieutrongkhanh.net/2018/06/hien-thuc-bo-parser-html-bang-xslt.html
    public static DOMResult crawl(String configPath, String xslPath) throws FileNotFoundException, TransformerConfigurationException, TransformerException, ParserConfigurationException {

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement("products");
        document.appendChild(root);

        root.setAttribute("xmlns", "http://www.maccenter.vn");
        root.setAttribute("link", "http://www.maccenter.vn/Adonit.aspx");
        root.setAttribute("host", "http://www.maccenter.vn");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Source xmlSource = new DOMSource(document);
        Result outputTarget = new StreamResult(outputStream);
        TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
        InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

        //test
        // init files 
        StreamSource xslCate = new StreamSource(xslPath);
//        InputStream is = new FileInputStream(configPath);
        //init transformer api 
        TransformerFactory factory = TransformerFactory.newInstance();
        DOMResult domRs = new DOMResult();
        MyURIResolver resolver = new MyURIResolver();
        // apply uriREsolver 
        factory.setURIResolver(resolver);

        Transformer transformer = factory.newTransformer(xslCate);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // transform xml config by input xsl 
        StreamSource streamSrc = new StreamSource(is);
        transformer.transform(streamSrc, domRs);
        return domRs;
    }

    public static InputStream crawlPage(PageDTO page, String xslPath) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        // 1. PageDTO => Domain + Path => Config document => Config stream source
        // 2. Xsl path => Xsl stream source
        // 3. Transformer factory + xsl stream source => Transfomer
        // 4. Apply resolver to transfomer 
        // 5. Transform: Config stream source => Output stream result
        // 6. Output stream result => Input stream => return

        // Create config document
        Document configDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = configDocument.createElement("products");
        configDocument.appendChild(root);
        root.setAttribute("xmlns", page.getDomain());
        root.setAttribute("link", page.getDomain() + page.getPath());
        root.setAttribute("host", page.getDomain());

        // Create config stream source
        InputStream configStream = XMLUtilities.getInputStreamFromDocument(configDocument);
        StreamSource configStreamSource = new StreamSource(configStream);

        // Create xsl stream source
        StreamSource xslStreamSource = new StreamSource(xslPath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // Apply URI resolver
        MyURIResolver resolver = new MyURIResolver();
        transformerFactory.setURIResolver(resolver);

        // Create transformer with xsl stream source
        Transformer transformer = transformerFactory.newTransformer(xslStreamSource);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Create output stream to output transforming
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Result outputTarget = new StreamResult(outputStream);

        // Transform 
        transformer.transform(configStreamSource, outputTarget);

        // Output stream => Input stream, return
        InputStream stream = new ByteArrayInputStream(outputStream.toByteArray());

        return stream;
    }

    static public CrawlPageDTO saveXMLStreamToDB(InputStream xmlStream) throws XMLStreamException, TransformerException, SQLException, ClassNotFoundException {

        boolean inProduct = false;
        boolean inTitle = false;
        boolean inPrice = false;
        boolean inImage = false;
        boolean inUrl = false;
        String domain = null;
        String title = null;
        String price = null;
        String image = null;
        String url = null;

        // Report lists
        MacbookListDTO createdMacbookList = new MacbookListDTO();
        MacbookListDTO updatedMacbookList = new MacbookListDTO();
        MacbookListDTO failedMacbookList = new MacbookListDTO();
        MacbookListDTO invalidatedMacbookList = new MacbookListDTO();
        MacbookListDTO unmatchedMacbookList = new MacbookListDTO();
        AccessoryListDTO createdAccessoryList = new AccessoryListDTO();
        AccessoryListDTO updatedAccessoryList = new AccessoryListDTO();
        AccessoryListDTO failedAccessoryList = new AccessoryListDTO();
        AccessoryListDTO invalidatedAccessoryList = new AccessoryListDTO();
        AccessoryListDTO unmatchedAccessoryList = new AccessoryListDTO();

        AccessoryCategoryDAO accessoryCategoryDAO = new AccessoryCategoryDAO();
        AccessoryCategoryListDTO accessoryCategoryList = accessoryCategoryDAO.getAccessoryCategoryList();

        MacbookModelDAO macbookModelDAO = new MacbookModelDAO();
        MacbookModelListDTO macbookModelList = macbookModelDAO.getMacbookModelList(true, false);

        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(xmlStream);

        while (reader.hasNext()) {
            try {
                int eventType = reader.getEventType();

                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String localName = reader.getLocalName();
                        if (localName.equals("products")) {
                            domain = reader.getNamespaceURI();
                        } else if (localName.equals("product")) {
                            inProduct = true;
                        } else if (localName.equals("title")) {
                            inTitle = true;
                        } else if (localName.equals("price")) {
                            inPrice = true;
                        } else if (localName.equals("image")) {
                            inImage = true;
                        } else if (localName.equals("url")) {
                            inUrl = true;
                        }
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText();
                        if (inTitle) {
                            title = text;
                        } else if (inPrice) {
                            price = text;
                        } else if (inImage) {
                            image = text;
                        } else if (inUrl) {
                            url = text;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT: {
                        String localName = reader.getLocalName();
                        if (localName.equals("product")) {
                            inProduct = false;

                            // Validate accessory
                            String category = Utilities.getCategoryFromTitle(title, accessoryCategoryList);

                            if (category != null) {
                                AccessoryErrorDTO error = AccessoryValidator.validateCreateAccessory(domain, category, title, price, image, url);
                                if (error == null) {
                                    try {
                                        AccessoryDAO accessoryDAO = new AccessoryDAO();
                                        if (!accessoryDAO.exists(domain, category, title)) {
                                            // Create new accessory
                                            accessoryDAO = new AccessoryDAO();
                                            if (accessoryDAO.createAccessory(domain, category, title, Integer.parseInt(price), image, url)) {
                                                AccessoryDTO createdAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                                createdAccessoryList.getAccessory().add(createdAccessory);
                                            } else {
                                                AccessoryDTO failedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                                failedAccessoryList.getAccessory().add(failedAccessory);
                                            }
                                        } else {
                                            // Update accessory
                                            accessoryDAO = new AccessoryDAO();
                                            if (accessoryDAO.updateAccessory(domain, category, title, Integer.parseInt(price), image, url)) {
                                                AccessoryDTO updatedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                                updatedAccessoryList.getAccessory().add(updatedAccessory);
                                            } else {
                                                AccessoryDTO failedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                                failedAccessoryList.getAccessory().add(failedAccessory);
                                            }
                                        }
                                    } catch (Exception ex) {
                                        AccessoryDTO failedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                        failedAccessoryList.getAccessory().add(failedAccessory);
                                    }
                                } else {
                                    AccessoryDTO invalidatedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                    invalidatedAccessoryList.getAccessory().add(invalidatedAccessory);
                                }
                            } else {
                                AccessoryDTO unmatchedAccessory = new AccessoryDTO(domain, category, title, Integer.parseInt(price), image, url, 0);
                                unmatchedAccessoryList.getAccessory().add(unmatchedAccessory);
                            }

                            // Validate macbook
                            String modelID = Utilities.getModelIDFromTitle(title, macbookModelList);
                            if (modelID != null) {
                                MacbookErrorDTO error = MacbookValidator.validateCreateMacbook(domain, modelID, title, price, image, url);
                                if (error == null) {
                                    try {
                                        MacbookDAO macbookDAO = new MacbookDAO();
                                        if (!macbookDAO.exists(domain, modelID, title)) {
                                            // Create new macbook
                                            macbookDAO = new MacbookDAO();
                                            if (macbookDAO.createMacbook(domain, modelID, title, Integer.parseInt(price), image, url)) {
                                                MacbookDTO createdMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                                createdMacbookList.getMacbook().add(createdMacbook);
                                            } else {
                                                MacbookDTO failedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                                failedMacbookList.getMacbook().add(failedMacbook);
                                            }
                                        } else {
                                            // Update macbook
                                            macbookDAO = new MacbookDAO();
                                            if (macbookDAO.updateMacbook(domain, modelID, title, Integer.parseInt(price), image, url)) {
                                                MacbookDTO updatedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                                updatedMacbookList.getMacbook().add(updatedMacbook);
                                            } else {
                                                MacbookDTO failedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                                failedMacbookList.getMacbook().add(failedMacbook);
                                            }
                                        }
                                    } catch (Exception ex) {
                                        MacbookDTO updatedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                        updatedMacbookList.getMacbook().add(updatedMacbook);
                                    }
                                } else {
                                    MacbookDTO invalidatedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                    invalidatedMacbookList.getMacbook().add(invalidatedMacbook);
                                }
                            } else {
                                MacbookDTO unmatchedMacbook = new MacbookDTO(domain, modelID, title, Integer.parseInt(price), image, url);
                                unmatchedMacbookList.getMacbook().add(unmatchedMacbook);
                            }
                        } else if (localName.equals("title")) {
                            inTitle = false;
                        } else if (localName.equals("price")) {
                            inPrice = false;
                        } else if (localName.equals("image")) {
                            inImage = false;
                        } else if (localName.equals("url")) {
                            inUrl = false;
                        }
                        break;
                    }
                    default:
                        break;
                }
            } catch (Exception e) {

            } finally {
                reader.next();
            }
        }

//        System.out.println("created Accessory: " + createdAccessoryList.getAccessory().size());
//        System.out.println("updated Accessory: " + updatedAccessoryList.getAccessory().size());
//        System.out.println("failed Accessory: " + failedAccessoryList.getAccessory().size());
//        System.out.println("invalidated Accessory: " + invalidatedAccessoryList.getAccessory().size());
//        System.out.println("unmatched Accessory: " + unmatchedAccessoryList.getAccessory().size());
//        
//        System.out.println("created Macbook: " + createdMacbookList.getMacbook().size());
//        System.out.println("updated Macbook: " + updatedMacbookList.getMacbook().size());
//        System.out.println("failed Macbook: " + failedMacbookList.getMacbook().size());
//        System.out.println("invalidated Macbook: " + invalidatedMacbookList.getMacbook().size());
//        System.out.println("unmatched Macbook: " + unmatchedMacbookList.getMacbook().size());
        return new CrawlPageDTO(new SimpleDateFormat("HH:mm:ss").format(new Date()), null, createdMacbookList, updatedMacbookList, failedMacbookList, invalidatedMacbookList, unmatchedMacbookList, createdAccessoryList, updatedAccessoryList, failedAccessoryList, invalidatedAccessoryList, unmatchedAccessoryList);
    }
}
