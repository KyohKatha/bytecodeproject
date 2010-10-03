<%-- 
    Document   : cadastroParticipante
    Created on : 30/09/2010, 09:23:25
    Author     : 317586
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Participantes</title>
        <script type="text/javascript" src="funcoes.js"></script>
    </head>
    <body>
        <form action="ManutencaoUsuarios" onsubmit="return validarUsuario()">
            <p>E-mail: <input type ="text" name="email" id="email" maxlength="100"/></p>
            <p>Nome: <input type ="text" name="nome" id="nome" maxlength="50"/></p>
            <p>CPF: <input type ="text" name="cpf" id="cpf"/></p>
            <p>Senha: <input type ="password" name="senha" id="senha" maxlength="30" /></p>
            <p>Confirmar Senha: <input type ="password" id="confirmacao" maxlength="30"/></p>
            <p><input type="submit" value="Cadastrar"/>
                <input type="reset" value="Limpar Campos"/></p>

            <input type="hidden" name="tipo" value="0">
        </form>
    </body>
</html>