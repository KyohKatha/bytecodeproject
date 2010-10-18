<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>
<%@page import="aaTag.User"%>
<%@page import="PkgTagIT.Participante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%

     //   Participante usuarioLogado = new Participante();
        //apenas para teste
     //   usuarioLogado.setEmail("tiago.pasqualini@gmail.com");
        User usuarioLogado = (User) session.getAttribute("usuario");
     //   request.getSession().setAttribute("usuarioLogado", usuarioLogado);
        %>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Logado</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="topo">
            <div class="logo"></div>
            <div class="login">

                <form id="eLogin" action="#">
                    <table>
                        <tr>
                        <label id="titles"> </label>
                        </tr>
                        <td><p align="center">

                               <a href="ExibirEventosParticipante.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=3','iframe')" target="iframe" >Minhas inscrições</a></p>
                            <p><a href="ExibirMeusEventos.jsp" onclick="callServlet('','iframe')" target="iframe">Meus Eventos</a></p>
                            <p><a href="index.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=4','body')" >Logoff</a></p>
                            
                        </td>
                    </table>

                </form>
            </div>
        </div>
        <div class="menuTopo">
            <div class="menu">
                <ul>
                    <li> <a href="HomeLogado.jsp" target="iframe"> Principal </a><li>
                    <li> <a href="http://www.bytecodeufscar.blogspot.com" target="_blank"> Blog </a><li>
                    <li> <a href="www.google.com" target="iframe"> Faq </a></li>
                    <li> <a href="www.google.com" target="iframe"> Sobre </a></li>
                </ul>
            </div></div>

        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
                <iframe src="HomeLogado.jsp" id="iframe" name="iframe" class="autoHeight" width="934" scrolling="no" border="0" frameborder="0">
                </iframe>



                <!-- Fim DIV MEIOCONTAINER -->
            </div>
            <div class="rodape"><BR /><br />
                ByteCode - Ajuda - Tecnologia RFID - Contato
                <% if(usuarioLogado == null)
                    out.println("MERDA EH NULLO");
                    else
                        out.println("EMAIL: " + usuarioLogado.getEmail());
        %>
                <br />
                <hr />
                Todos os direitos reservados<br />
			Desenvolvidos por <a href="www.bytecodeufscar.blogspot.com">ByteCode</a></div>
            <!-- Fim DIV CONTEUDO -->
        </div>
    </body>
</html>
