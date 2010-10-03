<%-- 
    Document   : confirmacaoCadastroParticipante
    Created on : 30/09/2010, 11:10:54
    Author     : 317586
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="PkgTagIT.Participante" %>

<% Participante p;

    p = (Participante) request.getAttribute("part");

    if ( p != null ) {
   %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmação do Cadastro do Participante [PARA TESTEEEES!!!]</title>
    </head>
    <body>
        <p>E-mail: <%= p.getEmail() %> </p>
        <p>Nome: <%= p.getNome() %> </p>
        <p>CPF: <%= p.getCpf() %> </p>
        <p>Senha: <%= p.getSenha() %> </p>
    </body>
</html>

<% } %>
