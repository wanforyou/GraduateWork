package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;

/**
 * Created by Administrator on 2016/5/10.
 */
public class MainTodayhotFragment extends MainSuggestionFragment {


        public static MainTodayhotFragment getInstance()
        {
            return new MainTodayhotFragment();
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

