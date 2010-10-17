<%--
    Document   : CadastroEvento
    Created on : 30/09/2010, 09:24:07
    Author     : Tiago
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="PkgTagIT.Evento"%>
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

            <div class = "cadEvento">
                <div class="erros" id="erros">
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
                <% ArrayList<Evento> eventos = (ArrayList<Evento>) request.getAttribute("eventos");
                   String parametro = (String) request.getAttribute("parametro");
                   request.getSession().setAttribute("eventos", eventos);

                   if(eventos == null){
                        out.println("Nenhum resultado encontrado para <strong>" + parametro + "</strong>");
                    }else{
                       int i = 0;
                       while(i < eventos.size()){%>
                            <a href="IncricaoEvento.jsp" onclick="callServlet('IncricaoEvento?tipo=0&i=' + <%=i %> + '','iframe')"><%= eventos.get(i).getNome() %></a>
                            <% i++;
                       }
                    }
                %>
            </div>
    </body>
</html>
