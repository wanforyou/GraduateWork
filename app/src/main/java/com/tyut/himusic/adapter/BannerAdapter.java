package com.tyut.himusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class BannerAdapter extends PagerAdapter {
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }

    private Context context = null;
    private List<Integer> banners = null;
    private int imageCount = 0;
    private AutoScrollViewPager PosterPager;

    public BannerAdapter(Context mcontext,
                         List<Integer> banners)
    {
        this.context = mcontext;
        this.banners = banners;
        this.imageCount = banners.size();

    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }
}
