package org.iflab.wecenterandroid;

import android.app.Application;

import org.iflab.wecenterandroid.util.PicassoImageLoader;
import org.iflab.wecenterandroid.util.PicassoPauseOnScrollListener;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Lyn on 15/11/17.
 */

public class App extends Application {

        private static final String filePath= "/sdcard/Wecenter/";

        @Override
        public void onCreate() {
            super.onCreate();
            ThemeConfig theme = ThemeConfig.CYAN;
//配置功能
            FunctionConfig functionConfig = new FunctionConfig.Builder()
                    .setEnableCamera(true)
                    .setEnableEdit(true)
                    .setEnableCrop(true)
                    .setForceCropEdit(true)
                    .setEnableRotate(true)
                    .setCropSquare(true)
                    .setEnablePreview(true)
                    .build();

//配置imageloader
            ImageLoader imageloader = new PicassoImageLoader();
            File file = new File(filePath);

            CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                    .setDebug(BuildConfig.DEBUG)
                    .setFunctionConfig(functionConfig)
                    .setPauseOnScrollListener(new PicassoPauseOnScrollListener(false,true))
                    .setEditPhotoCacheFolder(file)
                    .setTakePhotoFolder(file)
                    .setNoAnimcation(false)
                    .build();
            GalleryFinal.init(coreConfig);
        }
}


