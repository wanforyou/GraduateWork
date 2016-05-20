/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tyut.himusic.R;

import butterknife.ButterKnife;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainHiVideoFragment extends BaseFragment
{

    private HiVideoMainFragment hiVideoMainFragment;
    private HiVideoMvFragment hiVideoMvFragment;
    private HiVideoSeceneFragment hiVideoSeceneFragment;
    private HiVideoManFragment hiVideoManFragment;


    @Bind(R.id.frag_hivideo_main)
    Button hiVideoMain;
    @Bind(R.id.frag_hivideo_secene)
    Button hiVideoScene;
    @Bind(R.id.frag_hivideo_mv)
    Button hiVideoMv;
    @Bind(R.id.frag_hivideo_man)
    Button hiVideoMan;




    public static MainHiVideoFragment getInstance()
    {
        return new MainHiVideoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setDefaultFragment();
        log.d("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_hi_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private void setDefaultFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (hiVideoMainFragment != null && hiVideoMainFragment.isAdded())
        {
            ft.show(hiVideoMainFragment);
        } else
        {
            // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
            hiVideoMainFragment = HiVideoMainFragment.getInstance();
//
            ft.add(R.id.frag_hivideo, hiVideoMainFragment);
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
    protected void initData()
    {
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.frag_hivideo_main,R.id.frag_hivideo_secene,R.id.frag_hivideo_mv,R.id.frag_hivideo_man})
    public void onClick(View view)
    {

        hiVideoMain.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMain.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        hiVideoMain.setElevation(7);
        hiVideoScene.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoScene.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        hiVideoScene.setElevation(7);
        hiVideoMv.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMv.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        hiVideoMv.setElevation(7);
        hiVideoMan.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMan.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        hiVideoMan.setElevation(7);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        if (hiVideoMvFragment != null)
        {
            ft.hide(hiVideoMvFragment);
        }
        if (hiVideoMainFragment != null)
        {
            ft.hide(hiVideoMainFragment);
        }
        if (hiVideoSeceneFragment != null)
        {
            ft.hide(hiVideoSeceneFragment);
        }
        if (hiVideoManFragment != null)
        {
            ft.hide(hiVideoManFragment);
        }
        switch (view.getId())
        {
            case R.id.frag_hivideo_main:
                hiVideoMain.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue_1));
                hiVideoMain.setTextColor(getContext().getColor(R.color.bottom_white));
                hiVideoMain.setElevation(2);
                if (hiVideoMainFragment != null && hiVideoMainFragment.isAdded())
                {
                    ft.show(hiVideoMainFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMainFragment = HiVideoMainFragment.getInstance();
                    ft.add(R.id.frag_hivideo, hiVideoMainFragment);
                }
                break;
            case R.id.frag_hivideo_mv:
                hiVideoMv.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue_1));
                hiVideoMv.setTextColor(getContext().getColor(R.color.bottom_white));
                hiVideoMv.setElevation(2);
                if (hiVideoMvFragment != null && hiVideoMvFragment.isAdded())
                {
                    ft.show(hiVideoMvFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMvFragment = hiVideoMvFragment.getInstance();
                    ft.add(R.id.frag_hivideo, hiVideoMvFragment);
                }

                break;
            case R.id.frag_hivideo_secene:
                hiVideoScene.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue_1));
                hiVideoScene.setTextColor(getContext().getColor(R.color.bottom_white));
                hiVideoScene.setElevation(2);
                if (hiVideoSeceneFragment != null && hiVideoSeceneFragment.isAdded())
                {
                    ft.show(hiVideoSeceneFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoSeceneFragment = HiVideoSeceneFragment.getInstance();
                    ft.add(R.id.frag_hivideo, hiVideoSeceneFragment);
                }

                break;
            case R.id.frag_hivideo_man:
                hiVideoMan.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue_1));
                hiVideoMan.setTextColor(getContext().getColor(R.color.bottom_white));
                hiVideoMan.setElevation(2);
                if (hiVideoManFragment != null && hiVideoManFragment.isAdded())
                {
                    ft.show(hiVideoManFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoManFragment = hiVideoManFragment.getInstance();
                    ft.add(R.id.frag_hivideo, hiVideoManFragment);
                }

                break;
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}