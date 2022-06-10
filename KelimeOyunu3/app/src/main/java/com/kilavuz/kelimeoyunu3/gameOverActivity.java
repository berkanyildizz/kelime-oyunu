package com.kilavuz.kelimeoyunu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class gameOverActivity extends AppCompatActivity {
    TextView sonPuan, sonSure;
    ImageView result,kazandin,kaybettin;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity2 main2 = new MainActivity2();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        sonPuan = findViewById(R.id.sonPuan_tv);
        sonSure = findViewById(R.id.sonSure_tv);
        result = findViewById(R.id.result_tv);
        kazandin = findViewById(R.id.kazandin_iv);
        kaybettin = findViewById(R.id.kaybettin_iv);
        restart = findViewById(R.id.restart_btn);
        Intent veriAl = getIntent();
        String puanTut = veriAl.getStringExtra("skor1");
        String sureTut = veriAl.getStringExtra("sayac1");
        Boolean winTut = veriAl.getExtras().getBoolean("win1");
        int sure = Integer.parseInt(sureTut);
        int puan = Integer.parseInt(puanTut);
        if(winTut.equals(true)){
            result.setVisibility(View.INVISIBLE);
            kaybettin.setVisibility(View.INVISIBLE);
            kazandin.setVisibility(View.VISIBLE);
            sonPuan.setText("Puan: "+puanTut);
            sonSure.setText("Tamamlanan Süre: "+ (70-sure));
        }else if (winTut.equals(false)){
            kaybettin.setVisibility(View.VISIBLE);
            kazandin.setVisibility(View.INVISIBLE);
            sonPuan.setVisibility(View.INVISIBLE);
            sonSure.setVisibility(View.INVISIBLE);
            result.setVisibility(View.VISIBLE);
        }
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main2.soru =0;
                main2.cancelTimer= false;
                main2.cancelTimer2= false;
                main2.harfSayisi =6;
                main2.skor = 4000;
                main2.sayac =70;
                main2.win = true;

                Intent i3 = new Intent(gameOverActivity.this, MainActivity2.class);
                startActivity(i3);
                finish();
            }
        });
    }
}