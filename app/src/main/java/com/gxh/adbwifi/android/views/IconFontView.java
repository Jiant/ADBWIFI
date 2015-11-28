package com.gxh.adbwifi.android.views;/**
 * Created by Administrator on 2015/11/28.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author GuanXinHua
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(ICONFONT在APP中的使用)
 * @email cold279334079@gmail.com
 * @date ${date} ${time}
 */
public class IconFontView extends TextView {

    private Context mContext;

    public IconFontView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public IconFontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public IconFontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }


    private void initView(){
        // 有关字体设置
        Typeface iconfont = Typeface.createFromAsset(mContext.getAssets(),"iconfont.ttf");
        setTypeface(iconfont);
    }
}
