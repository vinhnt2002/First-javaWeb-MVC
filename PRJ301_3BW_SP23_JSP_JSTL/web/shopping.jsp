<%-- 
    Document   : shopping
    Created on : Apr 17, 2023, 3:11:53 PM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sp.bw.product.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="sp.bw.user.UserDTO"%>
<html>
    <head>
        <title>Shopping page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <style>
            body{
                margin-top: 20px;
                background-color: #4158D0;
                background-image: linear-gradient(43deg, #4158D0 0%, #C850C0 46%, #FFCC70 100%);
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
                margin-left: 68px;
            }
            
            .font{
                font-weight: bold;
                color: white;
            }
            
        </style>
    </head>
    <body class="position-absolute top-20 start-50 translate-middle-x">
        <div class="text__form" >Welcome to my Shop</div>
        <div class="btn__form">
            <form action="MainController">
                <input class="btn btn-primary" type="submit" name="action" value="Get all"/>
                <input class="btn btn-outline-secondary font" type="submit" name="action" value="View cart"/>
            </form>
        </div>
        <c:if test="${requestScope.LIST_PRODUCT != null}">
            <c:if test="${not empty requestScope.LIST_PRODUCT}">
                
        <table border="4">
            <thead class="thead">
                <tr>
                    <th class="text__form1">NO</th>
                    <th class="text__form1">Product ID</th>
                    <th class="text__form1">Name</th>
                    <th class="text__form1">Price</th>
                    <th class="text__form1">Quantity</th>
                    <th class="text__form1">Add</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="pro" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
            <form action="MainController" method="POST">
                <tr>
                    <td class="text__form2">${counter.count}</td>
                    <td class="text__form2">${pro.productID}</td>
                    <td class="text__form2">${pro.name}</td>
                    <td class="text__form2">${pro.price}</td>

                    <td class="text__form2 ">
                        
                        <input class="mg__bt" style="width: 50px" type="number" name="quantity" value="1" min="1" max="${pro.quantity}"/>
                    </td>
                    <td class="text__form2">
                        <input class="mg__bt " style="width: 50px" type="submit" name="action" value="Add"/>
                        <input type="hidden" name="productID" value="${pro.productID}" />
                    </td>

                </tr>
            </form>
                    </c:forEach>

        </tbody>

    </table>

        ${requestScope.MESSAGE}
    <br/>
    <div class="btn__reset">
        <a class="return btn btn-secondary" href="user.jsp">Return</a>
    </div>     
<!--            <form action="MainController">

                <input type="hidden" name="userID" value="${user.userID}"/>
        <input type="submit" name="action" value="Home"/>
            </form>-->

    </c:if>
        </c:if>
</body>
</html>