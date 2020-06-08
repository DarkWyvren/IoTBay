<%-- 
    Document   : profile
    Created on : 02/05/2020, 8:50:40 PM
    Author     : willi
--%>

<%@page import="java.util.Calendar"%>
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
                        
                        String errortext="";
                        boolean haserror=false;
                        Object init = request.getAttribute("response");
                        if(init!=null){
                            String postRes = (String)init;
                            if(postRes.equals("OK")){
                            }else if(postRes.length()>0){
                                haserror = true;
                                errortext = postRes;
                            }
                        }
                        Calendar thing = Calendar.getInstance();
                        
                        if(accountsesh!=null&&cust.getDOB()!=null){
                            System.out.println(cust.getDOB().toString()+ thing);
                            thing.setTime(cust.getDOB());
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
                    <form action="updateProf" method="POST">
                      <div class="form-group">
                        <label for="inputName">Full Name</label>
                        <input type="text" value="<%=cust.getName()%>" class="form-control" id="inputName" placeholder="Enter name" name="full_name">
                      </div>
                      <div class="form-group">
                        <label for="inputEmail">Email address</label>
                        <input type="email" value="<%=cust.getEmail()%>" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
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
                                <option value="<%=i%>" <%= thing.get(Calendar.DAY_OF_MONTH)==i?"selected":"" %>   ><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputMonth">Month</label>
                            <select id="inputMonth" class="form-control"  name="datemonth">
                                <% for (int i =1 ;i<=12;i++){%>
                                <option value="<%=i%>"  <%= thing.get(Calendar.MONTH)==i?"selected":"" %>   ><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputYear">Year</label>
                            <select id="inputYear" class="form-control"  name="dateyear">
                                <% for (int i =2020 ;i>1900;i--){%>
                                <option value="<%=i%>"   <%= thing.get(Calendar.YEAR)==i?"selected":"" %>  ><%=i%></option>
                                <%}%>
                            </select>
                        </div>    
                      </div>
                      <div class="row">
                            <div class="col">
                              <label for="inputAddress">Address</label>
                              <input type="text" class="form-control" id="inputAddress"  value="<%=cust.getAddress().split("\\|")[0]%>" placeholder="18-20 Perti St Dankstown Hobart Australia" name="address">
                            </div>       
                            <div class="col">
                              <label for="inputPost">Postcode</label>
                              <input type="number" class="form-control" id="inputPost"  value="<%=cust.getAddress().split("\\|").length>1?cust.getAddress().split("\\|")[1]:0%>" placeholder="1234" name="postalcode">
                            </div>  
                      </div>        
                      <div class="row">
                            <div class="col">
                              <label for="inputTitle">Title</label>
                              <input type="text" class="form-control" id="inputTitle" value="<%=cust.getTitle()%>"  placeholder="Mr, Mrs, etc" name="title">
                            </div>       
                            <div class="col">
                              <label for="inputPhone">Phone</label>
                              <input type="tel" class="form-control" id="inputPhone" value="<%=cust.getPhone()%>"pattern="+[0-9]{2}-[0-9]{10}" placeholder="+12-3456789012" name="phone">
                            </div>  
                      </div>  
                            
                      <div class="row" style = "height:20px;"></div>
                        
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <%if(haserror){%>
                        <small id="error_response" class="form-text text-danger"><%=errortext%></small>
                      <%}%>
                      
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </form> 
                </div>
                   
            </div>
        </div>
    </body>
</html>
