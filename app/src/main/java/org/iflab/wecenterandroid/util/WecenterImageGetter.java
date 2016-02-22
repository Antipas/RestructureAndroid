package org.iflab.wecenterandroid.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/30 10:44
 */

public class WecenterImageGetter implements Html.ImageGetter {
    private Context context;
    private int padding ;
    int width,height;

    private WecenterImageGetter(Builder builder){
        this.context = builder.context;
        this.padding = builder.padding;
        this.width = context.getResources().getDisplayMetrics().widthPixels;
        this.height = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public Drawable getDrawable(String source) {
        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(context).load(source).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int requestWidth;
            int requestHeight;
            int textViewWidth = width - DisplayUtil.dip2px(context, padding);
            if (width > textViewWidth) {
                requestWidth = textViewWidth;
                requestHeight = (int) (height * (textViewWidth / (width * 1.0)));
            } else if (width > textViewWidth / 2.0 ) {
                requestWidth = textViewWidth;
                requestHeight = (int) (height * (textViewWidth / (width * 1.0)));
            } else {
                requestWidth = width;
                requestHeight = height;
            }
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, requestWidth, requestHeight, true);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap1);
            if (bitmap.isRecycled()) {
                bitmap.recycle();
            }
            if (bitmap1.isRecycled()) {
                bitmap1.recycle();
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
        return null;
    }

    public static class Builder {
        private Context context;
        private int padding = 40;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder padding(int padding){
            this.padding = padding;
            return this;
        }

        public WecenterImageGetter build(){
            return new WecenterImageGetter(this);
        }
    }
}