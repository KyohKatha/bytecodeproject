/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

/**
 *
 * @author Gustavo
 */
public class ExcecaoArgumento extends Exception {

    public ExcecaoArgumento() {
    }

    public ExcecaoArgumento(String arg) {
        super(arg);
    }

    public ExcecaoArgumento(Throwable arg) {
        super(arg);
    }

    public ExcecaoArgumento(String arg, Throwable arg1) {
        super(arg, arg1);
    }
}
