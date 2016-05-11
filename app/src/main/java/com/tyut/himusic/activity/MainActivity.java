package com.tyut.himusic.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;

import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends Activity {
    private TextView mTabTodayHot;
    private TextView mTabWeekHot;
    private TextView mTabMonthHot;

    private Fragment mTodayHot;
    private Fragment mWeekHot;
    private Fragment mMonthHot;



    private AutoScrollViewPager viewPager;
    private List<Integer>       imageIdList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (AutoScrollViewPager) findViewById(R.id.banner_main_activity);
//        indexText = (TextView)findViewById(R.id.view_pager_index);


        imageIdList.add(R.drawable.ic_album_grey600_48dp);
        imageIdList.add(R.drawable.ic_av_timer_black_48dp);
        imageIdList.add(R.drawable.ic_closed_caption_black_48dp);
        imageIdList.add(R.drawable.ic_equalizer_black_48dp);
        viewPager.setAdapter(new BannerAdapter(this, imageIdList));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        mTabTodayHot = (TextView)findViewById(R.id.bottom_today_hot);
        mTabWeekHot = (TextView)findViewById(R.id.bottom_week_hot);
        mTabMonthHot = (TextView)findViewById(R.id.bottom_month_hot);

//        mTodayHot = (Fragment)findViewById(R.layout.main_todayhot_fragment) ;

//        mTabTodayHot.setOnClickListener(this);
//        mTabMonthHot.setOnClickListener(this);
//        mTabWeekHot.setOnClickListener(this);

        // 设置默认的Fragment
        setDefaultFragment();

    }
    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mTodayHot = new Fragment();
        transaction.replace(R.id.main_gridview, mTodayHot);
        transaction.commit();
    }
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.bottom_today_hot:
                if (mTodayHot == null)
                {
                    mTodayHot = new Fragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.main_gridview, mTodayHot);
                break;
            case R.id.bottom_week_hot:
                if (mWeekHot == null)
                {
                    mWeekHot = new Fragment();
                }
                transaction.replace(R.id.main_gridview, mWeekHot);
                break;
            case R.id.bottom_month_hot:
                if (mMonthHot == null)
                {
                    mMonthHot = new Fragment();
                }
                transaction.replace(R.id.main_gridview, mMonthHot);
                break;

        }
        // transaction.addToBackStack();
        // 事务提交
        transaction.commit();
    }


}

