<%-- 
    Document   : Interesses
    Created on : 14/10/2010, 22:03:37
    Author     : Mariana
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Servlet.Likes" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        
        <%
        try{
        ArrayList<Likes> lLikes = new ArrayList<Likes>();
        lLikes = (ArrayList<Likes>)request.getSession().getAttribute("lLikes");
        for (int i = 0; i < lLikes.size(); i++)
        {
        %>
        <p><b>Nome: </b><%=lLikes.get(i).getName() %></p>
        <p><b>Categoria:</b><%=lLikes.get(i).getCategory() %></p>
        <br/>
        <%
        }
        }
        catch(Exception e){}
        %>
    </body>
</html>
