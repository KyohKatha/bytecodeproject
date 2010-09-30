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
public class Participante {
    private double id;
    private String email;
    private String nome;
    private String senha;
    private double cpf;
    private boolean upgrade;
    private int tentivasUpgrade;
    private ArrayList<Evento> evento;
    private ArrayList<RedeSocial> redeSocial; //Os tokens do usuario

    public Participante(double id, String email, String nome, String senha, double cpf, boolean upgrade, int tentivasUpgrade, ArrayList<Evento> evento, ArrayList<RedeSocial> redeSocial) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.upgrade = upgrade;
        this.tentivasUpgrade = tentivasUpgrade;
        this.evento = evento;
        this.redeSocial = redeSocial;
    }

    // Construtor utilizado apenas no cadastro
    public Participante(String email, String nome, String senha, double cpf ) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getCpf() {
        return cpf;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTentivasUpgrade() {
        return tentivasUpgrade;
    }

    public void setTentivasUpgrade(int tentivasUpgrade) {
        this.tentivasUpgrade = tentivasUpgrade;
    }

    public boolean getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(boolean upgrade) {
        this.upgrade = upgrade;
    }

    public ArrayList<Evento> getEvento() {
        return evento;
    }

    public void setEvento(ArrayList<Evento> evento) {
        this.evento = evento;
    }

    public ArrayList<RedeSocial> getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(ArrayList<RedeSocial> redeSocial) {
        this.redeSocial = redeSocial;
    }

    
}
