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

/**
 *
 * @author 317624
 */
public class Facebook extends RedeSocial {

    private String fbToken;
    private String fbId;
    final private String server = "https://graph.facebook.com/";
    final private String serverMetodo = "https://api.facebook.com/method/";

    public Facebook(double id, String token, String fbId) {
        super(id, "Facebook");
        this.fbToken = token;
        this.fbId = fbId;
    }

    public Facebook(double id, String fbId) {
        super(id, "Facebook");
        this.fbId = fbId;
    }

    public String getToken() {
        return this.fbToken;
    }

    public String getFbId() {
        return fbId;
    }

    public void publicarFacebook(String para, Participante u, String postagem) throws MalformedURLException, IOException {
        postagem = aaTag.aaTagFunctions.UrlEncode(postagem);
        URL url = new URL(serverMetodo + "stream.publish?message=" + postagem + "&target_id=" + para + "&uid=" + fbId + "&access_token=" + fbToken);
        System.out.println(url);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        in.close();
    }

    public void cadastrarEvento(Evento e, String descricao)
            throws IOException, TagITDAOException {
        System.out.println("Metodo cadastrar evento");
        String jsonEvento = "";
        jsonEvento = "{\"name\":\"" + aaTag.aaTagFunctions.UrlEncode(e.getNome()) + "\",";
        jsonEvento += "\"start_time\":\"" + aaTag.aaTagFunctions.UrlEncode(e.getDataEvento() + "T" + e.getHora() + "+08") + "\",";
        jsonEvento += "\"description\":\"" + aaTag.aaTagFunctions.UrlEncode(descricao) + "\",";
        jsonEvento += "\"street\":\"" + aaTag.aaTagFunctions.UrlEncode(e.getRua()) + "\",";
        jsonEvento += "\"city\":\"" + aaTag.aaTagFunctions.UrlEncode(e.getCidade()) + "\",";

        String contato = e.getContato();
        if (contato.contains("@")) {
            jsonEvento += "\"email\":\"" + contato + "\"}";
        } else {
            jsonEvento += "\"phone\":\"" + contato + "\"}";
        }

        URL url = new URL(serverMetodo + "events.create?event_info" + jsonEvento + "&uid=" + fbId + "&access_token=" + fbToken);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;
        String idEvento = "";
        while ((linha = in.readLine()) != null) {
            idEvento = idEvento + linha;
        }
        in.close();

        /*
        Deve se salvar o id do evento
         */
    }

    public boolean removerEvento(String idEvento) throws MalformedURLException, IOException {
        URL url = new URL(serverMetodo + "events.cancel?eid=" + idEvento + "&access_token=" + fbToken);
        System.out.println(url);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String linha = null;
        String texto = "";
        while ((linha = in.readLine()) != null) {
            texto = texto + linha;
        }
        in.close();
        if (texto.equals("true")) {
            return true;
        }
        return false;
    }

    public String pegarLinkFoto() {
        return server + "me/picture?access_token=" + fbToken + "&type=large";
    }

    public aaTag.User atualizaLocalizacaoAtual(aaTag.User u) throws MalformedURLException, IOException {
        System.out.println("Metodo pegar cidade");
        String json = pegarCamposUsuario("current_location");
        System.out.println(json);
        Object obj = JSONValue.parse(json);
        try {
            JSONArray array = (JSONArray) obj;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject) array.get(i);
                JSONObject obj3 = (JSONObject) obj2.get("current_location");
                u.setEstado(obj3.get("state").toString());
                u.setPais(obj3.get("country").toString());
                u.setCidade(obj3.get("city").toString());
            }
        } catch (Exception e) {
            json = pegarCamposUsuario("hometown_location");
            obj = JSONValue.parse(json);
            try {
                JSONArray array = (JSONArray) obj;
                for (int i = 0; i < array.size(); i++) {
                    JSONObject obj2 = (JSONObject) array.get(i);
                    JSONObject obj3 = (JSONObject) obj2.get("hometown_location");
                    u.setEstado(obj3.get("state").toString());
                    u.setPais(obj3.get("country").toString());
                    u.setCidade(obj3.get("city").toString());
                }
            } catch (Exception e2) {
            }
        }
        return u;
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
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject) array.get(i);
                Interesse l = new Interesse();
                l.setCategoria(obj2.get("category").toString());
                l.setNome(obj2.get("name").toString());
                lInteresse.add(l);
                int contador = 0;
                for (int j = 0; j < lContaCateg.size(); j++) {
                    if (lContaCateg.get(j).getCategoria().equals(l.getCategoria())) {
                        lContaCateg.get(j).setContador(lContaCateg.get(j).getContador() + 1);
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
}
