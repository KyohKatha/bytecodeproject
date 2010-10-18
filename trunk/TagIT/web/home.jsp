<%-- 
    Document   : home
    Created on : 16/10/2010, 13:44:35
    Author     : Renato
--%>

<%@page import="aaTag.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="publicidadeContainer">
            <div class="publicidade">
                <a href="CadastrarEvento.jsp"><img align="right" src="images/btProp.png" width="169" height="213" /></a>
                <!-- Fim DIV PUBLICIDADE -->
            </div>
            <!-- Fim DIV PUBLICIDADECONTAINER --></div><table width="928" height="674">
            <tr>
                <td class="buscaPrincipal">

                    <!-- Fim DIV BUSCAPRINCIPAL -->
                </td>

                <td class="twitterContainer" >

                    <!-- Fim DIV TWITTERCONTAINER -->
                </td>
            </tr>
            <tr>
                <td align="center">
                    <form id="busca" name="busca" action="ManutencaoEventos">
                        <input name="parametro" type="text" id="parametro" value="Digite o termo da busca" /> <input class="botao" type="submit" value="Buscar" id="bBuscar" /><input type="hidden" name="tipo" value="1">
                    </form>
                    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                </td>
                <td class="twitter">
                </td>
            </tr>
            <tr>
                <td class="eventoPrincipal" colspan="2">
            </tr>
            <tr>
                <td class="eventoP1">
                    <form id="caixaEvento" action="#" >
                        <p id="titles"> Nome do Evento </p>
                        <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                            TESTEE <% User u = (User) session.getAttribute("usuario");
                    if(u == null)
                        out.println("MERDA EH NULLOO");
                    else
                        out.println("Email:   " + u.getEmail());
                %>
                
                        <input type="submit" name="participar" value="Participe!" />
                    </form>
                </td>
                <td class="eventoP2" align="center">
                    <form id="caixaEvento" action="#" >
                        <p id="titles"> Nome do Evento </p>
                        <p > Lorem Ipsum ai di ti Lorem Ipsum ai di ti Lorem Ipsum ai di ti </p>
                        <input type="submit" name="participar" value="Participe!" />
                    </form>
                </td>
            </tr>
        </table>

    </body>
</html>
