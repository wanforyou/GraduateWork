package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.adapter.MainHimusicManAdapter;
import com.tyut.himusic.adapter.MainIngAdapter;
import com.tyut.himusic.bean.MainHimusicListData;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainHiMusicFragment extends BaseFragment implements View.OnClickListener
{
//    @Bind(R.id.frag_main_himusic_banner)
    AutoScrollViewPager viewHimusicPager;
//    @Bind(R.id.frag_main_himusic_main)
    TextView fraghimusicMain;
//    @Bind(R.id.frag_main_himusic_man)
    TextView fraghimusicMan;
    @Bind(R.id.frag_main_himusic_recyclerview)
    RecyclerView recyclerView;
    private View mListViewHeader;

    private MainHimusicManAdapter adapter;
    private List<MainHimusicListData> datasman;

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
        datasman = new ArrayList<MainHimusicListData>();
        datasman.add(new MainHimusicListData("热门",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        datasman.add(new MainHimusicListData("心情",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        datasman.add(new MainHimusicListData("主题",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        datasman.add(new MainHimusicListData("场景",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        datasman.add(new MainHimusicListData("语种",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        datasman.add(new MainHimusicListData("流派",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "title5"));
        mListViewHeader = getActivity().getLayoutInflater().inflate(R.layout.main_himusic_frag_headview, null);
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
        viewHimusicPager = (AutoScrollViewPager)mListViewHeader.findViewById(R.id.frag_main_himusic_banner);
        fraghimusicMain = (TextView)mListViewHeader.findViewById(R.id.frag_main_himusic_main);
        fraghimusicMain.setOnClickListener(this);
        fraghimusicMan = (TextView)mListViewHeader.findViewById(R.id.frag_main_himusic_man) ;
        fraghimusicMan.setOnClickListener(this);
        himusicImageIdList= Arrays.asList(ImageUrlTestUtils.getImageUrls2());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new MainHimusicManAdapter(datasman, getContext());
        recyclerView.setAdapter(adapter);
        adapter.setHeaderView(mListViewHeader);
        viewHimusicPager=(AutoScrollViewPager)mListViewHeader.findViewById(R.id.frag_main_himusic_banner);
        viewHimusicPager.setAdapter(new BannerAdapter(getContext(), himusicImageIdList));
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
            ft.add(R.id.frag_main_himusic_fragment, hiMusicMainFragment);
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


    public void onClick(View view)
    {
        fraghimusicMain.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fraghimusicMain.setBackgroundColor(getContext().getColor(R.color.color_text));
        fraghimusicMan.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fraghimusicMan.setBackgroundColor(getContext().getColor(R.color.color_text));
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
                fraghimusicMain.setTextColor(getContext().getColor(R.color.color_text));
                fraghimusicMain.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fraghimusicMain.setElevation(2);
                adapter.setDatas(datasman);
                adapter.notifyDataSetChanged();
                if (hiMusicMainFragment != null && hiMusicMainFragment.isAdded())
                {
                    ft.show(hiMusicMainFragment);
                } else
                {
                    hiMusicMainFragment = HiMusicMainFragment.getInstance();
                    ft.add(R.id.frag_main_himusic_fragment, hiMusicMainFragment);
                }
                break;
            case R.id.frag_main_himusic_man:
                fraghimusicMan.setTextColor(getContext().getColor(R.color.color_text));
                fraghimusicMan.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fraghimusicMain.setElevation(2);

                if (hiMusicManFragment != null && hiMusicManFragment.isAdded())
                {
                    ft.show(hiMusicManFragment);
                } else
                {
                    hiMusicManFragment = HiMusicManFragment.getInstance();
                    ft.add(R.id.frag_main_himusic_fragment, hiMusicManFragment);
                }

                break;

        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}
