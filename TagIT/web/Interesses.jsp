<%-- 
    Document   : Interesses
    Created on : 14/10/2010, 22:03:37
    Author     : Mariana

    Página que exibe os dados recuperados do facebook

----------------------------------------------------------------------------------------------
    
    ESTA PÁGINA DEVERÁ SER APAGADA DO PROJETO POSTERIORMENTE

----------------------------------------------------------------------------------------------
--%>

<%@page import="PkgTagIT.Interesse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        

        
        <%
        try{
        ArrayList<Interesse> lInteresses = new ArrayList<Interesse>();
        lInteresses = (ArrayList<Interesse>)request.getSession().getAttribute("lInteresses");
        %>
        <h1>Interesses do Usuário:</h1>
        <%
        for (int i = 0; i < lInteresses.size(); i++)
        {
        %>
        <p><b>Nome: </b><%=lInteresses.get(i).getNome() %></p>
        <p><b>Categoria:</b><%=lInteresses.get(i).getCategoria() %></p>
        <br/>
        <%
        }
        }
        catch(Exception e)
        {
            %>
        <h1>Erro: Dados não recuperados</h1>    
            <%
        }
        %>
    </body>
</html>
