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
import sample.tbl_customer.tbl_customerDAO;
import sample.tbl_customer.tbl_customerDTO;
import sample.tbl_order.tbl_orderDAO;
import sample.tbl_order.tbl_orderDTO;
import sample.tbl_orderDetail.tbl_orderDetailDAO;
import sample.tbl_orderDetail.tbl_orderDetailDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "ViewDetailServlet", urlPatterns = {"/ViewDetailServlet"})
public class ViewDetailServlet extends HttpServlet {

    private final String orderDetailPage = "orderDetails.jsp";

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
        
        String url=orderDetailPage;
        //get the orderID of the record needed to be viewed in detail
        String orderID = request.getParameter("orderID");
        
        try {
            
            tbl_orderDAO orderDao = new tbl_orderDAO();
            tbl_orderDTO orderDTO = orderDao.getOrderById(orderID);
            request.setAttribute("ORDER_DETAIL", orderDTO);
            
            String custID=orderDTO.getCustID();
            tbl_customerDAO custDao= new tbl_customerDAO();
            tbl_customerDTO custDto= custDao.getCustomerById(custID);
            request.setAttribute("CUST_DETAIL", custDto);
            
            tbl_orderDetailDAO dao= new tbl_orderDetailDAO();
            dao.searchByOrderID(orderID);
            List<tbl_orderDetailDTO> result= dao.getListOrderDetail();
            request.setAttribute("RESULT_DETAIL", result);
            
        } catch (SQLException ex) {
            log("ViewDetailServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ViewDetailServlet_NamingException:" + ex.getMessage());
        } finally {
            //use RequestDispatcher to keep request obj, to show result on orderDetail.jsp
            RequestDispatcher rd= request.getRequestDispatcher(url);
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
