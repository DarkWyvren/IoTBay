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
                     <jsp:include page="SupplierNavBar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron">
                       <div class="container">
                        <div class="row">
                          <div class="col-sm">
                            <a href="SupplierAdd.jsp" class="btn btn-primary h-75 mt-0"  role="button" ><h5>Add New Supplier</h5></a>
                          </div>
                          <div class="col-sm">
                            <input class="form-control mr-sm-2 h-75" type="text" placeholder="Search by Type">
                          </div>
                          <div class="col-sm">
                            <input class="form-control mr-sm-2 h-75" type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">
                          </div>
                          
                        </div>
                     
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
                                    <th></th>
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
                                        <%=sb.getCompanyStatus() == 0 ? "Deactive":"Active"  %>
                                    </td> <%--Make 1 and 0 into active or inactive --%>
                                    <td style="height: 100px;">
                                        <a role="button" href="${pageContext.request.contextPath}/SupplierEdit?SID=<%= sb.getSupplierID()%>" >Edit</a>
                                        <a role="button" href="${pageContext.request.contextPath}/deleteSupplier?SID=<%= sb.getSupplierID()%>">Delete</a>
                                    </td>
                                    

                                </tr>
                                
                                
                              <%}%>
                            </tbody>
                        </table>
                            
                    </div>                    
                </div>
            </div>
        </div>
      
</html>
