package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class CadHomeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_home);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater(); // metodo para criar menu
        menuInflater.inflate(R.menu.menu_main,menu); // inflando o menu em cima do menu paramentro
        return true;
    }
    @Override
    // evento de click do menu que direciona as acoes de cada botao do menu
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_sair:
                deslogarUsuario();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void deslogarUsuario(){

        firebaseAuth.signOut();
        Intent intent = new Intent(CadHomeActivity.this, LoginActivity.class);
        startActivity( intent );
        finish();

    }
}
