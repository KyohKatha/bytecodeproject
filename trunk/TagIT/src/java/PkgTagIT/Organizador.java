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
public class Organizador extends Participante{

    public Organizador(double id, String email, String nome, String senha, String cpf, boolean upgrade, int tentivasUpgrade, ArrayList<Evento> evento, ArrayList<RedeSocial> redeSocial) {
        super(id, email,nome, senha, cpf, upgrade, tentivasUpgrade, evento, redeSocial);
    }

    // Construtor utilizado apenas no cadastro
    public Organizador(String email, String nome, String senha, String cpf ) {
        super(email, nome, senha, cpf);
    }
    
}
