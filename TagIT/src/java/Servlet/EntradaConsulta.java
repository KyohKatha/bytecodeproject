/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.TagITDAOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tiago
 */
public class EntradaConsulta extends HttpServlet {
   
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
        String tag;
        String token;
        String verififer;
        try {

            /*
             * Tipos
             * 1 - Entrada no evento
             * 2 - Login
             * 3 - Retorno Login
             * 4 - Retorna Token
             * 5 - Retorno Entrada no Evento
             */

            int tipo = Integer.parseInt(request.getParameter("tipo"));
            switch(tipo) {
                case 1:
                    try {
                        entradaEvento(request, response);
                    } catch(TagITDAOException e) {
                        out.println(e.getMessage());
                    } /*catch (Exception e) {
                        out.println("Ocorreu um erro");
                    }*/
                    break;
                case 2:
                    tag = request.getParameter("tag");
                    request.getSession().setAttribute("numeroTag", tag);
                    String urlRetorno = "EntradaConsulta?tipo=3";
                    String servlet = "ServletAcessaAPI?metodo=realizarLogin&paginaRetorno=" + urlRetorno;
                    RequestDispatcher rd = request.getRequestDispatcher(servlet);
                    rd.forward(request, response);
                    break;
                case 3:
                    tag = (String) request.getSession().getAttribute("numeroTag");
                    token = (String) request.getSession().getAttribute("token");
                    verififer = (String) request.getSession().getAttribute("verifier");
                    try {
                        ConexaoBD.getInstance().guardaToken(tag, token, verififer);
                        out.println("Login feito com sucesso.");
                    } catch (Exception e) {
                        //nao usa
                    }

                    break;
                case 4:
                    tag = request.getParameter("tag");
                    try {
                        String tokenVerifier = ConexaoBD.getInstance().retornaToken(tag);
                        System.out.println(tokenVerifier);
                        String lista[] = tokenVerifier.split(";");
                        out.println(lista[0]);
                        out.println(lista[1]);
                    } catch (Exception e) {
                        //nada
                    }
                    break;
                case 5:
                    out.println((String) request.getSession().getAttribute("sucesso"));
                    break;


            }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EntradaConsulta</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EntradaConsulta at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    }

    private void entradaEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String tag = request.getParameter("tag");
        String evento = request.getParameter("evento");
        ArrayList<String> dadosTag = pegaDadosTag(tag);
        String token = request.getParameter("token");
        String verifier = request.getParameter("verifier");
        String email = dadosTag.get(15);
        String nome = dadosTag.get(0);

        String acessoAPI = "http://localhost:8080/TagIT/ServletAcessaAPI?"
                + "metodo=AddRegister"
                + "&nomeEvento=" + evento
                + "&codTag=" + tag
                + "&leitura=sim"
                + "&token=" + token
                + "&verifier=" + verifier
                + "&redireciona=sim"
                + "&paginaRetorno=EntradaConsulta?tipo=5";


        URL url = new URL(acessoAPI);
        System.out.println(acessoAPI);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        conexao.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        if(in.readLine().compareTo("true") != 0) {
            response.getWriter().println("Erro ao registrar entrada na API");
        } else {
            System.out.println("Email: " + email + " Evento: " + evento);
            ConexaoBD.getInstance().entradaEvento(email, evento);
            response.getWriter().println("O participante : " + nome + " acabou de entrar");
        }

    }

    private ArrayList<String> pegaDadosTag(String tag)
            throws ServletException, IOException, TagITDAOException {

        StringBuilder sbUrl;
        URL url;
        HttpURLConnection conexao;
        BufferedReader in = null;
        String entrada;
        ArrayList<String> lstEntrada = new ArrayList<String>();

        sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?metodo=GetUser&codTag=");
        sbUrl.append(tag);
        sbUrl.append("&redireciona=nao");
        System.out.println(sbUrl.toString());
        url = new URL(sbUrl.toString());
        conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        while((entrada = in.readLine()) != null) {
            System.out.println(entrada);
            lstEntrada.add(entrada);
        }

        return lstEntrada;

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
