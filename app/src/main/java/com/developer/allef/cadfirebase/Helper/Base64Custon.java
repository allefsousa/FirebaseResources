package com.developer.allef.cadfirebase.Helper;

import android.util.Base64;

/**
 * Created by Allef on 30/12/2016.
 */

public class Base64Custon {

    public static String converterBase64(String texto){
        // convertetendo o texto recebido com uma criptografia de base 64
        // Site https://www.base64decode.org/
        // passando o texto a ser convertido e a forma que sera convertido
        String textoConvertido = Base64.encodeToString(texto.getBytes(), Base64.DEFAULT);
        return textoConvertido.trim();
    }
    // metodo para decodificar ps emails criptografados
    public static String decodificarBase64(String textocodificado){
        byte [] bayteDecodificado = Base64.decode(textocodificado, Base64.DEFAULT);
        return new String(bayteDecodificado);
    }
}
