package com.developer.allef.cadfirebase.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Allef on 15/01/2017.
 */

public class Preferencias {
    private Context context; // variavel para receber o contexto do paramentro
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "CadFire.preferencia"; // nome para passar no paramentro do sharedpreferences
    private final int MODE = 0; /* a forma de leitura desse arquivo do sharedpreferences // define que o
                                ** somente esse aplicativo tera acesso a esse arquivo de sharedpreferences   */
    private SharedPreferences.Editor editor; // variavel para editar itens de preferencia


    private final String CHAVE_IDENTIFICADOR = "identificadorUsuario";
    private final String CHAVE_NOME = "nome";

    public Preferencias(Context contextoPramentro){
        context = contextoPramentro; // atribuindo o contexto a variavel
        preferences = context.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit(); // fazendo a associação da preferencia com a variavel para que possa ser editada


    }


    public void salvarDados( String identificador,String nome ){
        editor.putString(CHAVE_IDENTIFICADOR, identificador);
        editor.putString(CHAVE_NOME, nome);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null );
    }

    public String getnome(){
        return preferences.getString(CHAVE_NOME, null );
    }

}
