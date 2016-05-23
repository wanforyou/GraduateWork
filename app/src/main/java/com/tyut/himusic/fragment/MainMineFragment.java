/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tyut.himusic.R;
import com.tyut.himusic.activity.MyInformation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainMineFragment extends BaseFragment
{
    @Bind(R.id.my_imformation_more)
    RelativeLayout myImformationMore;

    @Bind(R.id.my_video)
    ImageView myVideo;
    @Bind(R.id.my_music)
    ImageView myMusic;
    @Bind(R.id.my_nosim)
    ImageView myNosim;
    @Bind(R.id.my_vip)
    ImageView myVip;

    @Bind(R.id.my_setting)
    RelativeLayout mySeting;
    @Bind(R.id.my_save)
    RelativeLayout mySafe;
    @Bind(R.id.my_time_close)
    RelativeLayout myTimeClose;
    @Bind(R.id.my_theme)
    RelativeLayout myTheme;
    @Bind(R.id.my_about_himusic)
    RelativeLayout myAboutHimusic;
    @Bind(R.id.my_button_outline)
    Button myButtonOutline;


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
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.my_imformation_more,
            R.id.my_video,R.id.my_music,R.id.my_nosim,R.id.my_vip,
            R.id.my_setting,R.id.my_save, R.id.my_time_close,R.id.my_theme,R.id.my_about_himusic,R.id.my_button_outline})
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.my_imformation_more:
                startActivity(new Intent(getContext(),MyInformation.class));
                break;
        }

}
}
