/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.ConexaoBD;
import PkgTagIT.ContaCategoriaFacebook;
import PkgTagIT.Interesse;
import PkgTagIT.Participante;
import PkgTagIT.TagITDAOException;
import aaTag.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Mariana
 */
public class Facebook extends HttpServlet {

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
        System.out.println("Servlet do facebook");
        try {
            if (request.getSession().getAttribute("codigo") != null) {
                /* Pega o código de autorização e pede o token do usuário*/
                String link = "https://graph.facebook.com/oauth/access_token?"
                        + "client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp&"
                        + "client_secret=0a45f0bdb1682cde85fc8ee210b5b919&" + request.getSession().getAttribute("codigo");
                URL url = new URL(link);
                
                System.out.println(link);

                URLConnection urlConnection = (URLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String linha = null;
                String access_token = "";
                while ((linha = in.readLine()) != null) {
                    access_token = access_token + linha;
                }
                System.out.println(access_token);
                //in.close();
                aaTag.User u = (aaTag.User) request.getSession().getAttribute("usuario");
                System.out.println(u.getNome());
                access_token = access_token.substring(13);
                System.out.println("access_token: " + access_token);
                String id = "";
                boolean traco = false;
                for (int k = 16; k < access_token.length(); k++) {
                    if (access_token.charAt(k) == '-') {
                        traco = true;
                    } else if (access_token.charAt(k) == '|') {
                        break;
                    } else if (traco) {
                        id += access_token.charAt(k);
                    }
                }

                PkgTagIT.Facebook fb = new PkgTagIT.Facebook(0, access_token, id);
                System.out.println(fb.pegarLinkFoto());
                System.out.println(fb.pegarSexoUsuario());
                System.out.println("Localização: " + fb.localizacaoAtual());
                //u.addRedeSocial(fb);
                if (fb.salvarInteresses(u.getEmail())) {
                    request.getSession().setAttribute("usuario", u);
                    request.getSession().setAttribute("type", "success");
                    request.getSession().setAttribute("message", "<p>- Informações recuperadas com sucesso .</p><p>- Clique na caixa para fechar.</p>");
                } else {
                    request.getSession().setAttribute("type", "critical");
                    request.getSession().setAttribute("message", "<p>-<strong>Erro:</strong> As informações não puderam ser recuperadas. Tente novamente mais tarde.</p><p>- Clique na caixa para fechar.</p>");
                }
            }
        } catch (Exception e) {
            request.getSession().setAttribute("type", "critical");
            request.getSession().setAttribute("message", "<p>-<strong>Erro:</strong> As informações não puderam ser recuperadas. Tente novamente mais tarde.</p><p>- Clique na caixa para fechar.</p>");
            
        } finally {
            response.sendRedirect("index.jsp");
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
        try {
            processRequest(request, response);



        } catch (TagITDAOException ex) {
            Logger.getLogger(Facebook.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Facebook.class.getName()).log(Level.SEVERE, null, ex);
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
