/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Mobile.tbl_MobileUpdateError;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String updateErrorPage = "updateError.html";
    private final String staffPage = "staffPage.jsp";

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
        String mobileId = request.getParameter("txtMobileId");
        String description = request.getParameter("txtDescription");
        String priceString = request.getParameter("txtPrice");
        String quantityString = request.getParameter("txtQuantity");
        String sale = request.getParameter("chkNotSale");
        String form = request.getParameter("txtForm");
        boolean notSale = false;
        if (sale != null) {
            notSale = true;
        }
        String searchId = request.getParameter("lastSearchId");
        String searchName = request.getParameter("lastSearchName");
        String url = updateErrorPage;
        boolean bError = false;
        tbl_MobileUpdateError errors = new tbl_MobileUpdateError();
        errors.setMobileId(mobileId);
        try {

            if (description == null || description.trim().length() == 0) {
                bError = true;
                errors.setDescriptionBlankError("Description must not be blank!!!");
            }

            Float price = null;
            try {
                if (priceString != null) {
                    if (priceString.trim().length() > 0) {
                        price = Float.parseFloat(priceString);
                    }
                }
            } catch (NumberFormatException ex) {
                bError = true;
                errors.setPriceFormatError("Price must be a real number!!!");
                log("UpdateServlet_NumberFormatException: " + ex.getMessage());
            } catch (Exception ex) {
                log("UpdateServlet_Exception: " + ex.getMessage());
            }

            Integer quantity = null;
            try {
                if (quantityString != null) {
                    if (quantityString.trim().length() > 0) {
                        quantity = Integer.parseInt(quantityString);
                    }
                }
            } catch (NumberFormatException ex) {
                bError = true;
                errors.setQuantityFormatError("Quantity must be an integer!!!");
                log("UpdateServlet_NumberFormatException: " + ex.getMessage());
            } catch (Exception ex) {
                log("UpdateServlet_Exception: " + ex.getMessage());
            }

            if (bError) {
                url = "ControlServlet?btAction=Search&txtSearchId="
                        + searchId + "&txtSearchName=" + searchName;
                request.setAttribute("ERROR_UPDATE_" + form, errors);
            } else {
                tbl_MobileDAO dao = new tbl_MobileDAO();
                boolean result = dao.updateRecord(mobileId, description, price, quantity, notSale);
                if (result) {
                    url = "ControlServlet?btAction=Search&txtSearchId="
                            + searchId + "&txtSearchName=" + searchName;
                }
            }
        } catch (NamingException ex) {
            //Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("UpdateServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            //Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("UpdateServlet_SQLException: " + ex.getMessage());
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
