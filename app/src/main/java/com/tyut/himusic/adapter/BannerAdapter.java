package com.tyut.himusic.adapter;

import android.content.Context;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class BannerAdapter extends PagerAdapter {

    private Context context = null;
    private List<Integer> banners = null;
    private int imageCount = 0;

    private AutoScrollViewPager viewPager;


    public BannerAdapter(Context mcontext,
                         List<Integer> banners) {
        this.context = mcontext;
        this.banners = banners;
        this.imageCount = banners.size();

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(context.getDrawable(banners.get(position)));
        ((AutoScrollViewPager) container).addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    @Override
    public int getCount()
    {
        return 4;
    }



    class BannerClickListener implements View.OnClickListener {

        private int position;

        public BannerClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 处理点击每个事件的处理

            try {
//                Toast.makeText(getApplicationContext(), "点击了图片", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

            }
        }
    }
}