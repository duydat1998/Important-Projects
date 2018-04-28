/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Duy Dat
 */
public class ControlServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String loginServlet = "LoginServlet";
    private final String logoutServlet="LogoutServlet";
    private final String searchIdNameServlet="SearchIdNameServlet";
    private final String deleteServlet="DeleteServlet";
    private final String updateServlet="UpdateServlet";
    private final String insertServlet="InsertServlet";
    private final String searchInRangeServlet="SearchInRangeServlet";
    private final String addToCartServlet="AddToCartServlet";
    private final String viewCartPage="viewCart.jsp";
    private final String removeItemServlet="RemoveItemServlet";
    private final String storeToDBServlet="StoreToDBServlet";
    

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
        String url = loginPage;
        try {
            String button = request.getParameter("btAction");
            //check if button is null
            if (button == null) {
                //default redirect to login page
            } else if (button.equals("Login")) {
                url = loginServlet;
            } else if (button.equals("Logout")) {
                url = logoutServlet;
            } else if (button.equals("Search")) {
                url = searchIdNameServlet;
            } else if (button.equals("Delete")) {
                url = deleteServlet;
            } else if (button.equals("Update")) {
                url = updateServlet;
            } else if (button.equals("Insert")) {
                url = insertServlet;
            } else if(button.equals("Search in range")){
                url=searchInRangeServlet;
            } else if(button.equals("Add")){
                url=addToCartServlet;
            } else if(button.equals("View Cart")){
                url=viewCartPage;
            } else if(button.equals("Remove")){
                url=removeItemServlet;
            } else if(button.equals("Store to DB")){
                url=storeToDBServlet;
            }
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
