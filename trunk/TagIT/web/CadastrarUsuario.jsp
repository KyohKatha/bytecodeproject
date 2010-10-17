<%-- 
    Document   : CadastrarUsuario
    Created on : 30/09/2010, 09:23:25
    Author     : 317586
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuário</title>
        <script type="text/javascript" src="funcoes.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        
            <div class = "cadParticipante">
                <div class="erros" id="erros">
                    <fieldset class="information" onclick="fecharCaixaMensagem()">
                        <legend>Informação</legend>
                        <p>- Todos os campos com (*) são obrigatórios.</p>
                        <p>- Clique na caixa para fechá-la.</p>
                    </fieldset>
                </div>
                <form class="formParticipante" action="ManutencaoUsuarios" onsubmit="return validarCadastroUsuario()">
                    <table>
                        <tr>
                            <td><label>(*)CPF: </label></td>
                            <td><input type ="text" name="cpf" id="cpf" maxlength="11"/></td>
                        <tr>
                            <td><input class="botao" type="submit" value="Acessar aaTag"/></td>
                            <td><input class="botao" type="reset" value="Limpar"/></td>

                        <input type="hidden" name="tipo" value="0">
                    </table>
                </form>
            </div>
            <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        
    </body>
</html>