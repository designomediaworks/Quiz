package com.piquor.app.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.piquor.app.quiz.activity.NavigationDrawerActivity;
import com.piquor.app.quiz.database.DBHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DBHelper.getInstance(this);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,NavigationDrawerActivity.class));
                        SplashActivity.this.finish();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
    }




}
