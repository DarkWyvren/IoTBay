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
  name = "UpdateSupplier", 
  urlPatterns = "/SupplierEdit")

    public class SupplierEditController extends HttpServlet {
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
        int S_ID = Integer.parseInt(request.getParameter("SID"));
               try {
                   sb = manager.getSupplier(S_ID);
               } catch (SQLException ex) {
                   Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
        String name = sb.getCompanyName();
        String address = sb.getCompanyAddress();
        String type =sb.getCompanyType();
        String email =sb.getCompanyEmail();
        int status = sb.getCompanyStatus();
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id: " +S_ID + "name: " +name + "status:" +status); 
        request.setAttribute("SupplierInfo2", sb);
        request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        Supplier sb = new Supplier();
        String oldEmail = "";
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            System.out.println(paraNames);
            switch(paraNames){
                case "SupplierID":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setSupplierID(Integer.parseInt(request.getParameter(paraNames)));
                    break;
                case "CName":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setCompanyName(request.getParameter(paraNames));
                    break;
                case "CAddress":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setCompanyAddress(request.getParameter(paraNames));
                    break;
                case "CType":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setCompanyType(request.getParameter(paraNames));
                    break;
                case "CEmail":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setCompanyEmail(request.getParameter(paraNames));
                    break;
                case "CEmail2":
                    System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    oldEmail = request.getParameter(paraNames);
                    break;    
                case "CStatus":
                   System.out.println("LOOK AT ME DEBUG: " +request.getParameter(paraNames));
                    sb.setCompanyStatus(Integer.parseInt(request.getParameter(paraNames)));
                    break;
            }
        }
               try {
                   String SE = (String)manager.getSupEmail(sb);
                   System.out.println("TESTPLEASEWORK" +SE);
                   if( SE != null && !oldEmail.equals(SE)) {
                       System.out.println(manager.getSupEmail(sb));
                       request.setAttribute("EmailExistErr", "Supplier Email Already Exists!");
                       request.setAttribute("SupplierInfo2", sb);
                       request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
                       return;
                   }else if(sb.getCompanyName().length() == 0 || sb.getCompanyAddress().length() == 0 || sb.getCompanyEmail().length() == 0){
                       request.setAttribute("InfoMissinErr", "Please fill out all the information!");
                       request.setAttribute("SupplierInfo2", sb);
                       request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
                       return;
                   }else{
                       try {
                           System.out.println("test: " +sb);
                           manager.updateSupplier(sb);
                           
                       } catch (SQLException ex) {
                           Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
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
               } catch (SQLException ex) {
                   Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    
    


         

        /*HttpSession session = request.getSession();
        String C_Name = request.getParameter("CName");
        String C_Type = request.getParameter("CType");
           
        Supplier supplier = (Supplier)session.getAttribute ("manager");
        supplier = null;  

        try {
            supplier = manager.findSupplier(C_Name, C_Type);
            if (supplier != null){
               session.setAttribute("ContactName", supplier);
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
            } else {
               session.setAttribute("ExistErr", "Supplier does not exist in Database");
               request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);
            }
        } catch (SQLException ex) {
               Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("SupplierUpdate.jsp").include(request, response);*/
}

