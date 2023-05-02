<%-- 
    Document   : history
    Created on : Apr 24, 2023, 10:43:48 AM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
                <style>
    body{
        margin-top: 20px;
        background-image: url('img/shopping.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        
    }
    .btn__form{
       display: flex;
       justify-content: space-evenly;
       text-align: center;
       margin-top: 15px;
    }
            
    .text__form{
        text-align: center;
        font-size: 3rem;
    }
    .text__form1{
        text-align: center;
        font-size: 1rem;
        padding-right: 20px;
                
    }
    .text__form2{
        text-align: center;
        font-size: 1rem;
        padding-right: 20px;
                
    }
            
    .thead{
        border-bottom: 3px;
    }
            
    .mg__bt{
        margin : 0 0 5px 0;
    }
            
    .return{
        text-align: center;
        text-decoration: none;
        display: flex;
        justify-content: center;
    }
    .btn__reset {
        width: 200px;
        display: flex;
        /* justify-content: center; */
        justify-content: flex-end;
        margin-left: 50px;
        margin-top: 14px;
    }
            
    .font{
        font-weight: bold;
        color: white;
    }
    
    .mg_t{
        margin-top: 2rem;
    }
    
    h2{
        margin: 10px 0 0 200px;
    }
        </style>
    </head>
    <body class="position-absolute top-20 start-50 translate-middle-x">
        <h1 class="text__form mg_t">${sessionScope.LOGIN_USER.fullName}</h1>
        <h5 class="text__form mg_t">HISTORY CHECK</h5>
        
        <table border="1">
            <thead>
                <tr>
                    <th class="text__form1">NO</th>
                    <th class="text__form1">ORDER ID</th>
                    <th class="text__form1">PRODUCT ID</th>
                    <th class="text__form1">QUANTITY</th>
                    <th class="text__form1">PRICE</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" varStatus="counter" items="${sessionScope.listOrder}" >
                <tr>
                    <td class="text__form2">${counter.count}</td>
                    <td class="text__form2">${order.orderID}</td>
                    <td class="text__form2">${order.productID}</td>
                    <td class="text__form2">${order.quantity}</td>
                    <td class="text__form2">${order.price}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
<div class="btn__reset">
        <a class="return btn btn-secondary" href="user.jsp">Return</a>
    </div>   
    </body>
</html>
