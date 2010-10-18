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

                <form id="eLogin" action="Login.jsp" target="iframe">
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
                    <li> <a href="Login.jsp" > Link </a></li>
                </ul>
            </div></div>

        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
                 <div class = "loginPagina">
            <div class="erros" id="erros">

                <fieldset class="information" onclick="fecharCaixaMensagem()">
                    <legend>Informação</legend>
                    <p>- Clique no botão para realizar o login no site da aaTag.</p>
                    <p>- Clique na caixa para fechá-la.</p>
                </fieldset>
            </div>
            <form action="EfetuarLogin" target="iframe" class="formLogin" method="post" >
                <table>
                    <tr>
                        <td><input class="botao" type="submit" value="Acessar aaTag"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>



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
