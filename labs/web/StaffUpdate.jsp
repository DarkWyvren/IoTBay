<%-- 
    Document   : StaffUpdate
    Created on : Jun 8, 2020, 2:25:35 PM
    Author     : Danny16
--%>

<%@page import="uts.isd.model.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    Object staffTest = request.getAttribute("StaffInfo");
    Staff current= null;
                        if(staffTest !=null){
                            current = (Staff)staffTest;
                        }else{
                              current = new Staff();
                          }
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Supplier Page</title>
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
                    <h1>Update</h1> 
                    <div class="jumbotron">
                        <form method="POST" action="StaffEdit">
                            <input type="hidden" id="SID" name="Id" value="<%= current.getId()%>">
                        <table>
                            
                            <tr><td>Company Name: </td><td><input type="text" value= "<%= current.getFullName().toString() %>" placeholder="Enter Company Name" name="Name"></td></tr>
                            <tr><td>Address: </td><td><input type="text" value= "<%= current.getAddress().toString() %>" placeholder="Enter Address" name="Address"></td></tr>
                            <tr><td>Company Type: </td><td><input type="text" value= "<%=current.getPosition().toString()%>" placeholder="Enter Company Type" name="Pos"></td></tr>
                            <tr><td>Email: </td><td><input type="email" value= "<%=current.getEmail().toString()%>" placeholder="Enter Email" name="Email"></td></tr>
                            <tr><td>Status: </td><td><input type="checkbox" value= "<%=current.getStatus() == 0 ? "Activate":"Deactivate"  %>" placeholder="" name="Status"></td></tr>   
                        </table>
                               <input class="button" type="submit" value="Update" role="button" > 
                        </form>
                    </div>
                </div>
            </div>  
        </div>
    </body>
</html>
