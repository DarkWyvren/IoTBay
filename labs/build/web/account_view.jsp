<%-- 
    Document   : acccount_view
    Created on : 02/05/2020, 11:39:33 PM
    Author     : willi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Accounts</title>
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
                    <% String[] s = AccountTracker.getAllEmails();%>
                    <% for(String accem:s){ 
                        CustomerBean cb = AccountTracker.getCustomerByEmail(accem);
                    %>
                    <div class="row">
                        <div class="col-4 bg-primary"><%=cb.getEmail()%></div>
                        <div class="col bg-name"><%=cb.getName()%></div>
                        <div class="col bg-danger"><%=cb.getPassword()%></div>
                        <div class="col bg-info"><%=cb.getJoined().toString()%></div>
                    </div>
                    <%}%>
                </div>
                    
            </div>
        </div>
    </body>
</html>
