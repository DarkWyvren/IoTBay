/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Supplier;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author mood35-Laptop
 */

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   public class SupplierAllController extends HttpServlet {
       private DBConnector db; // db 
       private DBManager manager; // 
       private Connection conn;
       RequestDispatcher rd = null;
       
      @Override 
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            manager = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SupplierAllController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(SupplierAllController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String action = request.getServletPath();
        try{
            switch(action){
            case "/LIST":
                showList(request,response);
                break;
            
            case "/ADD":
                addSupplier(request,response);
                break;
            
            case "/EDIT":
                editSupplier(request,response);
                break;
                
             
            default:
                showList(request,response);
                break;    
        }
        }catch (SQLException e){
            Logger.getLogger(SupplierAllController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
     
    }
    
    protected void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Supplier> queryresult = null;
           try {
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(SupplierAllController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.print(queryresult.toString() + "inside dogetmethod");
        RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp"); 
        request.setAttribute("SupplierInfo",  queryresult);
        rd.forward(request, response);
        /*try {
               request.setAttribute("listSupplier", new DBManager.fetchSupplierList());
           } catch (SQLException ex) {
               Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
           }
        */
    
     
    }
    
    protected void addSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("CName");
        String address = request.getParameter("CAddress");
        String type =request.getParameter("CType");
        String email =request.getParameter("CEmail");
        
        System.out.println("Name: " +name);
        System.out.println("Address: " +address);
        System.out.println("Type: " +type);
        System.out.println("Email: " +email);
        
        showList(request,response);
                
    }
   
    protected void editSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id:" +id);
        RequestDispatcher rd = request.getRequestDispatcher("SupplierUpdate.jsp"); 
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
