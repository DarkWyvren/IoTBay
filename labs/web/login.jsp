<%-- 
    Document   : login
    Created on : 01/05/2020, 5:52:46 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login</title>
        
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
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
                        

                        Object accountsesh = session.getAttribute("login");
                        
                        boolean loggedin=accountsesh!=null;
                        String errortext="";
                        boolean haserror=false;
                        Object init = request.getAttribute("response");
                        if(init!=null){
                            String postRes = (String)init;
                            if(postRes.equals("OK")){
                                loggedin = true;
                            }else if(postRes.length()>0){
                                haserror = true;
                                errortext = postRes;
                            }
                        }
                    %>
                    <% if (!loggedin) { %>
                    <h1>Login</h1>
                    <form action="loginauth" method="POST">
                      <div class="form-group">
                        <label for="inputEmail">Email address</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
                      </div>  
                      <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword"  aria-describedby="passHelp" placeholder="Password" name="password">
                        <small id="passHelp" class="form-text text-muted">Forgot your password? too bad!</small>
                      </div>
                        
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <%if(haserror){%>
                        <small id="error_response" class="form-text text-danger"><%=errortext%></small>
                      <%}%>
                    </form>
                    <% }else{ %>
                    <h4> Logged in! Return to <a href="index.jsp" > Home </a>  or <a href="loginauth"> Logout</a>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
