<%--
    Document   : UpgradeOrganizador
    Created on : 03/10/2010, 18:14:25
    Author     : Kaori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="PkgTagIT.Participante" %>

<% Participante p = (Participante) session.getAttribute("part");
   /*  Participante p = new Participante("aa@bb.cc", "aaa", "######", "38197921806");
     request.getSession().setAttribute("part", p);*/
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upgrade</title>
    </head>
    <body>
        <p>Gostaria de utilizar nossos serviços para criar um evento?</p>
        <p>Solicite agora mesmo um upgrade da sua conta de participante, para
         ter todos os privilégios de um organizador! Basta informar sua senha
         no campo abaixo.</p>
        <form action="ManutencaoUsuarios" method="post">
            <p><input type="password" name="senha" id="senha" maxlength="30"/>
                <input type="submit" value="Confirmar"/></p>

            <input type="hidden" name="tipo" value="3">

        </form>

        <p>Você já tentou <%//= p.getTentivasUpgrade() %> vezes.</p>
    </body>
</html>
