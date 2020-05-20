/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author willi
 */
@WebServlet(
  name = "LoginServlet", 
  urlPatterns = "/loginauth")
public class LoginController  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerBean current = (CustomerBean)req.getSession().getAttribute("login");
        if(AccountTracker.isValidLogin(current.getEmail(), current.getPassword())){
            AccountTracker.logout(current);
            req.getSession().removeAttribute("login");
            System.out.println("valid account, session removed");
        }
        RequestDispatcher dispatch = req.getRequestDispatcher("index.jsp");
        req.setAttribute("response",  "OK");
        dispatch.include(req, resp);
        dispatch.forward(req, resp);
        System.out.println("CMON"+req.getSession().getAttribute("login"));
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        String email="",pass="";
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            switch(paramName){
                case "email":
                    email = (req.getParameter(paramName));
                    break;
                case "password":
                    pass = (req.getParameter(paramName));
                    break;    
            }
        }
        System.out.println("Login");
        
        CustomerBean current = (CustomerBean)req.getSession().getAttribute("login");
        if(AccountTracker.isLoggedIn(email)&&AccountTracker.isValidLogin(current.getEmail(), current.getPassword())){
            AccountTracker.logout(current);
            req.getSession().setAttribute("login", null);
            RequestDispatcher dispatch = req.getRequestDispatcher("index.jsp");
            req.setAttribute("response",  "OK");
            dispatch.forward(req, resp);
            return;
        }
        
        if(!AccountTracker.isValidLogin(email, pass)){
            RequestDispatcher dispatch = req.getRequestDispatcher("login.jsp");
            req.setAttribute("response",  "Invalid email or password");
            dispatch.forward(req, resp);
        }else{
            
            AccountTracker.login(current);
            req.getSession().setAttribute("login", AccountTracker.getCustomerByEmail(email));
            RequestDispatcher dispatch = req.getRequestDispatcher("index.jsp");
            req.setAttribute("response",  "OK");
            dispatch.forward(req, resp);
        }
        
        
    }
    
}
