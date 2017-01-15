package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.allef.cadfirebase.Helper.Base64Custon;
import com.developer.allef.cadfirebase.Model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class CadActivity extends AppCompatActivity {

    private EditText no,em,se;
    private Button ca;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);
        ca = (Button)findViewById(R.id.cadd);
        em = (EditText)findViewById(R.id.txtemail);
        no = (EditText)findViewById(R.id.txtnome);
        se = (EditText)findViewById(R.id.txtsenha);
        firebaseAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(no.getText().toString());
                usuario.setEmail(em.getText().toString());
                usuario.setSenha(se.getText().toString());

                if(no.getText().toString().isEmpty() || em.getText().toString().isEmpty() || se.getText().toString().isEmpty()){
                    Toast.makeText(CadActivity.this,"Impossivel Cadastrar,Existem campos Eem Branco !",Toast.LENGTH_LONG).show();
                }else{
                    cadastroUsuario();
                }



            }
        });


    }

    private void cadastroUsuario() {
        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser(); // pegando o usuario cadastrado
                    String id = Base64Custon.converterBase64(usuario.getEmail()); //
                    usuario.setid(id);
                    usuario.cFire();
                    Toast.makeText(CadActivity.this,"Cadastro realizado Com Sucesso !!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CadActivity.this,LoginActivity.class);
                    startActivity(intent);



                }else{
                    Toast.makeText(CadActivity.this,"Impossivel Cadastrar Usuario !! "+task.getException() ,Toast.LENGTH_LONG).show();
                    Limpar();

                }
            }
        });



    }

    private void Limpar() {
        em.setText("");
        se.setText("");
        no.setText("");

    }

}
