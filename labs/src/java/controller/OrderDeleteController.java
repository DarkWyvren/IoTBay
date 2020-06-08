package controller;

   import java.io.IOException;
   import java.sql.Connection;
   import java.sql.SQLException;
   import java.util.ArrayList;
import java.util.Date;
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
   import uts.isd.model.OrderBean;

 
@WebServlet(
  name = "OrderDelete", 
  urlPatterns = "/deleteOrder")

   public class OrderDeleteController extends HttpServlet {
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
            Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    OrderBean od = null;
        int O_ID = Integer.parseInt(request.getParameter("OID"));
        System.out.println("id:" +O_ID);
           try {
               manager.deleteOrder(O_ID);
           } catch (SQLException ex) {
               Logger.getLogger(OrderDeleteController.class.getName()).log(Level.SEVERE, null, ex);
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