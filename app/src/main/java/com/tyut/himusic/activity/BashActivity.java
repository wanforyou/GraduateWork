/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.activity;

import android.support.v4.app.FragmentActivity;

import com.tyut.himusic.util.MyLog;

/**
 * @author luqingchuan on 16/5/1 22:50
 * @email luqingchuan@foxmail.com
 */
public abstract class BashActivity extends FragmentActivity
{
    protected static MyLog log = MyLog.qcLog();

    protected abstract void initView();


    protected abstract void initData();


}
