package com.tyut.himusic.adapter;

import android.content.Context;


import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.activity.MainActivity;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class BannerAdapter extends PagerAdapter {

    private Context context = null;
    private List<String> banners = null;
    private int imageCount = 0;

    private AutoScrollViewPager viewPager;


    public BannerAdapter(Context mcontext,
                         List<String> banners) {
        this.context = mcontext;
        this.banners = banners;
        this.imageCount = banners.size();

    }


    @Override
       public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView draweeView = new SimpleDraweeView(context);
        draweeView.setAdjustViewBounds(true);
        draweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        android.view.ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        draweeView.setLayoutParams(params);

        if (imageCount != 0)
        {
            draweeView.setImageURI(Uri.parse(banners.get(position % imageCount)));
            container.addView(draweeView);

            draweeView.setOnClickListener(new BannerClickListener(position
                    % imageCount));
        }
        return draweeView;
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
        return Integer.MAX_VALUE;
    }





    class BannerClickListener implements View.OnClickListener {

        private int position;

        public BannerClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 处理点击每个事件的处理
//                Toast.makeText(BannerAdapter.this, "点击了图片", Toast.LENGTH_SHORT).show();

        }
    }
}