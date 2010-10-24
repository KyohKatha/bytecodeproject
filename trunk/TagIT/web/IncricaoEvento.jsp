<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page import="aaTag.Event"%>
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

            String insc = (String) request.getAttribute("ins");
%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Inscrição</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" href="menu/menu_style.css" type="text/css" />

    </head>
    <body>
        <div class="topo">
            <div class="logo"></div>
            <table class="login">
                <%if (usuarioLogado != null) {%>
                <tr>
                    <td><label> Bem-vindo <%= usuarioLogado.getNome()%> </label></td>
                </tr>

                <form action="" method="POST" id="formLogado">
                    <tr><td><a href="https://graph.facebook.com/oauth/authorize?client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp">Acesse o Facebook</a></td></tr>
                    <tr><td><a href="ManutencaoUsuarios?tipo=4">Logoff</a></td></tr>

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
        <div style="margin-top:-44px; position: relative;">
            <ul id="menu">
                <li style="margin-left:150px"><a href="index.jsp" target="_self" title="Principal">Principal</a></li>
                <%if (usuarioLogado != null) {%>
                <li><a href="ManutencaoUsuarios?tipo=3" target="_self" title="Minhas Inscrições">Minhas Inscrições</a></li>
                <li><a href="ManutencaoEventos?tipo=7" target="_self" title="Meus Eventos">Meus Eventos</a></li>
                <li><a href="http://localhost:8080/TagIT/CadastrarEvento.jsp" target="_self" title="Cadastrar Evento">Cadastrar Evento</a></li>

                <%} else {%>
                <li><a href="CadastrarUsuario.jsp" target="_self" title="Registre-se">Registre-se</a></li>
                <%}%>
                <li><a href="http://www.bytecodeufscar.blogspot.com" target="_blank" title="Blog">Blog</a></li>
                <li><a href="Faq.jsp" target="_self" title="FAQ">FAQ</a></li>
                <li><a href="Sobre.jsp" target="_self" title="Sobre">Sobre</a></li>
            </ul>
        </div>
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
                                    Event eventoApi = (Event) request.getSession().getAttribute("eventoAPI");
                                    Evento evento = null;

                                    String modo = (String) request.getAttribute("modo");

                                    if ( modo.compareTo("busca") == 0 ){
                                        evento = (Evento) request.getSession().getAttribute("eventoBusca");
                                    } else {
                                        evento = (Evento) request.getSession().getAttribute("eventoInscrito");
                                    }
            
                                    if (evento == null) {
                                        out.println("<fieldset class=\"critical\" >");
                                        out.println("<legend>Erro</legend>");
                                        out.println("<p>- Falha ao carregar evento. Por favor, tente novamente.</p>");
                                        out.println("</fieldset>");
                                    } else {

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
                                    }
                        %>

                    </div>
                    <%if (evento != null) {%>
                    <form action="ManutencaoEventos" class="formInscricao" id="formInscricao" method="post">
                        <fieldset style="text-align: center; ">
                            <legend>Dados do evento</legend>
                            <table style="margin-left: 270px; font-size: 12px; text-transform:none;">
                            <tr>
                                <td><label>Nome:</label></td><td> <%=evento.getNome()%></td>
                            </tr>
                            <tr>
                                <%
                                if(!eventoApi.getDescription().isEmpty()){%>
                                <td><label>Descrição:</label></td><td> <%=eventoApi.getDescription()%></td>
                                <%}%>
                            </tr>
                            <tr>
                                <td><label>Número de vagas:</label></td><td> <%=(int)evento.getVagasPrincipal()%></td>
                            </tr><tr>
                                <td><label>Data de início de inscrição:</label></td><td> <%=evento.getInscInicio().replace("-", "/") %></td>
                            </tr><tr>
                                <td><label>Data de término de inscrição:</label> </td><td><%=evento.getInscTermino().replace("-", "/")%></td>
                            </tr><tr>
                                <td><label>Rua:</label></td><td> <%=evento.getRua()%></td>
                            </tr><tr>
                                <td><label>Cidade:</label> </td><td><%=evento.getCidade()%></td>
                            </tr><tr>
                                <td><label>Data do evento:</label> </td><td><%=evento.getDataEvento().replace("-", "/")%></td>
                            </tr><tr>
                                <td><label>Contato: </label> </td><td><%=evento.getContato()%></td>
                            </tr>
                            
                            <tr>
                                <td>
                                </td>
                            </tr>

                        </table>
                            </fieldset>
                            <%
                                        System.out.println(insc);
                            if (usuarioLogado != null && insc.compareTo("0") != 0) {%>
                            <input style="left:-280px; position: relative;" class="botao" type="submit" value="Inscreva-se!"/>
                            <%}%>
                        <input type="hidden" value="6" name ="tipo" size="0" />
                        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        
                    </form><%}%>
                </div>

                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <!-- Fim DIV CONTEUDO -->
        </div>
        <div class="rodape"><BR /><br />
            <a href="http://bytecodeufscar.blogspot.com/"> ByteCode</a> - <a href="http://www.aatag.com/aatag/">aaTag</a> - <a href="http://bytecodeufscar.blogspot.com/p/fale-conosco.html">Contato</a>
            <br />
            <hr />
            Todos os direitos reservados<br />Desenvolvido por <a href="http://www.bytecodeufscar.blogspot.com">ByteCode</a>
        </div>

    </body>
</html>
