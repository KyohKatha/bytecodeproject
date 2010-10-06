<%-- 
    Document   : Login
    Created on : 05/10/2010, 12:07:21
    Author     : Valter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="funcoes.js"></script>
    </head>
    <body>
        <!-- <form action="ManutencaoUsuarios" id="formLogin" method="post"> -->
        <form action="ManutencaoUsuarios" method="post" onsubmit="return validarLogin()">
            <div>
                <p>E-mail:<input type="text" name="email" id="email" /></p>
                <p>Senha:<input type="text" name="senha" id="senha" /></p>

                <p><input type="submit" value="Acessar"/>
                <input type="hidden" name="tipo" value="3"></p>

            </div>
        </form>
    </body>
</html>
