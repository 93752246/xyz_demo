package com.example.kevin.xy_demo;

import android.app.usage.UsageEvents;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Main2Activity extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor acceler, magnetic,lacceler,gravity;
    TextView tv1, tv2, tv3;
    TextView tv_Mx, tv_My, tv_Mz;
    TextView tv_lax, tv_lay, tv_laz;
    int bitcnt = 1;
    DecimalFormat fnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initSensor();

        fnum = new DecimalFormat("##0.0");
        //String   dd=fnum.format(scale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.bit0:
                bitcnt = 0;
                fnum = new DecimalFormat("##0");
                break;
            case R.id.bit1:
                bitcnt = 1;
                fnum = new DecimalFormat("##0.0");
                break;
            case R.id.bit2:
                bitcnt = 2;
                fnum = new DecimalFormat("##0.00");
                break;
        }
        return true;
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceler = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //加速度传感器
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);//磁力
        lacceler = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);//线性加速度
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);    //重力加速度
    }
    private void initView() {
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv_Mx = (TextView) findViewById(R.id.tv_Mx);
        tv_My = (TextView) findViewById(R.id.tv_My);
        tv_Mz = (TextView) findViewById(R.id.tv_Mz);
        tv_lax = (TextView) findViewById(R.id.tv_lax);
        tv_lay = (TextView) findViewById(R.id.tv_lay);
        tv_laz = (TextView) findViewById(R.id.tv_laz);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acceler, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this,lacceler,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tv1.setText("x:" + fnum.format(x));
            tv2.setText("y:" + fnum.format(y));
            tv3.setText("z:" + fnum.format(z));
        }else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            tv_Mx.setText("x:" + fnum.format(x));
            tv_My.setText("y:" + fnum.format(y));
            tv_Mz.setText("z:" + fnum.format(z));
        }else if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            tv_lax.setText("x:" + fnum.format(x));
            tv_lay.setText("y:" + fnum.format(y));
            tv_laz.setText("z:" + fnum.format(z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
