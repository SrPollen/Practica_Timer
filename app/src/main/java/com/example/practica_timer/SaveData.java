package com.example.practica_timer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SaveData extends AppCompatActivity {

    private TimerTask timertask;
    private Timer timer;
    private Integer currenTime = 0;

    private TextView textTimeHome;
    private Button btnHome;
    private Button btnSave;
    private Boolean saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        btnHome= findViewById(R.id.btn_home);
        btnSave = findViewById(R.id.btn_save);
        textTimeHome = findViewById(R.id.time_home);

        Intent getData = getIntent();
        textTimeHome.setText("Time in home: " + String.valueOf(getData.getStringExtra("time")));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData = true;
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("guardado", saveData);
                intent.putExtra("timesave", currenTime);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        cronoSave();
    }


    public void cronoSave(){
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