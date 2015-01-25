<%@page import="models.MultipleChoiceQuestion"%>
<%@page import="models.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Play question</title>

        <style>
            h2 {color:black;font: 30px  verdana, arial, helvetica;}
            h3 {color:black;font: 20px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>
        <%
            Object wasCorrect = request.getAttribute("wasCorrect");

            if (wasCorrect != null) {
                if ((Boolean) wasCorrect) {
        %>
        <h2>The answer was correct!</h2>
        <%
        } else {
        %>
        <h2>The answer was wrong!</h2>
        <%
                }
            }
            
            Question que = (Question) request.getAttribute("currQuestion");
        %>
        <h3> <%=que.getQuestionText()%></h3>
        <form action="PlayServlet" method="POST">
            <%
                if (que instanceof MultipleChoiceQuestion) {
                    MultipleChoiceQuestion multi = (MultipleChoiceQuestion) que;
                    for (String option : multi.getOptions()) {
            %>
            <h4> <input type="radio" name="answer" value="<%=option%>" required><%=option%></h4>
                <%
                    }

                } else {
                %>
            <input type="text" name="answer" /> 
            <%
                }
            %>
            <br>
            <input type="submit" value="Submit"> 

        </form>
        <br>
        <br>

        <form action="GameEndedServlet">
            <input type="submit" value="Quit">
        </form>

    </body>
</html>

