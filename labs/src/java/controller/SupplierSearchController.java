/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Supplier;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author mood35-Laptop
 */
@WebServlet(
  name = "SupplierSearch", 
  urlPatterns = "/SearchSupplier")

    public class SupplierSearchController extends HttpServlet {
           private DBConnector db; // db 
       private DBManager manager; // 
       private Connection conn;
       
    @Override
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            manager = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    @Override
        public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Supplier sb = null;
            String type = request.getParameter("CompanyType");
            String name = request.getParameter("CompanyName");
            System.out.println("-THIS IS A DEBUG- Name: " +type+ "name: " +name + "test: " +sb);
               try {
                   sb = manager.findSupplier(type, name);
               } catch (SQLException ex) {
                   Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
         ArrayList<Supplier> queryresult = null;
           try {
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.print(queryresult.toString() + "inside dogetmethod");
        RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp"); 
        request.setAttribute("SupplierInfo",  queryresult);
        rd.forward(request, response);
        System.out.println("-THIS IS A DEBUG- Name: " +type+ "name: " +name + "test: " +sb); 
        request.setAttribute("SupplierInfo2", sb);
        request.getRequestDispatcher("Supplier.jsp").include(request, response);
        
    }
    
}

