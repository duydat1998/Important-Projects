<%-- 
    Document   : orderList
    Created on : 12-Mar-2018, 15:42:24
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List Page</title>
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
        <h3>Order List</h3>   
        <br/>
        <p><span style="margin-right: 100px">From ${sessionScope.FROM_DATE}</span>To ${sessionScope.TO_DATE}</p>
        <br/>
        
        <c:set var="result" value="${requestScope.RESULT}"/>
        <c:if test="${not empty result}">
            Result
            <form action="changeDeliverState">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Date</th>
                            <th>Total</th>
                            <th>Customer</th>
                            <th>Action</th>
                            <th>Reason</th>
                            <th>Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.orderDate}
                                </td>
                                <td>
                                    ${dto.total}
                                </td>
                                <td>
                                    ${dto.custID}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAction" value="${dto.orderID}" />
                                </td>
                                <td>
                                    ${dto.reason}
                                </td>
                                <td>
                                    <c:url var="viewLink" value="viewDetail">
                                        <c:param name="orderID" value="${dto.orderID}"/>
                                    </c:url>
                                    <a href="${viewLink}">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br/>
                
                <input type="submit" name="btAction" style="margin-left: 50px"
                       <c:if test="${sessionScope.IS_DELIVER}"> value="UnDeliver" </c:if>
                       <c:if test="${not sessionScope.IS_DELIVER}"> value="Deliver" </c:if>
                />
            </form>
        </c:if>
        <c:if test="${empty result}">
            <h3>No record is matched!!</h3>
        </c:if>
        <a href="search.jsp">Back to Search Page</a>
    </body>
</html>
