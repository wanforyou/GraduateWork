/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;

/**
 * @author luqingchuan on 16/5/11 22:36
 * @email luqingchuan@foxmail.com
 */
public class MainMineFragment extends BaseFragment
{
    public static MainMineFragment getInstance()
    {
        return new MainMineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_mine, container, false);
        return view;

    }
}
