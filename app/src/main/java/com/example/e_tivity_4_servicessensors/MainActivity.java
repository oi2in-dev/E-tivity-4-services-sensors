package com.example.e_tivity_4_servicessensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener{
    TextView textview;
    SensorManager sensorMgr;
    Intent serviceintent;
    Button PlayBtn, StopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceintent = new Intent(this,MusicService.class);

        PlayBtn = findViewById(R.id.play);
        StopBtn = findViewById(R.id.stop);

        PlayBtn.setOnClickListener(this);
        StopBtn.setOnClickListener(this);

        textview = findViewById(R.id.datatxt);
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorMgr!=null){

            Sensor proxiSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);

                if (proxiSensor!=null){
                    sensorMgr.registerListener(this, proxiSensor, SensorManager.SENSOR_DELAY_FASTEST);
                }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.datatxt)).setText("values: "+ sensorEvent.values[0]);

            if (sensorEvent.values[0] > 0){
                Toast.makeText(this, "You are far away!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "You are close!", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.play:
                    startService(new Intent(getApplicationContext(), MusicService.class));
                    break;
                case  R.id.stop:
                    stopService(new Intent(getApplicationContext(), MusicService.class));
                    break;
            }
    }
}