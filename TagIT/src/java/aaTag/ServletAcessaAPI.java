/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

//PAREI NO ALTER EVENT
import PkgTagIT.ConexaoBD;
import PkgTagIT.TagITDAOException;
import com.sun.jndi.toolkit.url.Uri;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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
 * @author Gustavo
 */
public class ServletAcessaAPI extends HttpServlet {

    private String server = "http://app.aatag.com/aatag_api/api";
    private String consumerSecret = "102199176251671921271547424441971801362061712351484185";
    private String tokenAcessarAPI, verifierAcessoAPI;
    private String paginaRetorno;

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

        String retornoServer = "http://localhost:8080/TagIT/ServletAcessaAPI?metodo=realizarLogin&retorno=Servidor";
        String metodo = request.getParameter("metodo");
        String redireciona = request.getParameter("redireciona");
        this.paginaRetorno = null;
        this.paginaRetorno = request.getParameter("paginaRetorno");

        if (metodo.equals(enmMethods.AddApplication.toString())) {
            this.addApplication(request, response);
        } else if (metodo.equals(enmMethods.AddEvent.toString())) {
            this.addEvent(request, response);
        } else if (metodo.equals(enmMethods.AddRegister.toString())) {
            this.addRegister(request, response);
        } else if (metodo.equals(enmMethods.AddTag.toString())) {
            this.addTag(request, response);
        } else if (metodo.equals(enmMethods.AlterApplication.toString())) {
            this.addApplication(request, response);
        } else if (metodo.equals(enmMethods.AlterEvent.toString())) {
            this.alterEvent(request, response);
        } else if (metodo.equals(enmMethods.AlterUser.toString())) {
            this.alterUser(request, response);
        } else if (metodo.equals(enmMethods.GetApplication.toString())) {
            this.getApplication(request, response);
        } else if (metodo.equals(enmMethods.GetEvent.toString())) {
            this.getEvent(request, response);
        } else if (metodo.equals(enmMethods.GetEvents.toString())) {
            this.getEvents(request, response);
        } else if (metodo.equals(enmMethods.GetTag.toString())) {
            this.getTag(request, response);
        } else if (metodo.equals(enmMethods.GetTagInfo.toString())) {
            this.getTagInfo(request, response);
        } else if (metodo.equals(enmMethods.GetTags.toString())) {
            this.getTags(request, response);
        } else if (metodo.equals(enmMethods.GetUser.toString())) {
            this.getUser(request, response);
        } else if (metodo.equals(enmMethods.GetUserInfo.toString())) {
            this.getUserInfo(request, response);
        } else if (metodo.equals(enmMethods.GetUserRegisters.toString())) {
            this.getUserRegisters(request, response);
        } else if (metodo.equals(enmMethods.InactivateTag.toString())) {
            this.inactivateTag(request, response);
        } else if (metodo.equals(enmMethods.PostFacebook.toString())) {
            this.postFacebook(request, response);
        } else if (metodo.equals(enmMethods.PostTwitter.toString())) {
            this.postTwitter(request, response);
        } else if (metodo.equals(enmMethods.RemoveEvent.toString())) {
            this.removeEvent(request, response);
        } else if (metodo.equals(enmMethods.RemoveTag.toString())) {
            this.removeTag(request, response);
        } else if (metodo.equals("realizarLogin")) {
            String retorno = request.getParameter("retorno");

            if (retorno == null || retorno.equals("")) {
                this.pegarToken(request, response, retornoServer + "&paginaRetorno=" + paginaRetorno + "&redireciona=sim");
                this.fazerLogin(request, response, tokenAcessarAPI);
            } else {
                this.tokenAcesso(request, response, tokenAcessarAPI, verifierAcessoAPI, paginaRetorno);
            }
        }

