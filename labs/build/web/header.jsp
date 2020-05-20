<%-- 
    Document   : header
    Created on : 02/05/2020, 8:23:52 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row h-10  no-gutters" style="">
    <div class="h-100 h-100 col-sm-6 col-md-3 " style="height: 150px; background-image: url('logo.png'); background-repeat: no-repeat; background-size: cover; background-size:100% 100%;"> 
        <a href="index.jsp"><img src="logotitle.png" style="height: 150px; "  border="0"></a>
    </div>
    <div class="w-100 h-100 col-sm-6 col-md-6 d-none d-sm-block"> 
        <div  style="height: 150px; width:100%; background-image: url('stretchbanner.png'); background-repeat: no-repeat; background-size: cover; background-size:100% 100%;"></div>
    </div>
    <%
        CustomerBean cust = new CustomerBean();
        cust.setName("Guest");
        cust.setPassword("");

        Object accountsesh = session.getAttribute("login");
        if(accountsesh==null){
            session.setAttribute("login", cust);
        }else{
            cust = (CustomerBean)accountsesh;
        }

        String profilelink= cust.getPassword().isEmpty()?"#":"profile.jsp";
    %>
    <div class="h-100 col-sm-12 col-md-3 bg-dark text-white p-3">
        <div class="container" style = "width:200px">
        <div class=" row"  style="height: 80px; ">
            <h4>Welcome <a href="<%=profilelink%>"><b id="USERNAME"> <%= cust.getName() %> </b></a></h4>
            <% if(cust.getPassword().trim().length()==0){ %>
                <p>Login to save ur details</p>
            <% } %>
        </div>
        <div class="row">
            <% if(cust.getPassword().trim().length()==0){ %>
            <a href="register.jsp" class="btn btn-primary"  role="button" >Register</a>
            <a href="login.jsp" class="btn btn-secondary ml-2"  role="button" >Login</a>
            <% } else { %>
            <a href="loginauth" class="btn btn-danger ml-2"  role="button" >Logout</a>
            <% } %>
        </div>
        </div>
    </div>
</div>
