package controller;




import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.OrderBean;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.payment;
import uts.isd.model.dao.DBManager;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 10847
 */
@WebServlet(
  name = "AddPayment", 
  urlPatterns = "/AddPayment")
public class paymentaddservlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
           try {
               OrderBean ob = manager.findOrder(Integer.parseInt(request.getParameter("orderid")));
               request.setAttribute("order", ob);
               request.setAttribute("paymentpref", manager.getPaymentPref(ob.getCustomerId()));
               RequestDispatcher rd = request.getRequestDispatcher("paymentadd.jsp");
               rd.forward(request, response);
           } catch (SQLException ex) {
               Logger.getLogger(paymentaddservlet.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
       String Payment_ID=request.getParameter("Payment_ID");
       String Payment_METHOD=request.getParameter("Payment_METHOD");
       String Payment_DATE=request.getParameter("Payment_DATE");
       String Creditcard_Details=request.getParameter("Creditcard_Details");
       String Payment_Amount=request.getParameter("Payment_Amount");
      
       
       try{
           payment exist=manager.findpayment(Payment_ID, Payment_DATE);
           if(exist!=null){
               session.setAttribute("existErr","Payment already exists in the Database!");
               request.getRequestDispatcher("paymentadd.jsp").include(request, response);
           }else{

               manager.addPayment( (request.getParameter("ordergay")), Payment_DATE,  Payment_METHOD,  Creditcard_Details,  Integer.parseInt(Payment_Amount));
               payment payment=new payment(Payment_ID, Payment_DATE,  Payment_METHOD,  Creditcard_Details,  Integer.parseInt(Payment_Amount));
               
               request.setAttribute("OrderInfo", manager.fetchOrderList());
               request.getRequestDispatcher("order.jsp").include(request,response);
           }
       }catch (SQLException ex){
           Logger.getLogger(paymentaddservlet.class.getName()).log(Level.SEVERE,null,ex);
           }
       }
    }