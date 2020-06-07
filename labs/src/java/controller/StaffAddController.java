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
  name = "AddStaff", 
  urlPatterns = "/AddNewStaff")
public class StaffAddController extends HttpServlet
{
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
       
    @Override
        public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            Enumeration<String> paraNames = request.getParameterNames();
            Staff st = new Staff();
            while(paraNames.hasMoreElements())
            {
                String parNames = paraNames.nextElement();
                System.out.println(parNames);
                switch(parNames)
                {
                    case "FullName":
                        st.setFullName(request.getParameter(parNames));
                    case "Address":
                    st.setAddress(request.getParameter(parNames));
                    break;
                case "Position":
                    st.setPosition(request.getParameter(parNames));
                    break;
                case "Email":
                    st.setEmail(request.getParameter(parNames));
                    break;    
                }
            }
            try
            {
                manager.addStaff(st);
            }
        }
}
