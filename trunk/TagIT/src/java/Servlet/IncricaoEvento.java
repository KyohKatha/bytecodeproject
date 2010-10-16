/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.Evento;
import PkgTagIT.Participante;
import PkgTagIT.TagITDAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mariana
 */
public class IncricaoEvento extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, TagITDAOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IncricaoEvento</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IncricaoEvento at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            inscreverParticipanteEvento(request, response);
        } finally { 
            out.close();
        }
    }

    private void inscreverParticipanteEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException{
        Participante participante = (Participante) request.getSession().getAttribute("usuarioLogado");
        Evento evento = (Evento) request.getSession().getAttribute("evento");

        String retorno = ConexaoBD.getInstance().inscreverParticipanteEvento(evento, participante);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (TagITDAOException ex) {
            Logger.getLogger(IncricaoEvento.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (TagITDAOException ex) {
            Logger.getLogger(IncricaoEvento.class.getName()).log(Level.SEVERE, null, ex);
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
