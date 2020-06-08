<%-- 
    Document   : orderManagement
    Author     : Forever
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Order Management</title>
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
                     <jsp:include page="navbar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron"><a href="product.jsp">&gt;Start ordering&lt;</div>
                    <div class="jumbotron"><a href="orderHistory.jsp">View my orders</div>
                    <div class="jumbotron"><a href="orderTrack.jsp">Track my orders</div>
                    <div class="jumbotron"><a href="orderUpdate.jsp">Update a onhold order</div>
                    <div class="jumbotron"><a href="orderRemove.jsp">Remove a onhold order</div>
                </div>
            </div>
        </div>
       
      </body>
</html>


