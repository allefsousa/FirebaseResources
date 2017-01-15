package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.allef.cadfirebase.Helper.Base64Custon;
import com.developer.allef.cadfirebase.Helper.Preferencias;
import com.developer.allef.cadfirebase.Model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R2.id.txtemaillogin)
    EditText email1;
    @BindView(R2.id.txtsenhalogin)
    EditText senha;
    @BindView(R2.id.btncadastrar)
    Button cadastro;
    @BindView(R2.id.btnlogar)
    Button logar;
    @BindView(R2.id.tvrecsenha)
    TextView recuperarsenha;
    private Toast toast;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference firebaseDatabase;
    private ValueEventListener valueEventListener;
    private long lastBackPressTime = 0;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // pegando instancia do auth
        firebaseAuth = FirebaseAuth.getInstance();
        verificarUsuarioLogado();



        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadActivity.class);
                startActivity(intent);
            }
        });
        recuperarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RedefiniSenhaActivity.class);
                startActivity(intent);

            }
        });
        
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email1.getText().toString());
                usuario.setSenha(senha.getText().toString());
                if(email1.getText().toString().isEmpty() || senha.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Impossivel Logar, Existem Campos em Branco.",Toast.LENGTH_LONG).show();
                }else{
                    validandoLogin();
                }
                
            }
        });


    }

    private void validandoLogin() {
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            // convertendo bara receber o mesmo valor pra buscar na arvore de dados
            String usuarioLogado = Base64Custon.converterBase64(usuario.getEmail());
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {

                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(usuarioLogado);

                valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //pegando os dados trazidos do BD
                        usuario = dataSnapshot.getValue(usuario.getClass());
                        // verificando se o login foi executado com sucesso
                        if(task.isSuccessful()) {
                            // salvando a chave e o nome do usuario Logado em preferencias
                            String identificauserlogado = Base64Custon.converterBase64(usuario.getEmail());
                            Preferencias preferencias = new Preferencias(LoginActivity.this);
                            preferencias.salvarDados(identificauserlogado, usuario.getNome());

                            abrirTelaPrincipal();
                        }
                        if(!task.isSuccessful()){
                            firebaseUser.sendEmailVerification();
                            if(task.getException()instanceof FirebaseNetworkException){
                                Toast.makeText(LoginActivity.this, "Verifique sua Conexão Com a Internet.", Toast.LENGTH_LONG);


                            }
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(LoginActivity.this, "Impossivel Autenticar, Usuario e senha Invalidos !.", Toast.LENGTH_LONG);


                            }

                        }


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }

                };
                firebaseDatabase.addValueEventListener(valueEventListener);


            }



        });
    }

    private void verificarUsuarioLogado(){
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!= null){
            abrirTelaPrincipal();
        }else{

        }
    }

    private void abrirTelaPrincipal() {
        startActivity(new Intent(LoginActivity.this,CadHomeActivity.class));
        finish();

    }

    @Override
    protected void onStart() {


        super.onStart();
        //firebaseDatabase.addValueEventListener(valueEventListener);
    }

    //region Metodo que trata o botao fisico do dispositico o voltar.
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
            finish();

        }
    }
    //endregion
}
