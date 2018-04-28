/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_order.tbl_orderDAO;
import sample.tbl_order.tbl_orderDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String orderListPage = "orderList.jsp";
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
        
        //in default, if there is any error, move to errorPage.html
        String url = errorPage;

        //all needed input are in session scope
        HttpSession session = request.getSession();

        Date fromDate = (Date) session.getAttribute("FROM_DATE");
        Date toDate = (Date) session.getAttribute("TO_DATE");
        boolean isDeliver=(boolean) session.getAttribute("IS_DELIVER");
        
        try {
            tbl_orderDAO dao = new tbl_orderDAO();
            dao.searchFromDateToDate(fromDate, toDate, isDeliver);
            List<tbl_orderDTO> result = dao.getListOrder();

            request.setAttribute("RESULT", result);
            
            url = orderListPage;

        } catch (SQLException ex) {
            log("SearchServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_NamingException:" + ex.getMessage());

        } finally {
            //use RequestDispatcher to keep request obj, to show result on orderList.jsp
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
