package com.vokasi.progressbar_abyan;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class coba extends AppCompatActivity {
    ViewGroup tContainer;
    ProgressBar pg;
    ProgressBar pg2;
    Button magic;


    @Override
    protected void onCreate (Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_main);

        tContainer = findViewById(R.id.transisi);   // buat layout semuanya
        pg = findViewById(R.id.jos); // ini yang bakal nampil
        pg2 = findViewById(R.id.jose);
        magic = findViewById(R.id.button);


        magic.setOnClickListener(new View.OnClickListener() {
           boolean visible;
            @Override
            public  void onClick(View v){


            }
        });


    }
}
