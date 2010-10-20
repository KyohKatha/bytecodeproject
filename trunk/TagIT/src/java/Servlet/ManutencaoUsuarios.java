/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.Participante;
import PkgTagIT.Evento;
import PkgTagIT.TagITDAOException;
import aaTag.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                        //alterarUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/AlterarUsuario.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 2:
                    try {
                      //  efetuarLogin(request, response);
                    } catch (Exception e) {
                        System.out.println("DEU ERRO NO LOGIN DO USUARIO");
                        request.setAttribute("erro", true);
                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/Login.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 3:
                    try {
                        buscarEventosParticipante(request, response);
                    } catch (Exception e) {
                        
                        request.setAttribute("erro", true);
                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }


                    break;
                case 4:

                    request.getSession().removeAttribute("usuario");
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

    private boolean statusMessage(HttpServletRequest request, String[] message) {
        if (message[0].equals("0")) {
            request.getSession().setAttribute("type", "critical");
            request.getSession().setAttribute("message", "<p>- <strong>Erro</strong> na operação realizada .</p><p>- Clique na caixa para fechar.</p>");
        } else {
            if (message[0].equals("1")) {
                request.getSession().setAttribute("type", "success");
                request.getSession().setAttribute("message", "<p>- Operação realizada com <strong>sucesso !</strong> </p><p>- Clique na caixa para fechar.</p>");

                return true;
            } else {
                if (message[0].equals("2")) {
                    request.getSession().setAttribute("type", "critical");
                    request.getSession().setAttribute("message", "<p>- <strong>Erro</strong> na operação realizada .</p><p>- Clique na caixa para fechar.</p>");
                } else {
                    if (message[0].equals("3")) {
                        request.getSession().setAttribute("type", "critical");
                        request.getSession().setAttribute("message", "<p>- Parâmetro <strong>não encontrado</strong>.</p><p>- Clique na caixa para fechar.</p>");
                    } else {
                        if (message[0].equals("4")) {
                            request.getSession().setAttribute("type", "critical");
                            request.getSession().setAttribute("message", "<p>-<strong>Falta</strong> de vagas para o cadastro do evento.</p><p>- Clique na caixa para fechar.</p>");
                        }
                    }

                }
            }
        }
        return false;
    }

    /**
     *
     * Cadastra um usuario no site. Equivalente a opcao 0.
     */
    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        Participante p;

        String cpf = request.getParameter("cpf");

        ConexaoBD con = ConexaoBD.getInstance();

        /* api.cadastroUsuario();
        p = api.GetUserInfo();
        p.setCpf(CPF);
        api.alterUser(p); */

        // login!!
        StringBuilder sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?");
        sbUrl.append("method=\"\"&parameter=\"\"");
        URL url = new URL(sbUrl.toString());
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        // fim do login. vai ter um token e um email na session.

        String token = in.readLine();


        // retorno das informacoes do usuario a partir do token
        sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?");
        sbUrl.append("method=\"GetUserInfo\"&parameter=\"");
        sbUrl.append(token);
        sbUrl.append("\"");
        url = new URL(sbUrl.toString());
        conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        // os dados do participante estarao na sessao

        p = (Participante) request.getSession().getAttribute("usuario");
        p.setCEP(cpf);


        // colocar o cpf obrigatoriamente no bd deles
        sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?");
        sbUrl.append("method=\"AlterUser\"&parameter=\"");
        sbUrl.append(p);
        sbUrl.append("\"");
        url = new URL(sbUrl.toString());
        conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);


        // verificar se o email ja foi cadastrado
        String[] erros = con.insereParticipante(p);
        statusMessage(request, erros);
        request.getSession().setAttribute("usuario", p);



        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/confirmacaoCadastroUsuario.jsp");
        rd.forward(request, response);

    }

    /**
     *
     * Altera as informacoes de um usuario no site. Equivalente a opcao 1.
     */
    /*private void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, TagITDAOException {

    Participante p = (Participante) request.getSession().getAttribute("usuario");

    String nome = request.getParameter("nome");
    String senha = request.getParameter("atual");
    String cpf = request.getParameter("cpf");
    String novaSenha = request.getParameter("senha");

    p.setNome(nome);
    p.setCPF(cpf);

    ConexaoBD con = ConexaoBD.getInstance();
    // verificar senha do participante logado no BD

    if ( con.verificarSenha(senha) ) {
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

    }*/

    private void buscarEventosParticipante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User participante = (User) request.getSession().getAttribute("usuario");

        ArrayList<Evento> eventos = null;
        String[] erros = null;
        try {

            eventos = ConexaoBD.getInstance().buscarEventosParticipante(participante);
            request.getSession().setAttribute("eventos", eventos);

        } catch (TagITDAOException ex) {
            Logger.getLogger(ManutencaoUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/ExibirEventosParticipante.jsp");
        rd.forward(request, response);

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
