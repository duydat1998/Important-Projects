<%-- 
    Document   : managerPage
    Created on : 06-Mar-2018, 10:39:31
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
    </head>
    <body style="padding-left: 200px; padding-right: 100px">
        <div width="100%" height="100">
            <font color="green">Welcome, ${sessionScope.USER}</font>
            <form action="ControlServlet" method="POST" style="position: absolute; right: 200px">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
        </div>
        <br/>
        <hr/>
        <h3>No function available yet!</h3>
    </body>
</html>
