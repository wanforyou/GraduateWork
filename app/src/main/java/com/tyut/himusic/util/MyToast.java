/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.util;

import android.widget.Toast;

import com.tyut.himusic.activity.MyApplication;


/**
 * @author luqingchuan on 16/1/16 12:16
 * @email luqingchuan@foxmail.com
 */
public class MyToast
{
    private static Toast toast;

    /**
     * 默认时间为Toast.LENGTH_SHORT
     *
     * @param msg 要显示的文字
     * @author luqingchuan
     * @version 创建时间：2016-1-16
     */
    public static void makeText(CharSequence msg)
    {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * @param resId 要显示的文字的资源，如R.string.hello
     * @author luqingchuan
     * @version 创建时间：2016-1-16
     */
    public static void makeText(int resId)
    {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public static void makeTextLong(CharSequence msg)
    {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void makeTextLong(int resId)
    {
        showToast(resId, Toast.LENGTH_LONG);
    }

    private static void showToast(CharSequence msg, int duration)
    {
        if (toast == null)
        {
            toast = Toast.makeText(MyApplication.getInstance(), msg, duration);
        } else
        {
            toast.setText(msg);
            toast.setDuration(duration);
        }
        toast.show();
    }

    private static void showToast(int resId, int duration)
    {
        if (resId == 0)
        {
            return;
        }
        if (toast == null)
        {
            toast = Toast.makeText(MyApplication.getInstance(), resId, duration);
        } else
        {
            toast.setText(resId);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
