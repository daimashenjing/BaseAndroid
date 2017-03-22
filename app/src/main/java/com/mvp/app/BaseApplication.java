package com.mvp.app;

import android.app.Application;

import com.mvp.utils.ImageLoad;

/**
 * Created by 史章华 on 2017/3/13.
 */

public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //初始化ImageLoader配置
        ImageLoad.getInstance().initImageLoader(this);
    }
}
