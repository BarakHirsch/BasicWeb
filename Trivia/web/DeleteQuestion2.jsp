
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

        <%
            Question[] questions = Manager.getInsance().getQuestions();

            for (int i = 0; i < questions.length; i++) {
                Question question = questions[i];
        %>

        <form action="DeleteQuestionServlet" method="POST">

            <h3>Category: <%=question.getCategory()%> Difficulty: <%=question.getDifficulty()%></h3>
            <h3><%=question.getQuestionText()%></h3>

            <input type="submit" value="Delete">

            <input type="hidden" name="QuestionIndex" value="<%=i%>">

        </form>
        <%
            }
        %>


    </body>
</html>
