/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PkgTagIT;

/**
 *
 * @author 317624
 */
public class Categoria {
    private double id;
    private String nome;

    public Categoria(double id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
