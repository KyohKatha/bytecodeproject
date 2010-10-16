<%-- 
    Document   : index
    Created on : 30/09/2010, 12:09:01
    Author     : 317624
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Participante" %>
<%
 Participante usuarioLogado = (Participante) request.getSession().getAttribute("usuarioLogado");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chamadas das funcionalidades</title>
    </head>
    <body>
        <%
        if(usuarioLogado != null) {
            %>
            <h3>Seja bem-vindo <%= usuarioLogado.getNome() %></h3>
            <p>Você é um: <%
            if(usuarioLogado.getClass().equals(Participante.class)) {
                out.println("Participante");
            }
            else {
                out.println("Organizador");
            } %></p>
            <p><a href="ManutencaoUsuarios?tipo=4">Logoff</a></p>
            <%
        }

        %>
        <h3><a href="CadastrarUsuario.jsp">Cadastro de usuários</a></h3>
        <h3><a href="AlterarUsuario.jsp">Alterar informações de usuários</a></h3>
        <h3><a href="CadastrarEvento.jsp">CadastrarEvento</a></h3>
        <h3><a href="ManutencaoEventos?tipo=4">PegaEventosDoOrganizador</a></h3>
        <h3><a href="Login.jsp">Login</a></h3>
    </body>
</html>
