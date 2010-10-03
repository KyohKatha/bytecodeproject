/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.Participante;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 317586
 */
public class ManutencaoUsuarios extends HttpServlet {

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
             * 0 : cadastrar
             * 1 : atualizar
             * 2 : remover
             * 3 : buscar
             */

            int tipo = Integer.parseInt(request.getParameter("tipo"));

            switch (tipo) {
                case 0:
                    try {
                        cadastrarUsuario(request, response);
                    } catch (Exception e) {

                        request.setAttribute("erro", true);

                        RequestDispatcher rd = null;
                        rd = request.getRequestDispatcher("/confirmacaoCadastroParticipante.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }

        } finally {
            out.close();
        }
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Participante p;

        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");

        p = new Participante(email, nome, senha, cpf);

        request.setAttribute("part", p);
        request.setAttribute("erro", false);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/confirmacaoCadastroParticipante.jsp");
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
