<%-- 
    Document   : Product
    Created on : 07/06/2020, 6:44:28 PM
    Author     : antho
--%>

<%@page import="java.util.ArrayList"%>
<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ArrayList ProductList = new ArrayList();
                        Object  ProductInfo= request.getAttribute("ProductInfo");
                        if(ProductInfo!=null){
                            ProductList = (ArrayList)ProductInfo;
                        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Product Information Page</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
         <script src="lib/jquery/jquery-3.5.0.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    </head>
    
        <div class="container">
            <jsp:include page="header.jsp" />
             <div class="row" >
                 <div class="col-sm-12 col-md-3">
                     <jsp:include page="SupplierNavBar.jsp" /> -needs to be looked at (potentially build new navbar ?)
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron">
                       <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">
                        <input type="text" placeholder="Search by Type">
                        <div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product ID</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Supplier Name</th>
                                    <th scope="col"># In Stock</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                              <% for(int i = 0;i<ProductList.size();i++){ %>
                                <tr>
                                  <%ProductBean pb = (ProductBean)ProductList.get(i);%>
                                  <%int rowNum = i+1; %>
                                    <td> <%=rowNum%> </td>
                                    <td scope="row"><%= pb.getID().toString() %></td>
                                    <td><%= pb.getName().toString() %></td>
                                    <td><%= pb.getPrice().toString() %></td>
                                    <td><%= pb.getCategory().toString() %></td>  
                                    <td><%= pb.getStockN().toString() %></td>                      
                                    
                                    <td>
                                        <a role="button" href="${pageContext.request.contextPath}/SupplierEdit?SID=<%= pb.getID()%>" >Edit</a>
                                        <a role="button" href="ProductUpdate">Delete</a>
                                    </td>
                                    

                                </tr>
                                
                                
                              <%}%>
                            </tbody>
                        </table>
                                       
                            
                        </div>
                    </div>                    
                </div>
            </div>
        </div>
      
</html>
