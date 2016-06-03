/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tyut.himusic.R;
import com.tyut.himusic.activity.VideoPlayerActivity;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainHiVideoFragment extends BaseFragment
{

    @Bind(R.id.frag_hivideo_main)
    Button hiVideoMain;
    @Bind(R.id.frag_hivideo_secene)
    Button hiVideoScene;
    @Bind(R.id.frag_hivideo_mv)
    Button hiVideoMv;
    @Bind(R.id.frag_hivideo_man)
    Button hiVideoMan;
    @Bind(R.id.frag_main_hivideo_banner)
    AutoScrollViewPager viewPagerHivideo;
    @Bind(R.id.main_top_music_running)
    ImageView goToMusicRunning;
    private HiVideoMainFragment hiVideoMainFragment;
    private HiVideoMvFragment hiVideoMvFragment;
    private HiVideoSeceneFragment hiVideoSeceneFragment;
    private HiVideoManFragment hiVideoManFragment;
    private List<String> imageIdList;


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
        initView();
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

    protected void initView()
    {
        imageIdList = Arrays.asList(ImageUrlTestUtils.getImageUrls2());
        viewPagerHivideo.setAdapter(new BannerAdapter(getContext(), imageIdList));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPagerHivideo
                .setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        viewPagerHivideo.setInterval(2000);
        viewPagerHivideo.startAutoScroll();
        viewPagerHivideo.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        viewPagerHivideo.stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        viewPagerHivideo.startAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:
                        viewPagerHivideo.startAutoScroll();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        goToMusicRunning.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), VideoPlayerActivity.class));
            }
        });
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

    @OnClick({R.id.frag_hivideo_main, R.id.frag_hivideo_secene, R.id.frag_hivideo_mv, R.id.frag_hivideo_man})
    public void onClick(View view)
    {

        hiVideoMain.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMain.setBackgroundColor(getContext().getColor(R.color.color_text));
        hiVideoMain.setElevation(7);
        hiVideoScene.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoScene.setBackgroundColor(getContext().getColor(R.color.color_text));
        hiVideoScene.setElevation(7);
        hiVideoMv.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMv.setBackgroundColor(getContext().getColor(R.color.color_text));
        hiVideoMv.setElevation(7);
        hiVideoMan.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoMan.setBackgroundColor(getContext().getColor(R.color.color_text));
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
                hiVideoMain.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                hiVideoMain.setTextColor(getContext().getColor(R.color.color_text));
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
                hiVideoMv.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                hiVideoMv.setTextColor(getContext().getColor(R.color.color_text));
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
                hiVideoScene.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                hiVideoScene.setTextColor(getContext().getColor(R.color.color_text));
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
                hiVideoMan.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                hiVideoMan.setTextColor(getContext().getColor(R.color.color_text));
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