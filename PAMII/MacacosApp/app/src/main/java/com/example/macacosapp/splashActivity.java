package com.example.macacosapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class splashActivity extends Activity implements Runnable {

    Thread thread;
    Handler handler;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        handler = new Handler();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        i = 1;

        try {
            while (i<100){
                Thread.sleep(15);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                    }
                });
            }

        } catch (Exception e) {

        }

        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}