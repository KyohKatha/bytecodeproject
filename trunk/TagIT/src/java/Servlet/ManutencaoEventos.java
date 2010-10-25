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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aaTag.*;
import java.util.List;

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
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("DENTROOOOOOOOOOOOOOOOOOOO DO MANUTENÇÃO EVENTOS");

        /*
         * 0 : cadastrar
         * 1 : atualizar
         * 2 : remover
         * 3 : buscar
         * 4 : buscar pelo organizador
         * 5 : seleciona evento
         * 6 : inscrever participante no evento
         * 7 : buscar todos eventos do organizador
         * 8 : buscar ultimos eventos
         * 9 : selecionar evento para exibir o relatorio das categorias
         * 9 : retornar todas as categorias do sistema
         */

        int tipo;

        try {
            tipo = Integer.parseInt(request.getParameter("tipo"));
            System.out.println(tipo);

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
                    System.out.println("TESTEEEE =DD");
                    try {
                        buscarTodosEventosOrganizador(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    System.out.println("Ultimos! =DD");
                    try {
                        buscarUltimosEventos(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    System.out.println("Ultimos! =DD");
                    try {
                        selecionarEventoRelatorio(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    try {
                        retornarTodasCategorias(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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
            throws ServletException, IOException, TagITDAOException, ParseException, Exception {

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
                System.out.println("CATEGORIA SELECIONADA NO EVENTO >>  " + categoria[i]);
            }
        }

        Evento evento = new Evento(nome, vagasPrincipal, inscInicio, inscTermino, rua, cidade, dataEvento, hora, contato, usuarioLogado, lstCategoria);
        RequestDispatcher rd = null;

        boolean retornou = statusMessage(request, conexaoBD.insereEvento(evento));
        if (retornou) {
            if (categoria != null) {
                for (int i = 0; i < categoria.length; i++) {
                    conexaoBD.insereCategoriaNoEvento(lstCategoria.get(i), evento);
                }
            }
            request.getSession().setAttribute("type", "success");
            request.getSession().setAttribute("message", "<p> - Operação realizada com <strong>sucesso</strong>.</p><p> Clique na caixa para fechá-lá</p>");

            String urlServidor = "ServletAcessaAPI?nomeEvento=" + nome + "&descricaoEvento=" + descricao + "&metodo=AddEvent&redireciona=sim&paginaRetorno=ManutencaoEventos?tipo=10";
            rd = request.getRequestDispatcher(urlServidor);
            rd.forward(request, response);

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
        /*rd = request.getRequestDispatcher("/ServletAcessaAPI");

        request.setAttribute("metodo", "getEvents");
        request.setAttribute("redireciona", "sim");
        request.setAttribute("paginaRetorno", "ExibirMeusEventos.jsp");

        rd.forward(request, response);

        String retorna = retorna = request.getSession().getAttribute("sucesso").toString();
        System.out.println("RETORNOU DA SERVLET ACESS AAPI >> " + retorna);*/
        String urlServidor = "ServletAcessaAPI?&metodo=GetEvents&redireciona=nao&paginaRetorno=ExibirMeusEventos.jsp";

        rd = request.getRequestDispatcher(urlServidor);
        rd.include(request, response);
        System.out.println("VOLTEI!!!!!");

        ArrayList<Evento> eventos = null;
        ArrayList<Evento> meusEventos = new ArrayList<Evento>();
        ArrayList<Event> eventosAPI = (ArrayList<Event>) request.getSession().getAttribute("arrayListEventos");
        for(int i = 0; i < eventosAPI.size(); i++){
            eventos = (ArrayList<Evento>) ConexaoBD.getInstance().buscarEventos(eventosAPI.get(i).getName());
            for(int j = 0; j < eventos.size(); j++){
                if(eventos.get(j).getNome().compareTo(eventosAPI.get(i).getName()) == 0){
                    meusEventos.add(eventos.get(j));
                    break;
                }
            }
        }
        rd = null;
        request.getSession().setAttribute("eventosOrganizador", meusEventos);

        rd = request.getRequestDispatcher("/ExibirMeusEventos.jsp");
        rd.include(request, response);

        //String retorna = request.getSession().getAttribute("sucesso").toString();
        
        /*} else {
        System.out.println("Retornará o erro >> " + request.getSession().getAttribute("message").toString());
        rd = request.getRequestDispatcher("/CadastrarEvento.jsp");
        rd.forward(request, response);
        }*/

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
        request.getSession().setAttribute("eventosBusca", eventos);

        request.setAttribute("parametro", parametro);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/buscarEvento.jsp");
        rd.forward(request, response);
    }

    private void inscreverParticipanteEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        User participante = (User) request.getSession().getAttribute("usuario");
        Evento evento = (Evento) request.getSession().getAttribute("eventoBusca");

        String[] erros = ConexaoBD.getInstance().inscreverParticipanteEvento(evento, participante);
        if (erros[0].equals("2")) {
            request.getSession().setAttribute("type", "critical");
            request.getSession().setAttribute("message", "<p>- Você já está inscrito neste evento .</p><p>- Clique na caixa para fechar.</p>");
        } else {
            statusMessage(request, erros);
        }

        request.setAttribute("modo", "busca");
        request.setAttribute("ins", "0");

        RequestDispatcher rd = null;

        rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);
    }

    private void selecionarEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        
        int i = Integer.parseInt(request.getParameter("i"));
        String ins = request.getParameter("insc");
        ArrayList<Evento> eventos = null;

        String modo = request.getParameter("modo");

        if (modo.compareTo("busca") == 0){
            eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventosBusca");
            request.getSession().setAttribute("eventoBusca", eventos.get(i));
        } else {
            eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventosInscrito");
            request.getSession().setAttribute("eventoInscrito", eventos.get(i));
        }

        request.setAttribute("modo", modo);

        request.setAttribute("ins", ins);
        System.out.println("Evento: " + eventos.get(i).getNome() + "ins: " + ins);

        RequestDispatcher rd = null;

        String urlServidor = "ServletAcessaAPI?&metodo=GetEvent&nomeEvento=" + eventos.get(i).getNome() + "&redireciona=sim&paginaRetorno=IncricaoEvento.jsp";

        rd = request.getRequestDispatcher(urlServidor);
        rd.forward(request, response);

        //String retorna = request.getSession().getAttribute("sucesso").toString();

        /*rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);*/
    }

    private void retornarTodasCategorias(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        System.out.println("DENTRO DO RETORNA CATEGORIAAAAAAAAAAAAAAAAAAAAS");
        ConexaoBD conexao = ConexaoBD.getInstance();
        List categorias = conexao.retornarTodasCategorias();

        request.setAttribute("categorias", categorias);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/CadastrarEvento.jsp");
        rd.forward(request, response);
        
    }

    private void buscarUltimosEventos(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        ArrayList<Evento> ultimosEventos = ConexaoBD.getInstance().buscarUltimosEventos();

        request.getSession().setAttribute("ultimosEventos", ultimosEventos);
        System.out.println("VOLTOU:"+ultimosEventos.get(0).getNome());
        RequestDispatcher rd = null;
        
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void selecionarEventoRelatorio(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        int i = Integer.parseInt(request.getParameter("i"));
        ArrayList<Evento> eventos = null;
        
        eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventosOrganizador");
        request.getSession().setAttribute("eventoOrganizador", eventos.get(i));
        ArrayList categorias = null;
        //DESCOMENTAR QUANDO ARRUMAR A PROCEDURE!!!!!
        categorias = ConexaoBD.getInstance().buscarCategoriasRanqueadas(eventos.get(i).getNome());
        /*for(int j = 0; j < eventos.get(i).getCategoria().size(); j++){
            categoria = ConexaoBD.getInstance().buscarCategoriasRanqueadas(eventos.get(i).getNome(), eventos.get(i).getCategoria().get(j).getNome());
            int id = (int) eventos.get(i).getCategoria().get(j).getId();
            categorias.set(id, categoria);
        }*/

        request.getSession().setAttribute("categorias", categorias);
        RequestDispatcher rd = null;

        rd = request.getRequestDispatcher("/RelatorioEvento.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ManutencaoEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
