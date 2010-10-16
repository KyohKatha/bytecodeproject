<%-- 
    Document   : CadastroEvento
    Created on : 30/09/2010, 09:24:07
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Participante" %>
<% // @page import="PkgTagIT.Organizador" %>
<%
/*  Fazer com que só consiga cadastrar um evento se estiver logado
            Participante usuarioLogado = (Participante) session.getAttribute("usuarioLogado");
            if (usuarioLogado == null) {
                out.println("Realize o login para cadastrar um evento");
            } else if (usuarioLogado.getClass().equals(Participante.class)) {
                out.println("Você necessita de uma conta de organizador para cadastrar um evento.");
            } else {
*/
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Eventos</title>
        <script type="text/javascript" src="funcoes.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>

        <div class="meioContainer">
            <div class = "cadEvento">
                <div class="erros" id="erros">
                    <fieldset class="information" onclick="fecharCaixaMensagem()">
                        <legend>Informação</legend>
                        <p>- Todos os campos com (*) são obrigatórios.</p>
                        <p>- Clique na caixa para fechá-la.</p>
                    </fieldset>
                </div>
                <form action="ManutencaoEventos" class="formEvento" id="formEvento" method="post" onsubmit="return validaEvento()">
                    <table>
                        <tr>
                            <td><label>(*)Nome:</label></td>
                            <td><input type="text" name="nome" id="nome" /></td>
                        </tr><tr>
                            <td><label>(*)Número de vagas:</label></td>
                            <td><input type="text" name="vagasPrincipal" id="vagasPrincipal" /></td>
                        </tr><tr>
                            <td><label>(*)Data de início de inscrição:</label></td>
                            <td><input type="text" name="inscInicio" id="inscInicio" /></td>
                        </tr><tr>
                            <td><label>(*)Data de término de inscrição:</label></td>
                            <td><input type="text" name="inscTermino" id="inscTermino" /></td>
                        </tr><tr>
                            <td><label>(*)Rua:</label></td>
                            <td><input type="text" name="rua" id="rua" /></td>
                        </tr><tr>
                            <td><label>(*)Número:</label></td>
                            <td><input type="text" name="numeroRua" id="numeroRua" /></td>
                        </tr><tr>
                            <td><label>(*)Cidade:</label></td>
                            <td><input type="text" name="cidade" id="cidade" /></td>
                        </tr><tr>
                            <td><label>(*)Data do evento:</label></td>
                            <td><input type="text" name="dataEvento" id="dataEvento" /></td>
                        </tr><tr>
                            <td><label>(*)Contato:</label></td>
                            <td><input type="text" name="contato" id="contato" /></td>
                        </tr><tr>
                            <td><label>Categoria:</label></td>
                            <td><select name="selectCategoria" id="selectCategoria">
                            <% //aqui vai ser preenchido conforme as categorias já cadastradas no bd %>
                            <option value="1">Musica</option>
                            <option value="2">Sexo</option>
                        </select></td>
                            <td><input class="botao" type="button" value="Selecionar" onclick="adicionaCategoria()" /></td>
                        </tr><tr>
                           <td><label>Categorias selecionadas:</label></td>
                           <td><select multiple name="categoriasSelecionadas" id="categoriasSelecionadas" size="3">
                        </select></td>
                        <td><input class="botao" type="button" value="Remover" onclick="removeCategoria()" /></td>
                        </tr><tr>
                            <td><input type="hidden" name="tipo" value="0" /></td>
                            <td><input class="botao" type="submit" value="Enviar" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>

<%

 // }

%>