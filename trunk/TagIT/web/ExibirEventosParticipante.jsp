<%-- 
    Document   : ExibirEventosParticipante
    Created on : 17/10/2010, 19:06:44
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
        <title>JSP Page</title>
        
        <script type="text/javascript" src="funcoes.js"></script>
    </head>
    <body>
                   <div class = "cadEvento">
                <div class="erros" id="erros">
                </div>

                <% ArrayList<Evento> eventos = (ArrayList<Evento>) request.getAttribute("eventos");
                   
                   request.getSession().setAttribute("eventos", eventos);

                   if(eventos == null){
                        out.println("Nenhum resultado encontrado para <strong></strong>");
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
