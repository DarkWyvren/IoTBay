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
import uts.isd.model.payment;
import uts.isd.model.dao.DBManager;
/**
 *
 * @author 10847
 */
public class paymentUpdateServlet extends HttpServlet {

  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session=request.getSession();
       String Payment_ID=request.getParameter("Payment_ID");
       String Payment_METHOD=request.getParameter("Payment_METHOD");
       String Payment_DATE=request.getParameter("Payment_DATE");
       String Creditcard_Details=request.getParameter("Creditcard_Details");
       String Payment_Amount=request.getParameter("Payment_Amount");
       payment payment=new payment(Payment_ID,Payment_METHOD,Payment_DATE,Creditcard_Details,Integer.parseInt(Payment_Amount));
       DBManager manager=(DBManager)session.getAttribute("manager");
      
      
      try{
          if (payment != null){
              session.setAttribute("payment", payment);
              manager.updatePayment(Payment_ID,Payment_METHOD,Payment_DATE,Creditcard_Details,Integer.parseInt(Payment_Amount));
              session.setAttribute("updated", "Update was successful");
              request.getRequestDispatcher("paymentupdat.jsp").include(request, response);
          }else{
              session.setAttribute("updated", "Update was not successful!");
              request.getRequestDispatcher("paymentupdate.jsp").include(request, response);
          }
         } catch (SQLException ex) {
          Logger.getLogger(paymentUpdateServlet.class.getName()).log(Level.SEVERE,null,ex);
      }
      response.sendRedirect("paymentupdate.jsp");
              
              
          }
      } 
       