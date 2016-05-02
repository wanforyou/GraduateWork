/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */
package com.tyut.himusic.activity;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.tyut.himusic.util.GlobalData;

/**
 * @author luqingchuan on 16/1/16 12:04
 * @email luqingchuan@foxmail.com
 */
public class MyApplication extends Application
{
    public static final String os = Build.BRAND;
    /*
     * 单例模式
     */
    private static MyApplication mInstance;
    private static GlobalData sData;
    //    private static ImageLoader imageLoader = ImageLoader.getInstance();
    //微信API
    private static Boolean isMainActivityRunning = false;

    public static MyApplication getInstance()
    {
        return mInstance;
    }

    public static GlobalData getGlobalData()
    {// 实例化对象
        if (sData == null)
        {
            synchronized (GlobalData.class)
            {
                if (sData == null)
                {
                    sData = new GlobalData();
                }
            }
        }
        return sData;
    }

    private static void setGlobalData(GlobalData data)
    {
        sData = data;
    }

    public static void setMainActivityRunning()
    {
        isMainActivityRunning = true;
    }

    public static void setHomeActivityStop()
    {
        isMainActivityRunning = false;
    }

    public static void clearCache()
    {
        setGlobalData(null);
    }


    public static Boolean isMainActivityRunning()
    {
        return isMainActivityRunning;
    }


    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context)
    {

        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses())
        {
            if (appProcess.pid == pid)
            {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        // 初始化imageloader
        if (mInstance == null)
        {
            mInstance = this;
        }


        if (getPackageName().equals(getCurProcessName(getApplicationContext())))
        {
            Fresco.initialize(getApplicationContext());
            ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(getApplicationContext())
                    .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                    .build();
            Fresco.initialize(getApplicationContext(), imagePipelineConfig);

        }
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }


}
