/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.Participante;
import PkgTagIT.TagITDAOException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 317586
 */
public class ManutencaoUsuarios extends HttpServlet {

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
        try {

            /*
             * 0 : cadastrar
             * 1 : atualizar
             * 2 : remover
             * 3 : upgrade
             */

            int tipo = Integer.parseInt(request.getParameter("tipo"));

            switch (tipo) {
                case 0:
                    try {
                        cadastrarUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/CadastrarUsuario.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 1:
                    try {
                        alterarUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/AlterarUsuario.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 2:
                    try {
                        removerUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/RemoverUsuario.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 3:
                    try {
                        upgradeUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/RemoverUsuario.jsp");
                        rd.forward(request, response);
                    }
                    break;
                default:
                    break;
            }

        } finally {
            out.close();
        }
    }

    /**
     *
     * Cadastra um usuario no site. Equivalente a opcao 0.
     */
    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        Participante p;

        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");

        p = new Participante(email, nome, senha, cpf);

        ConexaoBD con = ConexaoBD.getInstance();
        // verificar se o email já não foi cadastrado

        if (true) {
            // salvar no BD - gerar ID
            // recuperar do BD - com ID, upgrade (T/F), tentativas, mas sem senha

            request.getSession().setAttribute("part", p);
            request.setAttribute("erro", false);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/confirmacaoCadastroUsuario.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("erro", true);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/CadastrarUsuario.jsp");
            rd.forward(request, response);
        }

    }

    /**
     *
     * Altera as informacoes de um usuario no site. Equivalente a opcao 1.
     */
    private void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        Participante p = (Participante) request.getSession().getAttribute("part");

        String nome = request.getParameter("nome");
        String senha = request.getParameter("atual");
        String cpf = request.getParameter("cpf");
        String novaSenha = request.getParameter("senha");

        p.setNome(nome);
        p.setCpf(cpf);

        ConexaoBD con = ConexaoBD.getInstance();
        // verificar senha no BD

        if (true) {
            if (!novaSenha.equals("") && novaSenha != null) {
                p.setSenha(novaSenha);
            }

            // update no BD, com base no ID

            p.setSenha("");
            request.getSession().setAttribute("part", p);

            request.setAttribute("erro", false);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/ConfirmacaoAlteracaoUsuario.jsp");
            rd.forward(request, response);
        } else {

            request.getSession().setAttribute("part", p);

            request.setAttribute("erro", true);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/AlterarUsuario.jsp");
            rd.forward(request, response);
        }

    }

    /**
     *
     * Remover um usuario no site. Equivalente a opcao 2.
     */
    private void removerUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String senha = request.getParameter("senha");

        ConexaoBD con = ConexaoBD.getInstance();
        // verificar senha no BD

        if (true) {
            Participante p = (Participante) request.getSession().getAttribute("part");
            // remover participante p.getId();

            request.getSession().removeAttribute("part");
            request.setAttribute("erro", false);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("erro", true);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/RemoverUsuario.jsp");
            rd.forward(request, response);
        }

    }

    /**
     *
     * Envia uma solicitação de upgrade. Equivalente a opcao 3.
     */
    private void upgradeUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String senha = request.getParameter("senha");

        ConexaoBD con = ConexaoBD.getInstance();
        // verificar senha no BD

        if (true) {
            Participante p = (Participante) request.getSession().getAttribute("part");
            // modificar de participante para organizador

            p.setTentivasUpgrade(p.getTentivasUpgrade()+1);

            request.getSession().setAttribute("part", p);

            request.setAttribute("erro", false);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("erro", true);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/RemoverUsuario.jsp");
            rd.forward(request, response);
        }

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
