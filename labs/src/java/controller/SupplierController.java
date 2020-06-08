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
   import uts.isd.model.Supplier;

 
@WebServlet(
  name = "getSupplier", 
  urlPatterns = "/ShowSupplier")

   public class SupplierController extends HttpServlet {
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
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
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
        ArrayList<Supplier> queryresult = null;
           try {
               //get list from database and place results in an array querylist
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.print(queryresult.toString() + "inside dogetmethod");
        RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp"); 
        request.setAttribute("SupplierInfo",  queryresult);//sendquerylist to supplier.jsp
        rd.forward(request, response);
       
    }

   
   }