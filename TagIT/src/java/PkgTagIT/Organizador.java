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

    public Organizador(double id,String nome, String email, String senha, double cpf, boolean upgrade, int tentivasUpgrade, ArrayList<Evento> evento, ArrayList<RedeSocial> redeSocial) {
        super(id, email,nome, senha, cpf, upgrade, tentivasUpgrade, evento, redeSocial);
    }
    
}
