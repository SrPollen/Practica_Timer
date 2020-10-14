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

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnPermisos;

    private TextView timeHome;
    private TextView timeSave;
    private TextView timePermisos;
    private TextView saveData;
    private TextView addPermisos;

    private int requestSaveData = 1;
    private int requestAddPermisos = 2;

    private Integer currenTime = 0;
    private TimerTask timertask;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btn_go_save);
        btnPermisos = findViewById(R.id.btn_permisos);

        timeHome = findViewById(R.id.time_home);
        timeSave = findViewById(R.id.time_save);
        timePermisos = findViewById(R.id.time_permisos);
        saveData = findViewById(R.id.save_data);
        addPermisos = findViewById(R.id.add_permisos);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SaveData.class);
                intent.putExtra("time", String.valueOf(currenTime));
                startActivityForResult(intent, requestSaveData);
            }
        });
        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Permission.class);
                intent.putExtra("time", String.valueOf(currenTime));
                startActivityForResult(intent, requestAddPermisos);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 1:
                if (resultCode == MainActivity.RESULT_OK) {
                    //Boolean guardado = data.getBooleanExtra("guardado", false);
                    timeSave.setText("Time in save: " + String.valueOf(data.getIntExtra("timesave", 0)));
                    if(data.getBooleanExtra("guardado", false)){
                        saveData.setText("Save data: " + String.valueOf(data.getBooleanExtra("guardado", false)));
                    }

                }
                break;
            case 2:
                if (resultCode == MainActivity.RESULT_OK) {
                    timePermisos.setText("Time in permissions: " + String.valueOf(data.getIntExtra("timepermisos", 0)));
                    if(data.getBooleanExtra("permitir", false)){
                        addPermisos.setText("Add permissions: " + String.valueOf(data.getBooleanExtra("permitir", false)));
                    }
                }
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
       // this.timer.cancel();
        stopCrono();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // timer.schedule(timertask,1,1000);
        cronoHome();
    }

    public void cronoHome(){
        timertask = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currenTime++;
                        timeHome.setText("Time in this activity: " + String.valueOf(currenTime));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timertask,1,1000);
    }


    public void stopCrono(){
        timertask.cancel();
    }
}