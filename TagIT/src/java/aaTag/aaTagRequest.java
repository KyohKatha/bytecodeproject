/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Gustavo
 */
public class aaTagRequest {

    private String Server;

    public aaTagRequest(String Server) {
        Server = Server.trim();
        if (Server.length() != 0 && Server.charAt(Server.length() - 1) != '/') {
            Server += "/";
        }

        this.Server = Server;
    }

    private String getResponse(String urlServidor, aaTagOAuth oauth) throws MalformedURLException, IOException {
       
        String mensagem = oauth.getJSON();
        String mensagemCodificada = aaTagFunctions.UrlEncode(mensagem);
        byte[] bytes = aaTagFunctions.ConvertTextToBytes(mensagemCodificada);

        URL url = new URL(urlServidor);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Request-Method", "POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream input = connection.getOutputStream();
        input.write(bytes, 0, bytes.length);
        connection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder newData = new StringBuilder(10000);
        String s = "";
        while (null != ((s = br.readLine()))) {
            newData.append(s);
        }
        input.close();
        br.close();

        return newData.toString();
    }

    public String getRequestToken(aaTagOAuth oauth) throws UnsupportedEncodingException, IOException {
        return getResponse(Server + "request_token.aspx", oauth);
    }

    public String getAcessToken(aaTagOAuth oauth) throws UnsupportedEncodingException, IOException {
        return getResponse(Server + "access_token.aspx", oauth);
    }

    public aaTagReturn getMethod(aaTagOAuth oauth, String objetoReturn, String tipoLista) throws UnsupportedEncodingException, IOException {
        String StrRet = getResponse(Server + "methods.aspx", oauth);
        String sub = StrRet.substring(0, StrRet.length() - 1);
        Object returnAtributo = null;

        if (objetoReturn.equals("User")) { //Pegar um usuario
            sub += ",\"class\":\"aaTag.User\"}";
            returnAtributo = (User) JSON.Deserialize(sub, new User());
        } else if (objetoReturn.equals("Event")) { //Pegar um evento
            sub += ",\"class\":\"aaTag.Event\"}";
            returnAtributo = (Event) JSON.Deserialize(sub, new Event());
        } else if (objetoReturn.equals("Tag")) { //Pegar os dados de uma TAG
            sub += ",\"class\":\"aaTag.Tag\"}";
            returnAtributo = (Tag) JSON.Deserialize(sub, new Tag());
        } else if (objetoReturn.equals("Application")) {
            sub += ",\"class\":\"aaTag.Application\"}";
            returnAtributo = (Application) JSON.Deserialize(sub, new Application());
        }else if(objetoReturn.equals("UserRegister")){
            sub += "]";
            returnAtributo = (Application) JSON.Deserialize(sub, new Register());
            Register a = (Register) returnAtributo;
            System.out.println("NOME DO EVENTO " + a.getEventName());
        }else if (objetoReturn.equals("ArrayList")) {
            sub += "]";
            ArrayList list = (ArrayList) JSON.Deserialize(sub, new LinkedList<Map>());
       
            ArrayList retorno = new ArrayList();

            for(int i = 0; i < list.size(); i++){
                HashMap obj = (HashMap) list.get(i);

                if(tipoLista.equals("Event")){
                    String nome = (String) obj.get("Name");
                    String descricacao = (String) obj.get("Description");
                    Event event = new Event(nome, descricacao);
                    retorno.add(event);
                }else if(tipoLista.equals("Tag")){
                    int AccesLevel = Integer.parseInt( obj.get("AccessLevel").toString() );
                    int Visibility = Integer.parseInt( obj.get("Visibility").toString() );
                    String PublicCode = (String) obj.get("PublicCode");
                    Tag tag = new Tag(PublicCode, AccesLevel, Visibility);
                    retorno.add(tag);                    
                }else if(tipoLista.equals("UserRegister")){
                    //System.out.println("USERRRRRR REGISTER!!! " + obj.toString());
                }
        
            }

            returnAtributo = retorno;
          }

        return new aaTagReturn(returnAtributo);

    }

    public String getLinkAutorize(String Token) {
        return Server + "authorize.aspx?token=" + Token;
    }

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getServer() {
        return Server;
    }

    public void setServer(String Server) {
        this.Server = Server;
    }
    //</editor-fold>
}
