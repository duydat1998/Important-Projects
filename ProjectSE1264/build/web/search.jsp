<%-- 
    Document   : search
    Created on : 12-Mar-2018, 15:18:11
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body style="padding-left: 200px; padding-right: 100px">
        <div width="100%" height="100">
            <font color="green">Welcome, ${sessionScope.USER}</font>
            <form action="logout" method="POST" style="position: absolute; right: 200px">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
        </div>
        <br/>
        <hr/>
        <c:set var="errors" value="${requestScope.DATE_FORMAT_ERROR}"/>
        <form action="search" method="POST">
            <label>Search Order</label>
            <table>
                <tbody>
                    <tr>
                        <td>From Date</td>
                        <td colspan="2">
                            <input type="text" name="txtFromDate" value="${param.txtFromDate}" />
                        </td>
                        <c:if test="${not empty errors.invalidFromDate}">
                            <td>
                                <font color="red">${errors.invalidFromDate}</font>
                            </td>
                        </c:if>
                        <c:if test="${not empty errors.requieredFromDate}">
                            <td>
                                <font color="red">${errors.requieredFromDate}</font>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>To Date</td>
                        <td colspan="2">
                            <input type="text" name="txtToDate" value="${param.txtToDate}" />
                        </td>
                        <c:if test="${not empty errors.invalidToDate}">
                            <td>
                                <font color="red">${errors.invalidToDate}</font>
                            </td>
                        </c:if>
                        <c:if test="${not empty errors.requieredToDate}">
                            <td>
                                <font color="red">${errors.requieredToDate}</font>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td></td>
                        <td> 
                            <input type="checkbox" name="chkDeliver" value="true"/> Delivered  
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td> 
                            <input type="submit" value="Search" name="btAction" />
                        </td>
                        <td> 
                            <input type="submit" value="Reset" name="btAction" />
                        </td>
                    </tr>
                    <c:if test="${not empty errors.invalidTimeInterval}">
                        <tr>
                            <td colspan="3">
                                <font color="red">${errors.invalidTimeInterval}</font>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </form>
    </body>
</html>
