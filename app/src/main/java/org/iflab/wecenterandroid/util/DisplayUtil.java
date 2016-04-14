package org.iflab.wecenterandroid.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.text.SimpleDateFormat;

public class DisplayUtil {
    protected static SimpleDateFormat simpleDateFormat;
    protected static SimpleDateFormat timeFormat;

    static {
        simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("HH-mm");
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
    public static String lossOfTime(int time){
        long subtraction = System.currentTimeMillis() - time*1000;
        int hours = new java.util.Date(subtraction * 1000).getHours();
        int minutes = new java.util.Date(subtraction * 1000).getMinutes();
        int day = new java.util.Date(subtraction * 1000).getDay();
        if(day == 0){
            if(hours == 0){
                if(minutes == 0){
                    return "刚刚";
                }
                return minutes + "分钟之前";
            }
            return hours + "小时之前";
        }else if(day < 7){
            return day + "天之前";
        }else{
            return time2Date(time * 1000);
        }
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
