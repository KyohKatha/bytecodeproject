<%-- 
    Document   : ExibirMeusEventos
    Created on : 17/10/2010, 19:16:51
    Author     : Kaori
--%>

<%@page import="PkgTagIT.Evento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meus Eventos</title>
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
                        out.println("Você não tem nenhum evento");
                    }else{
                       int i = 0;
                       while(i < eventos.size()){ //mudar a chamada para alterar o evento%>
                            <a href="IncricaoEvento.jsp" onclick="callServlet('IncricaoEvento?tipo=0&i=' + <%=i %> + '','iframe')"><%= eventos.get(i).getNome() %></a>
                            <% i++;
                       }
                    }
                %>
            </div>
    </body>
</html>
