<%-- 
    Document   : store
    Created on : 04/06/2020, 6:13:42 PM
    Author     : antho
--%>

<%@page import="java.util.ArrayList"%>
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
    
    <% 
        
         Object accountsesh = session.getAttribute("login");
         CustomerBean cust = null;
        Staff stuff= null;
        if(accountsesh!=null){
            
            if(accountsesh instanceof CustomerBean){
                cust = (CustomerBean)accountsesh;
            }else{
                stuff = (Staff)accountsesh;
            }
            
        }
        
        
        ArrayList productlist =(ArrayList)request.getAttribute("products");
        
        if(productlist==null){
            productlist=new ArrayList();
        }
        

        String search= (String)request.getAttribute("search");
        if(search==null){
            search = "everything";
        }
        
        
     %>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" />
             <div class="row" >
                 <div class="col-sm-12 col-md-3">
                     <jsp:include page="navbar.jsp" />
                 </div>
                 <div class="col-sm-12 col-md-9 p-4">
                    <div id="myBtnContainer row">
                       <button class="btn active" onclick="filterSelection('all')"> Show all</button>
                       <button class="btn" onclick="filterSelection('computers')"> Computers</button>
                       <button class="btn" onclick="filterSelection('accessories')"> Accessories</button>
                       <button class="btn" onclick="filterSelection('cables')"> Cables</button>
                       <button class="btn" onclick="filterSelection('cases')"> Cases</button>
                       <button class="btn" onclick="filterSelection('books')"> Books</button>
                       
                   </div>
                     <div class="row justify-content-start">
                         <h3>
                             Currently viewing <%=search%>
                         </h3>
                             <%if(!search.equals("everything")){%>
                             <a href="store" class="pl-3 links">Back</a>
                             <%}%>
                     </div>
                     <div class="row justify-content-start">
                         
                         <%for(int j = 0;j<(productlist.size());j++){
                             ProductBean pb = (ProductBean)productlist.get(j);
                         %>
                         <jsp:include page="ProductDiv.jsp" flush="true">
                            <jsp:param name="product" value="<%=pb.getName() %>"/>
                            <jsp:param name="productcat" value="<%=pb.getCategory() %>"/>
                            <jsp:param name="productprice" value="<%=pb.getPrice() %>"/>
                            <jsp:param name="productid" value="<%=pb.getID() %>"/>
                            <jsp:param name="canEdit" value="<%=stuff!=null%>"/>
                        </jsp:include>
                        <%}%>
                     </div>
                    
                 </div>
                <script type="text/javascript">
                    filterSelection("all")
                    function filterSelection(c) {
                      var x, i;
                      x = document.getElementsByClassName("filterDiv");
                      if (c == "all") c = "";
                      // Add the "show" class (display:block) to the filtered elements, and remove the "show" class from the elements that are not selected
                      for (i = 0; i < x.length; i++) {
                        w3RemoveClass(x[i], "show");
                        if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show");
                      }
                    }

                    // Show filtered elements
                    function w3AddClass(element, name) {
                      var i, arr1, arr2;
                      arr1 = element.className.split(" ");
                      arr2 = name.split(" ");
                      for (i = 0; i < arr2.length; i++) {
                        if (arr1.indexOf(arr2[i]) == -1) {
                          element.className += " " + arr2[i];
                        }
                      }
                    }

                    // Hide elements that are not selected
                    function w3RemoveClass(element, name) {
                      var i, arr1, arr2;
                      arr1 = element.className.split(" ");
                      arr2 = name.split(" ");
                      for (i = 0; i < arr2.length; i++) {
                        while (arr1.indexOf(arr2[i]) > -1) {
                          arr1.splice(arr1.indexOf(arr2[i]), 1);
                        }
                      }
                      element.className = arr1.join(" ");
                    }

                    // Add active class to the current control button (highlight it)
                    var btnContainer = document.getElementById("myBtnContainer");
                    var btns = btnContainer.getElementsByClassName("btn");
                    for (var i = 0; i < btns.length; i++) {
                      btns[i].addEventListener("click", function() {
                        var current = document.getElementsByClassName("active");
                        current[0].className = current[0].className.replace(" active", "");
                        this.className += " active";
                      });
                    }

                </script>
            </div>
        </div>
                         
            
      </body>
</html>
