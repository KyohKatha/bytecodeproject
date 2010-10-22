<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page import="PkgTagIT.Evento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="aaTag.User" %>

<%

            User usuarioLogado = (User) request.getSession().getAttribute("usuario");
            //Para teste
            //usuarioLogado = new User();

%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Meus Eventos</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="topo">
            <div class="logo"></div>
            <div class="login">
<table>
                    <%if (usuarioLogado != null) {%>
                    <tr>
                        <td><label> Bem-vindo <%= usuarioLogado.getNome()%> </label></td>
                    </tr>

                    <form action="" method="POST" id="formLogado">
                        <tr><td><input type="submit" class="link" onclick="selecao('ManutencaoUsuarios', 3)" value="Minhas Inscrições" /></td></tr>
                        <tr><td><input type="submit" class="link" onclick="selecao('ManutencaoEventos', 7)" value="Meus Eventos" /></td></tr>
                        <tr><td><a href="https://graph.facebook.com/oauth/authorize?client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp">Acesse o Facebook</a></td></tr>
                        <tr><td><input type="submit" class="link" onclick="selecao('ManutencaoUsuarios', 4)" value="Logoff" /></td></tr>

                        <input type="hidden" id="tipo" name="tipo" value=""/>
                    </form>

                    <%} else {%>
                    <tr>

                        <td>
                            <form id="eLogin" action="Login.jsp" >
                                <p align="center">
                                    <input class="botao" type="submit" value="Logar-se" id="efetuarLogin" name="efetuarLogin"/>
                                </p>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
                
            </div>
        </div>
        <div class="menuTopo">
            <div class="menu">
                <ul>
                    <li> <a href="index.jsp"> Principal </a><li>
                    <li> <a href="http://www.bytecodeufscar.blogspot.com" target="_blank"> Blog </a><li>
                        <%if (usuarioLogado == null) {%>
                    <li> <a href="CadastrarUsuario.jsp"> Registrar-se </a></li>
                    <%}%>
                    <li> <a href="Faq.jsp"> Faq </a></li>
                    <li> <a href="Sobre.jsp"> Sobre </a></li>
                </ul>
            </div></div>

        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
<div class = "meusEventos">
                <div class="erros" id="erros">
                </div>
                
                <% ArrayList<Evento> eventos = (ArrayList<Evento>) request.getAttribute("eventos");
                   String parametro = (String) request.getAttribute("parametro");
                   request.getSession().setAttribute("eventos", eventos);

                   if(eventos == null){
                        out.println("Você não tem nenhum evento");
                    }else{
                       out.println("<ul>");
                       int i = 0;
                       while(i < eventos.size()){ //mudar a chamada para alterar o evento%>
                           <li><a href="AlterarEvento.jsp" onclick="callServlet('AlterarEvento?tipo=0&i=' + <%=i %> + '','body')"><%= eventos.get(i).getNome() %></a></li>
                            <% i++;
                       }
                       out.println("</ul>");
                    }

                %>
            </div>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <div class="rodape"><BR /><br />
                ByteCode - Ajuda - Tecnologia RFID - Contato
                <br />
                <hr />
                Todos os direitos reservados<br />
			Desenvolvidos por <a href="www.bytecodeufscar.blogspot.com">ByteCode</a></div>
            <!-- Fim DIV CONTEUDO -->
        </div>
    </body>
</html>
