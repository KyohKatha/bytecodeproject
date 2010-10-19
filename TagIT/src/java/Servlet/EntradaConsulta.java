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
        try {

            /*
             * Tipos
             * 1 - Entrada no evento
             * 2 - Consulta de usuarios no evento
             *
             */

            int tipo = Integer.parseInt(request.getParameter("tipo"));
            switch(tipo) {
                case 1:
                    try {
                        EntradaEvento(request, response);
                    } catch(TagITDAOException e) {
                        out.println(e.getMessage());
                    } /*catch (Exception e) {
                        out.println("Ocorreu um erro");
                    }*/
                    break;
                case 2:
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

    private void EntradaEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TagITDAOException {

        String tag = request.getParameter("tag");
        String evento = request.getParameter("evento");
        String email = pegaEmail(tag);


        ConexaoBD.getInstance().entradaEvento(email, evento);
        response.getWriter().println("O participante da Tag: " + tag + " acabou de entrar");

    }

    private String pegaEmail(String tag)
            throws ServletException, IOException, TagITDAOException {

        StringBuilder sbUrl;
        URL url;
        HttpURLConnection conexao;
        BufferedReader in = null;

        sbUrl = new StringBuilder("http://localhost:8080/TagIT/ServletAcessaAPI?metodo=GetUser&codTag=");
        sbUrl.append(tag);
        sbUrl.append("&leitura=sim");
        sbUrl.append("&redireciona=nao");
        System.out.println(sbUrl.toString());
        url = new URL(sbUrl.toString());
        conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestProperty("Request-Method", "POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        return in.readLine();

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
