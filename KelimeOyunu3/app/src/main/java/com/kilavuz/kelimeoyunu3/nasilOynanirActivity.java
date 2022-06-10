package com.kilavuz.kelimeoyunu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class nasilOynanirActivity extends AppCompatActivity {
    ImageButton geri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasil_oynanir);
        geri = findViewById(R.id.geri_btn);

        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(nasilOynanirActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}