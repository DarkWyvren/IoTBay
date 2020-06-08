package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
        ArrayList<Staff> queryResult = null;
        String name = ""; 
        try
        {
            queryResult = manager.findStaff(name);
        } catch (SQLException ex)
        {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("inside doget" + queryResult);
        RequestDispatcher rd = request.getRequestDispatcher("StaffEdit.jsp");
        request.setAttribute("Staff", queryResult);
        rd.forward(request, response);
    }
}
