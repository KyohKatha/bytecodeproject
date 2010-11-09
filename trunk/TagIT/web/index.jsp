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
            ArrayList<Evento> ultimosEventos = (ArrayList<Evento>) request.getSession().getAttribute("ultimosEventos");
            
            // favor nao alterar o nome do atributo 'eventosBusca' para 'eventos', pois da conflito com outras coisas. obrigada.
            // ah, se for realmente preciso alterar, por favor, modifique na pagina buscarEvento e
            // na servlet ManutencaoEvento (acho que eh so ai...) . Eu tambem preciso dos atributos de nomes
            // "modo" e "insc" pro controle dos eventos que sao exibidos, e pra ver se o botao "Inscrever-se" deve ou nao
            // aparecer. se puder manter, agradeço também. Kaori.
            request.getSession().setAttribute("eventosBusca", ultimosEventos);
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
        <link rel="stylesheet" href="menu/menu_style.css" type="text/css" />
        <script type="text/javascript">
            function limpar()
            {
                document.busca.parametro.value = '';
            }
            function carregarUltimosEventos(){
                callServlet('ManutencaoEventos?tipo=8', 'body')

            }
        </script>
    </head>
    <body  onload="carregarUltimosEventos()" >
        <div class="topo">
            <div class="logo"></div>
            <table class="login">
                <%if (usuarioLogado != null) {%>
                <tr>
                    <td><label> Bem-vindo <%= usuarioLogado.getNome()%> </label></td>
                </tr>

                <form action="" method="POST" id="formLogado">
                    <tr><td><a href="https://graph.facebook.com/oauth/authorize?client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp&scope=publish_stream,create_event,rsvp_event,sms,offline_access,user_location,+email,user_about_me,user_birthday,user_education_history,user_groups,user_interests,user_likes,user_photo_video_tags,user_photos,user_religion_politics,user_work_history,friends_about_me,friends_activities,friends_birthday,friends_education_history,friends_groups,friends_interests,friends_likes,friends_photo_video_tags,friends_photos,friends_relationships,friends_religion_politics,friends_work_history,friends_location,friends_hometown">Acesse o Facebook</a></td></tr>
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
                <li style="margin-left:150px"><a href="index.jsp" target="_self" title="Principal" class="current">Principal</a></li>
                <%if (usuarioLogado != null) {%>
                <li><a href="ManutencaoUsuarios?tipo=3" target="_self" title="Minhas Inscrições">Minhas Inscrições</a></li>
                <li><a href="ManutencaoEventos?tipo=7" target="_self" title="Meus Eventos">Meus Eventos</a></li>
                <li><a href="ManutencaoEventos?tipo=10" title="Cadastrar Evento">Cadastrar Evento</a></li>
                <%} else {%>
                <li><a href="CadastrarUsuario.jsp" title="Registre-se">Registre-se</a></li>
                <%}%>
                <li><a href="http://www.bytecodeufscar.blogspot.com" target="_blank" title="Blog">Blog</a></li>
                <li><a href="Faq.jsp" target="_self" title="FAQ">FAQ</a></li>
                <li><a href="Sobre.jsp" target="_self" title="Sobre">Sobre</a></li>
            </ul>
        </div>



        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
                <% //if (usuarioLogado == null) {%>
                <div class="publicidadeContainer">
                    <div class="publicidade">
                        <a href="CadastrarEvento.jsp"><img align="right" src="images/btProp.png" width="169" height="213" /></a>
                        <!-- Fim DIV PUBLICIDADE -->
                    </div>
                    <!-- Fim DIV PUBLICIDADECONTAINER --></div>
                    <% //}%>
                  
                 
                    <table width="928">
                        <tr>
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
                                <input name="parametro" type="text" id="parametro" value="Digite o termo da busca" onfocus="limpar()"/> <input class="botao" type="submit" value="Buscar" id="bBuscar" /><input type="hidden" name="tipo" value="1">
                            </form>
                            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        </td>
                        <td class="twitter">
                            <script src="http://widgets.twimg.com/j/2/widget.js"></script>
                            <script>
                                new TWTR.Widget({
                                    version: 2,
                                    type: 'profile',
                                    rpp: 4,
                                    interval: 6000,
                                    width: 350,
                                    height: 300,
                                    theme: {
                                        shell: {
                                            background: '#333333',
                                            color: '#ffffff'
                                        },
                                        tweets: {
                                            background: '#000000',
                                            color: '#ffffff',
                                            links: '#f09205'
                                        }
                                    },
                                    features: {
                                        scrollbar: false,
                                        loop: false,
                                        live: false,
                                        hashtags: true,
                                        timestamp: true,
                                        avatars: false,
                                        behavior: 'all'
                                    }
                                }).render().setUser('EventosdeTI').start();
                            </script>
                        </td>
                    </tr>
                    <% if (usuarioLogado != null && ultimosEventos != null && !ultimosEventos.isEmpty()) {
                    %>
                    <tr>
                        <td class="eventoPrincipal" colspan="2">
                    </tr>
                    <tr>
                        <td class="eventoP1">
                            <form id="caixaEvento" action="ManutencaoEventos" >
                                <p id="titles"><%=ultimosEventos.get(0).getNome()%></p>
                                <p > Data: <%=ultimosEventos.get(0).getDataEvento().replace("-", "/")%> </p>
                                <p > Endereço: <%=ultimosEventos.get(0).getRua()%>,<%=ultimosEventos.get(0).getCidade()%>  </p>
                                <input type="submit" id="participar" value="Participe!" />
                                <input type="hidden" name="tipo" value="5" />
                                <input type="hidden" name="insc" value="1" />
                                <input type="hidden" name="i" value="0" />
                                <input type="hidden" name="modo" value="busca" />
                            </form>
                        </td>
                        <td class="eventoP2" align="center">
                            <form id="caixaEvento" action="ManutencaoEventos" >
                                <p id="titles"><%=ultimosEventos.get(1).getNome()%></p>
                                <p > Data: <%=ultimosEventos.get(1).getDataEvento().replace("-", "/") %> </p>
                                <p > Endereço: <%=ultimosEventos.get(1).getRua()%>,<%=ultimosEventos.get(1).getCidade()%>  </p>
                                <input class="botao" type="submit" id="participar" value="Participe!" />
                                <input type="hidden" name="tipo" value="5" />
                                <input type="hidden" name="insc" value="1" />
                                <input type="hidden" name="i" value="1" />
                                <input type="hidden" name="modo" value="busca" />
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>

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
