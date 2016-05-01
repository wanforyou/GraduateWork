/*******************************************************************************
 * Recruit Andriod
 * Created by lqc
 * Copyright © 2016年 issuebang.com. All rights reserved.
 ******************************************************************************/
package com.tyut.himusic.http;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.SerializableCookie;
import com.tyut.himusic.activity.MyApplication;
import com.tyut.himusic.util.VersionUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.cookie.Cookie;

/**
 * Http请求工具类
 *
 * @author luqingchuan on 16/1/16 13:50
 * @email luqingchuan@foxmail.com
 */
public class HttpManager
{
    private static final String VERSION = VersionUtil.getCurrentVerName() + "(" + VersionUtil.getCurrentVerCode() + ")";
    //            + "  (" + "Phone:" + Build.MODEL
//            + " SDK: " + Build.VERSION.SDK_INT + " screen_height: " + ScreenUtil.getScreenHeightPix()
//            + " screen_width: " + ScreenUtil.getScreenWidthPix()
//            + " OS: " + android.os.Build.VERSION.RELEASE + ")";
    public static final String USER_AGENT = "Graduate_Android/" + VERSION;
    private static AsyncHttpClient client = null;

    static
    {
        getClient().setTimeout(10000); // 设置链接超时，如果不设置，默认为3s
    }

    public static AsyncHttpClient getClient()
    {// 实例化对象
        if (client == null)
        {
            synchronized (AsyncHttpClient.class)
            {
                if (client == null)
                {
                    client = new AsyncHttpClient();
                    PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getInstance());
                    client.setCookieStore(myCookieStore);
                    client.setUserAgent(USER_AGENT);


                }
            }
        }
        return client;
    }

    public static void setClient(AsyncHttpClient asyncHttpClient)
    {
        client = asyncHttpClient;
    }

    public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        getClient().get(urlString, res);
    }

    public static void get(String urlString, com.loopj.android.http.RequestParams params, AsyncHttpResponseHandler res) // url里面带参数
    {
        getClient().get(urlString, params, res);
    }

    public static void post(String urlString, com.loopj.android.http.RequestParams params,
                            AsyncHttpResponseHandler res) // url里面带参数
    {
        getClient().post(urlString, params, res);
    }

    public static void post(String urlString, com.loopj.android.http.RequestParams params,
                            JsonHttpResponseHandler res) // url里面带参数
    {
        getClient().post(urlString, params, res);
    }

    public static void post(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        getClient().post(urlString, res);
    }


    public static void post(Context context, String urlString, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler)
    {
        getClient().post(context, urlString, entity, contentType, responseHandler);
    }

    public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        getClient().get(urlString, res);
    }

    public static void get(String urlString, com.loopj.android.http.RequestParams params,
                           JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        getClient().get(urlString, params, res);
    }

    public static void get(String urlString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        getClient().get(urlString, bHandler);

    }

    public static void put(String urlString, com.loopj.android.http.RequestParams params, AsyncHttpResponseHandler handler)
    {
        getClient().put(urlString, params, handler);
    }

    public static void put(Context context, String urlString, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler)
    {
        getClient().put(context, urlString, entity, contentType, responseHandler);
    }

    public static void put(String urlString, AsyncHttpResponseHandler handler)
    {
        getClient().put(urlString, handler);
    }


    public static void delete(String urlString, com.loopj.android.http.RequestParams params, AsyncHttpResponseHandler handler)
    {
        getClient().delete(urlString, params, handler);
    }

    public static void delete(String urlString, AsyncHttpResponseHandler handler)
    {
        getClient().delete(urlString, handler);
    }


    private static Cookie decodeCookie(String cookieString)
    {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;

        try
        {
            ObjectInputStream e = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableCookie) e.readObject()).getCookie();
        } catch (IOException var6)
        {
            Log.d("PersistentCookieStore", "IOException in decodeCookie", var6);
        } catch (ClassNotFoundException var7)
        {
            Log.d("PersistentCookieStore", "ClassNotFoundException in decodeCookie", var7);
        }

        return cookie;
    }

    private static byte[] hexStringToByteArray(String hexString)
    {
        int len = hexString.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }

        return data;
    }
}
