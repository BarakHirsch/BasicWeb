<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User info</title>

        <style>
            h3 {color:white;font: 14px  verdana, arial, helvetica;}
        </style>
        <%
            User user = (User) request.getAttribute("User");
        %>
    </head>
    <body>
        <h3>Hello, <%= user.getName() %> <%
                if (user.isLoggedIn()) {
                    out.print("<a href=\"LogoutServlet\" target=\"_self\">(Logout)</a>");
                } else {

                    out.print(" <a href=\"Login.jsp\" target=\"_self\">(Login)</a>");
                }
            %>
        </h3>
    </body>
</html>