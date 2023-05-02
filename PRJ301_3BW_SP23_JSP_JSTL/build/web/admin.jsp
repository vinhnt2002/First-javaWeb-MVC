<%-- 
    Document   : admin
    Created on : Apr 13, 2023, 3:57:58 PM
    Author     : thanh
--%>

<%@page import="java.util.List"%>
<%@page import="sp.bw.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
        ></script>
        <style>
            body {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                background-color: #85FFBD;
                background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);

            }


            a {
                text-decoration: none;
                color: red;
            }

            table{
                margin-top:20px ;
            }

            table input {
                border: 0;
                border-radius: none;
            }

            .search__input{
                padding-bottom: 10px;
            }



        </style>
    </head>
    <body class="position-absolute top-20 start-50 translate-middle-x">
        <h1>HELLO : ${sessionScope.LOGIN_USER.fullName}</h1>


        <form class="form-label" action="MainController">
            <label class="form-label" for="search">Search</label> <input  class="search__input" type="text" name="search" value="${param.search}"/>
            <input class="btn btn-primary" type="submit" name="action" value="Search"/>
            <input  class="logout btn btn-outline-primary" type="submit" name="action" value="Logout"/>
        </form>
        <c:if test="${requestScope.LIST_USER != null}">
            <c:if test="${not empty requestScope.LIST_USER}">
                <table border="5" class="table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                        <form action="MainController">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="userID" value="${user.userID}" readonly=""/>
                                </td>
                                <td>
                                    <input type="text" name="fullName" value="${user.fullName}"/>
                                </td>
                                <td>
                                    <input type="text" name="roleID" value="${user.roleID}"/>
                                </td>
                                <td>
                                    ${user.password}
                                </td>
                                <td>
                                    <input  class="update__btn btn btn-outline-primary" type="submit" name="action" value="Update"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="search" value="${param.search}"></c:param>
                                        <c:param name="userID" value="${user.userID}"></c:param>
                                    </c:url>
                                    <a class="btn btn-outline-danger" href="${deleteLink}">Delete</a>
                                </td>

                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
            ${requestScope.ERROR}
        </c:if>
    </c:if>
</body>
</html>
