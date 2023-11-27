package com.example.foodninja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView logoimg;
    Animation anim;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        animLogo();
        time();
    }

    private void init() {
        logoimg = findViewById(R.id.logo);
        anim = AnimationUtils.loadAnimation(this, R.anim.logoanim);
    }

    private void animLogo() {
        logoimg.setAnimation(anim);
    }
    private void time(){
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this, Athentification.class);
                startActivity(intent);

                finish();
                overridePendingTransition(R.anim.logoanim,R.anim.fadeout);
            }
        },3000);
    }
}