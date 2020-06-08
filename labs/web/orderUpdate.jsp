<%-- 
    Document   : Supplier
    Author     : mood35-Laptop
--%>

<%@ page import="uts.isd.model.*"%>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    Object orderTest = request.getAttribute("OrderInfo2");
    OrderBean current= null;
                        if(orderTest !=null){
                            current = (OrderBean)orderTest;
                        }else{
                              current = new OrderBean();
                          }
                        
                        
                        
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Updating Order Details</title>
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
                     <jsp:include page="orderNavBar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <h1>Updating Order Details</h1> 
                    <div class="jumbotron">
                        <form method="POST" action="OrderEdit">
                            <input type="hidden" id="OID" name="OrderID" value="<%= current.getOrderId()%>">
                        <table>
                            
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
                            <label for="inputProductQuantity">Product Quantity</label>
                            <select id="inputProductQuantity" class="form-control"  name="productQuantity">
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
                            <button type="reset" class="btn  btn-secondary btn-lg" href="">Go back</button>
                            <button type="submit" class="btn  btn-primary btn-lg" href>Update</button>
                </div>
            </div>  
        </div>
      </body>
</html>
