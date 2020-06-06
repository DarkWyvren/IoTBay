/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
/**
 *
 * @author mood35-Laptop
 */

public class SupplierAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String C_Name = request.getParameter("CompanyName");
        String C_Address = request.getParameter("CompanyAddress");
        String C_Type = request.getParameter("CompanyType");
        String C_Email = request.getParameter("CompanyEmail");
        //int C_Status = request.getParameter("CompanyStatus");
        
           
        DBManager manager = (DBManager) session.getAttribute ("manager");
        Supplier supplier = null;  

        try {
            supplier = manager.findSupplier(C_Name, C_Email);
            if (supplier != null){
                session.setAttribute("ExistErr", "Supplier does not exist in Database");
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
               
            } else {
               //manager.addSupplier(C_Name, C_Address, C_Type, C_Email, C_Status);
               session.setAttribute("ContactName", supplier);
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
            }
        } catch (SQLException ex) {
               Logger.getLogger(SupplierUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
    }   
}
