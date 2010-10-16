<%-- 
    Document   : Login
    Created on : 05/10/2010, 12:07:21
    Author     : Valter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="funcoes.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <!-- <form action="ManutencaoUsuarios" id="formLogin" method="post"> -->
        <!-- onsubmit="return validarLogin()" -->
        <div class="meioContainer">
            <div class = "loginPagina">
              <div class="erros" id="erros">
                    <fieldset class="information" onclick="fecharCaixaMensagem()">
                        <legend>Informação</legend>
                        <p>- Clique no botão para realizar o login no site da aaTag.</p>
                        <p>- Clique na caixa para fechá-la.</p>
                    </fieldset>
                </div> 
                <form action="ManutencaoUsuarios" class="formLogin" method="post" >
                    <table>
                        <tr>
                            <td><input class="botao" type="submit" value="Acessar aaTag"/></td>
                            <td><input type="hidden" name="tipo" value="3"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
