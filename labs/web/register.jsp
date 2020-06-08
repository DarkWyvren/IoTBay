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
                      <div class="row">
                        <div class="col">
                            <label for="inputDay">Day</label>
                            <select id="inputDay" class="form-control"  name="dateday">
                                <% for (int i =1 ;i<=31;i++){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputMonth">Month</label>
                            <select id="inputMonth" class="form-control"  name="datemonth">
                                <% for (int i =1 ;i<=12;i++){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputYear">Year</label>
                            <select id="inputYear" class="form-control"  name="dateyear">
                                <% for (int i =2020 ;i>1900;i--){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>    
                      </div>
                      <div class="row">
                            <div class="col">
                              <label for="inputAddress">Address</label>
                              <input type="text" class="form-control" id="inputAddress"  placeholder="18-20 Perti St Dankstown Hobart Australia" name="address">
                            </div>       
                            <div class="col">
                              <label for="inputPost">Postcode</label>
                              <input type="number" class="form-control" id="inputPost" placeholder="1234" name="postalcode">
                            </div>  
                      </div>        
                      <div class="row">
                            <div class="col">
                              <label for="inputTitle">Title</label>
                              <input type="text" class="form-control" id="inputTitle"  placeholder="Mr, Mrs, etc" name="title">
                            </div>       
                            <div class="col">
                              <label for="inputPhone">Phone</label>
                              <input type="tel" class="form-control" id="inputPhone"  pattern="+[0-9]{2}-[0-9]{10}" placeholder="+12-3456789012" name="phone">
                            </div>  
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
