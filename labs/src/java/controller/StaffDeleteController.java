
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
   import uts.isd.model.Staff;

@WebServlet(
  name = "StaffDelete", 
  urlPatterns = "/deleteStaff")

public class StaffDeleteController extends HttpServlet{
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
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
       
    @Override
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Staff stf = null;
        int Id = Integer.parseInt(request.getParameter("Id"));
               try {
                   stf = manager.getStaff(Id);
               } catch (SQLException ex) {
                   Logger.getLogger(StaffEditController.class.getName()).log(Level.SEVERE, null, ex);
               }
        String name = stf.getFullName();
        System.out.println("id:" +Id + "name: " +name);
           try {
               manager.deleteStaff(stf);
           } catch (SQLException ex) {
               Logger.getLogger(StaffDeleteController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ArrayList<Staff> queryresult = null;
           try {
               queryresult = manager.fetchStaffList();
           } catch (SQLException ex) {
               Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("StaffInfo",  queryresult);
        RequestDispatcher rd = request.getRequestDispatcher("StaffView.jsp");
        rd.forward(request, response);
    }
}
