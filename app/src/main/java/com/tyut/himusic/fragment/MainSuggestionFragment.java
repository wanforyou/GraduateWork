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
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luqingchuan on 16/5/11 22:20
 * @email luqingchuan@foxmail.com
 */
public class MainSuggestionFragment extends BaseFragment
{
    private TextView mTabTodayHot;
    private TextView mTabWeekHot;
    private TextView mTabMonthHot;
    private AutoScrollViewPager viewPager;
    private List<Integer> imageIdList = new ArrayList<Integer>();

    public static MainSuggestionFragment getInstance()
    {
        return new MainSuggestionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_suggestion, container, false);
        viewPager = (AutoScrollViewPager) view.findViewById(R.id.banner_main_activity);
//        indexText = (TextView)findViewById(R.id.view_pager_index);
        imageIdList.add(R.drawable.ic_album_grey600_48dp);
        imageIdList.add(R.drawable.ic_av_timer_black_48dp);
        imageIdList.add(R.drawable.ic_closed_caption_black_48dp);
        imageIdList.add(R.drawable.ic_equalizer_black_48dp);

        viewPager.setAdapter(new BannerAdapter(getContext(), imageIdList));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        return view;
    }
}
