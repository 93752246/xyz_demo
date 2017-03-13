package com.example.kevin.xy_demo;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements SensorEventListener,View.OnClickListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Bundle bundle;
    private Coordinates mCoordinates;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mCoordinates.setData(bundle);
                    mCoordinates.invalidate();
            }
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnMsensor).setOnClickListener(this);
        findViewById(R.id.btnGsensor).setOnClickListener(this);
//        setContentView(R.layout.layout);
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        bundle = new Bundle();
//
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500, 300);
//        //params.setMargins(80, 80, 80, 80);
//        mCoordinates = new Coordinates(this);
//        mCoordinates.setLayoutParams(params);
//        relativeLayout.addView(mCoordinates);
//        //开启线程
//        new Thread(new GameThread()).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mSensorManager.unregisterListener(this);
    }

    /**
     * 获得三轴加速度数据，传到自定义view里面
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x =  event.values[0];
        float y =  event.values[1];
        float z =  event.values[2];


        bundle.putFloat("x", x);
        bundle.putFloat("y", y);
        bundle.putFloat("z", z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGsensor:
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btnMsensor:
                break;
        }

    }

    class GameThread implements Runnable {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = new Message();
                message.what = 1;
                // 发送消息
                MainActivity.this.mHandler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }


    }
}
