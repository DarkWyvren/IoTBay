<%-- 
    Document   : register
    Created on : 08/04/2020, 5:47:01 PM
    Author     : willi
--%>
<%@ page import="uts.isd.model.*"%>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Register</title>
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
                <div class="col-sm-12 col-md-9 p-4"> <!-- Register blocc -->
                    
                    <%
                        boolean registeredAlready=false;
                        String errortext="";
                        boolean haserror=false;
                        Object init = request.getAttribute("response");
                        if(init!=null){
                            String postRes = (String)init;
                            if(postRes.equals("OK")){
                                registeredAlready = true;
                            }else if(postRes.length()>0){
                                haserror = true;
                                errortext = postRes;
                            }
                        }
                        //cust.setName(request.getParameter("full_name"));
                        //request.getParameter("")
                        //cust.setName(request.getParameter("full_name"));
                    %>
                    <% if (!registeredAlready) { %>
                    <h1>Register!</h1>
                    <form action="registerauth" method="POST">
                      <div class="form-group">
                        <label for="inputName">Full Name</label>
                        <input type="text" class="form-control" id="inputName" placeholder="Enter name" name="full_name">
                      </div>
                      <div class="form-group">
                        <label for="inputEmail">Email address</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
                      </div>  
                      <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword"  aria-describedby="passHelp" placeholder="Password" name="password">
                        <small id="passHelp" class="form-text text-muted">Use a strong password that's not shared by any other site</small>
                      </div>
                      <div class="form-group">
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="agreeCheck"  name="agreeCheck">
                          <label class="form-check-label" for="agreeCheck">I agree to <a href="#">terms and conditions</a></label>
                        </div>
                      </div>
                        
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <%if(haserror){%>
                        <small id="error_response" class="form-text text-danger"><%=errortext%></small>
                      <%}%>
                    </form>
                    <% }else{ %>
                    <h4> Registered! <a href="login.jsp" > Login </a> or return to <a href="index.jsp" > Home </a>
                    <% }%>
                </div>
            </div>
        </div>
        
    </body>
</html>
