package com.bigbaozi.aspecttest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigbaozi.aspectjlibrary.annotation.CountTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @CountTime("Hello")
    public  void  Hello(){

        SystemClock.sleep(1000);
        int x=0;
        x++;
    }


   @CountTime("Around")
    public  void  Around(){

       SystemClock.sleep(500);
    }

    public void go(View view) {
        Hello();
        Around();
    }
}
