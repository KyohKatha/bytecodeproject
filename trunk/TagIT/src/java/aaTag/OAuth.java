/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

/**
 *
 * @author Gustavo
 */
public class OAuth {

    private String consumer_key = "23912720574251281157183245230812071251317922219624122";  // Chave da aplicação
    private String token;
    private String token_secret;
    private String verifier;
    private String signature_method = "HMAC-SHA1"; // Sempre "HMAC-SHA1"
    private String signature; // Explicação deste campo logo abaixo
    private String timestamp;  // Total em segundos deste 01/01/1970, vai dar um número inteiro
    private String nonce; // Nonce qualquer valor randomico entre 123400 e 9999999
    private String callback; // link da sua aplicação para retorno do meu sistema
    private Boolean callback_confirmed; // Informa se a comunicação teve sucesso
    private String version = "1.0"; // Sempre "1.0"
    private String method; // Nome do método que deverá ser acessado
    private Object[] parameters; // Lista de parâmetros do método

    // <editor-fold defaultstate="collapsed" desc="Construtor">
    public OAuth(){
        this.token = null;
        this.token_secret = null;
        this.verifier = null;
        this.signature = null;
        this.timestamp = null;
        this.nonce = null;
        this.callback = null;
        this.callback_confirmed = false;
        this.method = null;
        this.parameters = null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Boolean getCallback_confirmed() {
        return callback_confirmed;
    }

    public void setCallback_confirmed(Boolean callback_confirmed) {
        this.callback_confirmed = callback_confirmed;
    }

    public String getConsumer_key() {
        return consumer_key;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature_method() {
        return signature_method;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_secret() {
        return token_secret;
    }

    public void setToken_secret(String token_secret) {
        this.token_secret = token_secret;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getVersion() {
        return version;
    }

    public void setConsumer_key(String consumer_key) {
        this.consumer_key = consumer_key;
    }

    public void setSignature_method(String signature_method) {
        this.signature_method = signature_method;
    }

    public void setVersion(String version) {
        this.version = version;
    }

   

    //</editor-fold>
}
