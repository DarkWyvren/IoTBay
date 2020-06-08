package controller;

   import java.io.IOException;
   import java.sql.Connection;
   import java.sql.SQLException;
   import java.text.ParseException;
   import java.text.SimpleDateFormat;
   import java.util.ArrayList;
   import java.util.Arrays;
   import java.util.Calendar;
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
   import uts.isd.model.*;
/**
 *
 * @author Forever
 */
@WebServlet(
  name = "AddOrder", 
  urlPatterns = "/AddNewOrder")

public class OrderAddController extends HttpServlet {
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
        OrderBean ob = new OrderBean();
        boolean hastoc=false;
        int date[] = new int[3];
        String[] address = new String[2];
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println(paraNames);
            //try{
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
                /*case "status":
                    ob.setStatus(request.getParameter(paraNames));
                    break;
                case "productId":
                    ob.setProductId(Integer.parseInt(request.getParameter(paraNames)));
                    break;  */
                case "productName":
                    ob.setProductName(request.getParameter(paraNames));
                    break; 
                case "productPrice":
                    ob.setProductPrice(Double.parseDouble(request.getParameter(paraNames)));
                    break; 
                case "productQuanity":
                    ob.setProductQuanity(Integer.parseInt(request.getParameter(paraNames)));
                    break; 
                /*case "totalPrice":
                    ob.setTotalPrice(Double.parseDouble(request.getParameter(paraNames)));
                    break;*/
                case "agreeCheck":
                    hastoc=true;
                    break;
            }
            /*
            }catch(NumberFormatException ne){
                RequestDispatcher dispatch = request.getRequestDispatcher("orderAdd.jsp");
                request.setAttribute("response",  "Date has incorrect format");
                dispatch.forward(request, response);
                return;
            }*/
        }
        

        ob.setCustomerId(1);
        ob.setStatus("In ordering process");
        ob.setProductPrice(19.9);
        ob.setTotalPrice(19.9);
        ob.setShippingAddress(address[0]+"|"+address[1]);
        System.out.println(Arrays.toString(date));
        try{
        ob.setDOO(java.sql.Date.valueOf(date[2]+"-"+date[0]+"-"+date[1]));
        }catch(java.lang.IllegalArgumentException ec){
            RequestDispatcher dispatch = request.getRequestDispatcher("orderAdd.jsp");
            request.setAttribute("response",  "Date has incorrect format");
            dispatch.forward(request, response);
        }
        if(!hastoc){
            RequestDispatcher dispatch = request.getRequestDispatcher("orderAdd.jsp");
            request.setAttribute("response",  "Please agree to the TOC");
            dispatch.forward(request, response);
            return;
        }

        
        
        try {
                manager.addOrder(ob);
                System.out.println("test: " +ob);
            } catch (SQLException ex) {
                Logger.getLogger(OrderAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        ArrayList<OrderBean> queryresult = null;
           try {
               queryresult = manager.fetchOrderList();
           } catch (SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("OrderInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("orderView.jsp");
        rd.forward(request, response); 
    }
}
