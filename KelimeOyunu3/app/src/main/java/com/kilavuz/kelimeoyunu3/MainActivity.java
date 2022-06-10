package com.kilavuz.kelimeoyunu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button nasilOynanir;
    ImageButton oyna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = findViewById(R.id.logo_iv);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.intro_logo);
        logo.startAnimation(animation);
        oyna = findViewById(R.id.oyna_btn);
        nasilOynanir = findViewById(R.id.nasilOynanir_btn);

        oyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                finish();
            }
        });
        nasilOynanir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,nasilOynanirActivity.class);
                startActivity(i);
            }
        });


    }
}