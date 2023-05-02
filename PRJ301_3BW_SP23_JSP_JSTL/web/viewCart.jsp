<%-- 
    Document   : viewCart
    Created on : Apr 17, 2023, 9:48:35 PM
    Author     : thanh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sp.bw.product.Product"%>
<%@page import="sp.bw.shopping.Cart"%>
<%@page import="sp.bw.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
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
        margin-left: 110px;
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
                <h1 class="text__form mg_t">YOUR SHOPPING CART</h1>
                <div class="btn__form">
                    <form action="MainController" method="POST">
                        <input type="hidden" name="userID" value="${user.userID}"/>
                        <input class="btn btn-success" style="width: 200px" type="submit" name="action" value="Check out"/>
                        <input class="btn btn-success" style="width: 200px" type="submit" name="action" value="History"/>
                    </form>
                </div>

        <c:if test="${sessionScope.CART != null}">
            
        <table border="4">
            <thead>
                <tr>
                    <th class="text__form1">NO</th>
                    <th class="text__form1">Product ID</th>
                    <th class="text__form1">Name</th>
                    <th class="text__form1">Price</th>
                    <th class="text__form1">Quantity</th>
                    <th class="text__form1">Total</th>
                    <th class="text__form1">Edit</th>
                    <th class="text__form1">Remove</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="pro" varStatus="counter" items="${sessionScope.CART.cart.values()}">
            <form action="MainController" method="POST">
                <tr>
                    <td class="text__form2">${counter.count}</td>
                    <td class="text__form2">
                        <input class="mg__bt" style="width: 50px" type="text" name="productID" value="${pro.productID}" readonly=""/>
                    </td>
                    <td class="text__form2">${pro.name}</td>
                    <td class="text__form2">${pro.price}</td>
                    <td class="text__form2">
                        <input class="mg__bt" style="width: 50px" type="number" name="quantity" value="${pro.quantity}"required="" min="1" max="${pro.quantity}"  />
                    </td>
                    <td class="text__form2">${pro.price * pro.quantity} 
                        <c:set var="total" value="${pro.price * pro.quantity + total}"></c:set>
                    </td>
                    <!--Edit cart o day-->
                    <td class="text__form2">
                        <input class="mg__bt" style="width: 50px" type="submit" name="action" value="Edit"/>
                    </td>
                    <td class="text__form2">
                        <input class="mg__bt" style="width: 100px" type="submit" name="action" value="Remove"/>
                    </td>

                </tr>
                <br/>

            </form>
                </c:forEach>

        </tbody>

    </table>
        <h2> TOTAL: ${total}</h2>

           </c:if>
            <div class="btn__reset">
                <a class="return btn btn-secondary"href="MainController?action=Get all">ADD MORE</a>
            </div>     
        <h5> ${requestScope.MESSAGE1} </h5>
        <h5> ${requestScope.ERROR1} </h5>

    </body>
</html>
