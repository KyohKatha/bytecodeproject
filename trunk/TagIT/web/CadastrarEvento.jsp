<%-- 
    Document   : CadastroEvento
    Created on : 30/09/2010, 09:24:07
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="PkgTagIT.Participante" %>

<%

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Eventos</title>
        <script type="text/javascript" src="funcoes.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link type="text/css" href="jquery-ui-1.8.5.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="jquery-ui-1.8.5.custom.min.js"></script>
		<script type="text/javascript">
			$(function(){

				// Accordion
				$("#accordion").accordion({ header: "h3" });

				// Tabs
				$('#tabs').tabs();


				// Dialog
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() {
							$(this).dialog("close");
						},
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});

				// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				$('#datepicker').datepicker({
					inline: true
				});

				// Slider
				$('#slider').slider({
					range: true,
					values: [17, 67]
				});

				// Progressbar
				$("#progressbar").progressbar({
					value: 20
				});

				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); },
					function() { $(this).removeClass('ui-state-hover'); }
				);

			});
		</script>
		<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>
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
                            <td><input type="text" id="datepicker"  /></td>
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
