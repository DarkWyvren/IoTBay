<%-- 
    Document   : DeleteStaff
    Created on : Jun 6, 2020, 3:52:16 PM
    Author     : Danny16
--%>
<%@page import="java.util.ArrayList"%>
<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Edit Staff Information Page</title>
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
                </h1> Edit Staff info </h1>
                     <form method="post" action="">
                        <table class = "table"> 
                               
                       <tr><td> <input type="text" id="myInput" onkeyup="" placeholder="Search for names.." title="Type in a name">
                               . </td><td><input type="submit" name="submit" value="Search"> </td></tr>
                        <tr></tr>
                            <tr><td>Name: </td><td><input type="text" value= ""  name="FullName"></td></tr>
                            <tr><td>Address: </td><td><input type="text" value= ""  name="Address"></td></tr>
                            <tr><td>Position: </td><td><input type="text" value= ""  name="Pos"></td></tr>
                            <tr><td>Email: </td><td><input type="email" value= ""  name="Email"></td></tr>
                            
                            <tr><td></td><td><input type="submit" value="Delete"> <input class="btn  btn-primary btn-lg" type="submit" value="Update"> </td></tr>
                        </table>
                </div>
            </div>
        </div>
    </body>
</html>
