package com.gxh.adbwifi.android.activities;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.gxh.adbwifi.R;
import com.gxh.adbwifi.android.utils.DialogUtil;
import com.gxh.adbwifi.android.utils.Network;
import com.gxh.adbwifi.android.utils.ShellUtil;
import com.gxh.adbwifi.android.utils.Utils;
import com.gxh.adbwifi.android.views.IconFontView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.pedant.SweetAlert.SweetAlertDialog;

@ContentView(R.layout.activity_adb_wi_fi)
public class AdbWiFiActivity extends BaseActivity {
    public static final String PORT = "5555";
    public static boolean wifiState;
    public static boolean mState = false;
    public static final boolean USB_DEBUG = false;


    @ViewInject(R.id.iv_button)
    private IconFontView tv_button;

    @ViewInject(R.id.tv_footer1)
    private TextView tv_footer1;

    @ViewInject(R.id.tv_footer2)
    private TextView tv_footer2;

    @ViewInject(R.id.tv_footer3)
    private TextView tv_footer3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Utils.mNotificationManager!=null){
            Utils.mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); //  通过获取系统服务来获取该对象
        }
        if(!ShellUtil.hasRootPermission()){

            new SweetAlertDialog(AdbWiFiActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.no_root_title))
                    .setContentText(getString(R.string.no_root))
                    .setCancelText("继续使用")
                    .setConfirmText(getString(R.string.button_close))
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            AdbWiFiActivity.this.finish();
                        }
                    })
                    .show();





        if (!Utils.checkWiFiState(AdbWiFiActivity.this)){
            wifiState = false;
            Utils.saveWiFiState(AdbWiFiActivity.this,wifiState);
            if (Utils.prefsWiFiOn(AdbWiFiActivity.this)){
                Utils.enableWiFi(AdbWiFiActivity.this,true);
            }



            }else{
            Toast.makeText(this,"wifi ok", Toast.LENGTH_LONG).show();
        }





//            while(true){
//                tv_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Vibrator localVibrator = (Vibrator) AdbWiFiActivity.this.getSystemService(VIBRATOR_SERVICE);
//                        if(Utils.prefsHaptic(AdbWiFiActivity.this)){
//                            localVibrator.vibrate(45L);
//                        }
//                        if(AdbWiFiActivity.mState){
//                            ShellUtil.execCommand("adb kill-server",false);
//                           while (true)
//                            {
//                                AdbWiFiActivity.this.updateState();
//                                return;
////                                Utils.adbStop(AdbWiFiActivity.this);
//                            }
//                        }
//                    }
//                });
//            }
        }


    }


    private void updateState(){
        if(mState) {
            tv_footer1.setText("改变");
            try{
            tv_footer2.setText("adb connect " + Utils.getWifiIp(AdbWiFiActivity.this));
            tv_footer2.setVisibility(View.VISIBLE);
            tv_footer3.setVisibility(View.VISIBLE);
            tv_button.setText(R.string.button_wifi_off);
                return;
            } catch (Exception e){

                    tv_footer2.setText("adb connect ?");
            }
        }else{
        this.tv_footer1.setText("最终改变");
        this.tv_footer2.setVisibility(View.INVISIBLE);
        this.tv_footer3.setVisibility(View.INVISIBLE);
        this.tv_button.setText(R.string.button_wifi_on);
    }
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
