/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

/**
 *
 * @author Gustavo Henrique
 */
public class User {
    private String Nome ;/*Name*/
    private String Fantasia ;
    private String CPF ;
    private String RG ;
    private String CNPJ ;
    private String IE ;
    private String IM ;
    private String Logradouro ;
    private String NrEnd ;
    private String Complemento ;
    private String Bairro ;
    private String Cidade ;/*City*/
    private String Estado ;/*State*/
    private String Pais ;/*Country*/
    private String CEP ;
    private String Email ;
    private String Foto ;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public User() { }

    public User(String Nome, String Fantasia, String CPF, String RG, String CNPJ, String IE, String IM, String Logradouro, String NrEnd, String Complemento, String Bairro, String Cidade, String Estado, String Pais, String CEP, String Email, String Foto) {
        this.Nome = Nome;
        this.Fantasia = Fantasia;
        this.CPF = CPF;
        this.RG = RG;
        this.CNPJ = CNPJ;
        this.IE = IE;
        this.IM = IM;
        this.Logradouro = Logradouro;
        this.NrEnd = NrEnd;
        this.Complemento = Complemento;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Pais = Pais;
        this.CEP = CEP;
        this.Email = Email;
        this.Foto = Foto;
    }
//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFantasia() {
        return Fantasia;
    }

    public void setFantasia(String Fantasia) {
        this.Fantasia = Fantasia;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getNrEnd() {
        return NrEnd;
    }

    public void setNrEnd(String NrEnd) {
        this.NrEnd = NrEnd;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
    //</editor-fold>
    
}
