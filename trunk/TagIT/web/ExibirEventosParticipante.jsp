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
        <title>TagiT! - Minhas Inscrições</title>
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

                        <tr><td><input type="button" class="link" onclick="selecao('formLogado', 'ManutencaoUsuarios', 'tipo', 3)" value="Minhas Inscrições" /></td></tr>
                        <tr><td><input type="button" class="link" onclick="selecao('formLogado', 'ManutencaoEventos', 'tipo', 7)" value="Meus Eventos" /></td></tr>
                        <tr><td><a href="https://graph.facebook.com/oauth/authorize?client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp">Acesse o Facebook</a></td></tr>
                        <tr><td><a href="http://localhost:8080/TagIT/CadastrarEvento.jsp">Cadastrar Eventos</a></td></tr>
                        <tr><td><input type="button" class="link" onclick="selecao('formLogado', 'ManutencaoUsuarios', 'tipo', 4)" value="Logoff" /></td></tr>

                        <input type="hidden" id="tipo" name="tipo" value=""/>                    </form>

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
                <div class = "minhasInscricoes">
                    <form action="ManutencaoEventos" class="formBusca" id="formBusca" method="post" onsubmit="">
                        <table>
                            <tr>
                                <td><label>Busca:</label></td>
                                <td><input type="text" name="parametro" id="parametro" size="50" /></td>
                                <td><input class="botao" type="submit" value="Buscar" /></td>
                                <td><input type="hidden" name="tipo" value="1"></td>
                            </tr>
                        </table>
                    </form>
                    <div class="erros" id="erros">
                    </div>
                    <h3 style="margin-left: -600px">Eventos em que você está inscrito:</h3>
                    <table class="tabelaEventos">

                        <%
                                    ArrayList<Evento> eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventos");


                                    if (eventos == null) {
                                        out.println("<p>Você não está inscrito em nenhum evento</p>");
                                    } else {
                                        out.println("<tr>");
                                        int i = 0;
                                        while (i < eventos.size()) {%>
                        <tr><td>
                                <form id="formEventos" action="" method="POST">
                                    <input type="button" value="<%= eventos.get(i).getNome()%> (<%=eventos.get(i).getDataEvento()%>)" onclick="selecao('formEventos', 'ManutencaoEventos', 'i', <%=i%>)" />
                                    <input type="hidden" name="tipo" value="5" />
                                    <input type="hidden" name="insc" value="0" />
                                    <input type="hidden" name="i" id="i" value="" />
                                </form>
                            </td></tr>
                            <% i++;
                                            }
                                            out.println("</tr>");
                                        }
                            %>
                    </table>
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
