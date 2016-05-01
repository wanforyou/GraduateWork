/*******************************************************************************
 * Recruit Andriod
 * Created by lqc
 * Copyright © 2016年 issuebang.com. All rights reserved.
 ******************************************************************************/
package com.tyut.himusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tyut.himusic.activity.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luqingchuan on 16/1/16 13:33
 * @email luqingchuan@foxmail.com
 */
public class SharedPreferenceUtil
{
    // SharedPreference中 文件名
    public static final String USER_FILE = "User_file";

    private static SharedPreferences setting = MyApplication.getInstance().getSharedPreferences(
            USER_FILE, Context.MODE_MULTI_PROCESS);


    public static final SharedPreferences getSharedPreferencesInstance()
    {
        return setting;
    }

    public static final Editor getEditorInstance()
    {
        return getSharedPreferencesInstance().edit();
    }

    public static int getSharePreInt(String itemTag, int defval)
    {
        return getSharedPreferencesInstance().getInt(itemTag, defval);
    }

    public static boolean putSharePreInt(String itemTag,
                                         int val)
    {
        Editor editor = getEditorInstance();
        editor.putInt(itemTag, val);
        return editor.commit();
    }

    public static String getSharePreString(String itemTag,
                                           String defValue)
    {
        return getSharedPreferencesInstance().getString(itemTag, defValue);
    }

    public static boolean putSharePreString(String itemTag,
                                            String val)
    {
        Editor editor = getEditorInstance();
        editor.putString(itemTag, val);
        return editor.commit();
    }

    public static boolean getSharePreBoolean(String itemTag,
                                             boolean defValue)
    {
        return getSharedPreferencesInstance().getBoolean(itemTag, defValue);
    }

    public static boolean putSharePreBoolean(String itemTag,
                                             boolean val)
    {
        Editor editor = getEditorInstance();
        editor.putBoolean(itemTag, val);
        return editor.commit();
    }

    public static ArrayList<String> getSharePreStringList(String key)
    {
        ArrayList<String> list = JSON.parseObject(getSharedPreferencesInstance().getString(key, null),
                new TypeReference<ArrayList<String>>()
                {
                });
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    public static boolean putSharePreStringList(String key,
                                                List<String> list)
    {
        Editor editor = getEditorInstance();
        editor.putString(key, JSON.toJSONString(list));
        return editor.commit();
    }

    /**
     * 保存对象
     */
    public static boolean putObject(String tag, Object obj)
    {
        Editor editor = getEditorInstance();
        editor.putString(tag, JSON.toJSONString(obj));
        return editor.commit();
    }

    /**
     * 删除对象
     */
    public static boolean removeObject(String tag)
    {
        Editor editor = getEditorInstance();
        editor.remove(tag);
        return editor.commit();
    }


}
