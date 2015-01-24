<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Login</title>
        <style>
            h3 {color:white;font: 14px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>
        <form action="UserInfoServlet" method="post">
            <h3>First name: <input required type="text" name="firstName"> Last name:<input required type="text" name="lastName"> <input type="submit" name="submit" value="Log in"></h3>
        </form>
    </body>
</html>