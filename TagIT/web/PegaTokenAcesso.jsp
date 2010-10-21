<%-- 
    Document   : PegaTokenAcesso
    Created on : 12/10/2010, 17:24:27
    Author     : Mariana

    Essa página serve somente para guardar o código de autorização do usuário na sessão
    Depois é redirecionado para a Servlet Facebook
--%>

<%@page import="aaTag.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URL"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.InputStreamReader" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <% request.getSession().setAttribute("codigo", request.getQueryString()); %>
        
        <form action="Facebook" method="post" id="form"></form>
        <script type="text/javascript">
            document.getElementById("form").submit();
        </script>

    </body>
</html>
