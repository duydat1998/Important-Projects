<%-- 
    Document   : userPage
    Created on : 06-Mar-2018, 10:36:01
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
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

        <form action="ControlServlet" method="POST">
            <h3>Search device with price in range [min, max] </h3>
            From: 
            <input type="text" name="txtSearchMinPrice" value="${param.txtSearchMinPrice}" />
            To:
            <input type="text" name="txtSearchMaxPrice" value="${param.txtSearchMaxPrice}" />
            <input type="submit" value="Search in range" name="btAction"/>
        </form>
        <br/>
        <br/>
        <c:set var="searchMinValue" value="${param.txtSearchMinPrice}"/>
        <c:set var="searchMaxValue" value="${param.txtSearchMaxPrice}"/>
        <c:if test="${not empty searchMinValue}">
            <c:if test="${not empty searchMaxValue}">
                <c:set var="result" value="${requestScope.RESULT_RANGE}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Mobile ID</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Mobile Name</th>
                                <th>Year of Production</th>
                                <th>Add to cart</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="ControlServlet" method="POST">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.mobileId}
                                        <input type="hidden" name="txtMobileId" value="${dto.mobileId}" />
                                    </td>
                                    <td>
                                        ${dto.description}
                                    </td>
                                    <td>
                                        ${dto.price}
                                    </td>
                                    <td>
                                        ${dto.mobileName}
                                    </td>
                                    <td>
                                        ${dto.yearOfProduction}
                                    </td>
                                    <td>
                                        <input type="submit" value="Add" name="btAction" />
                                        <input type="hidden" name="lastSearchMinValue" value="${searchMinValue}" />
                                        <input type="hidden" name="lastSearchMaxValue" value="${searchMaxValue}" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h4><font color="red">No record is matched!!!</font></h4>
                </c:if>
            </c:if>
        </c:if>
    <br/>
    <br/>
    <hr/>
    <form action="ControlServlet" method="POST">
        <input type="submit" value="View Cart" name="btAction" />
        <input type="hidden" name="lastSearchMinValue" value="${searchMinValue}" />
        <input type="hidden" name="lastSearchMaxValue" value="${searchMaxValue}" />
    </form>
</body>
</html>
