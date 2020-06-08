/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.ValidProduct;
import uts.isd.model.ProductBean;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
import uts.isd.model.paymentprefBean;


/**
 *
 * @author antho
 */

@WebServlet(
  name = "PaymentPref", 
  urlPatterns = "/paymentPref")

public class UpdatePaymentPrefController extends HttpServlet{
    
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         try {
             req.setAttribute("prefs", manager.getPaymentPref(Integer.parseInt(req.getParameter("CID"))));
             RequestDispatcher dispatch = req.getRequestDispatcher("paymentprefs.jsp");
            dispatch.forward(req, resp);
         } catch (SQLException ex) {
             Logger.getLogger(UpdatePaymentPrefController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        //Enumeration<Double> paramPrice = req.getParameterPrice();
        paymentprefBean pb = new paymentprefBean();
        boolean hastoc=false;
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            //String paramPrice = paramPrice.nextDouble();
            switch(paramName){
                case "cid":
                    pb.setCusid(Integer.parseInt(req.getParameter(paramName)));
                    break;
                case "ID":
                    pb.setPayment_ID((req.getParameter(paramName)));
                    break;
                case "creditcard":
                    pb.setCreditcard_Details(req.getParameter(paramName));
                    break;
                case "pay_meth":
                    pb.setPayment_METHOD(req.getParameter(paramName));
                    break;    
                 
            }
        }
        
         try {
             if(manager.getPaymentPref(pb.getCusid())==null){
                 manager.addPaymentPref(pb);
             }else{
                 manager.updatePaymentPref(pb);
             }
         } catch (SQLException ex) {
             Logger.getLogger(UpdatePaymentPrefController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        RequestDispatcher dispatch = req.getRequestDispatcher("paymentprefs.jsp");
        req.setAttribute("prefs", pb);
        dispatch.forward(req, resp);
    }
    
    
    
}
