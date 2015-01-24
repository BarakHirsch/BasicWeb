<%@page import="enums.Difficulty"%>
<%@page import="enums.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Start game</title>


        <style>
            h2 {color:black;font: 30px  verdana, arial, helvetica;}
            body {color:black;font: 20px  verdana, arial, helvetica;}
        </style>
    </head>
    <body>
        <form action="StartGameServlet" method="POST">
            <div id="main_container">
                <%
                    for (Category cat : Category.values()) {
                        if (cat == Category.None) {
                            continue;
                        }
                %>
                <div class="main_content">
                    <h2>"<%=cat%></h2>
                    <img src="Images/<%=cat%>.jpg" alt="" title="" class="left_img">
                    <br>
                    <h4>Please choose difficulty for your questions:</h4>
                    <h5><input type="radio" name="radioButton<%=cat%>" value="<%=Difficulty.Easy.ordinal()%>" checked>Easy</h5>
                    <h5><input type="radio" name="radioButton<%=cat%>" value="<%=Difficulty.Medium.ordinal()%>">Medium</h5>
                    <h5><input type="radio" name="radioButton<%=cat%>" value="<%=Difficulty.Hard.ordinal()%>">Hard</h5>

                    <div align="center">
                        <input type="checkbox" name="checkBox<%=cat%>" value="<%=cat%>Check">Check if you like to play in this category<br>
                    </div>
                </div>
                <hr>

                <%
                    }
                %>
                <div align="center">
                    <input type="submit" value="Submit">
                </div>
            </div>
        </form>
    </body>
</html>
