
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/IoTBay.css">
        <title>Registration Page</title>
    </head>
    <body>
        
        <form method="post" action="payment.jsp">

                        <h1>add a payment</h1> 
                         <table class="panel_div2">
                            <tr><td>Payment method: </td><td><input type="text" placeholder="Enter Payment method" name="Payment_METHOD"></td></tr>
                            <tr><td>Credit card details: </td><td><input type="text" placeholder="Creditcard details" name="Creditcard_Details"></td></tr>
                            <tr><td>Amount:  </td><td><input type="text" placeholder="Enter Phone Number" name="Amount"></td></tr>
                            <tr><td>Date: </td><td><input type="date" name="Payment_DATE"></td></tr>
                        
                            <tr><td></td><td><input type="reset"> <input type="submit" value="Confirm"> </td></tr>
                          
                            <tr><td></td><td></td></tr>
                        </table>
        </form>
        </body>            
</html>