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
import com.tyut.himusic.activity.MusicRunningActivity;
import com.tyut.himusic.activity.MyInformation;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSuggestionFragment extends BaseFragment
{

    @Bind(R.id.frag_main_banner)
    AutoScrollViewPager viewPager;
    @Bind(R.id.frag_main_suggestion_today_hot)
    Button today;
    @Bind(R.id.frag_main_suggestion_week_hot)
    Button week;
    @Bind(R.id.frag_main_suggestion_month_hot)
    Button month;
    @Bind(R.id.main_top_music_running)
    ImageView goToMusicRunning;




    private MainTodayhotFragment mainTodayhotFragment;
    private MainMonthhotFragment mainMonthhotFragment;
    private MainWeekhotFragment mainWeekhotFragment;


    private List<String> imageIdList ;

    public static MainSuggestionFragment getInstance()
    {
        return new MainSuggestionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setDefaultFragment();
        log.d("");
    }

    protected void initView()
    {
        imageIdList=Arrays.asList(ImageUrlTestUtils.getImageUrls2());
        viewPager.setAdapter(new BannerAdapter(getContext(), imageIdList));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager
                .setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        viewPager.stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        viewPager.startAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:
                        viewPager.startAutoScroll();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    protected void initData()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_suggestion, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void setDefaultFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (mainTodayhotFragment != null && mainTodayhotFragment.isAdded())
        {
            ft.show(mainTodayhotFragment);
        } else
        {
            // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
            mainTodayhotFragment = MainTodayhotFragment.getInstance();
//
            ft.add(R.id.frag_main_suggestion_fragment, mainTodayhotFragment);
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }




    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.frag_main_suggestion_today_hot, R.id.frag_main_suggestion_week_hot, R.id.frag_main_suggestion_month_hot,R.id.main_top_music_running})
    public void onClick(View view)
    {
        today.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        today.setBackgroundColor(getContext().getColor(R.color.color_text));
        today.setElevation(10);
        week.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        week.setBackgroundColor(getContext().getColor(R.color.color_text));
        week.setElevation(10);
        month.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        month.setBackgroundColor(getContext().getColor(R.color.color_text));
        month.setElevation(10);


        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        if (mainTodayhotFragment != null)
        {
            ft.hide(mainTodayhotFragment);
        }
        if (mainWeekhotFragment != null)
        {
            ft.hide(mainWeekhotFragment);
        }
        if (mainMonthhotFragment != null)
        {
            ft.hide(mainMonthhotFragment);
        }
        switch (view.getId())
        {
            case R.id.frag_main_suggestion_today_hot:
                today.setTextColor(getContext().getColor(R.color.color_text));
                today.setBackgroundColor(getContext().getColor(R.color.color1));
                today.setElevation(3);
                if (mainTodayhotFragment != null && mainTodayhotFragment.isAdded())
                {
                    ft.show(mainTodayhotFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainTodayhotFragment = MainTodayhotFragment.getInstance();
                    ft.add(R.id.frag_main_suggestion_fragment, mainTodayhotFragment);
                }
                break;
            case R.id.frag_main_suggestion_week_hot:
                week.setTextColor(getContext().getColor(R.color.color_text));
                week.setBackgroundColor(getContext().getColor(R.color.color1));
                week.setElevation(3);
                if (mainWeekhotFragment != null && mainWeekhotFragment.isAdded())
                {
                    ft.show(mainWeekhotFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainWeekhotFragment = MainWeekhotFragment.getInstance();
                    ft.add(R.id.frag_main_suggestion_fragment, mainWeekhotFragment);
                }

                break;
            case R.id.frag_main_suggestion_month_hot:
                month.setTextColor(getContext().getColor(R.color.color_text));
                month.setBackgroundColor(getContext().getColor(R.color.color1));
                month.setElevation(3);
                if (mainMonthhotFragment != null && mainMonthhotFragment.isAdded())
                {
                    ft.show(mainMonthhotFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainMonthhotFragment = MainMonthhotFragment.getInstance();
                    ft.add(R.id.frag_main_suggestion_fragment, mainMonthhotFragment);
                }

                break;
            case R.id.main_top_music_running:
                startActivity(new Intent(getContext(),MusicRunningActivity.class));
                break;
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}