        if (redireciona != null && redireciona.equals("sim")) {
            RequestDispatcher req = request.getRequestDispatcher(this.paginaRetorno);
            req.forward(request, response);
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

    // <editor-fold defaultstate="collapsed" desc="Funcoes Auxiliares da Servlet">
    public aaTagOAuth inicialiarOAuth(HttpServletRequest request, HttpServletResponse response) {
        String leitura = request.getParameter("leitura"); //Atributo que indica se veio do site web ou do middleware
        String token = null, verifier = null;

        if(leitura != null && leitura.equals("sim")){
            token = (String) request.getParameter("token");
            verifier = (String) request.getParameter("verifier");
        }else{
            token = (String) request.getSession().getAttribute("token");
            verifier = (String) request.getSession().getAttribute("verifier");
        }

        aaTagOAuth objOAtuh = new aaTagOAuth();
        objOAtuh.getOauth().setToken(token);
        objOAtuh.getOauth().setVerifier(verifier);
        objOAtuh.getOauth().setCallback(this.paginaRetorno);

        try {
            objOAtuh.getOauth().setSignature(objOAtuh.getSignature(consumerSecret, new Uri("http://api.aatag.com")));
        } catch (MalformedURLException ex) {
            System.out.println("Excecao na hr de fazer getSignature no getUser");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Excecao na hr de fazer getSignature no getUser");
            ex.printStackTrace();
        }

        return objOAtuh;
    }

    public aaTagOAuth inicialiarOAuth(String tokenAcessoUsuario, String verifierAcessoUsuario, String paginaRetornoUsuario) {
        aaTagOAuth objOAtuh = new aaTagOAuth();
        objOAtuh.getOauth().setToken(tokenAcessoUsuario);
        objOAtuh.getOauth().setVerifier(verifierAcessoUsuario);
        objOAtuh.getOauth().setCallback(paginaRetornoUsuario);

        try {
            objOAtuh.getOauth().setSignature(objOAtuh.getSignature(consumerSecret, new Uri("http://api.aatag.com")));
        } catch (MalformedURLException ex) {
            System.out.println("Excecao na hr de fazer getSignature no getUser");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Excecao na hr de fazer getSignature no getUser");
            ex.printStackTrace();
        }

        return objOAtuh;
    }

    public void imprimir(HttpServletRequest request, HttpServletResponse response, Object obj, String email) {

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletAcessaAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj.getClass() == User.class) {
            User user = (User) obj;

            if (email != null && email.equals("sim")) {
                out.write(user.getEmail());
            } else {
                out.write(user.getNome() + "\n" + user.getFantasia() + "\n" + user.getCPF() + "\n" + user.getRG() + "\n" + user.getCNPJ() + "\n" + user.getIE() + "\n" + user.getIM() + "\n" + user.getLogradouro() + "\n" + user.getNrEnd() + "\n" + user.getComplemento() + "\n" + user.getBairro() + "\n" + user.getCidade() + "\n" + user.getEstado() + "\n" + user.getPais() + "\n" + user.getCEP() + "\n" + user.getEmail() + "\n" + user.getFoto());
            }
        } else if (obj.getClass() == Event.class) {
            Event event = (Event) obj;
            out.write("NOME EVENTO: " + event.getName());
        } else if (obj.getClass() == Tag.class) {
            Tag tag = (Tag) obj;
            out.write("Public code: " + tag.getPublicCode() + " nivel de acesso " + tag.getAccessLevel());
        }

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funcoes do Login">
    public void pegarToken(HttpServletRequest request, HttpServletResponse response, String callback) {
        aaTagRequest req = new aaTagRequest(server);

        aaTagOAuth bs = new aaTagOAuth();
        bs.getOauth().setCallback(callback);

        try {
            bs.getOauth().setSignature(bs.getSignature(consumerSecret, new Uri("http://app.aatag.com")));
        } catch (MalformedURLException ex) {
            System.out.println("Excecao na hr de pegar a assinatura");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Excecao na hr de pegar a assinatura");
            ex.printStackTrace();
        }

        try {
            bs.setJSON(req.getRequestToken(bs));
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Excecao na hr de setar o JSON");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Excecao na hr de setar o JSON");
            ex.printStackTrace();
        }

        this.tokenAcessarAPI = bs.getOauth().getToken();
        this.verifierAcessoAPI = bs.getOauth().getVerifier();

    }

