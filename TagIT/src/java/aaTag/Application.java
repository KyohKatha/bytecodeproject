/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

/**
 *
 * @author Gustavo Henrique
 */
public class Application {
    private String Name;
    private String Description;
    private String Organization;
    private String WebSite;
    private enmAccessType AccessType;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public Application(){ }

    public Application(String Name, String Description, String Organization, String WebSite, enmAccessType AccessType) {
        this.Name = Name;
        this.Description = Description;
        this.Organization = Organization;
        this.WebSite = WebSite;
        this.AccessType = AccessType;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public enmAccessType getAccessType() {
        return AccessType;
    }

    public void setAccessType(enmAccessType AccessType) {
        this.AccessType = AccessType;
    }

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

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String Organization) {
        this.Organization = Organization;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String WebSite) {
        this.WebSite = WebSite;
    }

    //</editor-fold>
}
