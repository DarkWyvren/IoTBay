package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
                    ob.setProduct(manager.getProduct(ob.getProductID()));
               } catch (SQLException ex) {
                   Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
               };
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id:" +O_ID); 
        request.setAttribute("order", ob);
        request.getRequestDispatcher("orderUpdate.jsp").include(request, response);
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        OrderBean ob = new OrderBean();
        boolean hastoc=false;
        int date[] = new int[3];
        String[] address = new String[2];
        while(paramNames.hasMoreElements()){
            try {
                String paraNames = paramNames.nextElement();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                System.out.println(paraNames);
                switch(paraNames){
                    case "inputCustomerId":
                        ob.setCustomerId(Integer.parseInt(request.getParameter(paraNames)));
                        break;
                    case "OrderID":
                        ob.setOrderId(Integer.parseInt(request.getParameter(paraNames)));
                        break;
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
                    case "productID":
                        ob.setProduct(manager.getProduct(Integer.parseInt(request.getParameter(paraNames))));
                        break;
                    case "productQuantity":
                        ob.setProductQuantity(Integer.parseInt(request.getParameter(paraNames)));
                        break;
                    case "agreeCheck":
                        hastoc=true;
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ob.setShippingAddress(address[0]+"|"+address[1]);
        
        System.out.println(Arrays.toString(date));
        try{
            ob.setProduct(manager.getProduct(ob.getProductID()));
            ob.setDOO(java.sql.Date.valueOf(date[2]+"-"+date[0]+"-"+date[1]));
        }catch(java.lang.IllegalArgumentException ec){
            RequestDispatcher dispatch = request.getRequestDispatcher("orderUpdate.jsp");
            request.setAttribute("response",  "Date has incorrect format");
            request.setAttribute("order", ob);
            dispatch.forward(request, response);
            return;
        }  catch (SQLException ex) {
           Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
       }
        String etext="";
        
        if(!hastoc){
            etext = "Please agree to the TOC";
           
        }
        if(ob.getProductQuantity()<=0){
            etext="cannot order nothing or less";
        }
        
        if(etext.length()!=0){
            try {
                ProductBean pbean = manager.getProduct(ob.getProductID());
                 RequestDispatcher dispatch = request.getRequestDispatcher("orderUpdate.jsp");
                 request.setAttribute("product", pbean);
                request.setAttribute("response",  etext);
                dispatch.forward(request, response);
                return;
                
            } catch (SQLException ex) {
                Logger.getLogger(OrderAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        try {
                manager.updateOrder(ob);
                System.out.println("test: " +ob);
            } catch (SQLException ex) {
                Logger.getLogger(OrderEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         ArrayList<OrderBean> queryresult = null;
           try {
               queryresult = manager.fetchOrderList();
           } catch (SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("OrderInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
        rd.forward(request, response);
    }
}

