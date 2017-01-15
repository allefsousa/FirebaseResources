package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R2.id.txtemaillogin) EditText email1;
    @BindView(R2.id.txtsenhalogin) EditText senha;
    @BindView(R2.id.btncadastrar) Button cadastro;
    @BindView(R2.id.btnlogar) Button logar;
    @BindView(R2.id.tvrecsenha)TextView recuperarsenha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CadActivity.class);
                startActivity(intent);
            }
        });
        recuperarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RedefiniSenhaActivity.class);
                startActivity(intent);
            }
        });





    }
}
