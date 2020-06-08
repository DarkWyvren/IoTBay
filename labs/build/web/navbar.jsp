<%-- 
    Document   : navbar
    Created on : 02/05/2020, 8:54:41 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.Staff"%>
<%@page import="uts.isd.model.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    CustomerBean cust = new CustomerBean();
    Staff stuff = null;
    cust.setName("Guest");
    cust.setPassword("");

    Object accountsesh = session.getAttribute("login");
    if(accountsesh!=null){
        if(accountsesh instanceof CustomerBean){
            cust = (CustomerBean)accountsesh;

        }else{
            stuff = (Staff)accountsesh;
        }
    }
%>
<ul class="flex-sm-column nav  nav-pills ">
    <li class="nav-item">
        <a href="index.jsp">
            <div class="nav-fill navButt bg-dark text-white">
                <div style="margin: 25px; width:100%;">
                    <svg class="bi bi-house-door-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"/>
                        <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                      </svg>
                    <b>Home</b>
                </div>
            </div>
        </a>
    </li>
    <li class="nav-item">
        <form action="store" method="GET"  class="form-inline  bg-dark text-white">
            <div class="row p-2">
                <div class="form-group col-9 pr-0">
                    <input type="text" class="form-control lineBox w-100" id="inputProductName" placeholder="Search" name="search_product">
                </div>
                <div class="col-3 pl-0">
                    <button type="submit" class="btn btn-dark">
                        <svg class="bi bi-search" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                      </svg>
                    </button>
                </div>
            </div>
            
            <div class="form-group p-2 mt-2 ">
                <div class="form-check ml-2 ">
                    <input class="form-check-input" type="checkbox" value="" id="catagory1"  name="Computers">
                    <label class="form-check-label  text-white ml-2 " style="font-family: IBM_Plex, Helvetica ,sans-serif; font-size: 10pt;" for="catagory1">
                      Computers
                    </label>
                </div>
            </div>
            <div class="form-group p-2 " >
                <div class="form-check ml-2 ">
                    <input class="form-check-input" type="checkbox" value="" id="catagory2"  name="Accessories">
                    <label class="form-check-label  text-white ml-2 " style="font-family: IBM_Plex, Helvetica ,sans-serif; font-size: 10pt;" for="catagory2">
                      Accessories
                    </label>
                </div>
            </div>
            <div class="form-group p-2 mb-2">
                <div class="form-check ml-2 ">
                    <input class="form-check-input" type="checkbox" value="" id="catagory3"  name="Cables">
                    <label class="form-check-label  text-white ml-2 " style="font-family: IBM_Plex, Helvetica ,sans-serif; font-size: 10pt;" for="catagory3">
                      Cables
                    </label>
                </div>
            </div>
            <div class="form-group p-2 mb-2">
                <div class="form-check ml-2 ">
                    <input class="form-check-input" type="checkbox" value="" id="catagory4"  name="Cases">
                    <label class="form-check-label  text-white ml-2 " style="font-family: IBM_Plex, Helvetica ,sans-serif; font-size: 10pt;" for="catagory4">
                      Cases
                    </label>
                </div>
            </div>
            <div class="form-group p-2 mb-2">
                <div class="form-check ml-2 ">
                    <input class="form-check-input" type="checkbox" value="" id="catagory5"  name="Books">
                    <label class="form-check-label  text-white ml-2 " style="font-family: IBM_Plex, Helvetica ,sans-serif; font-size: 10pt;" for="catagory5">
                      Books
                    </label>
                </div>
            </div>
            
        </form>
    </li>
    
    <li class="nav-item">
        <a href="store">
            <div class="nav-fill navButt bg-dark text-white">
                <div style="margin: 25px; width:100%;">
                    <svg class="bi bi-house-door-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"/>
                        <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                      </svg>
                    <b>Store</b>
                </div>
            </div>
        </a>
    </li>
    <%if(stuff!=null){%>
    <li class="nav-item">    
        <a href="Management.jsp">
            <div class="nav-fill navButt  bg-light" style="color: #404040">
                <div style="margin: 25px; width:100%;">
                    <b>Management</b>
                </div>
            </div>
        </a>
    </li>
    <%}%>
    <%if(accountsesh!=null){%>
        <li class="nav-item">
            <a href="accessLogs">
                <div class="nav-fill navButt bg-light" style="color: #404040">
                    <div style="margin: 25px; width:100%;">
                        <svg class="bi bi-card-text" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                            <path fill-rule="evenodd" d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8zm0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z"/>
                          </svg>
                        <b>Access logs</b>
                    </div>
                </div>
            </a>
        </li>
    <%}%>
</ul>
