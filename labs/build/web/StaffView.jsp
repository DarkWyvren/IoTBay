<%-- 
    Document   : ViewStaff
    Created on : Jun 6, 2020, 3:49:59 PM
    Author     : Danny16
--%>
<%@page import="java.util.ArrayList"%>
<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ArrayList staffList = new ArrayList();
        Object data = request.getAttribute("StaffInfo");
        if(data!=null)
        {
            staffList = (ArrayList)data;
        }
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>View Staff Information Page</title>
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
                </h1> View Staff info </h1>
                     <div class="jumbotron">
                     <table class = "table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Position</th>
                    <th>Status</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tbody>
                <%for(int i = 0; i < staffList.size(); i++){ %>
                <tr>
                    <% Staff st = (Staff)staffList.get(i); %>
                    <td><%= st.getFullName().toString()%></td>
                    <td><%= st.getEmail().toString()%></td>
                    <td><%= st.getPosition().toString()%></td>
                    <td><%= st.getStatus()%></td>
                    <td><%= st.getAddress()%></td>
                </tr>
                
                <% } %>
            </tbody>
        </table>
                     </div>
                </div>
            </div>
        </div>
    </body>
</html>
