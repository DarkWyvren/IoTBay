package controller;




import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class paymentaddservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
       String Payment_ID=request.getParameter("Payment_ID");
       String Payment_METHOD=request.getParameter("Payment_METHOD");
       String Payment_DATE=request.getParameter("Payment_DATE");
       String Creditcard_Details=request.getParameter("Creditcard_Details");
       String Payment_Amount=request.getParameter("Payment_Amount");
       DBManager manager=(DBManager)session.getAttribute("manager");
       
       try{
           payment exist=manager.findpayment(Payment_ID, Payment_DATE);
           if(exist!=null){
               session.setAttribute("existErr","Payment already exists in the Database!");
               request.getRequestDispatcher("paymentadd.jsp").include(request, response);
           }else{
               manager.addPayment( Payment_ID, Payment_DATE,  Payment_METHOD,  Creditcard_Details,  Payment_Amount);
               payment payment=new payment(Payment_ID, Payment_DATE,  Payment_METHOD,  Creditcard_Details,  Payment_Amount);
               session.setAttribute("payment", payment);
               request.getRequestDispatcher("payment.jsp").include(request,response);
           }
       }catch (SQLException ex){
           Logger.getLogger(paymentaddservlet.class.getName()).log(Level.SEVERE,null,ex);
           }
       }
    }