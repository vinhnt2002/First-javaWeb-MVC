<%-- 
    Document   : CreateUser
    Created on : Apr 15, 2023, 3:27:57 PM
    Author     : thanh
--%>

<%@page import="sp.bw.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/createUserStyle.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <style>
            body{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                margin: 0 auto;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #4158D0;
                background-image: linear-gradient(43deg, #4158D0 0%, #C850C0 46%, #FFCC70 100%);
                font-weight: bold;
            }

            .create__form{
                display: flex;
                flex-direction: column;
                justify-content: center;
                font-size: 16px;
            }

            .create__form input{
                padding: 5px 20px 5px 2px;
            }
            .btn {
                cursor: pointer;
            }

            .message {
                color: red;
            }
            a{
                margin-bottom: 10px;
            }
        </style>
        <title>Create User</title>
    </head>
    <body>
        Create new User

        <form class="create__form" action="MainController" method="POST" id="form">
            <label for="userID">UserID</label> <input type="text" name="userID" required=""/> 
            ${requestScope.USER_ERROR.userIDError} </br>
            <label for="fullName">Full Name</label> <input type="text" name="fullName" required=""/> 
            ${requestScope.USER_ERROR.fullNameError} </br>
            <label for="roleID">Role ID</label> <input type="text" name="roleID" required="" value="US"/> 
            ${requestScope.USER_ERROR.roleIDError} </br>
            <label for="password">Password</label> <input type="password" name="password" required=""/> 
            ${requestScope.USER_ERROR.passwordError} </br>
            <label for="confirm">Confirm Password</label> <input type="password" name="confirm" required=""/> 
            <div class="message"> ${requestScope.USER_ERROR.confirmError} </div> <br/>
            <div class="g-recaptcha" data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>
            <div id="error"></div>
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
                     
            <input name="action" value="Create" hidden=""/><br/>
            <input class="btn btn-primary" type="submit" value="Create"/><br/>
            <a class="btn btn-outline-light text-dark" href="login.html">Login</a>
            <input class="btn btn btn-light" type="reset" value="Reset"/>
            <%--<%= userError.getErrorError() %>--%>
           <div class="message"> ${requestScope.USER_ERROR.errorError}</div> 

        </form>
    </body>
</html>
