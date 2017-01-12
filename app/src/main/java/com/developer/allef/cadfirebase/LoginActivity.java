package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText email,senha;
    private Button logar,cadastrar;
    private TextView recuperarsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.txtemaillogin);
        senha = (EditText)findViewById(R.id.txtsenhalogin);
        logar = (Button)findViewById(R.id.btnlogar);
        cadastrar = (Button)findViewById(R.id.btncadastrar);
        recuperarsenha = (TextView)findViewById(R.id.tvrecsenha);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(intent);
            }
        });





    }
}
