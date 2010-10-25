<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page import="PkgTagIT.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="PkgTagIT.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="aaTag.User" %>

<%

            User usuarioLogado = (User) request.getSession().getAttribute("usuario");
            Evento evento = (Evento) request.getSession().getAttribute("eventoOrganizador");
            //Para teste
            //usuarioLogado = new User();

            /*evento = new Evento("a",1,"a","a","a","a","a","a");
            ArrayList a = new ArrayList();
            a.add("Música");
            evento.setCategoria(a);*/

%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Relatório</title>
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
                <li><a href="ManutencaoEventos?tipo=7" target="_self" title="Meus Eventos" class="current">Meus Eventos</a></li>
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
                <div class="relatorio">
                    <fieldset style="text-align: left; ">
                        <legend align="center">Dados do evento</legend>
                        <table style="margin-left: 270px; font-size: 12px; text-transform:none;">
                            <tr>
                                <td><label>Nome:</label></td><td> <%=evento.getNome()%></td>
                            </tr>
                            <tr>
                                <td><label>Número de vagas:</label></td><td> <%=(int) evento.getVagasPrincipal()%></td>
                            </tr><tr>
                                <td><label>Data de início de inscrição:</label></td><td> <%=evento.getInscInicio().replace("-", "/")%></td>
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
                    <% Date dataAtual = new Date();
                       Date dataEvento = new Date(Integer.parseInt(evento.getDataEvento().substring(0, 3)), Integer.parseInt(evento.getDataEvento().substring(5, 6)), Integer.parseInt(evento.getDataEvento().substring(8, 9)));
                       if(dataAtual.getTime() >= dataEvento.getTime()){
                           out.println("<p style=\"margin-left: -600px\">Seu evento ainda não começou</p>");
                       }else{
                    %>
                    <%if (evento != null && evento.getCategoria() != null) {%>
                    <table>
                        <tr>
                            <%int i = 0; %>
                            <td>
                                <label> Categoria: </label>
                            </td>
                            <% while (i < evento.getCategoria().size()) { Categoria c = evento.getCategoria().get(i); %>
                            <td><center> <%= c.getNome() %></center></td></tr>
                        <tr>
                            <td>
                                <% ArrayList categorias = (ArrayList) request.getSession().getAttribute("categorias");
                                   if(categorias != null){
                                       int id = (int) evento.getCategoria().get(i).getId();
                                       ArrayList aux = (ArrayList) categorias.get(id);
                                       for(int k = 0; k < aux.size(); k++){
                                            out.println(aux.get(k));
                                       }
                                       
                                   }
                                %>
                            </td>
                            <%i++;
                            }%>
                        </tr>
                    </table>
                    <%}
                                if (evento.getCategoria() == null) {%>
                    <p style="margin-left: -600px">Você não cadastrou nenhuma categoria para esse evento! Não há relatórios a serem gerados!</p>
                    <%}
                    }%>

                </div>
                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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
