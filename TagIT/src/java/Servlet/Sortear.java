/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
@WebServlet(name="BitLy", urlPatterns={"/BitLy"})
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
                        //busca de evento
                        criarLink(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Erro ao criar Link");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try{
                        sortear(request, response);
                    } catch (TagITDAOException e) {
                        out.println("Erro ao sortear");
                        e.printStackTrace();
                    }
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
         * Pegar do bd as pessoas que estiveram no eventoSorteio (so as hashcodes? ou usar pair?)
         */

        ArrayList<String> hashPart = new ArrayList<String>();

        // para testes.
        hashPart.add("9eKH3W");
        hashPart.add("aYH0AA");
        hashPart.add("bxjCcW");
        hashPart.add("anWUQD");

        ArrayList<String> hashPartRandom = new ArrayList<String>();

        Provider p = as("bytecodeufscar", "R_31c53ca04d1f4a404bdbd0a4b048c46d");

        System.out.println("\n\nHASHCODES:");
        // verificar quantos cliques cada link recebeu
        int size = hashPart.size();
        for ( int i=0; i<size; i++ ){
            String hashcode = hashPart.get(i);
            System.out.println(hashcode);

            UrlClicks clicks = p.call(clicks(hashcode));
            long qntdClicks = clicks.getUserClicks();

            // inserir as hashcodes no vetor, de acordo com a quantidade de cliques
            for ( int j=0; j<qntdClicks; j++ ){
                System.out.println(hashcode);
                hashPart.add(hashcode);
            }

        }


        // embaralhar os indices utilizando o random.org
        int max = hashPart.size() - 1;
        String urlEmbaralhar = "http://www.random.org/sequences/?min=0&max=" + max +
                "&col=1&format=plain&rnd=new";
        URL url = new URL(urlEmbaralhar);

        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;

        System.out.println("RANDOM LIST\n\n");
        while ((linha = in.readLine()) != null) {
            int i = Integer.parseInt(linha);
            System.out.println(i);
            hashPartRandom.add(hashPart.get(i));
        }


        // sortear um participante utilizando o random.org
        String urlSorteio = "http://www.random.org/integers/?num=1&min=0&max=" + max +
                "&col=1&base=10&format=plain&rnd=new";
        url = new URL(urlSorteio);

        urlConnection = (URLConnection) url.openConnection();
        in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        linha = null;
        if ((linha = in.readLine()) != null) {
            int i = Integer.parseInt(linha);
            System.out.println("\n\nSORTEADO: " + hashPartRandom.get(i));

            /*
             * Pegar no bd quem eh o dono da hashcode? ou entao usar o pair lah em cima
             * request.getSession.setAttribute("partSorteado", -usuario-);
             */
        }

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/Sorteio.jsp");
        rd.forward(request, response);

    }
}
