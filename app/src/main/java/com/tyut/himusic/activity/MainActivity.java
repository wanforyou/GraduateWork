package com.tyut.himusic.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.adapter.ImagePagerAdapter;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {
    private AutoScrollViewPager viewPager;


    private List<Integer>       imageIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (AutoScrollViewPager)findViewById(R.id.banner_main_activity);
//        indexText = (TextView)findViewById(R.id.view_pager_index);

        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.ic_album_grey600_48dp);
        imageIdList.add(R.drawable.ic_av_timer_black_48dp);
        imageIdList.add(R.drawable.ic_closed_caption_black_48dp);
        imageIdList.add(R.drawable.ic_equalizer_black_48dp);
        viewPager.setAdapter(new BannerAdapter(context, imageIdList).setInfiniteLoop(true));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
//        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));


//        innerViewPagerDemo = (Button)findViewById(R.id.auto_scroll_view_pager_inner);
//        innerViewPagerDemo.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, AutoScrollViewPagerInnerDemo.class));
//            }
//        });
    }

//    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
//
//        @Override
//        public void onPageSelected(int position) {
//            indexText.setText(new StringBuilder().append((position) % ListUtils.getSize(imageIdList) + 1).append("/")
//                    .append(ListUtils.getSize(imageIdList)));
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {}
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // stop auto scroll when onPause
//        viewPager.stopAutoScroll();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // start auto scroll when onResume
//        viewPager.startAutoScroll();
//    }
//
//}
