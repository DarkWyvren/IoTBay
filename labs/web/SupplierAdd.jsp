<%-- 
    Document   : Supplier
    Author     : mood35-Laptop
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%  
                        Object ErrorMsg = request.getAttribute("EmailExistErr");
                        if(ErrorMsg == null){
                            ErrorMsg = "";
                        }
                        
                        Object InfoErr =request.getAttribute("InfoMissinErr");
                        if(InfoErr == null){
                            InfoErr = "";
                        }
                        
                        Supplier sbia = (Supplier)request.getAttribute("incorrectdeets");
                        if(sbia == null){
                            sbia = new Supplier();
                        }
                        
                    %>
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
                        <div class="row h2 text-right">
                           <h2 class="float-right pb-8">Add New Supplier</h2> 
                        </div>
                        <div class="row h2 text-right">
                          <p class="float-right pb-8 text-danger"> <%=InfoErr%></p>  
                        </div>
                        
                    </div>
                    <div class="jumbotron">
                        <form  method="POST" Action="AddNewSupplier">
                            <input type="hidden" id="stats" name="CStatus" value=1>
                            <div class="form-group">
                                <label for="inputName">Company Name:</label>
                                <input type="text" value="<%= sbia.getCompanyName()%>" class="form-control" placeholder="Enter Company Name" name="CName">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Address: </label>
                                <input type="text" value="<%= sbia.getCompanyAddress()%>" class="form-control" placeholder="Enter Address" name="CAddress">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Company Type: </label>
                                <select class="form-control" placeholder="" name="CType">                                   
                                    <option value ="Case" <%= sbia.getCompanyType().equals("Case") ? "selected":"" %> >Case</option>
                                    <option value ="CPU" <%= sbia.getCompanyType().equals("CPU") ? "selected":"" %>>CPU</option>
                                    <option value ="Fan" <%= sbia.getCompanyType().equals("Fan") ? "selected":"" %>>Fan</option>
                                    <option value ="MotherBoard" <%= sbia.getCompanyType().equals("MotherBoard") ? "selected":"" %>>MotherBoard</option>
                                    <option value ="Peripherals" <%= sbia.getCompanyType().equals("Peripherals") ? "selected":"" %>>Peripherals</option>
                                    <option value ="Power Supply" <%= sbia.getCompanyType().equals("Power Supply") ? "selected":"" %>>Power Supply</option>
                                    <option value ="RAM" <%= sbia.getCompanyType().equals("RAM") ? "selected":"" %>>RAM</option>
                                    <option value ="Rasberry PI" <%= sbia.getCompanyType().equals("Rasberry PI") ? "selected":"" %>>Rasberry Pi</option>
                                    <option value ="SSD" <%= sbia.getCompanyType().equals("SSD") ? "selected":"" %>>SSD</option>
                                    <option value ="System" <%= sbia.getCompanyType().equals("System") ? "selected":"" %>>System</option>
                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputName">Email:</label>
                                <label type ="hidden" class="text-danger"><%= ErrorMsg%></label>
                                <input type="email" value="<%= sbia.getCompanyEmail()%>" class="form-control" placeholder="Enter Email" name="CEmail">
                            </div>
                            <div class="float-right">
                                <button class="btn btn-secondary p-2" type="reset" >Reset</button>
                                <button class="btn btn-primary p-2"   type="submit" value="Register">Add Supplier</button>
                            </div> 
                        </form>
                    </div>
                  
                </div>
            </div>
        </div>
      </body>
</html>
