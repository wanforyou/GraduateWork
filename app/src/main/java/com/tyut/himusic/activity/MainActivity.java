package com.tyut.himusic.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;

import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends Activity {

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
    }
}

