package com.example.kevin.xy_demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;

public class MSensorActivity extends Activity implements SensorEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msensor);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        manager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);//磁力传感器
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
