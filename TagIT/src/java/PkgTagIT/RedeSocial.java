/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PkgTagIT;

/**
 *
 * @author 317624
 */
public abstract class RedeSocial {
    private double id;
    private String nome;

    public RedeSocial(double id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public abstract String getToken();


}
