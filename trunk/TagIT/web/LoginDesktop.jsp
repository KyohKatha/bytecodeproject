<%-- 
    Document   : LoginDesktop
    Created on : 20/10/2010, 22:23:53
    Author     : Tiago
--%>

<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
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
        <%/*
        String token = (String) session.getAttribute("token");
        String verifier = (String) session.getAttribute("verifier");
        String tag = (String) session.getAttribute("numeroTag");

        PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(getServletContext().getRealPath(tag + ".txt"))));
        out1.println(token);
        out1.println(verifier);
        out1.close();
        */

        out.println((String) session.getAttribute("sucesso"));


        %>
        <a href="http://localhost:8080/TagIT/<%//=tag%>.txt">download</a>
    </body>
</html>
