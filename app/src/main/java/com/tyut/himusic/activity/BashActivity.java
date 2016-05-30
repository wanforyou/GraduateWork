/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.tyut.himusic.util.MyLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author luqingchuan on 16/5/1 22:50
 * @email luqingchuan@foxmail.com
 */
public abstract class BashActivity extends FragmentActivity
{
    protected static MyLog log = MyLog.qcLog();

    //魅族专用设置状态栏模式(黑色白色)
    public static boolean setStatusBarDarkIcon(Window window, boolean dark)
    {
        boolean result = false;
        if (window != null)
        {
            try
            {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark)
                {
                    value |= bit;
                } else
                {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e)
            {
                log.d("setStatusBarDarkIcon: failed");
            }
        }
        return result;
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //小米魅族手机api大于19的机型,状态栏透明且字体改成黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            if (MyApplication.os.toUpperCase().equals("XIAOMI"))
            {
                setStatusBarDarkMode(true, this);
            } else if (MyApplication.os.toUpperCase().equals("MEIZU"))//"HUAWEI"
            {
                setStatusBarDarkIcon(this.getWindow(), true);
            }

        }
        //否则状态栏黑色
    }

    //小米专用设置状态栏模式(黑色白色)
    public void setStatusBarDarkMode(boolean darkmode, Activity activity)
    {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try
        {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
