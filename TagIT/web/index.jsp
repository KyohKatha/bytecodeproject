<%-- 
    Document   : index
    Created on : 07/10/2010, 19:31:13
    Author     : Mariana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% if (session.getAttribute("code") != null)
        {%>
        <h1><%= session.getAttribute("code") %></h1>
        <%}%>
        <a href="https://graph.facebook.com/oauth/authorize?client_id=153940577969437&redirect_uri=http://localhost:8080/Teste/PegaTokenAcesso.jsp">clique</a>
    </body>
</html>
