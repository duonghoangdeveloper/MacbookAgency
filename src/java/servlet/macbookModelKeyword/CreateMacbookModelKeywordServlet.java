/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.macbookModelKeyword;

import dao.MacbookModelKeywordDAO;
import dto.AdminDTO;
import dto.MacbookModelKeywordErrorDTO;
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
import validator.MacbookModelKeywordValidator;

/**
 *
 * @author Duong
 */
public class CreateMacbookModelKeywordServlet extends HttpServlet {

    private final String SUCCESS = "GetMacbookModelListServlet";
    private final String INVALID = "updateMacbookModel.jsp";
    private final String ERROR = "error.jsp";

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

        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            AdminDTO admin = (AdminDTO) session.getAttribute("ADMIN_INFO_DTO");

            if (admin != null) {
                String txtModelID = request.getParameter("txtModelID");
                String txtKeyword = request.getParameter("txtKeyword");
                MacbookModelKeywordErrorDTO error = MacbookModelKeywordValidator.validateCreateMacbookModelKeyword(txtModelID, txtKeyword);

                if (error == null) {
                    MacbookModelKeywordDAO dao = new MacbookModelKeywordDAO();
                    if (!dao.exists(txtModelID, txtKeyword)) {
                        dao = new MacbookModelKeywordDAO();
                        boolean success = dao.createMacbookModelKeyword(txtModelID, txtKeyword);
                        if (success) {
                            url = SUCCESS;
                        } else {
                            request.setAttribute("ERROR_MESSAGE", "Create macbook model keyword failed");
                        }
                    } else {
                        error = new MacbookModelKeywordErrorDTO(null, "Keyword exists");
                        request.setAttribute("MACBOOK_MODEL_KEYWORD_ERROR_DTO", error);
                        url = INVALID;
                    }
                } else {
                    request.setAttribute("MACBOOK_MODEL_KEYWORD_ERROR_DTO", error);
                    url = INVALID;
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Unauthorized");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateMacbookModelKeywordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateMacbookModelKeywordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
