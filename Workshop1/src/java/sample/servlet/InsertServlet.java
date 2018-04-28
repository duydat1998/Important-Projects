/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import sample.tbl_Mobile.tbl_MobileInsertError;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "InsertServlet", urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {

    private final String errorPage = "insertError.html";

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String mobileId = request.getParameter("txtMobileId");
        String description = request.getParameter("txtDescription");
        String priceString = request.getParameter("txtPrice");
        String mobileName = request.getParameter("txtMobileName");
        String yearString = request.getParameter("txtYear");
        String quantityString = request.getParameter("txtQuantity");
        String sale = request.getParameter("chkNotSale");
        String searchId = request.getParameter("lastSearchId");
        String searchName = request.getParameter("lastSearchName");
        boolean notSale = false;
        if (sale != null) {
            notSale = true;
        }
        String url = "ControlServlet?btAction=Search&txtSearchId="
                + searchId + "&txtSearchName=" + searchName;
        boolean bError = false;
        tbl_MobileInsertError errors = new tbl_MobileInsertError();
        try {
            if (mobileId == null || mobileId.trim().length() == 0) {
                bError = true;
                errors.setMobileIdBlankError("Mobile ID must not be blank!!!");
            }
            if (description == null || description.trim().length() == 0) {
                bError = true;
                errors.setDescriptionBlankError("Description must not be blank!!!");
            }
            if (mobileName == null || mobileName.trim().length() == 0) {
                bError = true;
                errors.setMobileNameBlankError("Mobile Name must not be blank!!!");
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
                log("InsertServlet_NumberFormatException: " + ex.getMessage());
            } catch (Exception ex) {
                log("InsertServlet_Exception: " + ex.getMessage());
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
                log("InsertServlet_NumberFormatException: " + ex.getMessage());
            } catch (Exception ex) {
                log("InsertServlet_Exception: " + ex.getMessage());
            }

            Integer year = null;
            try {
                if (yearString != null) {
                    if (yearString.trim().length() > 0) {
                        year = Integer.parseInt(yearString);
                    }
                }
            } catch (NumberFormatException ex) {
                bError = true;
                errors.setQuantityFormatError("Year of production must be an integer!!!");
                log("InsertServlet_NumberFormatException: " + ex.getMessage());
            } catch (Exception ex) {
                log("InsertServlet_Exception: " + ex.getMessage());
            }

            if (bError) {
                request.setAttribute("ERROR_INSERT", errors);
            } else {
                tbl_MobileDAO dao = new tbl_MobileDAO();
                boolean result = dao.insertNewMobile(mobileId, description, mobileName, price, year, quantity, notSale);
                if (result) {
                    request.setAttribute("SUCCESS_INSERT", "Insert successfully!");
                }
            }
        } catch (NamingException ex) {
            url = errorPage;
            //Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("InsertServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            //Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getMessage().contains("duplicate")) {
                errors.setMobileIdDuplicatedError(mobileId + " is existed!!!");
                request.setAttribute("ERROR_INSERT", errors);
            }
            log("InsertServlet_SQLException: " + ex.getMessage());
            
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
