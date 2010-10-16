<%--
    Document   : AlterarUsuario
    Created on : 30/09/2010, 09:23:25
    Author     : 317586
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="PkgTagIT.Participante" %>

<% Participante p = (Participante) session.getAttribute("usuarioLogado");
   /*  Participante p = new Participante("aa@bb.cc", "aaa", "######", "38197921806");
     request.getSession().setAttribute("part", p);*/
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de Usuário</title>
        <script type="text/javascript" src="funcoes.js"></script>
    </head>
    <body>
        <form action="ManutencaoUsuarios" onsubmit="return validarAlteracaoUsuario()">
            <p>E-mail: <input type ="text" name="email" id="email" maxlength="100" disabled="disable" value="<%= p.getEmail() %>"/></p>
            <p>Nome: <input type ="text" name="nome" id="nome" maxlength="50" value="<%= p.getNome() %>" /></p>
            <p>CPF: <input type ="text" name="cpf" id="cpf" maxlength="11" value="<%= p.getCpf() %>"/></p>
            <p>Senha Atual: <input type ="password" name="atual" id="atual" maxlength="30"/></p>
            <p>Nova Senha: <input type ="password" name="senha" id="senha" maxlength="30"/></p>
            <p>Confirmar Senha: <input type ="password" id="confirmacao" maxlength="30"/></p>
            <p><input type="submit" value="Alterar"/>
                <input type="reset" value="Limpar Campos"/></p>

            <input type="hidden" name="tipo" value="1">
        </form>
    </body>
</html>