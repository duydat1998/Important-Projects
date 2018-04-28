<%-- 
    Document   : staffPage
    Created on : 06-Mar-2018, 10:36:13
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
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

        <s:url id="insertLink" action="insertPage"/>
            <s:a href="%{insertLink}">Click here to Insert a new mobile.</s:a>
            <br/>
            <hr/>
        <s:form action="searchStaff" method="POST">
            <h3>Search device with id </h3>
            <s:textfield name="searchId" label="Search Id"/>
            <s:hidden name="searchName" value="%{searchName}" />
            <s:submit value="Search"/>
            <hr/>
        </s:form>
        <br/>
        <br/>

        <s:if test="%{exception != null}">
            <h4><font color="red">Price must be real number</font></h4>
            <h4><font color="red">Quantity must be integer number</font></h4>
        </s:if>
        <s:if test="%{!searchId.isEmpty()}">
            <s:if test="%{listId != null}">
                <h3>Result:</h3>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Mobile ID</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Mobile Name</th>
                            <th>Year of Production</th>
                            <th>Quantity</th>
                            <th>Not Sale</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listId" status="counter">
                            <s:form action="updateStaff" theme="simple">
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td>
                                    <td>
                                        <s:property value="mobileId"/>
                                        <s:hidden name="mobileId" value="%{mobileId}"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="description"/>--%>
                                        <s:textfield name="description" value="%{description}"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="price"/>--%>
                                        <s:textfield name="price" value="%{price}"/>
                                    </td>
                                    <td>
                                        <s:property value="mobileName"/>
                                    </td>
                                    <td>
                                        <s:property value="yearOfProduction"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="quantity"/>--%>
                                        <s:textfield name="quantity" value="%{quantity}"/>
                                    </td>
                                    <td>
                                        <s:checkbox name="notSale" value="%{notSale}"/>
                                    </td>
                                    <td>
                                        <s:url id="deleteLink" action="deleteRecord">
                                            <s:param name="mobileId" value="mobileId"/>
                                            <s:param name="searchId" value="searchId"/>
                                            <s:param name="searchName" value="searchName"/>
                                        </s:url>
                                        <s:a href="%{deleteLink}">
                                            Delete
                                        </s:a>
                                    </td>
                                    <td>
                                        <s:hidden name="searchId" value="%{searchId}"/>
                                        <s:hidden name="searchName" value="%{searchName}"/>
                                        <s:submit value="Update"/>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </s:if>
                <s:else>
                    <h3>No record is matched!!!</h3>
                </s:else>
            </s:if>

            
            <s:form action="searchStaff" method="POST">
                <h3>Search device with name </h3>
                <s:textfield name="searchName" label="Search Name"/>
                <s:hidden name="searchId" value="%{searchId}" />
                <s:submit value="Search"/>
                <hr/>
            </s:form>
            <br/>
            <br/>

            <s:if test="%{!searchName.isEmpty()}">
            <s:if test="%{listName != null}">
                <h3>Result:</h3>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Mobile ID</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Mobile Name</th>
                            <th>Year of Production</th>
                            <th>Quantity</th>
                            <th>Not Sale</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="listName" status="counterName">
                            <s:form action="updateStaff" theme="simple">
                                <tr>
                                    <td>
                                        <s:property value="%{#counterName.count}"/>
                                    </td>
                                    <td>
                                        <s:property value="mobileId"/>
                                        <s:hidden name="mobileId" value="%{mobileId}"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="description"/>--%>
                                        <s:textfield name="description" value="%{description}"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="price"/>--%>
                                        <s:textfield name="price" value="%{price}"/>
                                    </td>
                                    <td>
                                        <s:property value="mobileName"/>
                                    </td>
                                    <td>
                                        <s:property value="yearOfProduction"/>
                                    </td>
                                    <td>
                                        <%--<s:property value="quantity"/>--%>
                                        <s:textfield name="quantity" value="%{quantity}"/>
                                    </td>
                                    <td>
                                        <s:checkbox name="notSale" value="%{notSale}"/>
                                    </td>
                                    <td>
                                        <s:url id="deleteLink" action="deleteRecord">
                                            <s:param name="mobileId" value="mobileId"/>
                                            <s:param name="searchId" value="searchId"/>
                                            <s:param name="searchName" value="searchName"/>
                                        </s:url>
                                        <s:a href="%{deleteLink}">
                                            Delete
                                        </s:a>
                                    </td>
                                    <td>
                                        <s:hidden name="searchId" value="%{searchId}"/>
                                        <s:hidden name="searchName" value="%{searchName}"/>
                                        <s:submit value="Update"/>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </s:if>
                <s:else>
                    <h3>No record is matched!!!</h3>
                </s:else>
            </s:if>
            
            
    </body>
</html>
