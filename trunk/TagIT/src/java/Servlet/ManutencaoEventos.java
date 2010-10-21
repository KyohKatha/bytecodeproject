/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.Categoria;
import PkgTagIT.ConexaoBD;
import PkgTagIT.Evento;
import PkgTagIT.Participante;
import PkgTagIT.TagITDAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aaTag.*;
import javax.servlet.ServletContext;

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
         * 4 : buscar pelo organizador
         * 5 : seleciona evento
         * 6 : inscrever participante no evento
         * 7 : buscar todos eventos do organizador
         */

        int tipo;

        try {
            tipo = Integer.parseInt(request.getParameter("tipo"));

            switch (tipo) {
                case 0:
                    try {
                        cadastraEvento(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Ja existe um evento com esse nome cadastrado!");
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        //busca de evento
                        buscaEventos(request, response);
                    } catch (TagITDAOException ex) {
                        Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    //buscar pelo organizador
                    try {
                        out.println("entrei aqui");
                        //buscaEventosDoOrganizador(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 5:
                    try {
                        selecionarEvento(request, response);
                    } catch (TagITDAOException ex) {
                        Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 6:
                    try {
                        inscreverParticipanteEvento(request, response);
                    } catch (TagITDAOException ex) {
                        Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 7:
                    try{
                        buscarTodosEventosOrganizador(request, response);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        } finally {
            out.close();
        }
    }

    /**
     * Método que realiza o cadastro do evento do usuário no sistema
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws TagITDAOException
     */
    private void cadastraEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String nome = request.getParameter("nomeEvento");
        String descricao = request.getParameter("descricaoEvento");
        double vagasPrincipal = Double.parseDouble(request.getParameter("vagasPrincipal"));
        String inscInicio = request.getParameter("inscInicio");
        String inscTermino = request.getParameter("inscTermino");
        String rua = request.getParameter("rua");
        String numeroRua = request.getParameter("numeroRua");
        rua += ", " + numeroRua;
        String cidade = request.getParameter("cidade");
        String dataEvento = request.getParameter("dataEvento");
        String hora = request.getParameter("hora");
        String contato = request.getParameter("contato");
        String[] categoria = request.getParameterValues("categoria"); //ver como fazer a categoria
        
        ArrayList<Categoria> lstCategoria = new ArrayList<Categoria>();

        Participante usuarioLogado = (Participante) request.getSession().getAttribute("usuarioLogado");
        ConexaoBD conexaoBD = ConexaoBD.getInstance();

        if (categoria != null) {
            for (int i = 0; i < categoria.length; i++) {
                lstCategoria.add(new Categoria(categoria[i]));
            }
        }

        Evento evento = new Evento(nome, vagasPrincipal, inscInicio, inscTermino, rua, cidade, dataEvento, hora, contato, usuarioLogado, lstCategoria);
        RequestDispatcher rd = null;

        boolean retornou = statusMessage(request, conexaoBD.insereEvento(evento));
        if (retornou) {
            rd = request.getRequestDispatcher("/ServletAcessaAPI");

            request.setAttribute("nomeEvento", nome);
            request.setAttribute("descricaoEvento", descricao);
            request.setAttribute("metodo", "addEvent");
            request.setAttribute("redireciona", "sim");
            request.setAttribute("paginaRetorno", "home.jsp");

            rd.forward(request, response);

            String retorna = retorna = request.getSession().getAttribute("sucesso").toString();
            System.out.println("RETORNOU DA SERVLET ACESS AAPI >> " + retorna);

        } else {
            System.out.println("Retornará o erro >> " + request.getSession().getAttribute("message").toString());
            rd = request.getRequestDispatcher("/CadastrarEvento.jsp");
            rd.forward(request, response);
        }
    }


    /**
     * Método que irá retorna true ou false se a operação foi efetuada com sucesso retornando true ou não conseguiu realizar a operação retornando false, neste método já estão as respostas das operações
     * @param request Request para poder atribuir o status da operação realizada
     * @param message O status que retornou no banco de dados
     * @return True se conseguiu realizar a operação com sucesso, false se não conseguiu realizar
     */
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

    private void buscarTodosEventosOrganizador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        System.out.println("BUSCANDO TODOS OS EVENTOS");
        
        RequestDispatcher rd = null;

        //boolean retornou = statusMessage(request, conexaoBD.insereEvento(evento));
        //if (retornou) {
            rd = request.getRequestDispatcher("/ServletAcessaAPI");

            request.setAttribute("metodo", "getEvents");
            request.setAttribute("redireciona", "sim");
            request.setAttribute("paginaRetorno", "ExibirMeusEventos.jsp");

            rd.forward(request, response);

            String retorna = retorna = request.getSession().getAttribute("sucesso").toString();
            System.out.println("RETORNOU DA SERVLET ACESS AAPI >> " + retorna);

    }

    /*private void buscaEventosDoOrganizador(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, TagITDAOException {
    Participante organizador = (Participante) request.getSession().getAttribute("usuarioLogado");
    ArrayList<Evento> lstEventos = null;

    lstEventos = (ArrayList<Evento>) ConexaoBD.getInstance().buscaEventosdoOrganizador(organizador);

    response.getWriter().println("Número de eventos do organizador: " + lstEventos.size());

    }*/
    private void buscaEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TagITDAOException {
        String parametro = request.getParameter("parametro");

        ArrayList<Evento> eventos = null;
        String[] erros = null;


        eventos = (ArrayList<Evento>) ConexaoBD.getInstance().buscarEventos(parametro);
        request.setAttribute("eventos", eventos);

        request.setAttribute("parametro", parametro);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/buscarEvento.jsp");
        rd.forward(request, response);


    }

    private void inscreverParticipanteEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        System.out.println("INSCRICAO!!!!!!!!!!!");

        User participante = (User) request.getSession().getAttribute("usuario");
        Evento evento = (Evento) request.getSession().getAttribute("evento");

        System.out.println("Evento:" + evento.getNome());
        String[] erros = ConexaoBD.getInstance().inscreverParticipanteEvento(evento, participante);
        if (erros[0].equals("2")){
            request.getSession().setAttribute("type", "critical");
            request.getSession().setAttribute("message", "<p>- Você já está inscrito neste evento .</p><p>- Clique na caixa para fechar.</p>");
        } else
            statusMessage(request, erros);

        request.getSession().removeAttribute("ins");
        request.getSession().removeAttribute("evento");

        RequestDispatcher rd = null;

        rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);
    }

    private void selecionarEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        ArrayList<Evento> eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventos");
        int i = Integer.parseInt(request.getParameter("i"));
        String ins = request.getParameter("insc");

        System.out.println(i + eventos.get(i).getNome());

        request.getSession().setAttribute("evento", eventos.get(i));
        request.getSession().setAttribute("ins", ins);

        request.getSession().removeAttribute("eventos");

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
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
