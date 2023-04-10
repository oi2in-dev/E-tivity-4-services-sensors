package com.example.e_tivity_4_servicessensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorMgr;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.datatxt);
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorMgr!=null){

            Sensor proxiSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);

                if (proxiSensor!=null){
                    sensorMgr.registerListener(this, proxiSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
}