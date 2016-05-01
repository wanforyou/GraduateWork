/*******************************************************************************
 * Recruit Andriod
 * Created by lqc
 * Copyright © 2016年 issuebang.com. All rights reserved.
 ******************************************************************************/
package com.tyut.himusic.util;

import java.util.HashMap;

/**
 * 全局变量储存
 *
 * @author luqingchuan on 16/1/16 14:47
 * @email luqingchuan@foxmail.com
 */
public class GlobalData
{


    private boolean loginStatus = false;
    private boolean rongyunConnected = false;
    private HashMap<String, String> headMap = new HashMap<String, String>();


    public boolean isLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus)
    {
        this.loginStatus = loginStatus;
    }

}
