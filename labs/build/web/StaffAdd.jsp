<%-- 
    Document   : AddStaff
    Created on : Jun 6, 2020, 3:50:33 PM
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
        <title>Add Staff Information Page</title>
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
                </h1> Add Staff info </h1>
                     <table>
                         <tr><td>Name:</td><td><input type="text" placeholder="Enter Name" name="name"></td></tr>
                         <tr><td>Email:</td><td><input type="email" placeholder="Enter Name" name="name"></td></tr>
                         <tr><td>Password:</td><td><input type="password" placeholder="Enter Name" name="name"></td></tr>
                         <tr><td>Address:</td><td><input type="text" placeholder="Enter Name" name="name"></td></tr>
                         <tr><td>Phone:</td><td><input type="int" placeholder="Enter Name" name="name"></td></tr>
                         <tr><td>Dob:</td><td><input type="date" placeholder="Enter Name" name="name"></td></tr>
                     </table>
                </div>
            </div>
        </div>
    </body>
</html>
