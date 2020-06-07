/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Staff;
import uts.isd.model.dao.DBManager;
/**
 *
 * @author Danny16
 */
@WebServlet(
  name = "AddSupplier", 
  urlPatterns = "/AddNewSupplier")
public class StaffAddController extends HttpServlet
{
      protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
        HttpSession session = request.getSession();
        String FullName = request.getParameter("FullName");
        String Address = request.getParameter("Address");
        String Position = request.getParameter("Position");
        String Email = request.getParameter("Email");
        
        DBManager manager = (DBManager) session.getAttribute ("manager");
        Staff staff = null;  
        
        try {
            staff = manager.findStaff(FullName, Email);
            if (staff != null){
                session.setAttribute("ExistErr", "Staff does not exist in Database");
               request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
               
            } else {
               
               session.setAttribute("FullName", staff);
               request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
            }
        } catch (SQLException ex) {
               Logger.getLogger(StaffEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
      }
}
