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
import com.tyut.himusic.activity.CircleMenuActivity;
import com.tyut.himusic.activity.MusicRunningActivity;
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

    @Bind(R.id.main_suggestion_searchView)
    ImageView goTosearch;

    private MainTodayhotFragment mainTodayhotFragment;
    private MainMonthhotFragment mainMonthhotFragment;
    private MainWeekhotFragment mainWeekhotFragment;


    private List<String> imageIdList;

    public static MainSuggestionFragment getInstance()
    {
        return new MainSuggestionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    protected void initView()
    {
        imageIdList = Arrays.asList(ImageUrlTestUtils.getImageUrls2());
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
        goToMusicRunning.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), MusicRunningActivity.class));
            }
        });
        goTosearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), CircleMenuActivity.class));
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
        setDefaultFragment();
        return view;

    }


    private void setDefaultFragment()
    {
        today.setSelected(true);
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

    @OnClick({R.id.frag_main_suggestion_today_hot, R.id.frag_main_suggestion_week_hot, R.id.frag_main_suggestion_month_hot})
    public void onClick(View view)
    {
        today.setSelected(false);
        today.setElevation(10);
        week.setSelected(false);
        week.setElevation(10);
        month.setSelected(false);
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

                today.setSelected(true);
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
                week.setSelected(true);

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
                month.setSelected(true);

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

        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}

