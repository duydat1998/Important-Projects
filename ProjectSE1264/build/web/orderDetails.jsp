<%-- 
    Document   : orderDetails
    Created on : 12-Mar-2018, 15:42:37
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
    </head>
    <body style="padding-left: 200px; padding-right: 100px">
        <div width="100%" height="100">
            <font color="green">User: ${sessionScope.USER}</font>
            <form action="logout" method="POST" style="position: absolute; right: 200px">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
        </div>
        <br/>
        <hr/>
        <h3>Order Details</h3> 
        <br/>
        <c:set var="orderDTO" value="${requestScope.ORDER_DETAIL}"/>
        <c:set var="custDTO" value="${requestScope.CUST_DETAIL}"/>
        <c:set var="result" value="${requestScope.RESULT_DETAIL}"/>
        <table cellpadding="10">
            <tbody>
                <tr>
                    <td>Order Id: ${orderDTO.orderID}</td>
                    <td>Date: ${orderDTO.orderDate}</td>
                </tr>
                <tr>
                    <td>Customer: ${custDTO.custID}-${custDTO.custName}</td>
                    <td>Phone: ${custDTO.phone}</td>
                </tr>
                <tr>
                    <td colspan="2">Address: ${custDTO.address}</td>
                </tr>
            </tbody>
        </table>
        <br/>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detailDTO" items="${result}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${detailDTO.productID}</td>
                        <td>${detailDTO.quantity}</td>
                        <td>${detailDTO.unitPrice}</td>
                        <td>${detailDTO.total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p style="margin-left: 200px">Total: ${orderDTO.total}</p> <br/>
        
        <c:url var="searchLink" value="search"/>
        <a href="${searchLink}">Back to Order List page.</a><br/>
        <a href="search.jsp">Back to Search page.</a>
        
    </body>
</html>
