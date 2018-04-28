/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.CartObj;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Order.tbl_OrderDAO;
import sample.tbl_OrderDetail.tbl_OrderDetailDAO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "StoreToDBServlet", urlPatterns = {"/StoreToDBServlet"})
public class StoreToDBServlet extends HttpServlet {

    private final String errorPage = "storeError.html";
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
        String url = errorPage;
        try {
            //1. get sesion
            HttpSession session = request.getSession(false);
            //2. check session != null
            if (session != null) {
                //3. get cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                String userId = (String) session.getAttribute("USER");
                boolean result = false;
                if (cart != null) {
                    tbl_OrderDAO orderDao = new tbl_OrderDAO();
                    int total = 0;
                    float totalAmount=0;
                    Map<String, Integer> items = cart.getItems();
                    for (Map.Entry<String, Integer> item : items.entrySet()) {
                        Integer quantity = item.getValue();
                        total += quantity;
                        tbl_MobileDAO mobileDao= new tbl_MobileDAO();
                        float price= mobileDao.getPriceById(item.getKey());
                        totalAmount+=(price*quantity);
                    }//end for
                    
                    int generatedKey = orderDao.insertOrder(total, userId, totalAmount);
                    if (generatedKey > 0) {
                        for (Map.Entry<String, Integer> item : items.entrySet()) {
                            String mobileId = item.getKey();
                            Integer quantity = item.getValue();
                            tbl_OrderDetailDAO detailDao = new tbl_OrderDetailDAO();
                            result = detailDao.insertOrderDetail(mobileId, quantity, generatedKey);
                        }//end for
                        if (result) {
                            url = userPage;
                            session.removeAttribute("CART");
                        }
                    }// end if generatedKey
                }//end if cart
            }//end if session
        } catch (NamingException ex) {
            //Logger.getLogger(StoreToDBServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("StoreToDBServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            //Logger.getLogger(StoreToDBServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("StoreToDBServlet_SQLException: " + ex.getMessage());
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
