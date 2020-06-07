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
    <%
            int count =0;
            if (request.getAttribute("StaffList")!=null)
            {
                Arraylist staffList = (ArrayList)request.getAttribute("StaffList");
            }
    %>
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
                               
                        <input type="text" id="myInput" onkeyup="myFunc()" placeholder="Search for names.." title="Type in a name">
                        <input type="text" placeholder="Search by Position">
                      
                        <tr></tr>
                            <tr><td>Name: </td><td><input type="text" value= "${Staff.FullName}" placeholder="Enter Company Name" name="SName"></td></tr>
                            <tr><td>Address: </td><td><input type="text" value= "${Staff.Address}" placeholder="Enter Address" name="SAddress"></td></tr>
                            <tr><td>Position: </td><td><input type="text" value= "${Staff.Position}" placeholder="Enter Company Type" name="SPosition"></td></tr>
                            <tr><td>Email: </td><td><input type="email" value= "${Staff.Email}" placeholder="Enter Email" name="SEmail"></td></tr>
                            
                            <tr><td></td><td><input type="reset"> <input class="btn  btn-primary btn-lg" type="submit" value="Update"> </td></tr>
                        </table>
                </div>
            </div>
        </div>
    </body>
</html>
