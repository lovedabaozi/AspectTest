package com.bigbaozi.aspecttest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigbaozi.aspectjlibrary.annotation.CountTime;
import com.bigbaozi.aspectjlibrary.interf.ICountTime;

public class MainActivity extends AppCompatActivity implements ICountTime {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @CountTime("Hello")
    public void Hello() {

        SystemClock.sleep(1000);
        int x = 0;
        x++;
    }


    @CountTime("Around")
    public void Around() {

        SystemClock.sleep(500);
    }

    public void go(View view) {
        Hello();
        Around();
    }

    /**
     *  接口回调方法名和时间，可自定义处理记时逻辑。
     * @param methodName
     * @param times
     */
    @Override
    public void GetTimes(String methodName, long times) {
        Log.e("AAA","---"+methodName+"---"+times);
    }
}
