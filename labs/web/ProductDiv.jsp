<%-- 
    Document   : ProductDiv
    Created on : 08/06/2020, 5:16:22 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.ProductBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    ProductBean p = new ProductBean();
    p.setName(request.getParameter("product"));
    p.setCategory(request.getParameter("productcat"));
    p.setPrice(Double.parseDouble(request.getParameter("productprice")));
    p.setID(Integer.parseInt(request.getParameter("productid")));
    boolean canEdit = (Boolean.parseBoolean(request.getParameter("canEdit")));
    boolean justView = (Boolean.parseBoolean(request.getParameter("justView")));
%>
<div class="<%=justView?"":"col-6 col-lg-2 col-md-4 float-left filterDiv "%> p-2 container-fluid <%=p.getCategory().toLowerCase()%>">
    
    <div class="row  m-0">
        <img class="w-100 "src="productimage<%=(p.getID()%5) %>.png">
    </div>
    <div class="row ml-0">
        <p class=" h-20 mb-1 mt-1"><b>
            <%=p.getName()%>
        </b></p>
    </div>
    <div class="row ml-0 mb-0">
        <p class=" h-20 mb-1">
            $<%=p.getPrice() %>
        </p>
    </div>
     <%if(!justView){%>
    <div class="row ml-0 p-0 mr-0  pb-1 " style="">
         <%if(canEdit){%>
        <a href="#" class="btn btn-warning col-md-4 col-sm-12  h-20  mb-0">
            <svg class="bi bi-pencil-square" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
              </svg>
        </a>
        <%}%>
        <a href="AddNewOrder?productID=<%=p.getID()%>" class="btn btn-success  col-md-8 h-20  col-sm-12  mb-0">
            <p class=" mb-0">Buy</p>
        </a>
    </div>
     <%}%>
    
</div>