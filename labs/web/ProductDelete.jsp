<%-- 
    Document   : ProductDelete
    Created on : 07/06/2020, 10:55:20 AM
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
        <title>Remove a Product</title>
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
                        <h1>Remove a Product from the Store</h1> 
                        <table> 
                            <tr><td>Product ID: </td><td><input type="text" placeholder="Enter Product ID" name="PID"></td></tr>
                            <tr><td></td><td><input type="reset"> <input type="submit" value="Delete"> </td></tr>
                            <tr><td><input type="checkbox" name="TermsCondition" > Are you sure? </td><td></td></tr>
                            <tr><td></td><td></td></tr>
                        </table>
                        <hidden value="SAdd"></hidden> 
                    </div>
                  
                </div>
            </div>
        </div>
      </body>
</html>
