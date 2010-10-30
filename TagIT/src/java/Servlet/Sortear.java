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

import java.io.IOException;
import java.io.PrintWriter;
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
}
