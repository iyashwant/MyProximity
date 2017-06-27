package com.example.iyashwant.myproximity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 0;
    TextView textView;
    TextView count;
    static MediaPlayer sound;
    public int i=0;

    static boolean continueBGmusic = false;
    static boolean delay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        textView = (TextView)findViewById(R.id.values);
        count = (TextView) findViewById(R.id.textview);
        sound = MediaPlayer.create(this, R.raw.alert);

        //  mp = MediaPlayer.create(context, R.raw.alone);

    }

    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float val = event.values[0];

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {



                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        continueBGmusic = true;

                        sound.start();
                        sound.setLooping(true);
                    }
                }, 10000);



                // new CountDownTask().execute();
                //Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
            } else {
                if(continueBGmusic)
                sound.pause();
               // delay=true;
                //Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();

            }
            textView.setText(String.valueOf(val));

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }





}
