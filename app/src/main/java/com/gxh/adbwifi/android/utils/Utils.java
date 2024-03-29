package com.gxh.adbwifi.android.utils;/**
 * Created by Administrator on 2015/11/28.
 */

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.gxh.adbwifi.R;
import com.gxh.adbwifi.android.activities.AdbWiFiActivity;
import com.gxh.adbwifi.android.data.DataKeeper;

/**
 * @author GuanXinHua
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @email cold279334079@gmail.com
 * @date ${date} ${time}
 */
public class Utils {

    public static NotificationManager mNotificationManager;  //状态栏通知的管理类，负责发通知、清楚通知等

    public static void saveWiFiState(Context paramContext, boolean paramBoolean){
//        SharedPreferences.Editor localEditer = paramContext.getSharedPreferences("WiFi",0).edit();
//        localEditer.putBoolean("wifiState",paramBoolean);
//        localEditer.commit();
        DataKeeper dk = new DataKeeper(paramContext,"WiFI");
        dk.put("wifiState",paramBoolean);


    }


    public static boolean prefsWiFiOn(Context paramContext)
    {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramContext.getResources().getString(R.string.pref_wifi_on_key),false);
    }


    public static void enableWiFi(Context paramContext, boolean paramBoolean){
        if(paramBoolean){
            Toast.makeText(paramContext,R.string.turning_on_wifi, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(paramContext,R.string.turning_off_wifi, Toast.LENGTH_LONG).show();
        }

        WifiManager mWiFiManager = (WifiManager) paramContext.getSystemService(Context.WIFI_SERVICE);
        mWiFiManager.setWifiEnabled(paramBoolean);
    }

    public static boolean adbStart(Context paramContext){

        if(!AdbWiFiActivity.USB_DEBUG){
            //serive.adb.top.prot:5555
            setProp("serive.adb.top.prot",AdbWiFiActivity.PORT);
            if(ShellUtil.execRootCommand("adbd",false)){
                ShellUtil.execCommand("stop adbd",false);
            }
            ShellUtil.execRootCommand("start adbd",false);

        }
        try
        {
            AdbWiFiActivity.mState = true;
        }
        catch (Exception e)
        {}

        SharedPreferences settings = paramContext.getSharedPreferences("wireless", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("mState", true);
        editor.commit();



        return true;
    }


    public static boolean setProp(String property, String value){
        return ShellUtil.execRootCommand("setprop " + property + " "+ value,false);
    }




    public static void WiFiDialog(Context context){
        DialogUtil.showTips(context,"",R.string.no_wifi,R.string.button_close,R.string.button_close, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                System.exit(0);
            }
        }).show();
    }



    public static boolean checkWiFiState(Context context){
        WifiManager mWiFiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiInfo = mWiFiManager.getConnectionInfo();
        if(!mWiFiManager.isWifiEnabled()||wifiInfo.getSSID() ==null){
            return false;
        }


        return true;
    }


    public static boolean prefsHaptic(Context paramContext){
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramContext.getResources().getString(R.string.pref_haptic_key),true);
    }

    public static String getWifiIp(Context paramContext)
    {
        int i = ((WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress();
        return (i & 0xFF) + "." + (0xFF & i >> 8) + "." + (0xFF & i >> 16) + "." + (0xFF & i >> 24);
    }



    }
