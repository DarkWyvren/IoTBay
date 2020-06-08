<%-- 
    Document   : profile
    Created on : 02/05/2020, 8:50:40 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.Staff"%>
<%@page import="java.util.Calendar"%>
<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
                        CustomerBean cust = null;
                        Staff stuff = null;

                        Object accountsesh = session.getAttribute("login");
                        
                        String name="";
                        String email="";
                        String address="";
                        
                        
                        if(accountsesh!=null){
                            if(accountsesh instanceof CustomerBean){
                                cust = (CustomerBean)accountsesh;
                                name = cust.getName();
                                email = cust.getEmail();
                                address = cust.getAddress();
                            }else{
                                stuff = (Staff)accountsesh;
                                name = stuff.getFullName();
                                email = stuff.getEmail();
                                address = stuff.getAddress();
                            }
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
                        
                        if(cust!=null&&accountsesh!=null&&cust.getDOB()!=null){
                            System.out.println(cust.getDOB().toString()+ thing);
                            thing.setTime(cust.getDOB());
                        }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=name%>'s Profile</title>
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
                     <%if(cust!=null){%>
                        <ul class="flex-sm-column nav  nav-pills ">
                            <li class="nav-item">
                                <a href="paymentPref?CID=<%=cust.getId() %>">
                                    <div class="nav-fill navButt bg-secondary text-white">
                                        <div style="margin: 25px; width:100%;">
                                            <svg class="bi bi-credit-card" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M14 3H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1zM2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2z"/>
                                                <rect width="3" height="3" x="2" y="9" rx="1"/>
                                                <path d="M1 5h14v2H1z"/>
                                              </svg>
                                            <b>Change payment details</b>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    <%}%>
                </div>
                
                <div class="col-sm-12 col-md-9 p-4">
                    <h1><%=name%>'s Profile</h1>
                    <form action="updateProf" method="POST">
                      <div class="form-group">
                        <label for="inputName">Full Name</label>
                        <input type="text" value="<%=name%>" class="form-control" id="inputName" placeholder="Enter name" name="full_name">
                      </div>
                      <div class="form-group">
                        <label for="inputEmail">Email address</label>
                        <input type="email" value="<%=email%>" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
                      </div>  
                      <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword"  aria-describedby="passHelp" placeholder="Password" name="password">
                        <small id="passHelp" class="form-text text-muted">Use a strong password that's not shared by any other site</small>
                      </div>
                        <%if(cust!=null){%>
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
                      <%}%>
                      <div class="row">
                            <div class="col">
                              <label for="inputAddress">Address</label>
                              <input type="text" class="form-control" id="inputAddress"  value="<%=address.split("\\|")[0]%>" placeholder="18-20 Perti St Dankstown Hobart Australia" name="address">
                            </div>       
                            <div class="col">
                              <label for="inputPost">Postcode</label>
                              <input type="number" class="form-control" id="inputPost"  value="<%=address.split("\\|").length>1?address.split("\\|")[1]:0%>" placeholder="1234" name="postalcode">
                            </div>  
                      </div>        
                       <%if(cust!=null){%>
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
                       <%}%> 
                      <div class="row" style = "height:20px;"></div>
                        
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <%if(haserror){%>
                        <small id="error_response" class="form-text text-danger"><%=errortext%></small>
                      <%}%>
                      
                      <%if(cust!=null){%>
                      <a class="btn btn-primary">Delete Account</a>
                      <%}%> 
                      
                      
                    </form> 
                </div>
                   
            </div>
        </div>
    </body>
</html>
