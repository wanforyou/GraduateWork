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

    @Bind(R.id.top_menu_text1)
    TextView topMenuText1;
    @Bind(R.id.top_menu_text2)
    TextView topMenuText2;
    @Bind(R.id.hivideo_main)
    TextView hiVideoMain;
    @Bind(R.id.hivideo_secene)
    TextView hiVideoScene;
    @Bind(R.id.hivideo_mv)
    TextView hiVideoMv;




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
            ft.add(R.id.frag_hivideo_mine, hiVideoMainFragment);
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

    @OnClick({R.id.top_menu_text1,R.id.top_menu_text2})
    public void onClick(View view)
    {
        topMenuText1.setText("视频");
        topMenuText1.setTextColor(getContext().getColor(R.color.bottom_green));
        topMenuText2.setText("达人");
        topMenuText2.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMain.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoScene.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoScene.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));

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
        switch (view.getId())
        {
            case R.id.hivideo_main:
                hiVideoMain.setTextColor(getContext().getColor(R.color.bottom_green));
                if (hiVideoMainFragment != null && hiVideoMainFragment.isAdded())
                {
                    ft.show(hiVideoMainFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMainFragment = HiVideoMainFragment.getInstance();
                    ft.add(R.id.frag_hivideo_mine, hiVideoMainFragment);
                }
                break;
            case R.id.hivideo_mv:
                hiVideoMv.setTextColor(getContext().getColor(R.color.bottom_green));
                if (hiVideoSeceneFragment != null && hiVideoSeceneFragment.isAdded())
                {
                    ft.show(hiVideoMvFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMvFragment = hiVideoMvFragment.getInstance();
                    ft.add(R.id.frag_hivideo_mine, hiVideoMvFragment);
                }

                break;
            case R.id.hivideo_secene:
                hiVideoScene.setTextColor(getContext().getColor(R.color.bottom_green));
                if (hiVideoSeceneFragment != null && hiVideoSeceneFragment.isAdded())
                {
                    ft.show(hiVideoSeceneFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoSeceneFragment = HiVideoSeceneFragment.getInstance();
                    ft.add(R.id.frag_hivideo_mine, hiVideoSeceneFragment);
                }

                break;
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}