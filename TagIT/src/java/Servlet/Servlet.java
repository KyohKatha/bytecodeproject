package Servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;

/**
 *
 * @author Mariana
 */
public class Servlet extends HttpServlet {

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

            String teste = "https://graph.facebook.com/oauth/access_token?"
                            + "client_id=153940577969437&redirect_uri=http://localhost:8080/Teste/PegaTokenAcesso.jsp&"
                            + "client_secret=0a45f0bdb1682cde85fc8ee210b5b919&" + request.getSession().getAttribute("code");
                    String texto = "";

                    try {
                        URL url = new URL(teste);
                        URLConnection urlConnection = (URLConnection) url.openConnection();
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                        String line = null;

                        while ((line = in.readLine()) != null) {
                            texto = texto + line;
                        }

                        in.close();

                        URL url2 = new URL("https://graph.facebook.com/me/likes?" + texto);
                        URLConnection urlConnection2 = (URLConnection) url2.openConnection();
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));

                        String likes = null;
                        texto = "";
                        while ((likes = in2.readLine()) != null) {
                            texto = texto + likes;
                        }

                        in2.close();

                    } catch (Exception e) {
                        texto = e.toString();
                    }
                    //request.getSession().setAttribute("likes", texto);
            ArrayList<Likes> lLikes;
            lLikes = new ArrayList<Likes>();
            //String s = (String) request.getSession().getAttribute("likes");
            String s = texto;
            s = s.substring(8, s.length()-1);
            Object obj = JSONValue.parse(s);
            JSONArray array = (JSONArray) obj;
            for (int i = 0; i < array.size(); i++) {
                //System.out.println(array.get(i));

                JSONObject obj2 = (JSONObject) array.get(i);
                Likes l = new Likes();
                l.setCategory(obj2.get("category").toString());
                l.setName(obj2.get("name").toString());
                lLikes.add(l);
            }
            request.getSession().setAttribute("lLikes", lLikes);
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
