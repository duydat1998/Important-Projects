<%-- 
    Document   : viewCart
    Created on : 10-Mar-2018, 07:22:35
    Author     : Nguyen Duy Dat
--%>

<%@page import="sample.tbl_Mobile.tbl_MobileDAO"%>
<%@page import="sample.tbl_Mobile.tbl_MobileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
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
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${empty items}">
                <h1>No mobile in your cart!!!</h1>
            </c:if>
            <c:if test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Mobile Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="ControlServlet" method="POST">
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <c:set var="dto" value="${cart.getMobileById(item.key)}"/>
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.mobileName}</td>
                                <td>${dto.price}</td>
                                <td>${item.value}</td>
                                <td>
                                    <input type="submit" value="Remove" name="btAction" />
                                    <input type="hidden" name="txtMobileId" value="${item.key}" />
                                </td>
                            </tr> 
                        </c:forEach>
                        <br/>
                    </form>
                </tbody>
            </table>
                <br/>
                <br/>
            <form action="ControlServlet" method="POST">
                <input type="submit" value="Store to DB" name="btAction" />
            </form>
        </c:if>
    </c:if>
    <c:if test="${empty cart}">
        <h1>No mobile in your cart!!!</h1>
    </c:if>

    <c:set var="lastSearchMinValue" value="${param.lastSearchMinValue}"/>
    <c:set var="lastSearchMaxValue" value="${param.lastSearchMaxValue}"/>
    <c:url var="backLink" value="ControlServlet?btAction=Search in range&txtSearchMinPrice=${lastSearchMinValue}&txtSearchMaxPrice=${lastSearchMaxValue}"/>
    <br/><br/>
    <a href="${backLink}">Click here to go back</a>
</body>
</html>
