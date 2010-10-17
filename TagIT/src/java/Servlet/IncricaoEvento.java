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
import java.util.ArrayList;
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

            int tipo = Integer.parseInt(request.getParameter("tipo"));

            System.out.println(tipo);

            if (tipo == 0){
                selecionarEvento(request, response);
            } else {
                inscreverParticipanteEvento(request, response);
            }
        } finally { 
            out.close();
        }
    }

    private void inscreverParticipanteEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException{
        Participante participante = (Participante) request.getSession().getAttribute("usuarioLogado");
        Evento evento = (Evento) request.getSession().getAttribute("evento");
        
        String[] erros = ConexaoBD.getInstance().inscreverParticipanteEvento(evento, participante);
        statusMessage(request, erros);
        RequestDispatcher rd = null;

        rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);
    }


    private void selecionarEvento(HttpServletRequest request, HttpServletResponse response) throws TagITDAOException, ServletException, IOException{
        ArrayList<Evento> eventos = (ArrayList<Evento>) request.getSession().getAttribute("eventos");
        int i = Integer.parseInt(request.getParameter("i"));

        System.out.println(i + eventos.get(i).getNome());

        request.getSession().setAttribute("evento", eventos.get(i));

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/IncricaoEvento.jsp");
        rd.forward(request, response);
    }


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
