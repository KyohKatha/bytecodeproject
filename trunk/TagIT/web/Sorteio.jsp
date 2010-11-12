<%--
    Document   : Sorteio
    Created on : 28/10/2010, 21:20:56
    Author     : Time Alfa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<% String s = (String) request.getAttribute("shortenLink");
   Long l = (Long) request.getAttribute("clicks");  %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Sortear" method="POST">
            <p><input type="text" name="link" />
                <input type="submit" value="Gerar Link"/>
                <input type="hidden" value="1" name="tipo" id="tipo"/></p>
        </form>
        <p>Link:
        <% if (s != null) {%>
            <%= s%>
        <% }%> </p>
        <p>Clicks para este link:
        <% if (l != null) {%>
            <%= l%>
        <% }%> </p>

        <form action="Sortear" method="POST">
            <p><input type="text" name="link" />
                <input type="submit" value="Sortear"/>
                <input type="hidden" value="2" name="tipo" id="tipo"/></p>
        </form>

    </body>
</html>
