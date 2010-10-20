/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import PkgTagIT.Interesse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getSession().getAttribute("codigo") != null) {
                /* Pega o código de autorização e pede o token do usuário*/
                String link = "https://graph.facebook.com/oauth/access_token?"
                        + "client_id=153940577969437&redirect_uri=http://localhost:8080/TagIT/PegaTokenAcesso.jsp&"
                        + "client_secret=0a45f0bdb1682cde85fc8ee210b5b919&" + request.getSession().getAttribute("codigo");
                URL url = new URL(link);
                URLConnection urlConnection = (URLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String linha = null;
                String texto = "";
                while ((linha = in.readLine()) != null) {
                    texto = texto + linha;
                }
                in.close();

                /* A partir do token, pegamos do facebook os interesses do usuário */
                URL url2 = new URL("https://graph.facebook.com/me/likes?" + texto);
                URLConnection urlConnection2 = (URLConnection) url2.openConnection();
                BufferedReader in2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));

                linha = null;
                texto = "";
                while ((linha = in2.readLine()) != null) {
                    texto = texto + linha;
                }

                in2.close();

                /* Crio uma lista de interesses do uSuário
                 * Esta lista está sendo criada apenas para exibir, para testes
                 * Talvez não haja necessidade de criar uma lista de usuários. Pode
                 * salvar no banco de dados direto.
                 */
                ArrayList<Interesse> lInteresse;
                lInteresse = new ArrayList<Interesse>();
                String jsonInteresse = texto;
                jsonInteresse = jsonInteresse.substring(8, jsonInteresse.length() - 1);
                Object obj = JSONValue.parse(jsonInteresse);
                JSONArray array = (JSONArray) obj;
                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj2 = (JSONObject) array.get(i);
                    Interesse l = new Interesse();
                    l.setCategoria(obj2.get("category").toString());
                    l.setNome(obj2.get("name").toString());
                    lInteresse.add(l);
                    /* Aqui deve se chamar a procedure para
                     * salvar no banco de dados os interesses do usuário */
                }
                request.getSession().setAttribute("lInteresses", lInteresse);
            }
            /* Está redirecionando para uma página somente para exibir os dados recuperados */
            response.sendRedirect("Interesses.jsp");
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
}
