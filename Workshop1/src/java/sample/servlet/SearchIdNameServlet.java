/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
@WebServlet(name = "SearchIdNameServlet", urlPatterns = {"/SearchIdNameServlet"})
public class SearchIdNameServlet extends HttpServlet {

    private final String staffPage="staffPage.jsp";
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
        PrintWriter out = response.getWriter();
        String url=staffPage;
        try  {
            String searchId=request.getParameter("txtSearchId");
            if(searchId.trim().length()>0){
                tbl_MobileDAO dao= new tbl_MobileDAO();
                List<tbl_MobileDTO> result=dao.searchMobileById(searchId);
                request.setAttribute("RESULT_ID", result);
            }
            String searchName = request.getParameter("txtSearchName");
            if (searchName.trim().length() > 0) {
                tbl_MobileDAO dao = new tbl_MobileDAO();
                List<tbl_MobileDTO> result = dao.searchMobileByName(searchName);
                request.setAttribute("RESULT_NAME", result);
            }
        } catch (NamingException ex) {
            //Logger.getLogger(SearchIdNameServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("SearchIdServlet_NamingException: "+ex.getMessage());
        } catch (SQLException ex) {
            //Logger.getLogger(SearchIdNameServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("SearchIdServlet_SQLException: "+ex.getMessage());
        } finally{
            RequestDispatcher rd= request.getRequestDispatcher(url);
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
