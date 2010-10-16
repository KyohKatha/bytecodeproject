<%--
    Document   : CadastroEvento
    Created on : 30/09/2010, 09:24:07
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Participante" %>
<%

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Evento - TagiT!</title>
        <script type="text/javascript" src="funcoes.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>

        <div class="meioContainer">
            <div class = "cadEvento">
                <div class="erros" id="erros">
                    <fieldset>
                    </fieldset>
                </div>
                <form action="ManutencaoEventos" class="formBusca" id="formBusca" method="post" onsubmit="">
                    <table>
                        <tr>
                            <td><label>Busca:</label></td>
                            <td><input type="text" name="parametro" id="parametro" size="50" /></td>
                            <td><input class="botao" type="submit" value="Buscar" /></td>
                        </tr>
                    </table>
                </form>
                <% //Exibir os resultados %>
            </div>
        </div>
    </body>
</html>
