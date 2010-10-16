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
    private String privateCode;
    private int AccessLevel;
    private enmVisibility Visibility;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public Tag(){ }

    public Tag(String privateCode, int AccessLevel, enmVisibility Visibility) {
        this.privateCode = privateCode;
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

    public enmVisibility getVisibility() {
        return Visibility;
    }

    public void setVisibility(enmVisibility Visibility) {
        this.Visibility = Visibility;
    }

    public String getPrivateCode() {
        return privateCode;
    }

    public void setPrivateCode(String privateCode) {
        this.privateCode = privateCode;
    }
    //</editor-fold>
    
}
