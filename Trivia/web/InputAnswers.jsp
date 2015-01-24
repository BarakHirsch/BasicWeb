<%@page import="helpers.ParseHelper"%>
<%@page import="enums.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Input answers</title>
        <style>
            #h2_{font: 10px  verdana, arial, helvetica;}
        </style>

    </head>
    <body>
        <form action="AddQuestionServlet" method="Post">

            <div id="h2_">
                <%
                    Difficulty diff = ParseHelper.parseDifficulty(request.getParameter("radioButtonDiff"));
                    Category category = ParseHelper.parseCategory(request.getParameter("radioButtonCat"));
                    String questionText = request.getParameter("question");
                    String questionType = request.getParameter("radioButtonType");

                    if ("Multiple".equals(questionType)) {
                %>
                <h2>Please insert 3 optional answers for your question.</h2>
                <h2>Please pick the true answer with the radio button at the right.</h2>
                <h2>Answer 1: <input type="text" name="answer1" value=""> <input type="radio" name="radioButtonTrue" value="0" checked> </h2>
                <h2>Answer 2: <input type="text" name="answer2" value=""> <input type="radio" name="radioButtonTrue" value="1"> </h2>
                <h2>Answer 3: <input type="text" name="answer3" value=""> <input type="radio" name="radioButtonTrue" value="2"> </h2>
                    <%
                    } else if ("Open".equals(questionType)) {
                    %>
                <h2>Please insert the answer for your question</h2>
                <input type="text" name="answer" value=""><br>
                <%
                } else if ("YesNo".equals(questionType)) {
                %>
                <h2>Please insert the answer for your question</h2>
                <input type="radio" name="radioButtonYesNo" value="Yes" checked>Yes<br>
                <input type="radio" name="radioButtonYesNo" value="No" >No<br>

                <%
                    }
                %>

                <input type="hidden" name="questionText" value="<%=questionText%>">
                <input type="hidden" name="questionType" value="<%=questionType%>">
                <input type="hidden" name="difficulty" value="<%=diff.ordinal()%>">
                <input type="hidden" name="category" value="<%=category.ordinal()%>">
                <h2><input type="submit" name="submit" value="click here"></h2>
            </div>
        </form>
    </body>
</html>
