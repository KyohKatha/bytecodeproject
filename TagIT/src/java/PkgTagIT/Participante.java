/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PkgTagIT;

import java.util.ArrayList;
import aaTag.User;
/**
 *
 * @author 317624
 */
public class Participante extends User {

    private ArrayList<Evento> evento;
    private ArrayList<RedeSocial> redeSocial; //Os tokens do usuario

    public Participante( String email, String nome, ArrayList<Evento> evento, ArrayList<RedeSocial> redeSocial ) {
        super ( email, nome );
        this.evento = evento;
        this.redeSocial = redeSocial;
    }

    public Participante(){

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

    public void addRedeSocial(RedeSocial rs){
        this.redeSocial.add(rs);
    }
}
