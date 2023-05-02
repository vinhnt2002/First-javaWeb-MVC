<%-- 
    Document   : select
    Created on : Apr 25, 2023, 10:43:23 AM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Page</title>
    </head>
    <body>
        <h1>SELECT TOP 2</h1>
        <c:if test="${requestScope.LIST_USERTOP != null}">
            <c:if test="${not empty requestScope.LIST_USERTOP}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>userID</th>
                            <th>FullName</th>
                            <th>Role ID</th>
                            <th>Password</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USERTOP}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${user.userID}</td>
                                <td>${user.fullName}</td>
                                <td>${user.roleID}</td>
                                <td>${user.password}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
