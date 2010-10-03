<%-- 
    Document   : removerUsuario
    Created on : 03/10/2010, 18:14:25
    Author     : Kaori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancelar Conta</title>
    </head>
    <body>
        <p>Nós vamos sentir sua falta :'(</p>
        <br/>
        <p>Você tem certeza disso? :'(((((   Se tiver, coloque sua senha no campo abaixo: </p>
        <form action="ManutencaoUsuarios" method="post">
            <p><input type="password" name="senha" id="senha" maxlength="30"/>
                <input type="submit" value="Confirmar"/></p>

            <input type="hidden" name="tipo" value="2">

        </form>
    </body>
</html>
