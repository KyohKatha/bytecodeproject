<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page import="PkgTagIT.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="aaTag.User" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscrever em Evento - TagiT!</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>


<%

            User usuarioLogado = (User) request.getSession().getAttribute("usuario");
            //Para teste
            //usuarioLogado = new User();

            String insc = (String) request.getSession().getAttribute("ins");
            

%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Inscrição</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="topo">
            <div class="logo"></div>
            <div class="login">

                <form id="eLogin" action="Login.jsp" >
                    <table>
                        <%if (usuarioLogado != null) {%>
                        <tr>
                            <td><label> Bem-vindo <%= usuarioLogado.getNome()%> </label></td>
                        </tr>

                        <tr><td><a href="ExibirEventosParticipante.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=3','body')">Minhas inscrições</a></td></tr>
                        <tr><td><a href="ExibirMeusEventos.jsp" onclick="callServlet('','body')">Meus Eventos</a></td></tr>
                        <tr><td><a href="index.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=4','body')" >Logoff</a></td></tr>
                        <%} else {%>
                        <tr>
                            <td><p align="center">
                                    <input class="botao" type="submit" value="Logar-se" id="efetuarLogin" name="efetuarLogin"/>
                                </p>
                            </td>
                        </tr>
                        <%}%>
                    </table>

                </form>
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
                <div class = "inscricao">
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
                        <%
                                    Evento evento = (Evento) request.getSession().getAttribute("evento");
                                    /*if (evento == null) {
                                        out.println("<fieldset class=\"critical\" >");
                                        out.println("<legend>Erro</legend>");
                                        out.println("<p>- Falha ao carregar evento. Por favor, tente novamente.</p>");
                                        out.println("</fieldset>");
                                    } else {*/

                                        String message, type;
                                        message = (String) session.getAttribute("message");
                                        type = (String) session.getAttribute("type");
                                        session.removeAttribute("message");
                                        session.removeAttribute("type");

                                        if (message != null) {
                                            out.println("<fieldset class=\"" + type + "\" onclick=\"fecharCaixaMensagem()\">");
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
                                        } else if (usuarioLogado != null) {
                                            if (insc.compareTo("0") != 0){
                                                out.println("<fieldset class=\"information\" onclick=\"fecharCaixaMensagem()\">");
                                                out.println("<legend>Informação</legend>");
                                                out.println("<p>- Clique no botão Inscreva-se para se inscrever no evento</p>");
                                                out.println("<p>- Clique na caixa para fechá-la.</p>");
                                                out.println("</fieldset>");
                                            }
                                        } else {
                                            out.println("<fieldset class=\"warning\" onclick=\"fecharCaixaMensagem()\">");
                                            out.println("<legend>Aviso</legend>");
                                            out.println("<p>- Você só pode se inscrever em um evento quando estiver logado.</p>");
                                            out.println("<p>- Clique na caixa para fechá-la.</p>");
                                            out.println("</fieldset>");
                                        }
                                   // }
                        %>

                    </div>
                    <%if (evento != null) {%>
                    <form action="ManutencaoEventos" class="formInscricao" id="formInscricao" method="post">
                        <table>
                            <tr>
                                <td><label>Nome:</label></td><td> <%=evento.getNome()%></td>
                            </tr>
                            <tr>
                                <td><label>Número de vagas:</label></td><td> <%=evento.getVagasPrincipal()%></td>
                            </tr><tr>
                                <td><label>Data de início de inscrição:</label></td><td> <%=evento.getInscInicio()%></td>
                            </tr><tr>
                                <td><label>Data de término de inscrição:</label> </td><td><%=evento.getInscTermino()%></td>
                            </tr><tr>
                                <td><label>Rua:</label></td><td> <%=evento.getRua()%></td>
                            </tr><tr>
                                <td><label>Cidade:</label> </td><td><%=evento.getCidade()%></td>
                            </tr><tr>
                                <td><label>Data do evento:</label> </td><td><%=evento.getDataEvento()%></td>
                            </tr><tr>
                                <td><label>Contato: </label> </td><td><%=evento.getContato()%></td>
                            </tr>
                            <%if (usuarioLogado != null && insc.compareTo("0") != 0) {%>
                            <tr>
                                <td><input class="botao" type="submit" value="Inscrever"/>
                                </td>
                            </tr><%}%>

                        </table>
                        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        <input type="hidden" value="6" name ="tipo" size="0" />
                    </form><%}%>
                </div>

                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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
