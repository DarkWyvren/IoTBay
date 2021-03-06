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
     import uts.isd.model.ProductBean;


/**
 *
 * @author antho
 */

@WebServlet(
  name = "AddProduct", 
  urlPatterns = "/AddNewProduct")

public class ProductAddController extends HttpServlet {
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
        ProductBean pb = new ProductBean();
       // double price[] = new double[3];
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            System.out.println(paraNames);
            switch(paraNames){
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
                    pb.setQuantity(Integer.parseInt(request.getParameter(paraNames)));
               // case "CStatus":
               //    pb.setCompanyStatus(Integer.parseInt(request.getParameter(paraNames)));
               //    break;
            }
        }
        
           try {
               manager.addProduct(pb);
           } catch (SQLException ex) {
               Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.println("test: " +pb);
            
        ArrayList<ProductBean> queryresult = null;
           try {
               queryresult = manager.fetchProductList();
           } catch (SQLException ex) {
               Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("ProductInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("Product.jsp");
        rd.forward(request, response);
        
               
       
    }
}
