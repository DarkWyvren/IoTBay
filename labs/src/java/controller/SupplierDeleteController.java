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
  name = "SupplierDelete", 
  urlPatterns = "/deleteSupplier")

   public class SupplierDeleteController extends HttpServlet {
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
    Supplier sd = null;
        int S_ID = Integer.parseInt(request.getParameter("SID"));
               try {
                   sd = manager.getSupplier(S_ID);
               } catch (SQLException ex) {
                   Logger.getLogger(SupplierEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
        String name = sd.getCompanyName();
        String address = sd.getCompanyAddress();
        String type =sd.getCompanyType();
        String email =sd.getCompanyEmail();
        //int status = Integer.parseInt(request.getParameter("CStatus"));
        System.out.println("id:" +S_ID + "name: " +name);
           try {
               manager.deleteSupplier(sd);
           } catch (SQLException ex) {
               Logger.getLogger(SupplierDeleteController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ArrayList<Supplier> queryresult = null;
           try {
               queryresult = manager.fetchSupplierList();
           } catch (SQLException ex) {
               Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("SupplierInfo",  queryresult);
        request.setAttribute("DeleteMsg",  "Supplier has been deleted");
        RequestDispatcher rd = request.getRequestDispatcher("Supplier.jsp");
        rd.forward(request, response);
    }

   
   }