    public void fazerLogin(HttpServletRequest request, HttpServletResponse response, String token) {
        aaTagRequest req = new aaTagRequest(server);

        try {
            response.sendRedirect(req.getLinkAutorize(token));
        } catch (Exception ex) {
            System.out.println("Excecao na hr de fazer o login!");
            ex.printStackTrace();
        }
    }

    public void tokenAcesso(HttpServletRequest request, HttpServletResponse response, String token, String verifier, String callback) {
        aaTagOAuth objOAuht = new aaTagOAuth();
        objOAuht.getOauth().setToken(token);
        objOAuht.getOauth().setVerifier(verifier);
        objOAuht.getOauth().setCallback(callback);

        try {
            objOAuht.getOauth().setSignature(objOAuht.getSignature(consumerSecret, new Uri("http://api.aatag.com")));
        } catch (Exception ex) {
            System.out.println("Excecao na hr de fazer a assinatura de novo");
            ex.printStackTrace();
        }

        aaTagRequest req = new aaTagRequest(server);

        try {
            objOAuht.setJSON(req.getAcessToken(objOAuht));
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Excecao na hr de chamar a funcao acessToken");
        } catch (IOException ex) {
            System.out.println("Excecao na hr de chamar a funcao acessToken");
        }

        if (objOAuht.getOauth().getCallback_confirmed()) {
            String tokenAcesso = objOAuht.getOauth().getToken();
            String verifierAcesso = objOAuht.getOauth().getVerifier();

            request.getSession().setAttribute("token", tokenAcesso);
            request.getSession().setAttribute("verifier", verifierAcesso);

            System.out.println("TOKEN PEGO NA API: " + token + " VERIFIER " + verifierAcesso);

            try {
                RequestDispatcher requestDis = request.getRequestDispatcher(callback);
                requestDis.forward(request, response);
            } catch (Exception ex) {
                System.out.println("DEU PAU NA HR D ENCAMINHAR");
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funcoes da API">
    private void addApplication(HttpServletRequest request, HttpServletResponse response) {
        String Name = request.getParameter("nome");
        String Description = request.getParameter("descricao");
        String Organization = request.getParameter("organizacao");
        String WebSite = request.getParameter("website");

        Application aplicacao = new Application(Name, Description, Organization, WebSite, enmAccessType.ReadAndWrite);
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.AddApplication(objOAuth, aplicacao);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    private void addEvent(HttpServletRequest request, HttpServletResponse response) {
        String nomeEvento = request.getParameter("nomeEvento");
        String descricaoEvento = request.getParameter("descricaoEvento");

        Event evento = new Event(nomeEvento, descricaoEvento);
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.AddEvent(objOAuth, evento);
        } catch (Exception ex) {
            sucesso = false;
        }

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    //É necessario passar o codigo grande da tag
    private void addRegister(HttpServletRequest request, HttpServletResponse response) {
        String nomeEvento = request.getParameter("nomeEvento");
        String codTag = request.getParameter("codTag");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.AddRegister(objOAuth, nomeEvento, codTag);
        } catch (Exception ex) {
            sucesso = false;
        }

        request.getSession().setAttribute("sucesso", sucesso.toString());

    }

    private void addTag(HttpServletRequest request, HttpServletResponse response) {
        String PublicCode = request.getParameter("codigoPublico");
        int AccessLevel = Integer.parseInt(request.getParameter("accessLevel"));
        int Visibility = Integer.parseInt(request.getParameter("visibility"));

        Tag tag = new Tag(PublicCode, AccessLevel, Visibility);
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.AddTag(objOAuth, tag);
        } catch (Exception ex) {
            sucesso = false;
        }

        request.getSession().setAttribute("sucesso", sucesso.toString());

    }

    private void alterEvent(HttpServletRequest request, HttpServletResponse response) {
        String nomeEventoAntigo = request.getParameter("nomeEventoAntigo");
        String nomeNovoEvento = request.getParameter("nomeNovoEvento");
        String descricaoNovoEvento = request.getParameter("descricaoNovoEvento");

        Event evento = new Event(nomeNovoEvento, descricaoNovoEvento);
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.AlterEvent(objOAuth, evento, nomeEventoAntigo);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());

    }

    private void alterUser(HttpServletRequest request, HttpServletResponse response) {
        User usuario = (User) request.getSession().getAttribute("usuario");
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);

        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.AlterUser(objOAuth, usuario);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("usuario", usuario);
        request.getSession().setAttribute("sucesso", sucesso);

    }

