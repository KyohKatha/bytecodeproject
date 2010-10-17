/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

/**
 *
 * @author Gustavo Henrique
 */
public class Tag {
    private String PublicCode;
    private int AccessLevel;
    private int Visibility;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public Tag(){ }

    public Tag(String privateCode, int AccessLevel, int Visibility) {
        this.PublicCode = privateCode;
        this.AccessLevel = AccessLevel;
        this.Visibility = Visibility;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public int getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(int AccessLevel) {
        this.AccessLevel = AccessLevel;
    }

    public int getVisibility() {
        return Visibility;
    }

    public void setVisibility(int Visibility) {
        this.Visibility = Visibility;
    }

    public String getPublicCode() {
        return PublicCode;
    }

    public void setPublicCode(String privateCode) {
        this.PublicCode = privateCode;
    }
    //</editor-fold>
    
}
