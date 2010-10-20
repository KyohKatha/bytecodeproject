<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="aaTag.User" %>

<%

            User usuarioLogado = (User) request.getSession().getAttribute("usuario");
            //Para teste
            //usuarioLogado = new User();

%>
<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jquery.autoHeight.js"></script>
        <script type="text/javascript" src="funcoes.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TagiT! - Cadastro de Evento</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="topo">
            <div class="logo"></div>
            <div class="login">

                <form id="eLogin" action="Login.jsp" >
<table>
                        <%if (usuarioLogado != null) {%>
                        <tr>
                            <td><label> Bem-vindo <%= usuarioLogado.getNome()%> </label></td>
                        </tr>

                        <tr><td><a href="ExibirEventosParticipante.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=3','body')">Minhas inscrições</a></td></tr>
                            <tr><td><a href="ExibirMeusEventos.jsp" onclick="callServlet('','body')">Meus Eventos</a></td></tr>
                            <tr><td><a href="index.jsp" onclick="callServlet('ManutencaoUsuarios?tipo=4','body')" >Logoff</a></td></tr>
                        <%} else {%>
                        <tr>
                        <td><p align="center">
                                <input class="botao" type="submit" value="Logar-se" id="efetuarLogin" name="efetuarLogin"/>
                            </p>
                        </td>
                        </tr>
                        <%}%>
                    </table>
                </form>
            </div>
        </div>
        <div class="menuTopo">
            <div class="menu">
                <ul>
                    <li> <a href="index.jsp"> Principal </a><li>
                    <li> <a href="http://www.bytecodeufscar.blogspot.com" target="_blank"> Blog </a><li>
                        <%if (usuarioLogado == null) {%>
                    <li> <a href="CadastrarUsuario.jsp"> Registrar-se </a></li>
                    <%}%>
                    <li> <a href="Faq.jsp"> Faq </a></li>
                    <li> <a href="Sobre.jsp"> Sobre </a></li>
                </ul>
            </div></div>

        <!-- INICIO CONTEUDO -->
        <div class="conteudo">

            <div class="meioContainer">
                <div class = "cadEvento">
                    <% if (usuarioLogado != null) {%>
                    <div class="erros" id="erros">
                        <fieldset class="information" onclick="fecharCaixaMensagem()">
                            <legend>Informação</legend>
                            <p>- Todos os campos com (*) são obrigatórios.</p>
                            <p>- Clique na caixa para fechá-la.</p>
                        </fieldset></div>
                    <form action="ManutencaoEventos" class="formEvento" id="formEvento" method="post" onsubmit="return validaEvento()">
                        <table>
                            <tr>
                                <td><label>(*)Nome:</label></td>
                                <td><input type="text" name="nomeEvento" id="nome" /></td>
                            </tr><tr>
                                <td><label>Descrição do evento:</label></td>
                                <td><input type="text" name="descricaoEvento" id="descricao" /></td>
                            </tr><tr>
                                <td><label>(*)Número de vagas:</label></td>
                                <td><input type="text" name="vagasPrincipal" id="vagasPrincipal" /></td>
                            </tr><tr>
                                <td><label>(*)Data de início de inscrição:</label></td>
                                <td><input type="text" name="inscInicio" id="inscInicio" /> Ex: dd/mm/aaaa</td>
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
                                <td><select multiple name="categoriasSelecionadas" id="categoriasSelecionadas" size="3" >
                                    </select></td>
                                <td><input class="botao" type="button" value="Remover" onclick="removeCategoria()" /></td>
                            </tr><tr>
                                <td><input class="botao" type="submit" value="Enviar" /></td>
                                <td><input type="hidden" name="tipo" value="0" /></td>
                                <td><input type="hidden" name="metodo" value="AddEvent" /></td>
                                <td><input type="hidden" name="paginaRetorno" value="/index.jsp" /></td>
                            </tr>
                        </table>
                    </form>
                    <%} else {%>
                    <div class="erros" id="erros">
                        <fieldset class="warning" onclick="fecharCaixaMensagem()">
                            <legend>Aviso</legend>
                            <p>- Você já não está Logado! É precisa realizar o Login para cadastrar eventos</p>
                            <p>- Clique na caixa para fechá-la.</p>
                        </fieldset>
                    </div>
                    <%}%>
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
