/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author luqingchuan on 16/5/11 22:20
 * @email luqingchuan@foxmail.com
 */
public class MainSuggestionFragment extends BaseFragment
{

    @Bind(R.id.bottom_today_hot)
    TextView bottom_today_hot;
    @Bind(R.id.bottom_week_hot)
    TextView bottom_week_hot;
    @Bind(R.id.bottom_month_hot)
    TextView bottom_month_hot;




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


         GridView gridview = (GridView) view.findViewById(R.id.main_todayhot_gridview);

        gridview.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            private MainTodayhotFragment mainTodayhotFragment;



            protected void initView()
            {
                setDefaultFragment();
            }

            protected void initData()
            {
            }

            private void setDefaultFragment()
            {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                if ( mainTodayhotFragment!= null && mainTodayhotFragment.isAdded())
                {
                    ft.show(mainTodayhotFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainTodayhotFragment = MainTodayhotFragment.getInstance().getInstance();
//
                    ft.add(R.id.act_main_fragment, mainTodayhotFragment);
                }
                ft.commitAllowingStateLoss();
                getChildFragmentManager().executePendingTransactions();
            }
            @OnClick({R.id.bottom_today_hot,R.id.bottom_week_hot,R.id.bottom_month_hot})
            public void onClick(View view)
            {
                bottom_today_hot.setTextColor(getColor(R.color.text_color_bottom_grey));
                bottom_week_hot
                        bottom_month_hot
            }


        });

    }
}

