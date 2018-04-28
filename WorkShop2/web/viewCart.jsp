<%-- 
    Document   : viewCart
    Created on : 10-Mar-2018, 07:22:35
    Author     : Nguyen Duy Dat
--%>

<%@page import="sample.tbl_Mobile.tbl_MobileDAO"%>
<%@page import="sample.tbl_Mobile.tbl_MobileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body style="padding-left: 200px; padding-right: 100px">
        <div width="100%" height="100">
            <font color="green">Welcome, <s:property value="#session.USER.fullName"/></font>
            <s:form action="logout" method="POST" style="position: absolute; right: 200px">
                <s:submit value="Logout"/>
            </s:form>
        </div>
        <br/>
        <br/>
        <hr/>
        <h1>Your Cart:</h1>
        <s:if test="%{#session.CART != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Mobile name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <s:form action="removeItem" theme="simple">
                        <s:iterator value="%{#session.CART.items}" status="counter">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}"/>
                                </td>
                                <td>
                                    <s:property value="key.mobileName"/>
                                </td>
                                <td>
                                    <s:property value="value"/>
                                </td>
                                <td>
                                    <s:property value="key.price"/>
                                </td>
                                <td>
                                    <s:checkbox name="selectedItem" 
                                                fieldValue="%{key.mobileId}"/>
                                </td>
                            </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="4">
                                <s:a href="addMore">Add More Items</s:a>
                                </td>
                                <td>
                                <s:submit value="Remove Selected Items"/>
                            </td>
                        </tr>
                    </s:form>
                </tbody>
            </table>
            <s:form action="checkOut" method="POST">
                <s:submit value="Check out"/>
            </s:form>
        </s:if>
        <s:else>
            <h3>Your cart is empty.</h3>
        </s:else>

        <s:a href="userPage">Click here to go back.</s:a>

    </body>
</html>
