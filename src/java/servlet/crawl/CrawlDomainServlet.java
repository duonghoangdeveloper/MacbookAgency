/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.crawl;

import dao.DomainDAO;
import dto.AdminDTO;
import dto.CrawlPageDTO;
import dto.CrawlPageListDTO;
import dto.DomainDTO;
import dto.PageDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import utils.Crawler;

/**
 *
 * @author Duong
 */
public class CrawlDomainServlet extends HttpServlet {

    private final String DOMAINS_PAGE = "domains.jsp";
    private final String ERROR_PAGE = "error.jsp";

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

        String url = ERROR_PAGE;

        try {
            HttpSession session = request.getSession();
            AdminDTO admin = (AdminDTO) session.getAttribute("ADMIN_INFO_DTO");

            if (admin != null) {
                String txtDomain = request.getParameter("txtDomain");

                DomainDAO dao = new DomainDAO();
                DomainDTO domain = dao.getDomain(txtDomain);

                if (domain != null) {
                    String realPath = getServletContext().getRealPath("/");
                    String xslPath = domain.getXslPath();

                    List<PageDTO> pageList = domain.getPageList().getPage();
                    
                    CrawlPageListDTO crawlPageList = new CrawlPageListDTO();

                    for (PageDTO page : pageList) {
                        InputStream stream = Crawler.crawlPage(page, realPath + xslPath);
                        CrawlPageDTO crawlPage = Crawler.saveXMLStreamToDB(stream);
                        crawlPage.setPage(page);
                        crawlPageList.getCrawlPage().add(crawlPage);
                    }
                    
                    JAXBContext jc = JAXBContext.newInstance(crawlPageList.getClass());
                    Marshaller marshaller = jc.createMarshaller();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.newDocument();

                    marshaller.marshal(crawlPageList, document);

                    if (document != null) {
                        request.setAttribute("CRAWL_PAGE_LIST_DOCUMENT", document);
                        url = DOMAINS_PAGE;
                    } else {
                        request.setAttribute("ERROR_MESSAGE", "Get domain failed");
                    }
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Get domain failed");
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Unauthorized");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(CrawlDomainServlet.class.getName()).log(Level.SEVERE, null, ex);
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
