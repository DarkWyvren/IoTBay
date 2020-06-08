/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.SQLException;
 import java.util.ArrayList;
import java.util.Enumeration;
 import java.util.Scanner;
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
import uts.isd.model.ProductBean;

/**
 *
 * @author antho
 */
@WebServlet(
  name = "getStore", 
  urlPatterns = "/store")

   public class StoreController extends HttpServlet {
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
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ProductBean> queryresult = null;
           try {
              queryresult = manager.fetchProductList();
           } catch (SQLException ex) {
               Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
           }
        ArrayList<String> allowedCatagories = new ArrayList();
        
        Enumeration<String> paramNames = request.getParameterNames();
        String namesearch="";
        while(paramNames.hasMoreElements()){
            String paraNames = paramNames.nextElement();
           switch(paraNames){
                case "search_product":
                   namesearch = request.getParameter(paraNames);
                   break;
                default:
                    allowedCatagories.add(paraNames.toLowerCase());
           }
        }
        System.out.println(""+allowedCatagories.toString());
        
        RequestDispatcher rd = request.getRequestDispatcher("store.jsp"); 
        if(queryresult!=null){
            for(int i = 0;i<queryresult.size();i++){
                boolean shoulddel = false;
                if(namesearch.length()!=0 && !queryresult.get(i).getName().toLowerCase().contains(namesearch.toLowerCase().trim())){
                    shoulddel=true;
                }
                if(!allowedCatagories.isEmpty()&&!allowedCatagories.contains(queryresult.get(i).getCategory().toLowerCase())){
                    shoulddel=true;
                }

                if(shoulddel){
                    queryresult.remove(i);
                    i--;
                }
            }
            request.setAttribute("products",  queryresult);
        }
      //   System.out.print(queryresult.toString() + "inside dogetmethod");
        
        
        String search = (namesearch.length()!=0?"'"+namesearch+"'":"") + (allowedCatagories.isEmpty()?"":(namesearch.length()!=0?" with ":"")+"Catagories:"+allowedCatagories.toString());
        if(queryresult==null){
            search="nothing, database is busy, try again later";
        }
        
        request.setAttribute("search",  search.length()>0?search:null);
        rd.forward(request, response);
       
    }

   }