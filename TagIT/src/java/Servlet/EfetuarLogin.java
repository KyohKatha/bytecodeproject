/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katha
 */
public class EfetuarLogin extends HttpServlet {

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
        String retorno = request.getParameter("retornoLog");
        String urlRetorno = "EfetuarLogin?retornoLog=Servidor";
        String servlet = "ServletAcessaAPI?metodo=realizarLogin&paginaRetorno=" + urlRetorno;
        
        if (retorno == null || retorno.equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher(servlet);
            rd.forward(request, response);
        } else {
            String token = (String) request.getSession().getAttribute("token");
            String verifier = (String) request.getSession().getAttribute("verifier");
            this.inicializarUsuarioSessao(request, response, token, verifier);
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

    private void inicializarUsuarioSessao(HttpServletRequest request, HttpServletResponse response, String token, String verifier) throws ServletException, IOException {
        String paginaRetorno = "index.jsp";
        String servlet = "ServletAcessaAPI?metodo=GetUserInfo&redireciona=sim&paginaRetorno=" + paginaRetorno + "&tokenAcesso=" + token + "&verifierAcesso=" + verifier;
        RequestDispatcher rd = request.getRequestDispatcher(servlet);
        rd.forward(request, response);
    }
}
