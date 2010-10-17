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
        
        Participante usuarioLogado = new Participante();
        //apenas para teste
        usuarioLogado.setEmail("a.nunc@aeneansed.org");
        request.getSession().setAttribute("usuarioLogado", usuarioLogado);
           //if (usuarioLogado == null) {
              //  out.println("Realize o login para se inscrever em um evento");
            //} else {

        Evento evento = (Evento) request.getSession().getAttribute("evento");
        //apenas para teste
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
            <div class = "cadEvento">
                <div class="erros" id="erros">
                    <%
                            String message, type;
                            message = (String) session.getAttribute("message");
                            type = (String) session.getAttribute("type");
                            session.removeAttribute("message");
                            session.removeAttribute("type");

                            if (message != null) {
                                out.println("<fieldset class=\"" + type + "\" onclick=\"closeMessageBox()\">");
                                String legend = "Undefined";
                                if (type == "information") {
                                    legend = "Information";
                                } else if (type == "critical") {
                                    legend = "Error";
                                } else if (type == "success") {
                                    legend = "Success";
                                } else if (type == "warning") {
                                    legend = "Warning";
                                }

                                out.println("<legend>" + legend + "</legend>");
                                out.println(message);
                                out.println("</fieldset>");
                            } else {
                                out.println("<fieldset class=\"information\" onclick=\"closeMessageBox()\">");
                                out.println("<legend>Informação</legend>");
                                out.println("<p>- Clique no botão Inscreva-se para se inscrever no evento");
                                out.println("</fieldset>");
                            }

                %>

                </div>
        <form action="IncricaoEvento" class="formInscricao" id="formInscricao" method="post">
        <table>
            <tr>
                <td>Título: <%=evento.getNome()%></td>
                <td><input type="hidden" value="1" name ="tipo" /></td>
            
            </tr>
            <tr>
                <td><input action="IncricaoEvento" class="botao" type="submit" value="Inscrever"/></td>
            </tr>
        </table>
                <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </form>
            </div>
                <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </body>
</html>
<%} 
//}%>