package com.example.clock_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class display_time extends AppCompatActivity {

    private TextView mT,london,nowyJork,tokio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_time);
        mT = findViewById(R.id.localx);
        london=findViewById(R.id.londonTime);
        nowyJork=findViewById(R.id.nowyjorkTime);
        tokio=findViewById(R.id.tokioTime);
        setUpText();
        Thread t=new Thread(){
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable(){
                            public void run(){
                                setUpText();
                            }
                        });
                    }
                } catch(InterruptedException e){
                    System.out.println("blad");
                }
            }
        };
        t.start();
    }

    public void setUpText() {
        TimeZone tz = TimeZone.getDefault();
        Calendar c = Calendar.getInstance(tz);
        @SuppressLint("DefaultLocale") String time = String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", c.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c.get(Calendar.SECOND));
        TimeZone tz1 = TimeZone.getTimeZone("Europe/London");
        Calendar c1 = Calendar.getInstance(tz1);
        @SuppressLint("DefaultLocale") String time1 = String.format("%02d", c1.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", c1.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c1.get(Calendar.SECOND));
        TimeZone tz2 = TimeZone.getTimeZone("America/New_York");
        Calendar c2 = Calendar.getInstance(tz2);
        @SuppressLint("DefaultLocale") String time2 = String.format("%02d", c2.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", c2.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c2.get(Calendar.SECOND));
        TimeZone tz3 = TimeZone.getTimeZone("Asia/Tokyo");
        Calendar c3 = Calendar.getInstance(tz3);
        @SuppressLint("DefaultLocale") String time3 = String.format("%02d", c3.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", c3.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c3.get(Calendar.SECOND));
        mT.setText(time);
        london.setText(time1);
        nowyJork.setText(time2);
        tokio.setText(time3);

    }
}

