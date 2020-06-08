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
   import java.util.Scanner;
   import java.util.logging.Level;
   import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
   import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import javax.servlet.http.HttpSession;
   import uts.isd.model.dao.*;
   import uts.isd.model.Supplier;

/**
 *
 * @author mood35-Laptop
 */
@WebServlet(
  name = "AddSupplier", 
  urlPatterns = "/AddNewSupplier")

public class SupplierAddController extends HttpServlet {
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    @Override
        public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        Supplier sb = new Supplier();
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            System.out.println(paraNames);
            switch(paraNames){
                case "CName":
                    sb.setCompanyName(request.getParameter(paraNames));
                    break;
                case "CAddress":
                    sb.setCompanyAddress(request.getParameter(paraNames));
                    break;
                case "CType":
                    sb.setCompanyType(request.getParameter(paraNames));
                    break;
                case "CEmail":
                    sb.setCompanyEmail(request.getParameter(paraNames));
                    break;    
                case "CStatus":
                    sb.setCompanyStatus(Integer.parseInt(request.getParameter(paraNames)));
                    break;
            }
        }
        try {
                manager.addSupplier(sb);
                System.out.println("test: " +sb);
            } catch (SQLException ex) {
                Logger.getLogger(SupplierAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        ArrayList<Supplier> queryresult = null;
           try {
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("SupplierInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp");
        rd.forward(request, response);
        
               
       
    }

   
        
}
