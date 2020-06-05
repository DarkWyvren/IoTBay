<%-- 
    Document   : store
    Created on : 04/06/2020, 6:13:42 PM
    Author     : antho
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Products</title>
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
                    </div>
                </div>
                         
            <div id="myBtnContainer">
                 <button class="btn active" onclick="filterSelection('all')"> Show all</button>
                 <button class="btn" onclick="filterSelection('computers')"> Computers</button>
                 <button class="btn" onclick="filterSelection('accessories')"> Accessories</button>
                 <button class="btn" onclick="filterSelection('cables')"> Cables</button>
                 <button class="btn" onclick="filterSelection('cases')"> Cases</button>
                 <button class="btn" onclick="filterSelection('books')"> Books</button>
            </div>
      </body>
</html>
