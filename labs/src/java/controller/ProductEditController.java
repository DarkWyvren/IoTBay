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
import uts.isd.model.Product;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author antho
 */
@WebServlet (
        name = "UpdateProduct",
        urlPatterns = "/ProductEdit")

public class ProductEditController extends HttpServlet{
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
            Logger.getLogger(ProductEditController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    @Override
        public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(ProductEditController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Product pb = null;
        int P_ID = Integer.parseInt(request.getParameter("PID"));
               try {
                   pb = manager.getProduct(P_ID);
               } catch (SQLException ex) {
                   Logger.getLogger(ProductEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        Integer ID = pb.getID();
        String name = pb.getName();
        Double price =  pb.getPrice();
        String category = pb.getCategory();
        Integer supplier = pb.getSupplier();
        Integer quantity = pb.getQuantity();
        
        
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id:" +P_ID + "name: " +name); 
        request.setAttribute("ProductInfo2", pb);
        request.getRequestDispatcher("ProductUpdate.jsp").include(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        Product pb = new Product();
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            System.out.println(paraNames);
            switch(paraNames){
                
                case "ProductID":
                    pb.setID (Integer.parseInt(request.getParameter(paraNames)));
                    break;
                case "PName":
                    pb.setName(request.getParameter(paraNames));
                    break;
                case "PPrice":
                    pb.setPrice(Double.parseDouble(request.getParameter(paraNames)));
                    break;
                case "PCategory":
                    pb.setCategory(request.getParameter(paraNames));
                    break;
                case "PSupplier":
                    pb.setSupplier(Integer.parseInt(request.getParameter(paraNames)));
                    break;
                case "PQUANT":
                    System.out.println("This is a test" + request.getParameter(paraNames));
                    pb.setQuantity(Integer.parseInt(request.getParameter(paraNames)));    
                /*case "CompanyStatus":
                    sb.setCompanyStatus(request.getParameter(paraNames));
                    break;*/
            }
        }
        try {
                manager.updateProduct(pb);
                System.out.println("test: " +pb);
            } catch (SQLException ex) {
                Logger.getLogger(ProductEditController.class.getName()).log(Level.SEVERE, null, ex);
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
        //request.setAttribute("response",   );
        //request.getRequestDispatcher("Supplier.jsp").include(request, response);
    }
}
