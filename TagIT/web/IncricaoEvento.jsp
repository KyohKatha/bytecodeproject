<%-- 
    Document   : IncricaoEvento
    Created on : 16/10/2010, 15:23:34
    Author     : Mariana
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Evento" %>
<%@page import="PkgTagIT.Participante" %>
<%
        
        Participante usuarioLogado = (Participante) session.getAttribute("usuarioLogado");
            //if (usuarioLogado == null) {
              //  out.println("Realize o login para se inscrever em um evento");
            //} else {

        Evento evento = (Evento) session.getAttribute("evento");
        //apenas para teste
        
        evento = new Evento("Teste", 60, 60, "lalala", "lalala", "aaa", "aaa", "bbb", "bbb", usuarioLogado, new ArrayList());
        //fim teste
        if(evento != null){
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscrever em Evento - TagiT!</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="meioContainer">
            <div class = "cadEvento">
                <div class="erros" id="erros">
                    <fieldset class="information" onclick="fecharCaixaMensagem()">
                        <legend>Informação</legend>
                        <p>- Todos os campos com (*) são obrigatórios.</p>
                        <p>- Clique na caixa para fechá-la.</p>
                    </fieldset>
                </div>
        <form action="InscricaoEvento" class="formInscricao" id="formInscricao" method="post">
        <table>
            <tr>
                <td>Título: <%=evento.getNome()%></td>
            </tr>
            <tr>
                <td><input class="botao" type="submit" value="Inscrever"/></td>
            </tr>
        </table>
        </form>
            </div></div>
    </body>
</html>
<%}
//}%>