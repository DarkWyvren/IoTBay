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
import uts.isd.model.ValidProduct;
import uts.isd.model.Product;


/**
 *
 * @author antho
 */

@WebServlet(
  name = "ProductServlet", 
  urlPatterns = "/productauth")

public class AddProductController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        //Enumeration<Double> paramPrice = req.getParameterPrice();
        Product pb = new Product();
        boolean hastoc=false;
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            //String paramPrice = paramPrice.nextDouble();
            switch(paramName){
                case "ID":
                    pb.setID(req.getParameter(paramName));
                    break;
                case "name":
                    pb.setName(req.getParameter(paramName));
                    break;
    //            case "price":
    //                pb.setPrice(req.getDouble(paramPrice));
    //                break;
                case "category":
                    pb.setCategory(req.getParameter(paramName));
                    break;
                case "supplier":
                    pb.setCategory(req.getParameter(paramName));
                    break;
                case "agreeCheck":
                    hastoc=true;
                    break;
            }
        }
           System.out.println("YES");
           RequestDispatcher dispatch = req.getRequestDispatcher("");
           req.setAttribute("", ValidProduct.createProduct(pb));
           dispatch.forward(req, resp);
    }
    
    
    
}
