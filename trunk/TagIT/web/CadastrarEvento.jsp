<%--
    Document   : index
    Created on : 16/10/2010, 13:42:31
    Author     : Renato
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
                <li><a href="ManutencaoEventos?tipo=7" target="_self" title="Meus Eventos">Meus Eventos</a></li>
                <li><a href="http://localhost:8080/TagIT/CadastrarEvento.jsp" target="_self" title="Cadastrar Evento" class="current">Cadastrar Evento</a></li>

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
                <div class = "cadEvento">
                    <% if (usuarioLogado != null) {
                            String type = (String) session.getAttribute("type");
                            String message = (String) session.getAttribute("message");

                            if (type != null && message != null){
                                    session.removeAttribute("type");
                                    session.removeAttribute("message");
            %>
                            <div class="erros" id="erros">
                                <fieldset class="<%=type%>" onclick="fecharCaixaMensagem()">
                                <legend>Informação</legend>
                                <%=message%>                                
                                </fieldset>
                            </div>
                                <% }else {%>
                    <div class="erros" id="erros">
                        <%
                             message = (String) session.getAttribute("message");
                             type = (String) session.getAttribute("type");
                             session.removeAttribute("message");
                             session.removeAttribute("type");

                             if (message != null) {
                                 out.println("<fieldset class=\"" + type + "\" onclick=\"fecharCaixaMensagem()\">");
                                 String legend = "Undefined";
                                 if (type == "information") {
                                     legend = "Information";
                                 } else if (type == "critical") {
                                     legend = "Error";
                                 } else if (type == "success") {
                                     legend = "Success";
                                 } else if (type == "warning") {
                                     legend = "Warning";
                                 }

                                 out.println("<legend>" + legend + "</legend>");
                                 out.println(message);
                                 out.println("</fieldset>");
                             } else {

                                 out.println("<fieldset class=\"information\" onclick=\"fecharCaixaMensagem()\">");
                                 out.println("<legend>Informação</legend>");
                                 out.println("<p>- Todos os campos com (*) são obrigatórios.</p>");
                                 out.println("<p>- Clique na caixa para fechá-la.</p>");
                                 out.println("</fieldset>");

                             }
                        %>
                    </div>
                        <% } %>
                    <form onload="carregarCategorias" action="ManutencaoEventos" class="formEvento" id="formEvento" method="post" onsubmit="return validaEvento()">
                        <table>
                            <tr>
                                <td><label>Nome<em>*</em></label></td>
                                <td><input title="Informe o seu nome complet Ex: José Antonio da Silva" type="text" name="nomeEvento" id="nome" /></td>
                            </tr><tr>
                                <td><label>Descrição do evento<em>*</em></label></td>
                                <td><input title="Informe algo sobre o evento Ex: Uma festa a fantasia" type="text" name="descricaoEvento" id="descricao" /></td>
                            </tr><tr>
                                <td><label>Número de vagas<em>*</em></label></td>
                                <td><input title="Informe o limite máximo de inscrições para o seu evento" type="text" name="vagasPrincipal" id="vagasPrincipal" /></td>
                            </tr><tr>
                                <td><label>(*)Data de início de inscrição:</label></td>
                                <td><input title="Informe a data de inicio para receber inscrições para seu evento Ex : 01/01/2001" type="text" name="inscInicio" id="inscInicio" /></td>
                            </tr><tr>
                                <td><label>Data de término de inscrição<em>*</em></label></td>
                                <td><input title="Informe a data final para receber inscrições para seu evento Ex : 01/02/2001" type="text" name="inscTermino" id="inscTermino" /></td>
                            </tr><tr>
                                <td><label>Rua<em>*</em></label></td>
                                <td><input title="Informe o endereço para o evento" type="text" name="rua" id="rua" /></td>
                            </tr><tr>
                                <td><label>Número<em>*</em></label></td>
                                <td><input title="Informe o número do local na rua citada acima" type="text" name="numeroRua" id="numeroRua" /></td>
                            </tr><tr>
                                <td><label>Cidade<em>*</em></label></td>
                                <td><input title="Informe em qual cidade será realizada o evento" type="text" name="cidade" id="cidade" /></td>
                            </tr><tr>
                                <td><label>Data do evento<em>*</em></label></td>
                                <td><input title="Informe em qual data será realizada o evento Ex : 01/01/2001" type="text" name="dataEvento" id="dataEvento" /></td>
                            </tr><tr>
                                <td><label>Hora do evento<em>*</em></label></td>
                                <td><input title="Informe qual horas dará inicio ao o evento" type="text" name="hora" id="hora" /></td>
                            </tr><tr>
                                <td><label>Contato<em>*</em></label></td>
                                <td><input title="Informe um telefone ou email para que as pessoas possam entrar em contato com você" type="text" name="contato" id="contato" /></td>
                            </tr><tr>
                                <td><label>Categoria</label></td>
                                <td><select name = "selectCategoria" id="selectCategoria"  size="1" >
                                        <%Iterator itr;%>
                                        <% List data = (List) request.getAttribute("categorias");
                                            int i = 1;
                                            if(data != null){
                                            for (itr = data.iterator(); itr.hasNext();) {
                                                 String s = itr.next().toString();
                                        %>
                                        <option value = "<%=i%>" >  <%=s%> </option>
                                        <%i++; }}%>
                                    </select></td>
                                <td><input class="botao" type="button" value="Selecionar" onclick="adicionaCategoria()" /></td>
                            </tr><tr>
                                <td><label>Categorias selecionadas</label></td>
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
                            <p>- Você não está Logado! É preciso realizar o Login para cadastrar eventos</p>
                            <p>- Clique na caixa para fechá-la.</p>
                        </fieldset>
                    </div>
                    <%}%>
                </div>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

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
