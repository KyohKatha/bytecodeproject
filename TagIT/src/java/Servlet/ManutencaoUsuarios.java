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
             * 2 : login
             * 3 : logoff
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
                    try{
                        validarLogin(request, response);
                    }catch(Exception e){
                        System.out.println("DEU ERRO NO LOGIN DO USUARIO");
                        request.setAttribute("erro", true);
                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/Login.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 3:
                    request.getSession().removeAttribute("usuarioLogado");
                    RequestDispatcher rd = null;
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
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


        // verificar se o email ja foi cadastrado
        if ( con.retornaDadosParticipante(email) == null ) {
            con.insereParticipante(p);

            p.setSenha("");

            request.getSession().setAttribute("usuarioLogado", p);
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

        Participante p = (Participante) request.getSession().getAttribute("usuarioLogado");

        String nome = request.getParameter("nome");
        String senha = request.getParameter("atual");
        String cpf = request.getParameter("cpf");
        String novaSenha = request.getParameter("senha");

        p.setNome(nome);
        p.setCpf(cpf);

        ConexaoBD con = ConexaoBD.getInstance();
        // verificar senha do participante logado no BD
        Participante p2 = con.retornaDadosParticipante(p.getEmail());

        if ( senha.equals(p2.getSenha()) ) {
            if (!novaSenha.equals("") && novaSenha != null) {
                p.setSenha(novaSenha);
            }

            // update no BD
            con.alteraParticipante(p);

            p.setSenha("");
            request.getSession().setAttribute("usuarioLogado", p);

            request.setAttribute("erro", false);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/ConfirmacaoAlteracaoUsuario.jsp");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("usuarioLogado", p);

            request.setAttribute("erro", true);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("/AlterarUsuario.jsp");
            rd.forward(request, response);
        }

    }


    private void validarLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {
        
        RequestDispatcher rd = null;
        ConexaoBD con = ConexaoBD.getInstance();
        Participante part = null;

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try{
            part = con.validarLogin(email, senha);
            if (part != null) {
                request.getSession().setAttribute("usuarioLogado", part);
                rd = request.getRequestDispatcher("homeLogada.jsp");
                rd.forward(request, response);
            } else {
                request.getSession().setAttribute("type", "critical");
                request.getSession().setAttribute("message", "<p>- Seu usuário ou senha estão incorretos!");
                rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }

        }catch(Exception e){
            e.printStackTrace();
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
