<%-- 
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT!</title>
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
                            <input class="botao" type="submit" value="Logar-se" id="efetuarLogin" name="efetuarLogin"/>
                            </p>
                        </td>
                    </table>

                </form>
            </div>
        </div>
        <div class="menuTopo">
            <div class="menu">
                <ul>
                    <li> <a href="home.jsp" target="iframe"> Principal </a><li>
                    <li> <a href="http://www.bytecodeufscar.blogspot.com" target="_blank"> Blog </a><li>
                    <li> <a href="CadastrarUsuario.jsp" target="iframe"> Registrar-se </a></li>
                    <li> <a href="www.google.com" target="iframe"> Faq </a></li>
                    <li> <a href="www.google.com" target="iframe"> Sobre </a></li>
                    <li> <a href="Login.jsp" target="iframe"> Link </a></li>
                </ul>
            </div></div>

        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
                <iframe src="home.jsp" id="iframe" name="iframe" class="autoHeight" width="934" scrolling="no" border="0" frameborder="0">
                </iframe>



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
