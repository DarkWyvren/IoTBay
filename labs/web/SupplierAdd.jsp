<%-- 
    Document   : Supplier
    Author     : mood35-Laptop
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Add to Supplier Page</title>
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
                     <ul class="flex-sm-column nav  nav-pills p-3">
                        <li class="nav-item">
                            <a href="index.jsp"><div class="nav-link nav-fill navButt"><h3>Home</h3></div></a>
                        </li>
                        <li class="nav-item">
                            <a href="ShowSupplier"><div class="nav-link nav-fill navButt"><h5>Go back to Supplier List</h5></div></a>
                        </li>
                    </ul>
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
                    <div class="jumbotron">
                        <h2 class="float-right pb-8">Add new Supplier</h2>    
                    </div>
                    <div class="jumbotron">
                        <form  method="POST" Action="AddNewSupplier">
                            <input type="hidden" id="stats" name="CStatus" value=1>
                            <div class="form-group">
                                <label for="inputName">Company Name:</label>
                                <input type="text" class="form-control" placeholder="Enter Company Name" name="CName">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Address: </label>
                                <input type="text" class="form-control" placeholder="Enter Address" name="CAddress">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Company Type: </label>
                                <select class="form-control" placeholder="" name="CType">                                   
                                    <option>Case</option>
                                    <option>CPU</option>
                                    <option>Fan</option>
                                    <option>MotherBoard</option>
                                    <option>Peripherals</option>
                                    <option>Power Supply</option>
                                    <option>RAM</option>
                                    <option>Rasberry Pi</option>
                                    <option>SSD</option>
                                    <option>System</option>
                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputName">Email:</label>
                                <input type="email" class="form-control" placeholder="Enter Email" name="CEmail">
                            </div>
                            <div class="mx-auto align-middle">
                                <input class="btn btn-secondary p-4 "  role="button" type="reset" >  
                                <input  class="btn btn-primary p-4 "  role="button" type="submit" value="Register">
                            </div> 
                        </form>
                    </div>
                  
                </div>
            </div>
        </div>
      </body>
</html>
