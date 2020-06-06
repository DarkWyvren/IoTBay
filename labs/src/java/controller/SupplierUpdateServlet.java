package controller;


import controller.SupplierController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Supplier;
import uts.isd.model.dao.DBManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mood35-Laptop
 */
public class SupplierUpdateServlet  extends HttpServlet{
    
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String C_Name = request.getParameter("CompanyName");
        String C_Email = request.getParameter("CompanyEmail");
           
        DBManager manager = (DBManager) session.getAttribute ("manager");
        Supplier supplier = null;  

        try {
            supplier = manager.findSupplier(C_Name, C_Email);
            if (supplier != null){
               session.setAttribute("ContactName", supplier);
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
            } else {
               session.setAttribute("ExistErr", "Supplier does not exist in Database");
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
            }
        } catch (SQLException ex) {
               Logger.getLogger(SupplierUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
    }   
    
}
