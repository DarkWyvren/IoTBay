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
                        CustomerBean cust = null;
                        Staff stuff= new Staff();
                        Object accountsesh = session.getAttribute("login");
                        String profilelink= "#";
                        String name="Guest";
                        if(accountsesh!=null){

                            if(accountsesh instanceof CustomerBean){
                                cust = (CustomerBean)accountsesh;
                                name= cust.getName();
                            }else{
                                stuff = (Staff)accountsesh;
                                name=stuff.getFullName();
                            }
                            profilelink="profile.jsp";

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
                        
                        ProductBean pt= (ProductBean)request.getAttribute("product");
                        
                        
                    %>
                    <h1>Making an Order</h1>
                    <form action="AddNewOrder" method="POST">
                      <div class="row">
                        <div class="col">
                            <% if(pt!=null){%>
                            <label for="inputproductName">Product Name</label>
                            <div class="w-100 h-100">
                            <jsp:include page="ProductDiv.jsp" flush="true">
                                <jsp:param name="product" value="<%=pt.getName() %>"/>
                                <jsp:param name="productcat" value="<%=pt.getCategory() %>"/>
                                <jsp:param name="productprice" value="<%=pt.getPrice() %>"/>
                                <jsp:param name="productid" value="<%=pt.getID() %>"/>
                                <jsp:param name="canEdit" value="<%=false%>"/>
                                <jsp:param name="justView" value="<%=true%>"/>
                            </jsp:include>
                            </div>
                            <input type="hidden" name="productid" value="<%=pt.getID() %>">
                            <%}else{%>
                            <a class="w-100 h-100 btn btn-light" href="store">
                                No product selected
                            </a>
                            <%}%>
                        </div>
                        <div class="col">
                            <label for="inputproductQuantity">Product Quantity</label>
                            <input type="number" class="form-control" id="inputproductQuantity" class="form-control"  placeholder="1" name="productQuantity">
                                
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
                              <input type="hidden" id="inputCustomerId" name="inputCustomerId" value="<%= cust==null? "You are NOT Registered":String.valueOf(cust.getId()).toString()%>" readonly>
                            </div>       
                      </div>  
                     
                      <div class="form-group">
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="agreeCheck"  name="agreeCheck">
                          <label class="form-check-label" for="agreeCheck">I agree to <a href="#">terms and conditions</a></label>
                        </div>
                      </div>
                      <% if(pt!=null){%>
                      <div class="form-group">
                        <label class="text-danger"><%=errortext%></label>
                        </div>
                        <button type="submit" class="btn btn-primary" href>Add</button>
                      <%}%>
                </div>
            </div>
        </div>
        
    </body>
</html>
