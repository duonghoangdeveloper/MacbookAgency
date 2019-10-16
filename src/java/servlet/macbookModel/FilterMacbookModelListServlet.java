/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.macbookModel;

import dao.MacbookModelDAO;
import dto.MacbookModelListDTO;
import java.io.IOException;
import java.sql.SQLException;
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
import org.w3c.dom.Document;

/**
 *
 * @author Duong
 */
public class FilterMacbookModelListServlet extends HttpServlet {

    private final String MACBOOK_PAGE = "macbook.jsp";
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
            String txtType = request.getParameter("txtType");
            String txtYear = request.getParameter("txtYear");
            String txtSsd = request.getParameter("txtSsd");
            String txtScreenSize = request.getParameter("txtScreenSize");
            String txtTouchbar = request.getParameter("txtTouchbar");
            
            if (txtType != null && txtType.isEmpty()) {
                txtType = null;
            }
            if (txtYear != null && txtYear.isEmpty()) {
                txtYear = null;
            }
            if (txtSsd != null && txtSsd.isEmpty()) {
                txtSsd = null;
            }
            if (txtScreenSize != null && txtScreenSize.isEmpty()) {
                txtScreenSize = null;
            }
            if (txtTouchbar != null && txtTouchbar.isEmpty()) {
                txtTouchbar = null;
            }
            
            MacbookModelDAO dao = new MacbookModelDAO();
            MacbookModelListDTO macbookModelList = dao.filterMacbookModelList(txtType, txtYear, txtSsd, txtScreenSize, txtTouchbar);

            if (macbookModelList != null && macbookModelList.getMacbookModel() != null) {
                JAXBContext jc = JAXBContext.newInstance(macbookModelList.getClass());
                Marshaller marshaller = jc.createMarshaller();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();

                marshaller.marshal(macbookModelList, document);

                if (document != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("FILTERED_MACBOOK_MODEL_LIST_DOCUMENT", document);
                    url = MACBOOK_PAGE;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetMacbookModelListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetMacbookModelListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(GetMacbookModelListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GetMacbookModelListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
