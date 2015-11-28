package com.gxh.adbwifi.android.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gxh.adbwifi.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    public static boolean  CONNECT_ON_BOOT_PREFERENCE_DEFAULT = false;  //默认启动连接的偏好
//
//    public static final String SERVER_STATUS_CHANGED = (this.getClass().getSimpleName(); //改变服务状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {

    }


//    public static SharedPreferences getPreferences(Context paramContext){
//        initDefaultValues(paramContext);
//        return ;
//    }

    private static void initDefaultValues(Context paramContext) {


    }

    private class MainBroadcastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("com.gxh.adbwifi.activities.Sev")){

            }
        }
    }



}
