/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import uts.isd.model.*;
import uts.isd.model.dao.*;

/**
 *
 * @author mood35-Laptop
 */
@WebServlet(
  name = "UpdateOrder", 
  urlPatterns = "/OrderEdit")

    public class OrderEditController extends HttpServlet {
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
            Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    @Override
        public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            OrderBean ob = null;
        int O_ID = Integer.parseInt(request.getParameter("OID"));
               try {
                    ob = manager.getOrderBean(O_ID);
               } catch (SQLException ex) {
                   Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
               };
                int OrderId = ob.getOrderId();
                int CustomerId = ob.getCustomerId();
                Date DOO = ob.getDOO();
                String ShippingAddress = ob.getShippingAddress();
                String Status = ob.getStatus();
                int ProductId = ob.getProductId();
                String ProductName = ob.getProductName();
                double ProductPrice = ob.getProductPrice();
                int ProductQuanity = ob.getProductQuanity();
                double Total_Price = ob.getTotalPrice();
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id:" +O_ID); 
        request.setAttribute("OrderInfo2", ob);
        request.getRequestDispatcher("OrderUpdate.jsp").include(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        OrderBean ob = new OrderBean();
        boolean hastoc=false;
        int date[] = new int[3];
        String[] address = new String[2];
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println(paraNames);
            switch(paraNames){
                case "dateday":
                    date[1] = Integer.parseInt(request.getParameter(paraNames));
                    break;    
                case "datemonth":
                    date[0] = Integer.parseInt(request.getParameter(paraNames));
                    break;
                case "dateyear":
                    date[2] = Integer.parseInt(request.getParameter(paraNames));
                    break;  
                case "address":
                    address[0] = request.getParameter(paraNames);
                    break;
                case "postalcode":
                    address[1] = request.getParameter(paraNames);
                    break; 
                case "status":
                    ob.setStatus(request.getParameter(paraNames));
                    break;
                case "productId":
                    ob.setProductId(Integer.parseInt(request.getParameter(paraNames)));
                    break;  
                case "productName":
                    ob.setProductName(request.getParameter(paraNames));
                    break; 
                case "productPrice":
                    ob.setProductPrice(Double.parseDouble(request.getParameter(paraNames)));
                    break; 
                case "productQuanity":
                    ob.setProductQuanity(Integer.parseInt(request.getParameter(paraNames)));
                    break; 
                case "totalPrice":
                    ob.setTotalPrice(Double.parseDouble(request.getParameter(paraNames)));
                    break;
                case "agreeCheck":
                    hastoc=true;
                    break;
            }
        }
        try {
                manager.updateOrder(ob);
                System.out.println("test: " +ob);
            } catch (SQLException ex) {
                Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        ArrayList<Supplier> queryresult = null;
           try {
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("OrderInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
        rd.forward(request, response);
        //request.setAttribute("response",   );
        //request.getRequestDispatcher("Supplier.jsp").include(request, response);
    }
}

