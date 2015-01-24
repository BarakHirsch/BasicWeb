
<%@page import="models.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Question added</title>

        <style>
            h2 {color:black;font: 30px  verdana, arial, helvetica;}
            h3 {color:black;font: 20px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>
        <h2>The question you entered was added successfully.</h2> 
        <jsp:useBean id="newQuestion" type="models.Question" scope="request" />
        <h3><jsp:getProperty name="newQuestion" property="questionText" /></h3>
        <h3>Category: <jsp:getProperty name="newQuestion" property="category" /> Difficulty: <jsp:getProperty name="newQuestion" property="difficulty" /></h3>
    </body>
</html>

