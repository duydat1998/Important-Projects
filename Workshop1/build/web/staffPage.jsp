<%-- 
    Document   : staffPage
    Created on : 06-Mar-2018, 10:36:13
    Author     : Nguyen Duy Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
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

        <form action="ControlServlet" method="POST" border="1">
            <h3>Search device with id </h3>
            Search Id: 
            <input type="text" name="txtSearchId" value="${param.txtSearchId}" />
            <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <br/>
        <br/>

        <c:set var="searchIdValue" value="${param.txtSearchId}"/>
        <c:if test="${not empty searchIdValue}">
            <c:set var="resultSearchId" value="${requestScope.RESULT_ID}"/>
            <c:if test="${not empty resultSearchId}">
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
                    <c:set var="errorsUpdateId" value="${requestScope.ERROR_UPDATE_ID}"/>
                    <tbody>
                        <c:forEach var="dto" items="${resultSearchId}" varStatus="counter">
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
                                    <input type="text" name="txtDescription" value="${dto.description}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPrice" value="${dto.price}" />
                                </td>
                                <td>
                                    ${dto.mobileName}
                                </td>
                                <td>
                                    ${dto.yearOfProduction}
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity" value="${dto.quantity}" />                              
                                </td>
                                <td>
                                    <input type="checkbox" name="chkNotSale" value="ON" 
                                           <c:if test="${dto.notSale}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="ControlServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.mobileId}"/>
                                        <c:param name="lastSearchId" value="${searchIdValue}"/>
                                        <c:param name="lastSearchName" value="${searchNameValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchId" value="${searchIdValue}" />
                                    <input type="hidden" name="lastSearchName" value="${searchNameValue}" />
                                    <input type="hidden" name="txtForm" value="ID" />
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty errorsUpdateId.priceFormatError}">
                <font color="red">
                Update ${errorsUpdateId.mobileId} Error: ${errorsUpdateId.priceFormatError}<br/>
                </font>
            </c:if><br/>
            <c:if test="${not empty errorsUpdateId.descriptionBlankError}">
                <font color="red">
                Update ${errorsUpdateId.mobileId} Error: ${errorsUpdateId.descriptionBlankError}<br/>
                </font>
            </c:if><br/>
            <c:if test="${not empty errorsUpdateId.quantityFormatError}">
                <font color="red">
                Update ${errorsUpdateId.mobileId} Error: ${errorsUpdateId.quantityFormatError}<br/>
                </font>
            </c:if><br/>
        </c:if>

        <c:if test="${empty resultSearchId}">
            <h4><font color="red">No record is matched!!!</font></h4>
            </c:if>
        </c:if>

    <hr/>
    <form action="ControlServlet" method="POST" border="1">
        <h3>Search device with name </h3>
        Search name: 
        <input type="text" name="txtSearchName" value="${param.txtSearchName}" />
        <input type="hidden" name="txtSearchId" value="${param.txtSearchId}" />
        <input type="submit" value="Search" name="btAction"/>
    </form>

    <br/>
    <br/>

    <c:set var="searchNameValue" value="${param.txtSearchName}"/>
    <c:if test="${not empty searchNameValue}">
        <c:set var="resultSearchName" value="${requestScope.RESULT_NAME}"/>
        <c:if test="${not empty resultSearchName}">
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
                <c:set var="errorsUpdateName" value="${requestScope.ERROR_UPDATE_NAME}"/>
                <tbody>
                    <c:forEach var="dto" items="${resultSearchName}" varStatus="counter">

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
                                <input type="text" name="txtDescription" value="${dto.description}" />

                            </td>
                            <td>
                                <input type="text" name="txtPrice" value="${dto.price}" />

                            </td>
                            <td>
                                ${dto.mobileName}
                            </td>
                            <td>
                                ${dto.yearOfProduction}
                            </td>
                            <td>
                                <input type="text" name="txtQuantity" value="${dto.quantity}" />

                            </td>
                            <td>
                                <input type="checkbox" name="chkNotSale" value="ON" 
                                       <c:if test="${dto.notSale}">
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <c:url var="deleteLink" value="ControlServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.mobileId}"/>
                                    <c:param name="lastSearchId" value="${searchIdValue}"/>
                                    <c:param name="lastSearchName" value="${searchNameValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="lastSearchId" value="${searchIdValue}" />
                                <input type="hidden" name="lastSearchName" value="${searchNameValue}" />
                                <input type="hidden" name="txtForm" value="NAME" />
                            </td>
                        </tr>
                    </form>

                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty errorsUpdateName.priceFormatError}">
            <font color="red">
            Update ${errorsUpdateName.mobileId} Error: ${errorsUpdateName.priceFormatError}<br/>
            </font>
        </c:if><br/>
        <c:if test="${not empty errorsUpdateName.descriptionBlankError}">
            <font color="red">
            Update ${errorsUpdateName.mobileId} Error: ${errorsUpdateName.descriptionBlankError}<br/>
            </font>
        </c:if><br/>
        <c:if test="${not empty errorsUpdateName.quantityFormatError}">
            <font color="red">
            Update ${errorsUpdateName.mobileId} Error: ${errorsUpdateName.quantityFormatError}<br/>
            </font>
        </c:if><br/>
    </c:if>

    <c:if test="${empty resultSearchName}">
        <h4><font color="red">No record is matched!!!</font></h4>
        </c:if>
    </c:if>

<hr/>
<c:set var="success" value="${requestScope.SUCCESS_INSERT}"/>
<c:set var="errorsInsert" value="${requestScope.ERROR_INSERT}"/>
<form action="ControlServlet" method="POST" border="1">
    <h3>Insert new Mobile</h3>
    <table>
        <tr>
            <td>Mobile ID *</td>
            <td><input type="text" name="txtMobileId" value="" /></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.mobileIdBlankError}">
                    ${errorsInsert.mobileIdBlankError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Description *</td>
            <td><textarea name="txtDescription" rows="5" cols="50"></textarea></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.descriptionBlankError}">
                    ${errorsInsert.descriptionBlankError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="txtPrice" value="" /></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.priceFormatError}">
                    ${errorsInsert.priceFormatError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Mobile Name * </td>
            <td><input type="text" name="txtMobileName" value="" /></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.mobileNameBlankError}">
                    ${errorsInsert.mobileNameBlankError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Year Of Production</td>
            <td><input type="text" name="txtYear" value="" /></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.yearFormatError}">
                    ${errorsInsert.yearFormatError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Quantity </td>
            <td><input type="text" name="txtQuantity" value="" /></td>
            <td style="color: red">
                <c:if test="${not empty errorsInsert.quantityFormatError}">
                    ${errorsInsert.quantityFormatError}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>Not sale</td>
            <td><input type="checkbox" name="chkNotSale" value="ON"/></td>
            <td></td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="lastSearchId" value="${searchIdValue}" />
                <input type="hidden" name="lastSearchName" value="${searchNameValue}" />
            </td>
            <td>
                <input type="submit" value="Insert" name="btAction" />
            </td>
        </tr>
        <tr>
            <td style="column-span: 2">
                <c:if test="${not empty success}">
                    <font color="blue">${success}</font>
                </c:if>
                <c:if test="${not empty errorsInsert.mobileIdDuplicatedError}">
                    <font color="red">${errorsInsert.mobileIdDuplicatedError}</font>
                </c:if>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
