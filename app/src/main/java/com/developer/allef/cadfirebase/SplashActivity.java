package com.developer.allef.cadfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //region Funçao responsavel por mostar a Splash e iniciar a uotra activity com uma nova tarefa e o timer
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));

            }

        }, 3000 );
        //endregion


        button = (Button)findViewById(R.id.btnsplash);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    // Metodo responsavel por finalizar a activity quando ela entrar em pausa
    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
