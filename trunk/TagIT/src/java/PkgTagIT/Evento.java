/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PkgTagIT;

import java.util.ArrayList;

/**
 *
 * @author 317624
 */
public class Evento {
    private double id;
    private String nome;
    private double vagasPrincipal;
    private double vagasEspera;
    private String inscInicio;
    private String inscTermino;
    private String rua;
    private String cidade;
    private String dataEvento;
    private String contato;
    private Participante participante;
    private ArrayList<Categoria> categoria;

    public Evento(String nome, double vagasPrincipal, double vagasEspera, String inscInicio, String inscTermino, String rua, String cidade, String dataEvento, String contato, Participante participante, ArrayList<Categoria> categoria) {
        this.nome = nome;
        this.vagasPrincipal = vagasPrincipal;
        this.vagasEspera = vagasEspera;
        this.inscInicio = inscInicio;
        this.inscTermino = inscTermino;
        this.rua = rua;
        this.cidade = cidade;
        this.dataEvento = dataEvento;
        this.contato = contato;
        this.participante = participante;
        this.categoria = categoria;
    }

    public Evento(double id, String nome, double vagasPrincipal, double vagasEspera, String inscInicio, String inscTermino, String rua, String cidade, String dataEvento, String contato, Participante participante, ArrayList<Categoria> categoria) {
        this.id = id;
        this.nome = nome;
        this.vagasPrincipal = vagasPrincipal;
        this.vagasEspera = vagasEspera;
        this.inscInicio = inscInicio;
        this.inscTermino = inscTermino;
        this.rua = rua;
        this.cidade = cidade;
        this.dataEvento = dataEvento;
        this.contato = contato;
        this.participante = participante;
        this.categoria = categoria;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getInscInicio() {
        return inscInicio;
    }

    public void setInscInicio(String inscInicio) {
        this.inscInicio = inscInicio;
    }

    public String getInscTermino() {
        return inscTermino;
    }

    public void setInscTermino(String inscTermino) {
        this.inscTermino = inscTermino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public double getVagasEspera() {
        return vagasEspera;
    }

    public void setVagasEspera(double vagasEspera) {
        this.vagasEspera = vagasEspera;
    }

    public double getVagasPrincipal() {
        return vagasPrincipal;
    }

    public void setVagasPrincipal(double vagasPrincipal) {
        this.vagasPrincipal = vagasPrincipal;
    }

    public ArrayList<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(ArrayList<Categoria> categoria) {
        this.categoria = categoria;
    }

    public Participante getOrganizador() {
        return participante;
    }

    public void setOrganizador(Participante participante) {
        this.participante = participante;
    }

    
}
