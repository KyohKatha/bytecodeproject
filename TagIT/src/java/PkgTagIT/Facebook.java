/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PkgTagIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

/**
 *
 * @author 317624
 */
public class Facebook extends RedeSocial {

    private String fbToken;
    private String fbId;
    final private String server = "https://graph.facebook.com/";
    final private String serverMetodo = "https://api.facebook.com/method/";

    public Facebook(String token, String fbId) {
        super(2, "Facebook");
        this.fbToken = token;
        this.fbId = fbId;
    }

    public Facebook(String fbId) {
        super(2, "Facebook");
        this.fbId = fbId;
    }

    public String getToken() {
        return this.fbToken;
    }

    public String getFbId() {
        return fbId;
    }


    /**
     * @param para id do facebook para quem deseja mandar a mensagem. Se for postar no próprio mural, mandar uma string vazia
     * @param u usuario logado
     * @param postagem texto a ser postado
     * @throws MalformedURLException
     * @throws IOException
     */
    public void publicarFacebook(String para, Participante u, String postagem) throws MalformedURLException, IOException {
        postagem = aaTag.aaTagFunctions.UrlEncode(postagem);
        URL url = new URL(serverMetodo + "stream.publish?message=" + postagem + "&target_id=" + para + "&uid=" + fbId + "&access_token=" + fbToken);
        System.out.println(url);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        in.close();
    }


    /**
     * @param e evento que deseja publicar no facebook
     * @param descricao descrição do evento
     * @throws IOException
     * @throws TagITDAOException
     */
    public void cadastrarEvento(Evento e, String descricao)
            throws IOException, TagITDAOException {
        System.out.println("Metodo cadastrar evento");
        String jsonEvento = "";
        jsonEvento = "{\"name\":\"" + e.getNome() + "\",";
        jsonEvento += "\"start_time\":\"" + e.getDataEvento() + "T" + e.getHora() + "+08\",";
        jsonEvento += "\"description\":\"" + descricao + "\",";
        jsonEvento += "\"street\":\"" + e.getRua() + "\",";
        jsonEvento += "\"city\":\"" + e.getCidade() + "\",";
        System.out.println("jsonEvento" + jsonEvento);
        String contato = e.getContato();
        if (contato.contains("@")) {
            jsonEvento += "\"email\":\"" + contato + "\"}";
        } else {
            jsonEvento += "\"phone\":\"" + contato + "\"}";
        }
        System.out.println("jsonEvento" + jsonEvento);
        System.out.println(serverMetodo + "events.create?event_info=" + aaTag.aaTagFunctions.UrlEncode(jsonEvento)  + "&uid=" + fbId + "&access_token=" + fbToken + "&format=json");
        URL url = new URL(serverMetodo + "events.create?event_info=" + aaTag.aaTagFunctions.UrlEncode(jsonEvento) + "&uid=" + fbId + "&access_token=" + fbToken +"&format=json");
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;
        String idEvento = "";
        while ((linha = in.readLine()) != null) {
            idEvento = idEvento + linha;
            System.out.println(idEvento);
        }
        System.out.println(e.getNome());
        ConexaoBD.getInstance().atualizarFacebookEvento(idEvento, e.getNome());
        in.close();
    }


    /**
     * @param idEvento id do envento cadastrado no facebook
     * @param nomeEvento
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws TagITDAOException
     */
    public boolean removerEvento(String idEvento, String nomeEvento) throws MalformedURLException, IOException, TagITDAOException {
        URL url = new URL(serverMetodo + "events.cancel?eid=" + idEvento + "&access_token=" + fbToken + "&format=json");
        System.out.println(url);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;
        String texto = "";
        while ((linha = in.readLine()) != null) {
            texto = texto + linha;
            System.out.println(texto);
        }
        in.close();
        if (texto.equals("true")) {
            ConexaoBD.getInstance().atualizarFacebookEvento("", nomeEvento);
            return true;
        }
        return false;
    }

