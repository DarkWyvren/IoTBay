<%-- 
    Document   : orderAdd
    Author     : Max
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
        <title>Ordering</title>
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
                     <jsp:include page="orderAddNavBar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4"> <!-- Register blocc -->
                    
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
                        Calendar thing = Calendar.getInstance();
                        
                        if(accountsesh!=null&&cust.getDOB()!=null){
                            System.out.println(cust.getDOB().toString()+ thing);
                            thing.setTime(cust.getDOB());
                        }
                        

                        ArrayList datalist = new ArrayList();
                        Object  data= request.getAttribute("data");
                        if(data!=null){
                            datalist = (ArrayList)data;
                        }
                        
                        boolean orderedUnAlready=false;
                        String errortext="";
                        boolean haserror=false;
                        Object init = request.getAttribute("response");
                        if(init!=null){
                            String postRes = (String)init;
                            if(postRes.equals(null)){
                                orderedUnAlready = true;
                            }else if(postRes.length()>0){
                                haserror = true;
                                errortext = postRes;
                            }
                        }
                    %>
                    <h1>Making an Order</h1>
                    <form action="AddNewOrder" method="POST">
                      <div class="row">
                        <div class="col">
                            <label for="inputProductName">Product Name</label>
                            <select id="inputProductName" class="form-control"  name="productName">
                                <option value="IoTBay Laptop">IoTBay Laptop</option>
                                <option value="IoTBay Controller">IoTBay Controller</option>
                                <option value="IoTBay SmartPhone">IoTBay SmartPhone</option>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputProductQuanity">Product Quanity</label>
                            <select id="inputProductQuanity" class="form-control"  name="productQuanity">
                                <% for (int i =1 ;i<=5;i++){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>    
                      </div>
                      <div class="row">
                            <div class="col">
                              <label for="inputAddress">Shipping Address</label>
                              <input type="text" class="form-control" id="inputAddress"  placeholder="18-20 Perti St Dankstown Hobart Australia" name="address">
                            </div>       
                            <div class="col">
                              <label for="inputPost">Postcode</label>
                              <input type="number" class="form-control" id="inputPost" placeholder="1234" name="postalcode">
                            </div>  
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
                                <% for (int i =6 ;i<=12;i++){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col">
                            <label for="inputYear">Year</label>
                            <select id="inputYear" class="form-control"  name="dateyear">
                                <% for (int i =2020 ;i<=2021;i++){%>
                                <option value="<%=i%>"><%=i%></option>
                                <%}%>
                            </select>
                        </div> 
                      </div>
                      <div class="row">
                            <div class="col">
                              <label for="inputCustomerId">Your Customer ID</label>
                              <input type="text" id="inputCustomerId" name="inputCustomerId" value="<%= (cust.getId())==0? "You are NOT Registered":String.valueOf(cust.getId()).toString()%>" readonly>
                            </div>       
                      </div>  
                     
                      <div class="form-group">
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="agreeCheck"  name="agreeCheck">
                          <label class="form-check-label" for="agreeCheck">I agree to <a href="#">terms and conditions</a></label>
                        </div>
                      </div>
                     
                        
                      <button type="submit" class="btn btn-primary" href>Add</button>

                </div>
            </div>
        </div>
        
    </body>
</html>
