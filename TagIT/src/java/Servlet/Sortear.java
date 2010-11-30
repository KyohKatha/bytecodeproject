/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.Evento;
import PkgTagIT.TagITDAOException;
import com.rosaloves.bitlyj.Url;
import com.rosaloves.bitlyj.UrlClicks;
import static com.rosaloves.bitlyj.Bitly.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katha
 */
@WebServlet(name = "BitLy", urlPatterns = {"/BitLy"})
public class Sortear extends HttpServlet {

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
         * 0 : selecionar Evento
         * 1 : Criar Link
         * 2 : Sorteio
         */

        int tipo;

        try {
            tipo = Integer.parseInt(request.getParameter("tipo"));
            System.out.println(tipo);

            switch (tipo) {
                case 0:
                    try {
                        selecionarEvento(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Ja existe um evento com esse nome cadastrado!");
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        criarLink(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Erro ao criar Link");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        sortear(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Erro ao sortear");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try{
                        mostrarConfirmacao(request, response);
                    }  catch (TagITDAOException e) {
                        out.println("Erro ao sortear");
                        e.printStackTrace();
                    }
                    break;
            }
        } finally {
            out.close();
        }

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

    /*
     * Metodo pra setar o atributo eventoSorteio na sessao. Utilizado apenas na
     * hora em que o sorteio vai ser realizado
     */
    private void selecionarEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {

        int i = Integer.parseInt(request.getParameter("i"));
        ArrayList<Evento> eventos = null;
        eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventosOrganizador");
        request.getSession().setAttribute("eventoSorteio", eventos.get(i));

        System.out.println("Evento: " + eventos.get(i).getNome());

        RequestDispatcher rd = null;


        rd = request.getRequestDispatcher("/Sorteio.jsp");
        rd.forward(request, response);

        //String retorna = request.getSession().getAttribute("sucesso").toString();

        /*rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);*/
    }

    private void criarLink(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {

        String s = request.getParameter("link");

        Provider p = as("bytecodeufscar", "R_31c53ca04d1f4a404bdbd0a4b048c46d");
        Url url = p.call(shorten(s));
        UrlClicks clicks = p.call(clicks(url.getShortUrl()));

        request.setAttribute("shortenLink", url.getShortUrl());
        request.setAttribute("clicks", clicks.getUserClicks());

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/Sorteio.jsp");
        rd.forward(request, response);

    }

    private void sortear(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {

        Evento eventoSorteio = (Evento) request.getSession().getAttribute("eventoSorteio");

        /*
         * Pegar do bd o email das pessoas que estiveram no eventoSorteio
         * Pegar tambem a quantidade de ganhadores
         */

        ArrayList<String> emailPart = ConexaoBD.getInstance().retornarEntrada(eventoSorteio.getNome());
        int ganhadores = 1;

        ArrayList<String> emailPartRandom = new ArrayList<String>();
        ArrayList<String> sorteados = new ArrayList<String>();

        Provider p = as("bytecodeufscar", "R_31c53ca04d1f4a404bdbd0a4b048c46d");


        System.out.println("\n\nEMAILS:");
        // verificar quantos cliques cada link recebeu
        int size = emailPart.size();
        for (int i = 0; i < size; i++) {
            String email = emailPart.get(i);
            System.out.println(email);

            String link = "http://tagit.orgfree.com/?email=" + email + "&evento=" + eventoSorteio.getNome();

            Url url = p.call(shorten(link));
            UrlClicks clicks = p.call(clicks(url.getShortUrl()));
            long qntdClicks = clicks.getUserClicks();

            // inserir as hashcodes no vetor, de acordo com a quantidade de cliques
            for (int j = 0; j < qntdClicks; j++) {
                System.out.println(email);
                emailPart.add(email);
            }

        }


        // embaralhar os indices utilizando o random.org
        int max = emailPart.size() - 1;
        String urlEmbaralhar = "http://www.random.org/sequences/?min=0&max=" + max
                + "&col=1&format=plain&rnd=new";
        URL url = new URL(urlEmbaralhar);

        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;

        System.out.println("RANDOM LIST\n\n");
        while ((linha = in.readLine()) != null) {
            int i = Integer.parseInt(linha);
            System.out.println(i);
            emailPartRandom.add(emailPart.get(i));
        }


        // sortear um participante utilizando o random.org
        String urlSorteio = "http://www.random.org/integers/?num=" + ganhadores + "&min=0&max=" + max
                + "&col=1&base=10&format=plain&rnd=new";
        url = new URL(urlSorteio);

        urlConnection = (URLConnection) url.openConnection();
        in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        linha = null;
        while (emailPartRandom != null && (linha = in.readLine()) != null) {
            int i = Integer.parseInt(linha);
            String sorteado = emailPartRandom.get(i);
            System.out.println("\n\nSORTEADO: " + sorteado);
            /*
             * sorteados.add(sorteado);
             */

            // tirar esse ganhador da lista
            int cont = 0;
            while ( !emailPartRandom.isEmpty() && cont < emailPartRandom.size() ){
                if ( emailPartRandom.get(cont).compareTo(sorteado) == 0 ){
                    System.out.println(emailPartRandom.get(cont));
                    emailPartRandom.remove(cont);
                    cont--;
                }
                cont++;
            }

            System.out.println("\n\n\nTESTEE");
            for ( String email : emailPartRandom ){
                System.out.println(email);
            }
        }


        /* request.getSession.setAttribute("partSorteados", sorteados); */

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

    }

    private void mostrarConfirmacao(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException {
        String email = request.getParameter("email");
        String evento = request.getParameter("evento");

        request.setAttribute("email", email);
        request.setAttribute("evento", evento);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/Sorteio.jsp");
        rd.forward(request, response);
    }

}
