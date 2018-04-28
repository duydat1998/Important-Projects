/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "SearchInRangeServlet", urlPatterns = {"/SearchInRangeServlet"})
public class SearchInRangeServlet extends HttpServlet {

    private final String userPage = "userPage.jsp";

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
        String minString = request.getParameter("txtSearchMinPrice");
        String maxString = request.getParameter("txtSearchMaxPrice");
        String url = userPage;
        try {
            Float min = null;
            if (minString.trim().length() > 0) {
                try {
                    min = Float.parseFloat(minString);
                } catch (NumberFormatException e) {
                    log("SearchInRangeServlet_NumberFormatException: " + e.getMessage());
                }
            }

            Float max = null;
            if (maxString.trim().length() > 0) {
                try {
                    max = Float.parseFloat(maxString);
                } catch (NumberFormatException e) {
                    log("SearchInRangeServlet_NumberFormatException: " + e.getMessage());
                }
            }

            if (min != null && max != null && min <= max) {
                tbl_MobileDAO dao = new tbl_MobileDAO();
                List<tbl_MobileDTO> result = dao.searchMobileByPriceRange(min, max, false);
                request.setAttribute("RESULT_RANGE", result);
            }
        } catch (NamingException ex) {
            log("SearchInRangeServlet_NamingException: " + ex.getMessage());
            //Logger.getLogger(SearchInRangeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            log("SearchInRangeServlet_SQLException: " + ex.getMessage());
            //Logger.getLogger(SearchInRangeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
