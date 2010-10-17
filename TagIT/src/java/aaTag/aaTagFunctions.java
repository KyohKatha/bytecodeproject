/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Gustavo
 */
public class aaTagFunctions {

    static final String unreservedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~ãõáéíóúàâêîôûüÃÕÁÉÍÓÚÀÂÊÎÔÛÜ";

    public static String UrlEncode(String value) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char symbol = value.charAt(i);
            if (unreservedChars.indexOf(symbol) != -1) {
                result = result.append(symbol);
            } else {
                result = result.append('%').append(String.format("%x", (int) symbol));
            }
        }

        return result.toString();
    }

    public static byte[] ConvertTextToBytes(String Text) throws UnsupportedEncodingException {
        return Text.getBytes("UTF-8");
    }
}
