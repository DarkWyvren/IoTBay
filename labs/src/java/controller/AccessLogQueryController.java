/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import uts.isd.model.CustomerAccessLogBean;
import uts.isd.model.CustomerBean;
import uts.isd.model.Staff;
import uts.isd.model.StaffAccessLogBean;
import uts.isd.model.UniversalAccessLog;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author willi
 */
@WebServlet(
  name = "getAccessLogs", 
  urlPatterns = "/accessLogs")
public class AccessLogQueryController  extends HttpServlet {
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
        Object sesh = req.getSession().getAttribute("login");
        
        try{
            ArrayList<UniversalAccessLog> queryresult = new ArrayList();
            if(sesh instanceof CustomerBean){
                ArrayList<CustomerAccessLogBean> cblist =  manager.listCustomerLoginRecord(((CustomerBean)sesh).getId());
                for(CustomerAccessLogBean cb: cblist){
                    queryresult.add(new UniversalAccessLog(cb.getLoggedin(), cb.getLoggedout()));
                }
            }else{
                ArrayList<StaffAccessLogBean> cblist =  manager.listStaffLoginRecord(((Staff)sesh).getId());
                cblist.forEach((cb) -> {
                    queryresult.add(new UniversalAccessLog(cb.getLoggedin(), cb.getLoggedout()));
                });
            }
            
            String query = req.getParameter("search_date");
            if(query!=null &&query.trim().length()>0){
                query = query.trim();
                String mode = "EQUALS";
                String params[] = query.split(" +",2);
                if(params.length>1){
                    switch(params[0].toLowerCase().trim()){
                        case "equal":
                        case "equals":
                        case "=":
                        case "==":    
                            break;
                        case "<":
                        case "before":
                        case "before_date":
                            mode = "BEFORE";  
                            break;
                        case ">":
                        case "after":
                        case "past":
                            mode = "AFTER";  
                            break;    
                        default:
                            mode = "UNKNOWN";
                        break;
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sdf.parse(params[params.length>1?1:0]);
                Calendar cd = Calendar.getInstance();
                cd.setTime(d);
                int day_of_year = cd.get(Calendar.DAY_OF_YEAR);
                int year = cd.get(Calendar.YEAR);
                int dayindex= day_of_year+year*365;
                System.out.println("dayindex="+dayindex+ ","+day_of_year+","+year);
                for(int i = 0;i<queryresult.size();i++){
                    boolean shouldremove = false;
                    cd.setTime(queryresult.get(i).getLoggedin());
                    int loginday = cd.get(Calendar.DAY_OF_YEAR)+cd.get(Calendar.YEAR)*365;
                    System.out.println("login day index="+loginday+ ","+cd.get(Calendar.DAY_OF_YEAR)+","+cd.get(Calendar.YEAR));
                   switch(mode){
                       case "EQUALS":
                           if(loginday != dayindex ){
                               shouldremove = true;
                           }
                           break;
                       case "BEFORE":
                           if(loginday > dayindex ){
                               shouldremove = true;
                           }
                           break;
                        case "AFTER":
                           if(loginday< dayindex ){
                               shouldremove = true;
                           }
                           break;   
                   
                   }
                   if(shouldremove){
                       queryresult.remove(i);
                       i--;
                   }
                }
            }
            System.out.println("returned? "+queryresult.toString());
            RequestDispatcher dispatch = req.getRequestDispatcher("accesslogs.jsp");
            req.setAttribute("data",  queryresult);
            dispatch.forward(req, resp);
        }catch(Exception e){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
