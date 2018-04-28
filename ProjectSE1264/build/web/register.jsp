<%-- 
    Document   : register
    Created on : 12-Mar-2018, 15:30:39
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body style="padding-left: 200px; padding-right: 100px">
        <h3>Create account</h3>
        <c:set var="errors" value="${requestScope.INSERT_ERROR}"/>
        <form action="register" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td>Customer ID*:</td>
                        <td> <input type="text" name="txtCustID" value="" /> </td>
                        <c:if test="${not empty errors.custIDLengthError}">
                            <td><font color="red">${errors.custIDLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Password*:</td>
                        <td> <input type="password" name="txtPassword" value="" /> </td>
                        <c:if test="${not empty errors.passwordLengthError}">
                            <td><font color="red">${errors.passwordLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Confirm password*:</td>
                        <td> <input type="password" name="txtConfirm" value="" /> </td>
                        <c:if test="${not empty errors.confirmNotMatch}">
                            <td><font color="red">${errors.confirmNotMatch}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Customer name*:</td>
                        <td><input type="text" name="txtCustName" value="${param.txtCustName}" /></td>
                        <c:if test="${not empty errors.custNameLengthError}">
                            <td><font color="red">${errors.custNameLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td><input type="text" name="txtLastName" value="${param.txtLastName}" /></td>
                        <c:if test="${not empty errors.lastNameLengthError}">
                            <td><font color="red">${errors.lastNameLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Middle name:</td>
                        <td><input type="text" name="txtMiddleName" value="${param.txtMiddleName}" /></td>
                        <c:if test="${not empty errors.middleNameLengthError}">
                            <td><font color="red">${errors.middleNameLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Address*:</td>
                        <td><input type="text" name="txtAddress" value="${param.txtAddress}" /></td>
                        <c:if test="${not empty errors.addressLengthError}">
                            <td><font color="red">${errors.addressLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Phone*:</td>
                        <td><input type="text" name="txtPhone" value="${param.txtPhone}" /></td>
                        <c:if test="${not empty errors.phoneLengthError}">
                            <td><font color="red">${errors.phoneLengthError}</font></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td> <input type="submit" value="Register" name="btAction" /> </td>
                        <td> <input type="submit" value="Reset" name="btAction" /> </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <c:if test="${not empty errors.custIDDuplicated}">
            <font color="red">${errors.custIDDuplicated}</font>
        </c:if>
    </body>
</html>
