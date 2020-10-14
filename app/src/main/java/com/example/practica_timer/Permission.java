package com.example.practica_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Permission extends AppCompatActivity {

    private TimerTask timertask;
    private Timer timer;
    private Integer currenTime = 0;

    private TextView textTimeHome;
    private Button btnHome;
    private Button btnPermisos;
    private Boolean permisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        btnHome= findViewById(R.id.btn_go_home);
        btnPermisos = findViewById(R.id.btn_permiso);
        textTimeHome = findViewById(R.id.text_time_home);

        Intent getData = getIntent();
        textTimeHome.setText("Time in home: " + String.valueOf(getData.getStringExtra("time")));

        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos = true;
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("permitir", permisos);
                intent.putExtra("timepermisos", currenTime);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        cronoPermisos();
    }

    public void cronoPermisos(){
        timertask = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currenTime++;
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timertask,1,1000);
    }
}