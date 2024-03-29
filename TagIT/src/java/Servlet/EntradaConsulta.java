/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.Facebook;
import PkgTagIT.TagITDAOException;
import aaTag.Event;
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
import javax.swing.ImageIcon;

import com.rosaloves.bitlyj.Url;
import com.rosaloves.bitlyj.UrlClicks;
import static com.rosaloves.bitlyj.Bitly.*;

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
        String token = null;
        String verifier = null;
        String tokenVerifier;
        String[] listaToken;
        try {

            /*
             * Tipos
             * 1 - Entrada no evento
             * 2 - Login
             * 3 - Retorno Login
             * 4 - Retorna Token
             * 5 - Retorno Entrada no Evento
             * 6 - Pegar eventos
             */

            int tipo = Integer.parseInt(request.getParameter("tipo"));
            tag = request.getParameter("tag");
            //pega o token na hash



            switch (tipo) {
                case 1:
                    try {
                        entradaEvento(request, response);
                    } catch (TagITDAOException e) {
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
                    verifier = (String) request.getSession().getAttribute("verifier");
                    try {
                        ConexaoBD.getInstance().guardaToken(tag, token, verifier);
                        out.println("Login feito com sucesso.");
                    } catch (Exception e) {
                        //nao usa
                    }

                    break;
                case 4:
                    try {
                        tokenVerifier = ConexaoBD.getInstance().retornaToken(tag);
                        if (tokenVerifier != null) {
                            listaToken = tokenVerifier.split(";");
                            token = listaToken[0];
                            verifier = listaToken[1];
                        }
                    } catch (Exception e) {
                    }
                    out.println(token);
                    out.println(verifier);
                    break;
                case 5:
                    out.println((String) request.getSession().getAttribute("sucesso"));
                    break;
                case 6:
                    token = request.getParameter("token");
                    verifier = request.getParameter("verifier");
                    String urlApi = "http://localhost:8080/TagIT/ServletAcessaAPI?metodo=GetEvents&leitura=sim&token=" + token + "&verifier=" + verifier + "&redireciona=sim&paginaRetorno=EntradaConsulta?tipo=7";
                    System.out.println(urlApi);
                    response.sendRedirect(urlApi);
                    break;
                case 7:
                    Event evento;
                    ArrayList arrayListEvents = (ArrayList) request.getSession().getAttribute("arrayListEventos");
                    out.println(arrayListEvents.size());
                    for (int i = 0; i < arrayListEvents.size(); i++) {
                        evento = (Event) arrayListEvents.get(i);
                        out.println(evento.getName());
                    }
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
        final String evento = request.getParameter("evento");
        String token = request.getParameter("token");
        String verifier = request.getParameter("verifier");
        ArrayList<String> dadosTag = pegaDadosTag(tag, token, verifier);
        final String email = dadosTag.get(15);
        String nome = dadosTag.get(0);
        final Facebook fb = ConexaoBD.getInstance().retornarFacebookUsuario(email);
        final String emailCodificado, eventoCodificado;

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

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        if (in.readLine().compareTo("true") != 0) {
            response.getWriter().println("Erro ao registrar entrada na API");
        } else {
            System.out.println("Email: " + email + " Evento: " + evento);

            /* joga o armazenamento no BD em um thread pra
             (tentar) diminuir o tempo de etrada */
            (new Thread() {

                @Override
                public void run() {
                    try {
                        ConexaoBD.getInstance().entradaEvento(email, evento);
                    } catch (Exception e) {
                        //nada
                    }
                }

            }).start();
            response.getWriter().println(nome);

            if (fb != null) {
                response.getWriter().println(fb.pegarLinkFoto());

                //postar o link do sorteio se tiver sorteio :D
                emailCodificado = encodeURIcomponent(email);
                eventoCodificado = encodeURIcomponent(evento);


                /* tambem coloca em uma thread */
                (new Thread(){

                    @Override
                    public void run() {
                        try {
                            Provider p = as("bytecodeufscar", "R_31c53ca04d1f4a404bdbd0a4b048c46d");


                            /* hospedei uma página num servidor que mostra
                             * a confirmacao conforme o email e evento enviado
                             * só pra nao ficar localhost
                             */
                            Url urlBitLy = p.call(shorten("http://tagit.orgfree.com/?email="
                                    + email + "&evento=" + evento));

                            fb.publicarFacebook("", null, "Clique aqui para me ajudar no sorteio "
                                    + "do evento " + evento + "! " + urlBitLy.getShortUrl());
                        } catch (Exception e) {
                            //nao postou
                        }
                    }

                }).start();
            } else {
                response.getWriter().println("null");
            }
        }

    }

    private ArrayList<String> pegaDadosTag(String tag, String token, String verifier)
            throws ServletException, IOException, TagITDAOException {

        StringBuilder sbUrl;
        URL url;
        HttpURLConnection conexao;
        //BufferedReader in = null;
        String entrada;
        ArrayList<String> lstEntrada = new ArrayList<String>();

        sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?metodo=GetUser&codTag=");
        sbUrl.append(tag);
        sbUrl.append("&redireciona=nao");
        sbUrl.append("&leitura=sim");
        sbUrl.append("&token=");
        sbUrl.append(token);
        sbUrl.append("&verifier=");
        sbUrl.append(verifier);
        System.out.println(sbUrl.toString());
        url = new URL(sbUrl.toString().trim());
        conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        while (null != (entrada = in.readLine())) {
            //entrada = in.readLine();
            System.out.println(entrada);
            lstEntrada.add(entrada);
        }

        return lstEntrada;

    }

    private String encodeURIcomponent(String s) {
        StringBuilder o = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (isUnsafe(ch)) {
                o.append('%');
                o.append(toHex(ch / 16));
                o.append(toHex(ch % 16));
            } else {
                o.append(ch);
            }
        }
        return o.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0) {
            return true;
        }
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
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
