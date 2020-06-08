package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Staff;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
/**
 *
 * @author Danny16
 */

@WebServlet(
  name = "UpdateStaff", 
  urlPatterns = "/StaffEdit")
public class StaffEditController extends HttpServlet
{
       private DBConnector db; // db 
       private DBManager manager; // 
       private Connection conn;
    
       @Override
        public void init() 
    {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            manager = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
           @Override
    public void destroy()
    {
        try 
        { 
            db.closeConnection();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Staff stf = null;
        int Id = Integer.parseInt(request.getParameter("Id"));
      
        try
        {
            stf = manager.getStaff(Id);
        } catch (SQLException ex)
        {
            Logger.getLogger(StaffEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String name = stf.getFullName();
        System.out.println("id:" + Id + "name: " + name);
        request.setAttribute("StaffInfo", stf);
        request.getRequestDispatcher("StaffUpdate.jsp").include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> paramNames = request.getParameterNames();
        Staff stf = new Staff();
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
            System.out.println(paraNames);
            switch(paraNames){
                case "Id":
                    stf.setId(Integer.parseInt(request.getParameter(paraNames)));
                    break;
                case "Name":
                    stf.setFullName(request.getParameter(paraNames));
                    break;
                case "Address":
                    stf.setAddress(request.getParameter(paraNames));
                    break;
                case "Pos":
                    stf.setPosition(request.getParameter(paraNames));
                    break;
                case "Email":
                    stf.setEmail(request.getParameter(paraNames));
                    break;    
                /*case "CompanyStatus":
                    sb.setCompanyStatus(request.getParameter(paraNames));
                    break;*/
            }
        }
        try {
                manager.updateStaff(stf);
                System.out.println("test: " +stf);
            } catch (SQLException ex) {
                Logger.getLogger(StaffEditController.class.getName()).log(Level.SEVERE, null, ex);
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
