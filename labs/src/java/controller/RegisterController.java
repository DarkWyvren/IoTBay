/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.AccountTracker;
import uts.isd.model.CustomerBean;

/**
 *
 * @author willi
 */
@WebServlet(
  name = "RegisterServlet", 
  urlPatterns = "/registerauth")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        CustomerBean cb = new CustomerBean();
        boolean hastoc=false;
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
                case "agreeCheck":
                    hastoc=true;
                    break;
            }
        }
        if(!hastoc){
            RequestDispatcher dispatch = req.getRequestDispatcher("register.jsp");
            req.setAttribute("response",  "Please agree to the TOC");
            dispatch.forward(req, resp);
            return;
        }
        System.out.println("CMON");
        RequestDispatcher dispatch = req.getRequestDispatcher("register.jsp");
        req.setAttribute("response",  AccountTracker.registerAccount(cb));
        dispatch.forward(req, resp);
        
    }
    
    
    
    
}
