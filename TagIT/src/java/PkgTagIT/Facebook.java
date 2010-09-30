/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PkgTagIT;

/**
 *
 * @author 317624
 */
public class Facebook extends RedeSocial{
    private String token;

    public Facebook(double id, String token) {
        super(id, "Facebook");
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
