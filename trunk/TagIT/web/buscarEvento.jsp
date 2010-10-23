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
            String parametro = (String) request.getAttribute("parametro");
            
            //Para teste
            //usuarioLogado = new User();

%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Busca de Evento</title>
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
                <div class = "buscaNormal">
                    <div class="erros" id="erros">
                        <%if(usuarioLogado == null){%>
                        <fieldset class="warning" onclick="fecharCaixaMensagem()">
                            <legend>Aviso</legend>
                            <p>- Você precisa estar logado para visualizar mais informações sobre os eventos!</p>
                            <p>- Clique na caixa para fechá-la.</p>
                        </fieldset>
                        <%}%>
                    </div>
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
                    <p style="margin-left: -600px">
                    <% ArrayList<Evento> eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventos");
                                if (eventos.isEmpty()) {
                                    out.println("Nenhum resultado encontrado para <strong>" + parametro + "</strong></p>");
                                    }else{
                    %>
                    Resultados para <strong><%=parametro%></strong>:</p>
                    <table class="tabelaEventos">
                        <%
                                int i = 0;
                                while (i < eventos.size()) {%>
                                <tr><td>
                                        <%if(usuarioLogado == null){%>
                                        <%= eventos.get(i).getNome()%>(<%=eventos.get(i).getDataEvento()%>)
                                        <%}else{%>
                                    <a href="ManutencaoEventos?tipo=5&insc=1&i=<%=i%>" > <%= eventos.get(i).getNome()%> </a>(<%=eventos.get(i).getDataEvento()%>)
                                    <%}%>
                            </td></tr>
                    <% i++;
                                    }
                                }
                    %>
                    </table>
                </div>
<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                <!-- Fim DIV MEIOCONTAINER -->
            </div>
                
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
