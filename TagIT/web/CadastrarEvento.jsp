<%-- 
    Document   : CadastroEvento
    Created on : 30/09/2010, 09:24:07
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Participante" %>
<%@page import="PkgTagIT.Organizador" %>
<%
Participante usuarioLogado = (Participante) session.getAttribute("usuarioLogado");
if(usuarioLogado == null) {
    out.println("Realize o login para cadastrar um evento");
} else if(usuarioLogado.getClass().equals(Participante.class)) {
    out.println("Você necessita de uma conta de organizador para cadastrar um evento.");
} else {

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Eventos</title>
        <script type="text/javascript" src="funcoes.js"></script>
    </head>
    <body>
        <h1>Cadastro de Eventos</h1>
        <form action="ManutencaoEventos" id="formEvento" method="post" onsubmit="return validaEvento()">
            <p>Nome:<input type="text" name="nome" id="nome" /></p>
            <p>Número de vagas:<input type="text" name="vagasPrincipal" id="vagasPrincipal" /></p>
            <p>Número de vagas na lista de espera:<input type="text" name="vagasEspera" id="vagasEspera" /></p>
            <p>Data de início de inscrição:<input type="text" name="inscInicio" id="inscInicio" /></p>
            <p>Data de término de inscrição:<input type="text" name="inscTermino" id="inscTermino" /></p>
            <p>Rua:<input type="text" name="rua" id="rua" /></p>
            <p>Número:<input type="text" name="numeroRua" id="numeroRua" /></p>
            <p>Cidade:<input type="text" name="cidade" id="cidade" /></p>
            <p>Data do evento:<input type="text" name="dataEvento" id="dataEvento" /></p>
            <p>Contato:<input type="text" name="contato" id="contato" /></p>
            <p>


                
                Categoria:
                <select name="selectCategoria" id="selectCategoria">
                    <% //aqui vai ser preenchido conforme as categorias já cadastradas no bd %>
                    <option value="1">Musica</option>
                    <option value="2">Sexo</option>
                </select>
                <input type="button" value="Selecionar" onclick="adicionaCategoria()" />
            </p>
            <p>
                Categorias selecionadas:
                <select multiple name="categoriasSelecionadas" id="categoriasSelecionadas" size="3">
                </select>
                <input type="button" value="Remover" onclick="removeCategoria()" />
            </p>
            <input type="hidden" name="tipo" value="0" />
            <p><input type="submit" value="Enviar" /></p>
        </form>
    </body>
</html>

<%
}
%>