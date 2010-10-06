/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import PkgTagIT.Categoria;
import PkgTagIT.ConexaoBD;
import PkgTagIT.Evento;
import PkgTagIT.Organizador;
import PkgTagIT.TagITDAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 317586
 */
public class ManutencaoEventos extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*
         * 0 : cadastrar
         * 1 : atualizar
         * 2 : remover
         * 3 : buscar
         */

        int tipo;

        try {
            tipo = Integer.parseInt(request.getParameter("tipo"));

            switch(tipo) {
                case 0:
                    try {
                        cadastraEvento(request, response);
                        out.println("Evento inserido com sucesso!");
                    } catch (TagITDAOException e) {
                        out.println("Ja existe um evento com esse nome cadastrado!");
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        } finally { 
            out.close();
        }
    }

    private void cadastraEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String nome;
        double vagasPrincipal;
        double vagasEspera;
        String inscInicio;
        String inscTermino;
        String rua;
        String cidade;
        String numeroRua;
        String dataEvento;
        String contato;
        Organizador organizador; //organizador vai ser pego da sessão
        String[] categoria; //ver como fazer a categoria
        ArrayList<Categoria> lstCategoria;
        int i;
        Evento evento;


        nome = request.getParameter("nome");
        vagasPrincipal = Double.parseDouble(request.getParameter("vagasPrincipal"));
        vagasEspera = Double.parseDouble(request.getParameter("vagasEspera"));
        inscInicio = request.getParameter("inscInicio");
        inscTermino = request.getParameter("inscTermino");
        rua = request.getParameter("rua");
        numeroRua = request.getParameter("numeroRua");
        rua += ", " + numeroRua;
        cidade = request.getParameter("cidade");
        dataEvento = request.getParameter("dataEvento");
        contato = request.getParameter("contato");
        categoria = request.getParameterValues("categoria");
        //falta organizador, que sera pego da sessao
        organizador = (Organizador) request.getSession().getAttribute("usuarioLogado");

        lstCategoria = new ArrayList<Categoria>();
        for(i = 0; i < categoria.length; i++) {
            lstCategoria.add(new Categoria(categoria[i]));
        }

        evento = new Evento(nome, vagasPrincipal, vagasEspera, inscInicio, inscTermino, rua, cidade, dataEvento, contato, organizador, lstCategoria);

        ConexaoBD.getInstance().insereEvento(evento);


    }

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
