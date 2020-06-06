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
import uts.isd.model.Staff;
import uts.isd.model.dao.DBManager;
/**
 *
 * @author Danny16
 */
public class StaffEditController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String Name = request.getParameter("StaffName");
        String Email = request.getParameter("StaffEmail");
           
        DBManager manager = (DBManager) session.getAttribute ("manager");
        Staff staff = null;  

        try {
            staff = manager.findStaff(Name, Email);
            if (staff != null){
               session.setAttribute("ContactName", staff);
               request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
            } else {
               session.setAttribute("ExistErr", "Staff does not exist in Database");
               request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
            }
        } catch (SQLException ex) {
               Logger.getLogger(StaffEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("StaffEdit.jsp").include(request, response);
    }   
    
}
