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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSuggestionFragment extends BaseFragment
{

    @Bind(R.id.frag_main_suggestion_banner)
    AutoScrollViewPager viewPager;


    private MainTodayhotFragment mainTodayhotFragment;


    private List<Integer> imageIdList = new ArrayList<Integer>();

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
        imageIdList.add(R.drawable.ic_album_grey600_48dp);
        imageIdList.add(R.drawable.ic_av_timer_black_48dp);
        imageIdList.add(R.drawable.ic_closed_caption_black_48dp);
        imageIdList.add(R.drawable.ic_equalizer_black_48dp);
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

//        indexText = ( TextView)findViewById(R.id.view_pager_index);


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

    @OnClick({R.id.frag_main_suggestion_today_hot, R.id.frag_main_suggestion_week_hot, R.id.frag_main_suggestion_month_hot})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.frag_main_suggestion_today_hot:

                break;
            case R.id.frag_main_suggestion_week_hot:
                break;
            case R.id.frag_main_suggestion_month_hot:
                break;
        }
    }
}

