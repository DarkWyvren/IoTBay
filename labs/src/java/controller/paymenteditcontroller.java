package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.payment;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author 10847
 */
public class paymenteditcontroller extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String Payment_ID=request.getParameter("Payment_ID");
        String Payment_DATE=request.getParameter("Payment_DATE");
        DBManager manager=(DBManager) session.getAttribute("manager");
        
        payment payment=null;
        try{
            payment=manager.findpayment(Payment_ID,Payment_DATE);
            if(payment !=null){
                session.setAttribute("payment",payment);
                request.getRequestDispatcher("paymentupdate.jsp").include(request, response);
            }
            else{
                session.setAttribute("existErr","Payment doest not exist in the database!");
                request.getRequestDispatcher("paymentupdate.jsp").include(request, response);
            }
        }catch (SQLException ex){
            Logger.getLogger(paymenteditcontroller.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println(ex.getErrorCode()+"and"+ex.getMessage());
        }
        request.getRequestDispatcher("paymentview.jsp").include(request, response);
            
                
            }
            
        }