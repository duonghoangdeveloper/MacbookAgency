/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.PageDTO;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.Crawler;
import utils.Utilities;
import utils.XMLUtilities;

/**
 *
 * @author Duong
 */
public class CrawlServlet extends HttpServlet {

    private final String ADMIN_PAGE = "domains.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ADMIN_PAGE;

        try {
            String realPath = getServletContext().getRealPath("/");

//            try {

                String xslPath = realPath + "\\WEB-INF\\xsl\\macstores.xsl";
                String txtDomain = request.getParameter("txtDomain");
                System.out.println(txtDomain);
                
                
                
                

//                PageDTO page = new PageDTO("https://www.macstores.vn", "/accessories/phu-kien-macbook");
//                InputStream stream = Crawler.crawlPage(page, xslPath);
//
////                System.out.println("123");
////                Crawler.saveToDB(stream);
//                DOMResult rs = Crawler.crawl(realPath + "\\WEB-INF\\config\\macstores-config.xml", realPath + "\\WEB-INF\\xsl\\maccenter.xsl");
////                
//                Utilities.printStream(XMLUtilities.getInputStreamFromDocument((Document) rs.getNode()));
////                
////                
//                TransformerFactory factory = TransformerFactory.newInstance();
//                Transformer transformer = factory.newTransformer();
//                StreamResult sr = new StreamResult(new FileOutputStream(realPath + "\\WEB-INF\\maccenter.xml"));
//                
//                transformer.transform(new DOMSource(rs.getNode()), sr);
//                System.out.println("Write to file \\WEB-INF\\macstores.xml");
//            } catch (TransformerException ex) {
//                Logger.getLogger(CrawlServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParserConfigurationException ex) {
//                Logger.getLogger(CrawlServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (XMLStreamException ex) {
//                Logger.getLogger(CrawlServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(CrawlServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(CrawlServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
