<%-- 
    Document   : order
    Author     : Forever
--%>
<%@page import="uts.isd.model.Staff"%>
<%@page import="uts.isd.model.OrderBean"%>
<%@page import="uts.isd.model.CustomerAccessLogBean"%>
<%@page import="uts.isd.model.CustomerBean"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DBManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
          
<!DOCTYPE html>
<html>
    <%
                       CustomerBean cust = null;
                        Staff stuff= new Staff();
                        Object accountsesh = session.getAttribute("login");
                        String profilelink= "#";
                        String name="Guest";
                        if(accountsesh!=null){

                            if(accountsesh instanceof CustomerBean){
                                cust = (CustomerBean)accountsesh;
                                name= cust.getName();
                            }else{
                                stuff = (Staff)accountsesh;
                                name=stuff.getFullName();
                            }
                            profilelink="profile.jsp";

                        }

                        ArrayList datalist = new ArrayList();
                        Object  data= request.getAttribute("data");
                        if(data!=null){
                            datalist = (ArrayList)data;
                        }
                        //add a controler for this, using request.setAttribute("name", value); 
                        ///and request.getRequestDispatcher("something.jsp").forward(request, response);
                        
    
                        ArrayList OrderList = new ArrayList();
                        Object  OrderInfo= request.getAttribute("OrderInfo");
                        if(OrderInfo!=null){
                            OrderList = (ArrayList)OrderInfo;
                        }
    
        %> 
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Managing<%=name%>'s Order</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css">
         <script src="lib/jquery/jquery-3.5.0.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
  
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    </head>
        
          
    <body>
        <div class="container">
            <jsp:include page="header.jsp" />
            <div class="row" >
                <div class="col-sm-12 col-md-3">
                     <jsp:include page="orderViewNavBar.jsp" />
                </div>
                
                <div class="col-sm-12 col-md-9 p-4">
                    <h1 style='color: #218838'>Managing <%=name%>'s Order</h1>
                    <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Order Number</th>
                        <th scope="col">Customer ID</th>
                        <th scope="col">Date of Order</th>
                        <th scope="col">Shipping Address</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Product Quantity</th>
                        
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int i = 0;i<OrderList.size();i++){ 
                          OrderBean ob = (OrderBean)OrderList.get(i);
                          
                        %> 
                      <tr>
                        <th scope="row"><%=i%></th>
                        <td><%= String.valueOf(ob.getOrderId()).toString() %></td>
                        <td><%= String.valueOf(ob.getCustomerId()).toString()%></td>
                        <td><%= ob.getDOO()==null? "In ordering process":ob.getDOO().toString()%></td>
                        <td><%= ob.getShippingAddress()==null? "Unfilled in":ob.getShippingAddress().toString()%></td>
                        <td><%= ob.getProduct()==null? "Unfilled in":ob.getProduct().getName()%></td>
                        <td><%= String.valueOf(ob.getProductQuantity()).toString()%></td>
                        <td style="height: 100px;">
                            <a role="button" href="${pageContext.request.contextPath}/OrderEdit?OID=<%= ob.getOrderId()%>">Update</a>
                            <a role="button" href="${pageContext.request.contextPath}/deleteOrder?OID=<%= ob.getOrderId()%>">Cancel</a>
                        </td>
                      </tr>
                      <%}%>
                    </tbody>
                  </table>
                    
                </div>
                   
            </div>
        </div>
    </body>
</html>