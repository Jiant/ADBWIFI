package com.gxh.adbwifi.android.utils;/**
 * Created by Administrator on 2015/11/28.
 */

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.gxh.adbwifi.R;
import com.gxh.adbwifi.android.activities.AdbWiFiActivity;

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
        SharedPreferences.Editor localEditer = paramContext.getSharedPreferences("WiFi",0).edit();
        localEditer.putBoolean("wifiState",paramBoolean);
        localEditer.commit();
    }


    public static boolean prefsWiFiOn(Context paramContext)
    {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramContext.getResources().getString(R.string.pref_wifi_on_key),false);
    }


    public static void enableWiFi(Context paramContext, boolean paramBoolean){
        if(paramBoolean){
            Toast.makeText(paramContext, R.string.turning_on_wifi, Toast.LENGTH_LONG).show();
            while (true){
                ((WifiManager) paramContext.getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(paramBoolean);
                Toast.makeText(paramContext,R.string.turning_off_wifi, Toast.LENGTH_LONG).show();

                return;
            }

        }
    }

    public static boolean adbStart(Context paramContext){

        if(!AdbWiFiActivity.USB_DEBUG){
            //serive.adb.top.prot:5555
            setProp("serive.adb.top.prot",AdbWiFiActivity.PORT);


        }
        return false;
    }


    public static boolean setProp(String property, String value){
        return ShellUtil.execRootCommand("setprop " + property + " "+ value,false);
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