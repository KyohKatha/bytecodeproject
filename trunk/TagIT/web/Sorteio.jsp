<%--
    Document   : Confirmacao
    Created on : 21/11/2010, 17:52:06
    Author     : Kaori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% String email = (String) request.getAttribute("email");
   String evento = (String) request.getAttribute("evento"); %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmação</title>
    </head>
    <body>
        <p>Obrigado por clicar no link de <%=email%>!</p>
        <p>Agora seu amigo(a) tem mais chances de ganhar o sorteio do evento <%=evento%>!</p>
    </body>
</html>
