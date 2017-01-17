package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.allef.cadfirebase.Model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class RedefiniSenhaActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth ;
    private Usuario usuario = new Usuario();
    @BindView(R2.id.txtsenharedefine) EditText email;
    @BindView(R2.id.recupera)Button recuperasenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefini_senha);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();


      usuario.setEmail(email.getText().toString());


        recuperasenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RedefiniSenhaActivity.this,"Email de Redefinição Enviado, Verifique sua caixa de Email.",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RedefiniSenhaActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });



    }
}
