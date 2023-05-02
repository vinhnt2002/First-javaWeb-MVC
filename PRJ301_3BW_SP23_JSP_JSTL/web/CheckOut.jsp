<%-- 
    Document   : orderConfirm
    Created on : Apr 19, 2023, 5:46:19 PM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        
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
            
            .theEnd{
                display: flex;
                justify-content: center;
            }
            
            .mg__t{
                margin-top: 30px;
            }
            
        </style><script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </head>
    <body>
                <h1 class="text__form mg__t">Thanh toan thanh cong</h1>
                <form class="theEnd" action="MainController" method="POST">
                    <input class="btn btn-primary" type="submit" name="action" value="History"/>
                    <a class="btn btn-outline-secondary font" href="shopping.jsp">Add more</a>
                   
                </form>
    </body>
</html>