    private void getApplication(HttpServletRequest request, HttpServletResponse response) {
        String nomeAplicacao = request.getParameter("nomeAplicacao");
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.GetApplication(objOAuth, nomeAplicacao);
        } catch (Exception ex) {
            sucesso = false;
        }

        if (sucesso) {
            Application aplicacao = (Application) ret.getReturn();
            request.getSession().setAttribute("aplicacao", aplicacao);
        }
    }

    private void getEvent(HttpServletRequest request, HttpServletResponse response) {
        String nomeEvento = request.getParameter("nomeEvento");
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);

        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetEvent(objOAuth, nomeEvento);
        } catch (Exception ex) {
            sucesso = false;
        } 

        if (sucesso) {
            Event evento = (Event) ret.getReturn();
            request.getSession().setAttribute("eventoAPI", evento);
        }
    }

    private void getEvents(HttpServletRequest request, HttpServletResponse response) {
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);

        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetEvents(objOAuth);
        } catch (Exception ex) {
            sucesso = false;
        } 
        if (sucesso) {
            ArrayList arrayListEventos = (ArrayList) ret.getReturn();
            request.getSession().setAttribute("arrayListEventos", arrayListEventos);
        }
    }

    private void getTag(HttpServletRequest request, HttpServletResponse response) {
        String codPublicoTAG = request.getParameter("codPublicoTAG");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetTag(objOAuth, codPublicoTAG);
        } catch (Exception ex) {
            sucesso = false;
        }

        if (sucesso) {
            Tag tag = (Tag) ret.getReturn();
            request.getSession().setAttribute("tag", tag);
        }
    }

    private void getTagInfo(HttpServletRequest request, HttpServletResponse response) {
        String codTag = request.getParameter("codTag");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetTagInfo(objOAuth, codTag);
        } catch (Exception ex) {
            sucesso = false;
        }
        
        if (sucesso) {
            Tag tag = (Tag) ret.getReturn();
            request.getSession().setAttribute("tag", tag);
        }
    }

    private void getTags(HttpServletRequest request, HttpServletResponse response) {
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetTags(objOAuth);
        } catch (Exception ex) {
            sucesso = false;
        }

        if (sucesso) {
            ArrayList arrayListTag = (ArrayList) ret.getReturn();
            request.getSession().setAttribute("arrayListTag", arrayListTag);
        }
    }

    //SE NAo PASSAR O CODPUBLICTAG, retorna do usuario logado na api!
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        String codTag = request.getParameter("codTag");
        String email = request.getParameter("email");

        aaTagOAuth objOAuth = objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            if (codTag == null || codTag.equals("")) {
                ret = req.GetUser(objOAuth);
            } else {
                ret = req.GetUser(objOAuth, codTag);
            }
        } catch (Exception ex) {
            sucesso = false;
        }

        if (sucesso) {
            User user = (User) ret.getReturn();
            request.getSession().setAttribute("usuario", user);
            this.imprimir(request, response, user, email);
        }
    }

    private void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tokenAcessoUsuario = request.getParameter("tokenAcesso");
        String verifierAcessoUsuario = request.getParameter("verifierAcesso");

        aaTagOAuth objOAuth = this.inicialiarOAuth(tokenAcessoUsuario, verifierAcessoUsuario, this.paginaRetorno);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            ret = req.GetUserInfo(objOAuth);
        } catch (Exception ex) {
            sucesso = false;
        }
        
        if (sucesso) {
            User user = (User) ret.getReturn();
            request.getSession().setAttribute("usuario", user);
            String cpf = (String) request.getSession().getAttribute("cpf");

            if (cpf != null) {
                user.setCPF(cpf);
                request.getSession().removeAttribute("cpf");
            }

            ConexaoBD conexao;
            try {
                conexao = ConexaoBD.getInstance();
                conexao.insereParticipante(user);
            } catch (TagITDAOException ex) {
                request.getSession().setAttribute("sucesso", Boolean.FALSE.toString());
            }
        }
    }

    //METODO NAO TERMINADOOO!!
    private void getUserRegisters(HttpServletRequest request, HttpServletResponse response) {
//        String nomeUser = request.getParameter("nomeUser");
//        String emailUser = request.getParameter("emailUser");
//
//        User user = new User();
//        user.setNome(nomeUser);
//        user.setEmail(emailUser);
//
//        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
//        aaTagMethods req = new aaTagMethods(server);
//        aaTagReturn ret = null;
//
//        try {
//            ret = req.GetUserRegisters(objOAuth, user);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(ServletAcessaAPI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ServletAcessaAPI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (ret.getSuccess()) {
//            /// String volta = ret.getReturn().toString();
//            //  String volta2 = ret.getReturnJSON();
//
//            System.out.println("VOLTA 1 ");
//            System.out.println("VOLTA 2 ");
//
//            //String sub = volta.substring(0, volta.length() - 1);
//            //sub += ",\"class\":\"aaTag.Event\"}";
//
//            // Event evento = (Event) JSON.Deserialize( sub, new Event());
//            // request.getSession().setAttribute("evento", evento);
//        }
    }

    private void inactivateTag(HttpServletRequest request, HttpServletResponse response) {
        String codPublicoTag = request.getParameter("codPublicoTag");
        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.InactivateTag(objOAuth, codPublicoTag);
        } catch (Exception ex) {
            sucesso = false;
        }

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    private void postTwitter(HttpServletRequest request, HttpServletResponse response) {
        String mensagem = request.getParameter("mensagem");
        String codTag = request.getParameter("codTag");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;
        
        try {
            if (codTag == null || codTag.equals("")) {
                ret = req.PostTwitter(objOAuth, mensagem);
            } else {
                ret = req.PostTwitter(objOAuth, mensagem, codTag);
            }
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    private void removeEvent(HttpServletRequest request, HttpServletResponse response) {
        String nomeEvento = request.getParameter("nomeEvento");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.RemoveEvent(objOAuth, nomeEvento);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    private void removeTag(HttpServletRequest request, HttpServletResponse response) {
        String codTag = request.getParameter("codTag");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.RemoveTag(objOAuth, codTag);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }

    private void postFacebook(HttpServletRequest request, HttpServletResponse response) {
        String mensagem = request.getParameter("mensagem");

        aaTagOAuth objOAuth = this.inicialiarOAuth(request, response);
        aaTagMethods req = new aaTagMethods(server);
        aaTagReturn ret = null;
        Boolean sucesso = true;

        try {
            ret = req.PostFacebook(objOAuth, mensagem);
        } catch (Exception ex) {
            sucesso = false;
        } 

        request.getSession().setAttribute("sucesso", sucesso.toString());
    }
    //</editor-fold>
}
