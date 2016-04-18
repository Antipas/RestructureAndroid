package org.iflab.wecenterandroid.util;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.text.SimpleDateFormat;

public class DisplayUtil {
    protected static SimpleDateFormat simpleDateFormat;

    static {
        simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
    }

    private static int screenWidth = 0;
    private static int screenHeight = 0;
    /**
     * 将px值转换为dip或dp值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值
     */
    public static int dip2px(Context context, float dipValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    /**
     *
     * @param time 单位 秒
     * @return
     */
    public static String lossOfTime(long time){
        long subtraction = System.currentTimeMillis() - time * 1000;
        long days = subtraction/(24 * 3600 * 1000);
        if(days < 1){
            return "刚刚";
        }
        if(days > 10){
            return time2Date(time);
        }

        return days+"天之前";
    }

    public static String formatCount(int num){
        if(num > 999){
            return num/1000 + "k";
        }else{
            return String.valueOf(num);
        }
    }

    public static String time2Date(long timestampString) {
        return simpleDateFormat.format(new java.util.Date(timestampString * 1000));
    }
}
