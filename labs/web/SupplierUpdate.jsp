<%-- 
    Document   : Supplier
    Author     : mood35-Laptop
--%>

<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    Object supplyTest = request.getAttribute("SupplierInfo2");
    Supplier current= null;
                        if(supplyTest !=null){
                            current = (Supplier )supplyTest;
                        }else{
                              current = new Supplier();
                          }
    int currentStatus = current.getCompanyStatus();
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Supplier Page</title>
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
                        <h2 class="float-right pb-8">Edit Supplier Details</h2>    
                    </div>
                    <div class="jumbotron">
                        <form method="POST" action="SupplierEdit">
                            <input type="hidden" id="SID" name="SupplierID" value="<%= current.getSupplierID()%>">
                            <div class="form-group">
                                <label for="inputName">Company Name:</label>
                                <input type="text" class="form-control" value= "<%= current.getCompanyName().toString() %>" placeholder="Enter Company Name" name="CName">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Address: </label>
                                <input type="text" class="form-control" value= "<%= current.getCompanyAddress().toString() %>" placeholder="Enter Address" name="CAddress">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Company Type: </label>
                                <select class="form-control" placeholder="" name="CType">                                   
                                    
                                    <option value ="Case" <%= current.getCompanyType().equals("Case") ? "selected":"" %> >Case</option>
                                    <option value ="CPU" <%= current.getCompanyType().equals("CPU") ? "selected":"" %>>CPU</option>
                                    <option value ="Fan" <%= current.getCompanyType().equals("Fan") ? "selected":"" %>>Fan</option>
                                    <option value ="MotherBoard" <%= current.getCompanyType().equals("MotherBoard") ? "selected":"" %>>MotherBoard</option>
                                    <option value ="Peripherals" <%= current.getCompanyType().equals("Peripherals") ? "selected":"" %>>Peripherals</option>
                                    <option value ="Power Supply" <%= current.getCompanyType().equals("Power Supply") ? "selected":"" %>>Power Supply</option>
                                    <option value ="RAM" <%= current.getCompanyType().equals("RAM") ? "selected":"" %>>RAM</option>
                                    <option value ="Rasberry PI" <%= current.getCompanyType().equals("Rasberry PI") ? "selected":"" %>>Rasberry Pi</option>
                                    <option value ="SSD" <%= current.getCompanyType().equals("SSD") ? "selected":"" %>>SSD</option>
                                    <option value ="System" <%= current.getCompanyType().equals("System") ? "selected":"" %>>System</option>    
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputName">Email:</label>
                                <input type="email" class="form-control" value= "<%=current.getCompanyEmail().toString()%>" placeholder="Enter Email" name="CEmail">
                            </div>
                            <div class="form-group">
                                <label for="inputName">Status</label>
                                <select class="form-control" value= "currentStatus" placeholder="" name="CStatus">                                    
                                    <option value=1 <%=current.getCompanyStatus() == 1 ? "selected":""%>>Active</option>
                                    <option value=0 <%=current.getCompanyStatus() == 0 ? "selected":""%>>Inactive</option> 
                                </select>
                            </div>
                            <div class="float-right">
                                <button class="btn btn-secondary p-2" type="reset" >Reset</button>
                                <button class="btn btn-primary p-2"   type="submit" value="Register">Update Supplier</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>  
        </div>
      </body>
</html>
