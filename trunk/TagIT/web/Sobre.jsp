<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


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
        <title>TagiT! - Sobre</title>
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
                <div class="sobre"></div>

                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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
