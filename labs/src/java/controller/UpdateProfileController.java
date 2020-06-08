/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.AccountTracker;
import uts.isd.model.CustomerBean;
import uts.isd.model.Staff;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author willi
 */
@WebServlet(
  name = "UpdateProfileServlet", 
  urlPatterns = "/updateProf")
public class UpdateProfileController extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
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

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    CustomerBean populateCustomer(HttpServletRequest req, Enumeration<String> paramNames) throws NumberFormatException, java.lang.IllegalArgumentException{
        CustomerBean cb = new CustomerBean();
        int date[] = new int[3];
        String[] address = new String[2];
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            
            switch(paramName){
                case "full_name":
                    cb.setName(req.getParameter(paramName));
                    break;
                case "email":
                    cb.setEmail(req.getParameter(paramName));
                    break;
                case "password":
                    cb.setPassword(req.getParameter(paramName));
                    break;
                case "dateday":
                    date[1] = Integer.parseInt(req.getParameter(paramName));
                    break;    
                case "datemonth":
                    date[0] = Integer.parseInt(req.getParameter(paramName));
                    break;
                case "dateyear":
                    date[2] = Integer.parseInt(req.getParameter(paramName));
                    break;    
                case "address":
                    address[0] = req.getParameter(paramName);
                    break; 
                case "postalcode":
                    address[1] = req.getParameter(paramName);
                    break; 
                case "phone":
                    cb.setPhone(req.getParameter(paramName));
                    break;     
                case "title":
                    cb.setTitle(req.getParameter(paramName));
                    break;         
                
            }
            
        }
        cb.setAddress(address[0]+"|"+address[1]);
        System.out.println(Arrays.toString(date));
        cb.setDOB(java.sql.Date.valueOf(date[2]+"-"+date[0]+"-"+date[1]));
        return cb;
    }
    
    Staff populateStaff(HttpServletRequest req,Enumeration<String> paramNames){
        Staff s = new Staff();
        String[] address = new String[2];
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
           
            switch(paramName){
                case "full_name":
                    s.setFullName(req.getParameter(paramName));
                    break;
                case "email":
                    s.setEmail(req.getParameter(paramName));
                    break;
                case "password":
                    s.setPassword(req.getParameter(paramName));
                    break;
                case "address":
                    address[0] = req.getParameter(paramName);
                    break; 
                case "postalcode":
                    address[1] = req.getParameter(paramName);
                    break; 
            }
            
        }
        s.setAddress(address[0]+"|"+address[1]);
        return s;
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        Object editprof =  req.getSession().getAttribute("login");
        
        if(editprof instanceof CustomerBean){
            try{
                CustomerBean cb = populateCustomer(req, paramNames);
            
                cb.setId(((CustomerBean)req.getSession().getAttribute("login")).getId());

                
                String res = AccountTracker.updateAccount(manager, cb);
                req.setAttribute("response",  res);

                if(res.equals("OK")){
                    req.getSession().setAttribute("login", cb);
                }
                RequestDispatcher dispatch = req.getRequestDispatcher("profile.jsp");
                dispatch.forward(req, resp);
            }catch(java.lang.IllegalArgumentException ec){
                RequestDispatcher dispatch = req.getRequestDispatcher("profile.jsp");
                req.setAttribute("response",  "Date has incorrect format");
                dispatch.forward(req, resp);
                return;
            }
        }else{
            Staff sb = populateStaff(req, paramNames);
            String res = "OK";
            
            if(sb.getPassword().length()<6){
                res="Password must be 6 character or longer";
            }
            
            
            if(res.equals("OK")){
                req.getSession().setAttribute("login", sb);
                try {
                    manager.updateStaff(sb);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    req.setAttribute("response",  "SQL Died FUCK");
                }
            }else{
                req.setAttribute("response",  res);
            }
            RequestDispatcher dispatch = req.getRequestDispatcher("profile.jsp");
            dispatch.forward(req, resp);
            
        
        }
        
    }
    
    
    
    
}
