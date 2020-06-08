<%-- 
    Document   : orderHistoryNavBar
    Author     : Max
--%>
<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="flex-sm-column nav  nav-pills p-3">
    <li class="nav-item">
        <a href="index.jsp"><div class="nav-link nav-fill navButt"><h3>Home</h3></div></a>
    </li>
    <li class="nav-item">
        <a href="Management.jsp"><div class="nav-link nav-fill navButt"><h5>Go back to Main Management</h5></div></a>
    </li>
    <li class="nav-item">
        <a href="orderManagement.jsp"><div class="nav-link nav-fill navButt"><h5>Go back to Order Management</h5></div></a>
    </li>
    <li class="nav-item">
        <a href="Product.jsp"><div class="nav-link nav-fill navButt"><h5>Check our Products</h5></div></a>
    </li>
    <li class="nav-item">
        <a href="orderAdd.jsp"><div class="nav-link nav-fill navButt"><h5>Add a Order</h5></div></a>
    </li>
    <li class="nav-item">
        <a href="${pageContext.request.contextPath}/OrderView" ><div class="nav-link nav-fill navButt"><h5>Manage Orders</h5></div></a>
    </li>
</ul>
