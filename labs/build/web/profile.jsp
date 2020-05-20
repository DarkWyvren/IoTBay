<%-- 
    Document   : profile
    Created on : 02/05/2020, 8:50:40 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
                        CustomerBean cust = new CustomerBean();
                        cust.setName("Guest");
                        cust.setPassword("");

                        Object accountsesh = session.getAttribute("login");
                        if(accountsesh==null){
                            session.setAttribute("login", cust);
                        }else{
                            cust = (CustomerBean)accountsesh;
                        }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=cust.getName()%>'s Profile</title>
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
                    <h1><%=cust.getName()%>'s Profile</h1>
                    <p>Account created on <%=cust.getJoined().toString()%></p>
                </div>
                    
            </div>
        </div>
    </body>
</html>
