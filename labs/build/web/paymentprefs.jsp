<%-- 
    Document   : profile
    Created on : 02/05/2020, 8:50:40 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.paymentprefBean"%>
<%@page import="uts.isd.model.Staff"%>
<%@page import="java.util.Calendar"%>
<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
                        CustomerBean cust = null;
                        Staff stuff = null;

                        Object accountsesh = session.getAttribute("login");
                        
                        String name="";
                        String email="";
                        String address="";
                        
                        
                        if(accountsesh!=null){
                            if(accountsesh instanceof CustomerBean){
                                cust = (CustomerBean)accountsesh;
                                name = cust.getName();
                                email = cust.getEmail();
                                address = cust.getAddress();
                            }else{
                                stuff = (Staff)accountsesh;
                                name = stuff.getFullName();
                                email = stuff.getEmail();
                                address = stuff.getAddress();
                            }
                        }
                        
                        String errortext="";
                        boolean haserror=false;
                        Object init = request.getAttribute("response");
                        if(init!=null){
                            String postRes = (String)init;
                            if(postRes.equals("OK")){
                            }else if(postRes.length()>0){
                                haserror = true;
                                errortext = postRes;
                            }
                        }
                        Calendar thing = Calendar.getInstance();
                        
                        if(cust!=null&&accountsesh!=null&&cust.getDOB()!=null){
                            System.out.println(cust.getDOB().toString()+ thing);
                            thing.setTime(cust.getDOB());
                        }
                        
                        paymentprefBean pbf = (paymentprefBean)request.getAttribute("prefs");
                        if(pbf==null){
                            pbf = new paymentprefBean();
                        }
                        
                        /*
                        
                         Customer_ID int NOT NULL,
                        Payment_ID int  NOT NULL AUTO_INCREMENT,
                        Payment_METHOD varchar(128) NOT NULL,
                        Credicard varchar(16),
                        */
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=name%>'s Payment details</title>
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
                
                <div class="col-sm-12 col-md-9 p-4">
                    <h1><%=name%>'s Payment details</h1>
                    <form action="paymentPref" method="POST">
                        <input type="hidden" name="cid" value="<%=cust.getId()%>">
                      <div class="form-group">
                        <label for="inputName">Payment Method</label>
                        <select id="inputType" name="pay_meth">
                            <option value="MasterCard"   >MasterCard</option>
                            <option value="Visa"  >Visa</option>
                            <option value="Citibank"  >Citibank</option>
                            <option value="Chase"  >Chase</option>
                            <option value="American Express"  >American Express</option>
                            <option value="Capital one"  >Capital one</option>
                        </select>
                      </div>
                      <div class="form-group">
                        <label for="inputEmail">Number</label>
                        <input type="number" value="<%=pbf.getCreditcard_Details() %>" class="form-control" id="inputEmail" placeholder="Enter creditcard num" name="creditcard">
                      </div>  
                      
                      <div class="row" style = "height:20px;"></div>
                        
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <%if(haserror){%>
                        <small id="error_response" class="form-text text-danger"><%=errortext%></small>
                      <%}%>
                      
                       
                    </form> 
                </div>
                   
            </div>
        </div>
    </body>
</html>
