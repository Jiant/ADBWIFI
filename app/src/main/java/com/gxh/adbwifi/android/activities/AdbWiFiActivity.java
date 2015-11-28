package com.gxh.adbwifi.android.activities;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
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

@ContentView(R.layout.activity_adb_wi_fi)
public class AdbWiFiActivity extends BaseActivity {

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

            DialogUtil.showTips(this, R.string.no_root_title, R.string.no_root,R.string.button_close, new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
//                    AdbWiFiActivity.this.finish();
                }
            });
        }
        if (!Network.isWifiConnected(this)){

            wifiState = false;
            Utils.saveWiFiState(this,wifiState);

            if(Utils.prefsWiFiOn(this)){


                Utils.enableWiFi(this,true);
            }

            while(true){
                tv_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Vibrator localVibrator = (Vibrator) AdbWiFiActivity.this.getSystemService(VIBRATOR_SERVICE);
                        if(Utils.prefsHaptic(AdbWiFiActivity.this)){
                            localVibrator.vibrate(45L);
                        }
                        if(AdbWiFiActivity.mState){
                            ShellUtil.execCommand("adb kill-server",false);
                           while (true)
                            {
                                AdbWiFiActivity.this.updateState();
                                return;
//                                Utils.adbStop(AdbWiFiActivity.this);
                            }
                        }
                    }
                });
            }
        }
    }


    private void updateState(){
        if(mState) {
            tv_footer1.setText("改变");
            try{
            tv_footer2.setText("adb connect " + Utils.getWifiIp(AdbWiFiActivity.this));
            tv_footer2.setVisibility(View.VISIBLE);
            tv_footer3.setVisibility(View.VISIBLE);
            tv_button.setText(R.string.button_wifi_on);
                return;
            } catch (Exception e){
                while (true)
                    tv_footer2.setText("adb connect ?");
            }
        }
        this.tv_footer1.setText("最终改变");
        this.tv_footer2.setVisibility(View.INVISIBLE);
        this.tv_footer3.setVisibility(View.INVISIBLE);
        this.tv_button.setText(R.string.button_wifi_off);
    }


}
