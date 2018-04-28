<%-- 
    Document   : staffInsertPage
    Created on : 22-Mar-2018, 08:06:00
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <s:head/>
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
        <s:form action="insert" method="POST">
            <h3>Insert new Mobile</h3>
            <hr/>
            <s:textfield name="insertMobileId" label="MobileId *"/>
            <s:textfield name="insertDescription" label="Description *"/>
            <s:textfield name="insertPrice" label="Price"/>
            <s:textfield name="insertMobileName" label="Mobile Name *"/>
            <s:textfield name="insertYear" label="Year of Production"/>
            <s:textfield name="insertQuatity" label="Quantity"/>
            <s:checkbox name="insertNotSale" label="Is not sale"/>
            <s:submit value="Insert to Database"/>
            <s:reset value="Reset"/>
        </s:form>

        <s:if test="%{successMsq != null}">
            <h2><s:property value="successMsq"/></h2>
        </s:if>
        <s:if test="%{exception.message.contains('duplicate')}">
            <font color="red"><s:property value="insertMobileId"/> is existed!!!</font>
        </s:if>
        <s:url id="searchLink" action="staffPage"/>
        <s:a href="%{searchLink}">Click here to back to Search Page</s:a>
    </body>
</html>
