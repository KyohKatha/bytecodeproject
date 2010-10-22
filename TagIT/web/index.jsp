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
        <title>TagiT! - Principal</title>
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
                <%if (usuarioLogado == null) {%>
                <div class="publicidadeContainer">
                    <div class="publicidade">
                        <a href="CadastrarEvento.jsp"><img align="right" src="images/btProp.png" width="169" height="213" /></a>
                        <!-- Fim DIV PUBLICIDADE -->
                    </div>
                    <!-- Fim DIV PUBLICIDADECONTAINER --></div>
                    <%}%>
                <table width="928" height="674"><tr>
                        <td class="buscaPrincipal">

                            <!-- Fim DIV BUSCAPRINCIPAL -->
                        </td>

                        <td class="twitterContainer" >

                            <!-- Fim DIV TWITTERCONTAINER -->
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <form id="busca" name="busca" action="ManutencaoEventos">
                                <input name="parametro" type="text" id="parametro" value="Digite o termo da busca" /> <input class="botao" type="submit" value="Buscar" id="bBuscar" /><input type="hidden" name="tipo" value="1">
                            </form>
                            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        </td>
                        <td class="twitter">
                        </td>
                    </tr>
                    <%if (usuarioLogado == null) {%>
                    <tr>
                        <td class="eventoPrincipal" colspan="2">
                    </tr>
                    <tr>
                        <td class="eventoP1">
                            <form id="caixaEvento" action="#" >
                                <p id="titles"> Nome do Evento </p>
                                <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                                <input type="submit" name="participar" value="Participe!" />
                            </form>
                        </td>
                        <td class="eventoP2" align="center">
                            <form id="caixaEvento" action="#" >
                                <p id="titles"> Nome do Evento </p>
                                <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                                <input type="submit" name="participar" value="Participe!" />
                            </form>
                        </td>
                    </tr>
                    <% } else {%>
                    <tr>
                        <td class="eventoPrincipal" colspan="2">
                    </tr><tr>
                        <td class="eventoP1">
                            <form id="caixaEvento" action="#" >
                                <p id="titles"> Eventos do usuário </p>
                                <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                                <input type="submit" name="participar" value="Participe!" />
                            </form>
                        </td>
                        <td class="eventoP2" align="center">
                            <form id="caixaEvento" action="#" >
                                <p id="titles"> Eventos do usuário </p>
                                <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                                <input type="submit" name="participar" value="Participe!" />
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>

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
