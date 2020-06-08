<%-- 
    Document   : profile
    Created on : 02/05/2020, 8:50:40 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.CustomerAccessLogBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="uts.isd.model.dao.DBManager"%>
<%@page import="java.util.Calendar"%>
<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
          
<!DOCTYPE html>
<html>
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
                        
                        if(accountsesh!=null&&cust.getDOB()!=null){
                            System.out.println(cust.getDOB().toString()+ thing);
                            thing.setTime(cust.getDOB());
                        }
                        

                        ArrayList datalist = new ArrayList();
                        Object  data= request.getAttribute("data");
                        if(data!=null){
                            datalist = (ArrayList)data;
                        }
                        //add a controler for this, using request.setAttribute("name", value); 
                        ///and request.getRequestDispatcher("something.jsp").forward(request, response);
        %> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><%=cust.getName()%>'s Access logs</title>
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
                     <jsp:include page="navbar.jsp" />
                </div>
                
                <div class="col-sm-12 col-md-9 p-4">
                    <h1><%=cust.getName()%>'s Access logs</h1>
                    <form action="accessLogs" method="GET"  class="form-inline ">
                        <div class="row">
                            <div class="col-10 pr-1">
                                <input type="text" class="form-control lineBox w-100" id="inputDate" placeholder="before 04/06/2020" name="search_date">
                            </div>
                            <div class="col-2 pl-0">
                                <button type="submit" class="btn">
                                    <svg class="bi bi-search" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                                    <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                                  </svg>
                                </button>
                            </div>
                        </div>
                    </form>
                    <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Logged in</th>
                        <th scope="col">Logged out</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for(int i = 0;i<datalist.size();i++){ 
                          CustomerAccessLogBean cb = (CustomerAccessLogBean)datalist.get(i);
                          
                        %> 
                      <tr>
                        <th scope="row"><%=i%></th>
                        <td><%= cb.getLoggedin().toString() %></td>
                        <td><%=cb.getLoggedout()==null? "not recorded":cb.getLoggedout().toString()%></td>
                        
                      </tr>
                      <%}%>
                    </tbody>
                  </table>
                    
                </div>
                   
            </div>
        </div>
    </body>
</html>
