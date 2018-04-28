/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_order.tbl_orderDAO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "ChangeDeliverStateServlet", urlPatterns = {"/ChangeDeliverStateServlet"})
public class ChangeDeliverStateServlet extends HttpServlet {

    private final String searchServlet = "search";
    private final String errorPage = "errorPage.html";

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
        
        String url = errorPage;
        try {
            //get all orderID of the selected record
            String[] orders = request.getParameterValues("chkAction");
            //get attribute IS_DELIVER in session scope
            HttpSession session = request.getSession();
            Boolean isDeliver = (Boolean) session.getAttribute("IS_DELIVER");
            
            //action must be not isDeliver
            boolean action = true;
            if (isDeliver) {
                action = false;
            }

            if (orders != null) {
                tbl_orderDAO dao = new tbl_orderDAO();
                for (String order : orders) {
                    dao.changeDeliverState(order, action);
                }
            }

            //call the search function again
            url = searchServlet;
        } catch (SQLException ex) {
            log("ChangeDeliverStateServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ChangeDeliverStateServlet_NamingException: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
