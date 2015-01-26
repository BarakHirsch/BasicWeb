<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="logic.TriviaGame"%>
<%@page import="helpers.UserHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Game end</title>
        <style>
            h1 {color:black;font: 40px  verdana, arial, helvetica;}
            h3 {color:black;font: 20px  verdana, arial, helvetica;}
            h4 {color:black;font: 18px  verdana, arial, helvetica;}
            h5 {color:red;font: 18px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>

        <h1>Game Over!</h1>
        
        <jsp:useBean id="currGame" type="logic.TriviaGame" scope="request" />
        
        <c:forEach items="${currGame.categories}" var="category" >
            <h3>In ${category} you were asked ${currGame.askedCount.get(category)} questions and answered ${currGame.answersCount.get(category)} correctly.</h3>
        </c:forEach>
       
        <h4>Thank you for playing, <%=UserHelper.getUser(request).getName()%></h4>

    </body>
</html>