<%-- 
    Document   : userPage
    Created on : 06-Mar-2018, 10:36:01
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
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

        <s:form action="searchUser" method="POST">
            <h3>Search device with price in range [min, max] </h3>
            <hr/>
            <s:textfield name="minPrice" label="From" />
            <s:textfield name="maxPrice" label="To" />
            <s:submit value="Search in range"/>
        </s:form>
        <br/>
        <br/>
        <s:if test="%{exception != null}">
            <h4><font color="red">Price must be real number</font></h4>
            </s:if>

        <s:else>
            <s:if test="%{(!minPrice.isEmpty())&&(!maxPrice.isEmpty())}">
                <s:if test="%{listMobile != null}">
                    <h3>Result:</h3>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Mobile Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Year of Production</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="listMobile" status="counter">
                                <s:form action="addToCart" theme="simple">
                                    <tr>
                                        <td>
                                            <s:property value="%{#counter.count}"/>
                                        </td>
                                        <td>
                                            <s:property value="mobileName"/>
                                            <s:hidden name="mobileId" value="%{mobileId}"/>
                                            <s:hidden name="minPrice" value="%{minPrice}" />
                                            <s:hidden name="maxPrice" value="%{maxPrice}" />
                                        </td>
                                        <td>
                                            <s:property value="description"/>
                                        </td>
                                        <td>
                                            <s:property value="price"/>
                                        </td>
                                        <td>
                                            <s:property value="yearOfProduction"/>
                                        </td>
                                        <td>
                                            <s:submit value="Add to cart"/>
                                        </td>
                                    </tr>
                                </s:form>
                            </s:iterator>
                        </tbody>
                    </table>
                    <br/>
                </s:if>
                <s:else>
                    <h3>No record is matched!!!</h3>
                </s:else>

                <s:if test="%{successMsg != null}">
                    <h3><s:property value="successMsg"/></h3>
                    <br/>

                </s:if>
            </s:if>
        </s:else>

        <br/>
        <s:form action="viewCart" method="POST">
            <s:submit value="View Cart" />
        </s:form>
    </body>
</html>
