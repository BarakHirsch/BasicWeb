<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <jsp:useBean id="user" type="models.User" scope="request" />
    </head>
    <body>
        <h3>Hello, <jsp:getProperty name="user" property="name" />
            <c:choose>
                <c:when test="${user.loggedIn}">
                <a href="LogoutServlet" target="_self">(Logout)</a>
                </c:when>
                <c:otherwise>
                <a href="Login.jsp" target="_self">(Login)</a>
                </c:otherwise>
            </c:choose>
        </h3>
    </body>
</html>