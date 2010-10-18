/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

import com.sun.jndi.toolkit.url.Uri;
import java.security.MessageDigest;
import java.util.Date;

/**
 *
 * @author Gustavo
 */
public class aaTagOAuth {

    private OAuth oauth;

    public aaTagOAuth() {
        this.oauth = new OAuth();
    this.oauth.setNonce(getNonce());
        this.oauth.setTimestamp(getTimeStamp());
    }

    private String getNonce() {
        int retorno = (int) (Math.random() * 9876599) + 123400;
        return retorno + "";

    }

    private String getTimeStamp() {
        Date date = new Date();
        long tempoEmMilisegundos = date.getTime();
        tempoEmMilisegundos /= 1000;
        return tempoEmMilisegundos + "";
    }

    
    private String getSignatureBase(Uri url) throws ExcecaoArgumento {
        if (oauth.getToken() == null) {
            oauth.setToken("");
        }

        if (oauth.getToken_secret() == null) {
            oauth.setToken_secret("");
        }
        
        if (oauth.getConsumer_key() == null || oauth.getConsumer_key().equals("")) {
            throw new ExcecaoArgumento("consumerKey");
        }

        if (oauth.getSignature_method() == null || oauth.getSignature_method().equals("")) {
            throw new ExcecaoArgumento("signatureType");
        }
        
        return "GET"
                + this.encodeURIcomponent(url.toString())
                + getNormalizeParams();
    }

    //callback, costumer_key, nonce, signature method, time_stamp, version
    //callback, costumer_key, nonce, signature_method, time_stamp, token, verifier, version
    //callback, costumer_key, nonce, signature_method, time_stamp, token, verifier, version , status
    private String getNormalizeParams() {
        String Params =
                "callback=" + oauth.getCallback() + "&"
                + "consumer_key=" + oauth.getConsumer_key() + "&"
                + "nonce=" + oauth.getNonce() + "&"
                + "signature_method=" + oauth.getSignature_method() + "&"
                + "timestamp=" + oauth.getTimestamp() + "&";

        if (oauth.getToken() != null && !(oauth.getToken().equals(""))) {
            Params += "token=" + oauth.getToken() + "&";
        }

        if (oauth.getVerifier() != null && !(oauth.getVerifier().equals(""))) {
            Params += "verifier=" + oauth.getVerifier() + "&";
        }

        Params += "version=" + oauth.getVersion() + "&";

        return this.encodeURIcomponent(Params);
    }

    public String getSignature(String consumer_secret, Uri url) throws Exception {
        String Conteudo = this.getSignatureBase(url);
         
        byte[] dataBuffer = Conteudo.getBytes("ASCII");
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(dataBuffer);
        
        byte[] hashBytes = md.digest(dataBuffer);
        String Signature = new sun.misc.BASE64Encoder().encode(hashBytes);
        return Signature;
    }

    /// Verifica se a assinatura desta estruruta está com o conteudo devido
    public Boolean CompareSignature(String consumer_secret, Uri url) throws Exception {
        return oauth.getSignature().equals(getSignature(consumer_secret, url));
    }

    /// Transforma o objeto base OAuth em script JSON
    public String getJSON() {
        return JSON.Serialize(this.oauth);
    }

    /// Transforma o script JSON em objeto OAuth
    public void setJSON(String script) {
        String sub = script.substring(0, script.length() - 1);
        sub += ",\"class\":\"aaTag.OAuth\"}";
        System.out.println("VAIII MANDDARR " + sub);
        this.oauth = (OAuth) JSON.Deserialize(sub, new OAuth());
    }

    // <editor-fold defaultstate="collapsed" desc="Funcoes URI.EscapeUriString">
    public String encodeURIcomponent(String s) {
        StringBuilder o = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (isUnsafe(ch)) {
                o.append('%');
                o.append(toHex(ch / 16));
                o.append(toHex(ch % 16));
            } else {
                o.append(ch);
            }
        }
        return o.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0) {
            return true;
        }
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }

 
   // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public OAuth getOauth() {
        return oauth;
    }

    public void setOauth(OAuth oauth) {
        this.oauth = oauth;
    }
    //</editor-fold>
}
