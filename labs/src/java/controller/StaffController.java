/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import javax.servlet.http.HttpSession;
   import uts.isd.model.dao.*;
   import uts.isd.model.Staff;

@WebServlet(
  name = "StaffView", 
  urlPatterns = "/StaffInfo")
 public class StaffController extends HttpServlet 
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<Staff> queryResult = null;
        try
        {
            queryResult = manager.fetchStaffList();
        } catch (SQLException ex)
        {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("inside doGet" + queryResult);
        RequestDispatcher rd = request.getRequestDispatcher("StaffView.jsp");
        request.setAttribute("StaffInfo", queryResult);
        rd.forward(request, response);
    }

}

