
<%@page import="java.util.Set"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="logic.TriviaGame"%>
<%@page import="helpers.UserHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.Category"%>
<%@page import="logic.Manager"%>
<%@page import="models.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete question</title>
        <style>
            h2 {color:black;font: 22px  verdana, arial, helvetica;}
            h3 {color:black;font: 14px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>

        <h2>Click Delete for each question you would like to delete:</h2>
        <jsp:useBean id="questionsAttribute" type="Set<Question>" scope="request" />
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${questionsAttribute}" var="que" >
            <form action="DeleteQuestionServlet" method="POST">
            <h3>Category: ${que.category}. Difficulty: ${que.difficulty}. </h3>
            <h3> ${que.questionText} </h3>
            <input type="submit" value="Delete">
            <c:set var="count" value="${count + 1}" scope="page"/>            
            <input type="hidden" name="QuestionIndex" value="${count}">
            </form>
        </c:forEach>
            
    </body>
</html>
