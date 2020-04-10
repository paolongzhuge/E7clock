package cn.a7e7.e7clock;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextView tvHour,tvMinutes,tvNoun;
    private static int TIME_TEXT_SIEZ = 220;
    private int noun = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //应用全屏模式
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //实例化
        tvHour = findViewById(R.id.tvHous);
        tvMinutes = findViewById(R.id.tvMinutes);
        tvNoun = findViewById(R.id.tvNoun);

        //设置字体大小
        tvHour.setTextSize(TIME_TEXT_SIEZ);
        tvMinutes.setTextSize(TIME_TEXT_SIEZ);
        tvNoun.setTextSize(150);

        //屏幕常亮设置
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //横屏设置
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //刷新设置
        timerHandler.sendEmptyMessage(0);

    }

    //刷新的方法
    @SuppressLint("DefaultLocale")
    private void refreshTime(){
        //创建Calendar时就相当于获取了一个次系统时间，所以为了每次刷新的时候都能获取到最新的时间，所以创建的操作也要放在刷新里
        Calendar c = Calendar.getInstance();
        //HOUR_OF_DAY为设置为24小时制的小时
        tvHour.setText(oneToTwo(c.get(Calendar.HOUR_OF_DAY)));
        tvMinutes.setText(oneToTwo(c.get(Calendar.MINUTE)));

        if (noun == 1){
            tvNoun.setText("");
            noun = 0;
        }else {
            tvNoun.setText(":");
            noun = 1;
        }
    }

    //尝试刷新显示时间的一系列方法
    @SuppressLint("HandlerLeak")
    private Handler timerHandler = new Handler(){

        @Override
        public void handleMessage(android.os.Message msg) {
            refreshTime();
            //1000毫秒以后再执行一次
            timerHandler.sendEmptyMessageDelayed(0,1000);
        }
    };

    //时间显示两位数化
    private String oneToTwo(int time){
        String a = "1";
        if (time < 10){
            return "0" + String.valueOf(time);
        }else {
            return String.valueOf(time);
        }
    }

}
