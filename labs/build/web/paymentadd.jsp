<%@ page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Payment add</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
         <script src="lib/jquery/jquery-3.5.0.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <% 
            paymentprefBean pb = (paymentprefBean)request.getAttribute("paymentpref");
            if(pb==null){
                pb = new paymentprefBean();
            }
            OrderBean o = (OrderBean)request.getAttribute("order");
            if(o==null){
                o= new OrderBean();
            }
        %>
        <div class="container">
            <jsp:include page="header.jsp" />
             <div class="row" >
                 <div class="col-sm-12 col-md-3">
                     <jsp:include page="navbar.jsp" />
                 </div>
                <div class="col-sm-12 col-md-9 p-4">
        <form method="post" action="AddPayment">

                        <h1>Add a payment</h1> 
                        <p> For order no. <%=o.getOrderId() %></p>
                        <input type="hidden" name="ordergay" value="<%=o.getOrderId()%>">
                        <input type="hidden" name="custgay" value="<%=pb.getCusid()%>">
                        <div class="form-group">
                            <label for="inputName">Payment Method</label>
                            <select id="inputType" name="Payment_METHOD">
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
                            <input type="number" value="<%=pb.getCreditcard_Details() %>" class="form-control" id="inputEmail" placeholder="Enter creditcard num" name="Creditcard_Details">
                        </div> 
                        <div class="form-group">
                            <label for="inputProductQuantity">Amount</label>
                            <input type="number" class="form-control" id="inputProductQuantity" placeholder="69" name="Payment_Amount">
                            
                        </div>
                        <div class="form-group mb-4">
                            <label for="Payment_DATE">Date</label>
                            <input type="date" class="form-control" id="Payment_DATE"  name="Payment_DATE">
                            
                        </div>
                        
                        
                        <button type="reset" class="btn btn-secondary   ">Reset</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
                </div>
             </div>
        </div>
        </body>            
</html>