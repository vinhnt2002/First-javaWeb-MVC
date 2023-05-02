<%-- 
    Document   : user
    Created on : Apr 13, 2023, 3:53:06 PM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sp.bw.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
                    <style>
            body {
                        display: flex;
                        flex-direction: column;
                        justify-content: center;
                        align-items: center;
                        margin-top: 20px;
                        background-color: #85FFBD;
                        background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);

                    }
                    .logout {
                        right: 0;
                        top: 10px;
                        cursor: pointer;
                    }
                    
                    .text__content{
                        display: flex;
                        margin: 10px 0;
                        font-size: 2rem;
                    }
                    
                    .mgBTN{
                        margin-bottom: 10px;
                    }
        </style>
        
    </head>
    <body class="position-absolute top-20 start-50 translate-middle-x">
        <h1>User Infomation ( US role)</h1>
        
               <a class="btn btn-outline-primary mgBTN" href="shopping.jsp">Shopping</a>
<!--               <form action="MainController">
                   <input type="hidden" name="userID" value="${user.userID}"/>
                   <input class="btn btn-outline-primary mgBTN" type="submit" name="action" value="shopping"/>
               </form>-->

                <a class="logout btn btn-outline-primary" href="MainController?action=Logout">Log Out</a>

                <div class="text__content">User ID: ${sessionScope.LOGIN_USER.userID} </div> 
                <div class="text__content">Full Name: ${sessionScope.LOGIN_USER.fullName}  </div> 
                <div class="text__content"> Role ID: ${sessionScope.LOGIN_USER.roleID} </div> 
                <div class="text__content"> Password: ${sessionScope.LOGIN_USER.password}</div> 
  
    </body>
</html>
