/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.util;

import android.content.pm.PackageManager;

import com.tyut.himusic.activity.MyApplication;


/**
 * 版本工具类
 *
 * @author luqingchuan on 16/1/16 13:51
 * @email luqingchuan@foxmail.com
 */
public class VersionUtil
{
    public static int getAndroidApiVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取当前版本号，对应于build.gradle中的android:versionCode
     */
    public static int getCurrentVerCode()
    {
        int verCode = -1;
        String packageName = MyApplication.getInstance().getPackageName();
        try
        {
            verCode = MyApplication.getInstance().getPackageManager().getPackageInfo(
                    packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e)
        {

        }
        return verCode;
    }

    /**
     * 获取当前版本名字，对应于build.gradle中的android:versionName
     */
    public static String getCurrentVerName()
    {
        String verName = "";
        String packageName = MyApplication.getInstance().getPackageName();
        try
        {
            verName = MyApplication.getInstance().getPackageManager().getPackageInfo(
                    packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e)
        {
        }
        return verName;
    }
}
