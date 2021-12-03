package com.example.humiditysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    SensorManager sensorManager;
    private Sensor humiditySensor;
    private Boolean isSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)!= null){
            humiditySensor =  sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isSensorAvailable =true;
        }else {
            textView.setText("This device Humidity Sensor not avaiable");
            isSensorAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0] +"%");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorAvailable)
        {
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}