    public String pegarLinkFoto() {
        return server + "me/picture?access_token=" + fbToken + "&type=large";
    }

    
    public String localizacaoAtual() throws MalformedURLException, IOException {
        String json = pegarCamposUsuario("current_location");
        System.out.println(json);
        Object obj = JSONValue.parse(json);
        String cidade = "";
        try {
            JSONArray array = (JSONArray) obj;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject) array.get(i);
                JSONObject obj3 = (JSONObject) obj2.get("current_location");
                cidade = obj3.get("city").toString() + "," + obj3.get("state").toString();
            }
        } catch (Exception e) {
            json = pegarCamposUsuario("hometown_location");
            obj = JSONValue.parse(json);
            try {
                JSONArray array = (JSONArray) obj;
                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj2 = (JSONObject) array.get(i);
                    JSONObject obj3 = (JSONObject) obj2.get("hometown_location");
                    cidade = obj3.get("city").toString() + ", " + obj3.get("state").toString();
                }
            } catch (Exception e2) {
            }
        }
        return cidade;
    }

    private String pegarCamposUsuario(String campo) throws MalformedURLException, IOException {
        String strUrl = serverMetodo + "users.getInfo?uids=" + fbId + "&fields=" + campo + "&access_token=" + fbToken + "&format=json";
        System.out.println(strUrl);
        URL url = new URL(strUrl);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;
        String jsonRetorno = "";
        while ((linha = in.readLine()) != null) {
            jsonRetorno = jsonRetorno + linha;
        }
        in.close();
        return jsonRetorno;
    }

    public boolean salvarInteresses(String email)
            throws IOException, TagITDAOException {
        /* A partir do token, pegamos do facebook os interesses do usuário */
        System.out.println("Salvar interesses do usuário");
        try {
            URL url2 = new URL(server + "me/likes?access_token=" + fbToken);
            URLConnection urlConnection2 = (URLConnection) url2.openConnection();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));

            String linha = null;
            String texto = "";
            while ((linha = in2.readLine()) != null) {
                texto = texto + linha;
            }

            in2.close();
            ArrayList<Interesse> lInteresse;
            ArrayList<ContaCategoriaFacebook> lContaCateg;
            lContaCateg = new ArrayList<ContaCategoriaFacebook>();
            lInteresse = new ArrayList<Interesse>();
            String jsonInteresse = texto;
            jsonInteresse = jsonInteresse.substring(8, jsonInteresse.length() - 1);
            Object obj = JSONValue.parse(jsonInteresse);
            JSONArray array = (JSONArray) obj;
            // cada objeto json da lista, coloco em uma outra lista de interesses
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject) array.get(i);
                Interesse l = new Interesse();
                System.out.println(traduzirInglesParaPortugues(obj2.get("category").toString().replace('_', ' ')));
                l.setCategoria(traduzirInglesParaPortugues(obj2.get("category").toString().replace('_', ' ')));
                l.setNome(obj2.get("name").toString());
                lInteresse.add(l);
                int contador = 0;
                // se a categoria do interesse já está na lista, aumento o seu contador
                for (int j = 0; j < lContaCateg.size(); j++) {
                    if (lContaCateg.get(j).getCategoria().equals(l.getCategoria())) {
                        lContaCateg.get(j).setContador(lContaCateg.get(j).getContador() + 1);
                        break;
                    } else {
                        contador++;
                    }
                }
                if (contador == lContaCateg.size()) {
                    ContaCategoriaFacebook c;
                    c = new ContaCategoriaFacebook();
                    c.setCategoria(l.getCategoria());
                    c.setContador(1);
                    lContaCateg.add(c);
                }
            }
            ConexaoBD conexaoBD = ConexaoBD.getInstance();
            conexaoBD.insereListaInteresseParticipante(lInteresse, email, lContaCateg);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String pegarSexoUsuario() throws MalformedURLException, IOException {
        System.out.println("Metodo pegar sexo do usuario");
        String json = pegarCamposUsuario("sex");
        System.out.println(json);
        String sexo = null;
        Object obj = JSONValue.parse(json);
        try {
            JSONArray array = (JSONArray) obj;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject) array.get(i);
                sexo = obj2.get("sex").toString();
            }
        } catch (Exception e) {
        }
        return sexo;
    }

    public String traduzirInglesParaPortugues(String texto) throws Exception{
        return Translate.translate(texto, Language.ENGLISH, Language.PORTUGESE);
    }
}
