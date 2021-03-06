/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_customer.tbl_customerDAO;
import sample.tbl_customer.tbl_customerDTO;
import sample.tbl_customer.tbl_customerInsertError;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String registerPage="register.jsp";
    private final String loginPage="login.html";
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
        
        //in default, if there is any error, move to register.jsp
        String url=registerPage;
        
        //get all needed input from request
        String custId=request.getParameter("txtCustID");
        String password=request.getParameter("txtPassword");
        String custName=request.getParameter("txtCustName");
        String lastName=request.getParameter("txtLastName");
        String middleName=request.getParameter("txtMiddleName");
        String address=request.getParameter("txtAddress");
        String phone=request.getParameter("txtPhone");
        
        //errors for check duplicated custID
        tbl_customerInsertError errors= new tbl_customerInsertError();
        
        tbl_customerDTO dto= new tbl_customerDTO(custId, password, custName, lastName, middleName, address, phone, 0);
        try{
            tbl_customerDAO dao= new tbl_customerDAO();
            boolean result=dao.insertCustomer(dto);
            //if regist successfully, move to login page
            if(result){
                url=loginPage;
            }
        } catch (SQLException ex) {
            log("RegisterServlet_SQLException: "+ex.getMessage());
            if(ex.getMessage().contains("duplicate")){
                errors.setCustIDDuplicated(custId+ "has existed!");
                request.setAttribute("INSERT_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("RegisterServlet_NamingException: "+ex.getMessage());
        } finally{
            //use RequestDispatcher to keep request obj to show error on the register.jsp
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
