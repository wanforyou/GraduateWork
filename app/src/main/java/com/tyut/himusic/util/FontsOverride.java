package com.tyut.himusic.util; /*******************************************************************************
 * Recruit Andriod
 * Created by lqc
 * Copyright © 2016年 issuebang.com. All rights reserved.
 ******************************************************************************/

import android.content.Context;



import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * @author luqingchuan on 16/1/17 12:50
 * @email luqingchuan@foxmail.com
 */
public final class FontsOverride
{

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName)
    {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface)
    {
        try
        {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}