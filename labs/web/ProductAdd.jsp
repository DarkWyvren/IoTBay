<%-- 
    Document   : ProductAdd
    Created on : 07/06/2020, 10:06:14 AM
    Author     : antho
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Add a Product</title>
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
                     <jsp:include page="ProductNavBar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    
                    <div class="jumbotron">
                        <form  method="POST" Action="AddNewProduct">
                        <h1>Add a Product to the Store</h1> 
                        <table> 
                            <tr><td>Product ID: </td><td><input type="text" placeholder="Enter Product ID" name="PID"></td></tr>
                            <tr><td>Product Number: </td><td><input type="text" placeholder="Enter Product Name" name="PName"></td></tr>
                            <tr><td>Price:  </td><td><input type="double" placeholder="Enter Price per Unit" name="PPrice"></td></tr>
                            <tr><td>Category: </td><td><input type="text" placeholder="Enter Product Category" name="PCategory"></td></tr>
                            <tr><td>Supplier: </td><td><input type="text" placeholder="Enter Supplier" name="PSupplier"></td></tr>
                            <tr><td>Quantity: </td><td><input type="text" placeholder="Enter Quantity" name="PQUANT"></td></tr>
                            <tr><td>  
                            </td><td> 
                            </td></tr>
                            <tr><td>
                                    
                            <tr><td></td><td></td></tr>
                          </table>
                        <input type="reset"> 
                        <input type="submit" value="Add">
                        </form>
                    </div>
                </div>
            </div>
        </div>
      </body>
</html>