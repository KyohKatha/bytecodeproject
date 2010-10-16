/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aaTag;

import java.util.Date;

/**
 *
 * @author Gustavo Henrique
 */
public class Register {
    private Date Date;
    private User User;
    private String OwnerEvent;
    private String EventName;
    private String ApplicationName;

    // <editor-fold defaultstate="collapsed" desc="Construtor">
    public Register() { }

    public Register(Date Date, User User, String OwnerEvent, String EventName, String ApplicationName) {
        this.Date = Date;
        this.User = User;
        this.OwnerEvent = OwnerEvent;
        this.EventName = EventName;
        this.ApplicationName = ApplicationName;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String ApplicationName) {
        this.ApplicationName = ApplicationName;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public String getOwnerEvent() {
        return OwnerEvent;
    }

    public void setOwnerEvent(String OwnerEvent) {
        this.OwnerEvent = OwnerEvent;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }
    //</editor-fold>
}
