package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainHiMusicFragment extends BaseFragment
{
    @Bind(R.id.frag_main_himusic_banner)
    AutoScrollViewPager viewHimusicPager;
    @Bind(R.id.frag_main_himusic_main)
    TextView himusicMain;
    @Bind(R.id.frag_main_himusic_man)
    TextView himusicMan;


private HiMusicMainFragment hiMusicMainFragment;
    private HiMusicManFragment hiMusicManFragment;

    private List<String> himusicImageIdList ;
    public static MainHiMusicFragment getInstance()
    {
        return new MainHiMusicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setDefaultFragment();
        log.d("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_hi_music, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    protected void initView()
    {
        himusicImageIdList= Arrays.asList(ImageUrlTestUtils.getImageUrls2());
        viewHimusicPager.setAdapter(new BannerAdapter(getContext(), himusicImageIdList));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewHimusicPager
                .setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        viewHimusicPager.setInterval(2000);
        viewHimusicPager.startAutoScroll();
        viewHimusicPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        viewHimusicPager.stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        viewHimusicPager.startAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:
                        viewHimusicPager.startAutoScroll();
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




    private void setDefaultFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (hiMusicMainFragment != null && hiMusicMainFragment.isAdded())
        {
            ft.show(hiMusicMainFragment);
        } else
        {
            // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
            hiMusicMainFragment = HiMusicMainFragment.getInstance();
//
            ft.add(R.id.frag_main_suggestion_fragment, hiMusicMainFragment);
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

    @OnClick({R.id.frag_main_himusic_main, R.id.frag_main_himusic_man})
    public void onClick(View view)
    {
        himusicMain.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        himusicMain.setBackgroundColor(getContext().getColor(R.color.bottom_white));

        himusicMan.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        himusicMan.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        if (hiMusicMainFragment != null)
        {
            ft.hide(hiMusicMainFragment);
        }
        if (hiMusicManFragment != null)
        {
            ft.hide(hiMusicManFragment);
        }

        switch (view.getId())
        {
            case R.id.frag_main_himusic_main:
                himusicMain.setTextColor(getContext().getColor(R.color.bottom_white));
                himusicMain.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue));
                if (hiMusicMainFragment != null && hiMusicMainFragment.isAdded())
                {
                    ft.show(hiMusicMainFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiMusicMainFragment = HiMusicMainFragment.getInstance();
                    ft.add(R.id.frag_main_himusic_fragment, hiMusicMainFragment);
                }
                break;
            case R.id.frag_main_himusic_man:
                himusicMan.setTextColor(getContext().getColor(R.color.bottom_white));
                himusicMan.setBackgroundColor(getContext().getColor(R.color.text_color_lightblue));

                if (hiMusicManFragment != null && hiMusicManFragment.isAdded())
                {
                    ft.show(hiMusicManFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiMusicManFragment = HiMusicManFragment.getInstance();
                    ft.add(R.id.frag_main_himusic_fragment, hiMusicManFragment);
                }

                break;

        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}
