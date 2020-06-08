    package controller;

    import java.io.IOException;
    import java.sql.Connection;
    import java.sql.SQLException;
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
    import uts.isd.model.*;

    /**
    *
    * @author Max
    */
    @WebServlet(
    name = "getOrder", 
    urlPatterns = "/OrderView")
    public class OrderViewController extends HttpServlet {

       private DBConnector db;
       private DBManager manager;
       private Connection conn;       

       @Override //Create and instance of DBConnector for the deployment session

       public void init() {
           try {
               db = new DBConnector();
               conn = db.openConnection();
               manager = new DBManager(conn);
           } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }      
       }

      

       @Override //Add the DBConnector, DBManager, Connection instances to the session
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<OrderBean> queryresult = null;
           try {
               queryresult = manager.fetchOrderList();
           } catch (SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
        System.out.print(queryresult.toString() + "inside dogetmethod");
        RequestDispatcher rd = request.getRequestDispatcher("order.jsp"); 
        request.setAttribute("OrderInfo",  queryresult);
        rd.forward(request, response);  
    }   

        

       @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
        public void destroy() {
           try {
               db.closeConnection();
           } catch (SQLException ex) {
               Logger.getLogger(OrderViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }