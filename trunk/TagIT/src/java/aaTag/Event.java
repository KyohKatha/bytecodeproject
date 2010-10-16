/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

/**
 *
 * @author Gustavo Henrique
 */
public class Event {
    private String Name;
    private String Description;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public Event(){ }

    public Event(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }
//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
//</editor-fold>
    
}
