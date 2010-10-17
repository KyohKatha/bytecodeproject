/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

/**
 *
 * @author Gustavo
 */
public class aaTagReturn {

    private Boolean Success;
    private String ErrorMessage;
    private Object Return;
 
    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public aaTagReturn() {
    }

    public aaTagReturn(Object Obj) {
        this.Success = true;
        this.ErrorMessage = "";
        this.Return = Obj;
    }

    public aaTagReturn(Boolean Success, String ErrorMessage) {
        this.Success = Success;
        this.ErrorMessage = ErrorMessage;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gets e Sets">
    public String getReturnJSON() {
        System.out.println("ANTES  " + this.Return.toString());

        return JSON.Serialize(this.Return);
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public Object getReturn() {
        return Return;
    }

    public void setReturn(Object Return) {
        this.Return = Return;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean Success) {
        this.Success = Success;
    }

    //</editor-fold>
}
