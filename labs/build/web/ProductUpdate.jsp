<%-- 
    Document   : ProductUpdate
    Created on : 08/06/2020, 7:26:44 PM
    Author     : antho
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    Object productTest = request.getAttribute("ProductInfo2");
    Product current= null;
                        if(productTest !=null){
                            current = (Product )productTest;
                        }else{
                              current = new Product();
                          }
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Product</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
         <script src="lib/jquery/jquery-3.5.0.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" />
             <div class="row" >
                 <div class="col-sm-12 col-md-3">
                     <jsp:include page="SupplierNavBar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <h1>Update</h1> 
                    <div class="jumbotron">
                        <form method="POST" action="ProductEdit">
                            <input type="hidden" id="PID" name="ProductID" value="<%= current.getID()%>">
                        <table>
                            
                           <%-- <tr><td>Product ID: </td><td><input type="text"  placeholder="Enter Product ID" name="PID"></td></tr>--%>
                            <tr><td>Product Name: </td><td><input type="text" value= "<%= current.getName().toString() %>" placeholder="Enter Product Name" name="PName"></td></tr>
                            <tr><td>Price:  </td><td><input type="double" value= "<%= current.getPrice() %>"placeholder="Enter Price per Unit" name="PPrice"></td></tr>
                            <tr><td>Category: </td><td><input type="text" value= "<%= current.getCategory().toString() %>" placeholder="Enter Product Category" name="PCategory"></td></tr>
                            <tr><td>Supplier: </td><td><input type="text" value= "<%= current.getSupplier()%>" placeholder="Enter Supplier" name="PSupplier"></td></tr>
                            <tr><td>Quantity: </td><td><input type="text" value= "<%= current.getQuantity()%>" placeholder="Enter Quantity" name="PQUANT"></td></tr>  
                        </table>
                               <input class="btn  btn-secondary btn-lg" type="reset" role="button" > <input class="btn  btn-primary btn-lg" type="submit" value="Update" role="button" > 
                            
                        </form>
                    </div>
                </div>
            </div>  
        </div>
      </body>
</html>
