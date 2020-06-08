<%-- 
    Document   : Staff
    Created on : Jun 5, 2020, 7:39:33 PM
    Author     : Danny16
--%>
<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Staff Information Page</title>
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
                     <jsp:include page="StaffNavbar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron" href="."><a href="StaffInfo">View Staff List</div>
                         <div class="jumbotron"><a href="StaffAdd.jsp">Add to Staff List</div>
                </div>
            </div>
        </div>
    </body>
</html>
