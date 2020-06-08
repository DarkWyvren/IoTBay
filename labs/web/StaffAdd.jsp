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
                     <jsp:include page="StaffNavbar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                </h1> Add Staff info </h1>
                     <div class="jumbotron">
                         <form method="POST" action="AddNewStaff">
                             <input type="hidden" id="stats" name="Status" value="1">
                     <table class = "table">
                         <tr><td>Name:</td><td><input type="text" placeholder="Enter Name" name="FullName"></td></tr>
                         <tr><td>Email:</td><td><input type="email" placeholder="Enter Name" name="Email"></td></tr>
                         <tr><td>Position:</td><td><input type="text" placeholder="Enter Name" name="Pos"></td></tr>
                         <tr><td>Address:</td><td><input type="text" placeholder="Enter Name" name="Address"></td></tr>
                         <tr><td></td><td><input type="submit" value="Register"> </td></tr>
                         
                          <tr><td><input type="checkbox" name="TermsCondition" > I have Read the Terms and Conditions</td><td></td></tr>
                     </table>
                     </div>
                </div>
            </div>
        </div>
    </body>
</html>
