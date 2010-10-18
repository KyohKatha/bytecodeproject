/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aaTag;

import flexjson.*;


/**
 *
 * @author Gustavo
 */
public class JSON {

    public static String Serialize(Object obj) {
        JSONSerializer serialize = new JSONSerializer();

        if(obj.getClass() == OAuth.class){
            return serialize.include("parameters").serialize(obj);
        }
        return serialize.serialize(obj);
    }

    public static Object Deserialize (String mensagem, Object obj){
        JSONDeserializer deserialize = new JSONDeserializer();
     //   System.out.println("ANTES DE desSERIALIZaR tipo do objeto: " + obj.getClass());
      //  System.out.println("VAI DESERIALIZAR " + mensagem);

        Object deserialized = null;

        deserialized = deserialize.deserialize(mensagem, obj.getClass());

    //    System.out.println("EH A CLASSE DO OJBETO: " + obj.getClass());
    //    System.out.println("EH DA CLASSE: " + deserialized.getClass());
   //     System.out.println("DESEREIALIZERD: " + deserialized.toString());

        return deserialized;
    }
}
