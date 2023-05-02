<%-- 
    Document   : login
    Created on : Apr 13, 2023, 3:50:14 PM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <style>
            *{
               
                padding: 0;
                margin: 0;
            }
            body{
                background-color: #4158D0;
                background-image: linear-gradient(43deg, #4158D0 0%, #C850C0 46%, #FFCC70 100%);
            }
            .container{
                background: white;
                justify-content: center;
                align-items: center;
            }
            .login__form{
                display: flex;
                flex-direction: column;
                justify-self: center;
                font-size: 16px;
            }
            
            .login__form input{
                padding: 5px 20px 5px 2px;
            }
            
            .form-control{
                width: 300px;
            }
            
            .btn {
                cursor: pointer;
                 margin: 10px 0px 10px 0;
                width: 300px;
            }

            .tag a{
                text-decoration: none;
                text-align: center;
                background-color: #fff;
                padding: 10px;
            }

            .error {
                margin-top: 15px;
                color: red;
            }
            .danger{
                color: red;
                font-weight: bold;
                margin-top: 20px;
            }
        </style>
    </head>
    <body class="position-absolute top-50 start-50 translate-middle">
        <div class="container">
        <form class="login__form" action="MainController" method="POST" id="form">
             <label for="userID">UserID</label> <input class="form-control" type="text" name="userID" required=""> <br/>
            <label for="password">Password</label> <input class="form-control" type="password" name="password" required=""> <br/>
            <div class="g-recaptcha" data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>
            <div id="error"></div>
            
            <input type="hidden" name="action" value="Login" />
            <input class="btn btn-primary" type="submit" name="action" value="Login" />

        </form>
            <form   action="MainController" method="POST">
                <input class="btn btn-info" type="submit" name="action" value="Login With Google"/><br/>
                <input class="btn btn-secondary" type="reset"  value="Reset" >

            </form>
            </div>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
                        window.onload = function (){
                                let isValid = false;
                                const form = document.getElementById("form");
                                const error = document.getElementById("error");
				
                                form.addEventListener("submit", function (event){
                                        event.preventDefault();
                                        const response = grecaptcha.getResponse();
                                        if (response){
                                                form.submit();
                                        } else {
                                                error.innerHTML = "Please check";
                                        }
                });
            }
        </script>
       
        <h4 class="danger">${requestScope.ERROR}</h4>
        
    </body>
</html>