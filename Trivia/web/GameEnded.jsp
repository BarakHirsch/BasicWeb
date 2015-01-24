<%-- 
    Document   : GameEnded
    Created on : Jan 24, 2015, 12:01:36 PM
    Author     : Barak
--%>

<%@page import="logic.TriviaGame"%>
<%@page import="helpers.UserHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Game ended</title>
        <style>
            h1 {color:black;font: 40px  verdana, arial, helvetica;}
            h3 {color:black;font: 20px  verdana, arial, helvetica;}
            h4 {color:black;font: 18px  verdana, arial, helvetica;}
            h5 {color:red;font: 18px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>

        <h1>Game Over!</h1>
        <%
            TriviaGame currentGame = (TriviaGame) request.getAttribute("currGame");

            HashMap<Category, Integer> askedCount = currentGame.getAskedCount();
            HashMap<Category, Integer> answersCount = currentGame.getAnswersCount();

            for (Category category : currentGame.getCategories()) {
        %>
        <h3>In <%=category%> you were asked <%=askedCount.get(category)%> questions and answered <%=answersCount.get(category)%> correctly.</h3>
        <%
            }
        %>
        <h4>Thank you for playing, <%=UserHelper.getUser(request).getName()%></h4>

    </body>
</html>