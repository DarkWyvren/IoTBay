<%-- 
    Document   : Supplier
    Created on : 01/06/2020, 8:48:29 PM
    Author     : mood35-Laptop
--%>

<%@page import="java.util.ArrayList"%>
<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ArrayList SupplierList = new ArrayList();
                        Object  SupplierInfo= request.getAttribute("SupplierInfo");
                        if(SupplierInfo!=null){
                            SupplierList = (ArrayList)SupplierInfo;
                        }
        Object successfuladdmsg = request.getAttribute("addmsg");
        if(successfuladdmsg == null){
                            successfuladdmsg = "";
                        }
        
        Object successfuldeletemsg = request.getAttribute("DeleteMsg");
        if(successfuldeletemsg == null){
                            successfuldeletemsg = "";
                        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Supplier Information Page</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
         <script src="lib/jquery/jquery-3.5.0.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    </head>
    
        <div class="container">
            <jsp:include page="header.jsp" />
             <div class="row" >
                 <div class="col-sm-12 col-md-3">
                    <ul class="flex-sm-column nav  nav-pills p-3">
                        <li class="nav-item">
                            <a href="index.jsp"><div class="nav-link nav-fill navButt"><h3>Home</h3></div></a>
                        </li>
                        <li class="nav-item">
                            <a href="Management.jsp"><div class="nav-link nav-fill navButt"><h5>Go back to Management</h5></div></a>
                        </li>
                        <li class="nav-item">
                            <a href="SupplierAdd.jsp"><div class="nav-link nav-fill navButt"><h5>Add Supplier</h5></div></a>
                        </li>
                    </ul>
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron jumbotron-fluid h-5">
                        <form method="get" action="SearchSupplier">
                            <div class="container">
                                <div class="row pl-3">
                                    <h1>Supplier List</h1>
                                </div>
                                <div class="row pl-3">
                                    <p class ="text-success" type="hidden"><%=successfuladdmsg %></p>
                                    <p class ="text-danger" type="hidden"><%=successfuldeletemsg %></p>
                                </div>
                                <div class="row">
                                    <div class="col-sm">
                                       <select class="form-control" placeholder="" name="CompanyType">                                   
                                            <option value="">Select Type</option>
                                            <option value="Case">Case</option>
                                            <option value="CPU">CPU</option>
                                            <option value="Fan">Fan</option>
                                            <option value="MotherBoard">MotherBoard</option>
                                            <option value="Peripherals">Peripherals</option>
                                            <option value="Power Supply">Power Supply</option>
                                            <option value="RAM">RAM</option>
                                            <option value="Rasberry Pi">Rasberry Pi</option>
                                            <option value="SSD">SSD</option>
                                            <option value="System">System</option>                                    
                                        </select>
                                    </div>
                                    <div class="col-sm">
                                        <input class="form-control" type="text" id="myInput" onkeyup="myFunction()" placeholder="Type In Company Name" name="CompanyName">   
                                    </div>
                                    <div class="col-sm">
                                        <button class="btn btn-primary p-1" type="submit"><i class="fa fa-search"></i>Search</button>
                                    </div>
                                </div>
                            </div>
                        </form> 

                    </div>
                    <div class="jumbotron">                     
                        <div>                       
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Company Name</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Company Type</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                              <% for(int i = 0;i<SupplierList.size();i++){ %>
                                <tr>
                                  <%Supplier sb = (Supplier)SupplierList.get(i);%>
                                  <%int rowNum = i+1; %>
                                    <td> <%=rowNum%> </td>
                                    <td scope="row"><%= sb.getCompanyName().toString() %></td>
                                    <td><%= sb.getCompanyAddress().toString() %></td>
                                    <td><%= sb.getCompanyType().toString() %></td>
                                    <td><%= sb.getCompanyEmail().toString() %></td>                        
                                    <td style="color: <%= sb.getCompanyStatus() == 0 ? "red":"green" %>" >
                                        <%=sb.getCompanyStatus() == 0 ? "Inactive":"Active"  %>
                                    </td> <%--Make 1 and 0 into active or inactive --%>
                                    <td style="height: 100px;">
                                        <a role="button" href="${pageContext.request.contextPath}/SupplierEdit?SID=<%= sb.getSupplierID()%>">Update</a>
                                        <a role="button" href="${pageContext.request.contextPath}/deleteSupplier?SID=<%= sb.getSupplierID()%>">Delete</a>
                                    </td>
                                </tr>  
                              <%}%>
                            </tbody>
                        </table>
                        <a href="ShowSupplier"><div class="nav-link nav-fill navButt float-right"><h5>Show All Suppliers</h5></div></a>
                    </div>                    
                </div>
            </div>
        </div>
      
</html>
