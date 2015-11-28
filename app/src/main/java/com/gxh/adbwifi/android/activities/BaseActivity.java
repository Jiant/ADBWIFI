package com.gxh.adbwifi.android.activities;/**
 * Created by Administrator on 2015/11/28.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * @author GuanXinHua
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(XUtils3)
 * @email cold279334079@gmail.com
 * @date ${date} ${time}
 */
public class BaseActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
