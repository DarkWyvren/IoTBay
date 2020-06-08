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
   import uts.isd.model.Product;


 
@WebServlet(
  name = "ProductDelete", 
  urlPatterns = "/deleteProduct")

   public class ProductDeleteController extends HttpServlet {
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
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Product pd = null;
        int P_ID = Integer.parseInt(request.getParameter("PID"));
               try {
                   pd = manager.getProduct(P_ID);
               } catch (SQLException ex) {
                   Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
        String name = pd.getName();
        Double price = pd.getPrice();
        String type =pd.getCategory();
        Integer supplierID =pd.getSupplier();
        Integer quantity =pd.getQuantity();
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id:" +P_ID + "name: " +name);
           try {
               manager.deleteProduct(pd);
           } catch (SQLException ex) {
               Logger.getLogger(ProductDeleteController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ArrayList<Product> queryresult = null;
           try {
               queryresult = manager.fetchProductList();
           } catch (SQLException ex) {
               Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("ProductInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("Product.jsp");
        rd.forward(request, response);
    }

   
   }
