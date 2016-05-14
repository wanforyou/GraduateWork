/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.util;

import android.util.Log;

/**
 * 自定义Log类，方便每个人对Log的输出以及开关
 *
 * @author luqingchuan on 16/1/16 13:25
 * @email luqingchuan@foxmail.com
 */
public class MyLog
{
    public final static String tag = "graduate";
    private final static boolean logFlag = true;
    private final static int logLevel = Log.VERBOSE;
    //显示的各自的名字
    private static final String FANGZHANG = "@fangzhang@";
    private static MyLog wjwLog;
    private String mLogName;

    private MyLog(String name)
    {
        mLogName = name;
    }

    /**
     * 晴川的Log
     *
     * @return
     */
    public static MyLog qcLog()
    {
        if (wjwLog == null)
        {
            synchronized (MyLog.class)
            {
                if (wjwLog == null)
                {
                    wjwLog = new MyLog(FANGZHANG);
                }
            }
        }
        return wjwLog;
    }

    /**
     * @return 当前运行的若干信息，包括log用户，线程名，Java文件，当前行号，方法名
     */
    private String getFunctionName()
    {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null)
        {
            return null;
        }
        for (StackTraceElement st : sts)
        {
            if (st.isNativeMethod())
            {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName()))
            {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName()))
            {
                continue;
            }
            return mLogName + "[ " + Thread.currentThread().getName() + ": "
                    + st.getFileName() + ":" + st.getLineNumber() + " "
                    + st.getMethodName() + " ]";
        }
        return null;
    }

    /**
     * The Log Level:info
     *
     * @param str
     */
    public void i(Object str)
    {
        if (logFlag)
        {
            if (logLevel <= Log.INFO)
            {
                String name = getFunctionName();
                if (name != null)
                {
                    Log.i(tag, name + " - " + str);
                } else
                {
                    Log.i(tag, str.toString());
                }
            }
        }

    }

    /**
     * The Log Level:debug
     *
     * @param str
     */
    public void d(Object str)
    {
        if (logFlag)
        {
            if (logLevel <= Log.DEBUG)
            {
                String name = getFunctionName();
                if (name != null)
                {
                    Log.d(tag, name + " - " + str);
                } else
                {
                    Log.d(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:Verbose
     *
     * @param str
     */
    public void v(Object str)
    {
        if (logFlag)
        {
            if (logLevel <= Log.VERBOSE)
            {
                String name = getFunctionName();
                if (name != null)
                {
                    Log.v(tag, name + " - " + str);
                } else
                {
                    Log.v(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:warm
     *
     * @param str
     */
    public void w(Object str)
    {
        if (logFlag)
        {
            if (logLevel <= Log.WARN)
            {
                String name = getFunctionName();
                if (name != null)
                {
                    Log.w(tag, name + " - " + str);
                } else
                {
                    Log.w(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:error
     *
     * @param str
     */
    public void e(Object str)
    {
        if (logFlag)
        {
            if (logLevel <= Log.ERROR)
            {
                String name = getFunctionName();
                if (name != null)
                {
                    Log.e(tag, name + " - " + str);
                } else
                {
                    Log.e(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:error
     *
     * @param ex
     */
    public void e(Exception ex)
    {
        if (logFlag)
        {
            if (logLevel <= Log.ERROR)
            {
                Log.e(tag, "error", ex);
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param log
     * @param tr
     */
    public void e(String log, Throwable tr)
    {
        if (logFlag)
        {
            String line = getFunctionName();
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}"
                    + "[" + mLogName + line + ":] " + log + "\n", tr);
        }
    }
}