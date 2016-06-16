package com.tyut.himusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.activity.MusicRunningActivity;
import com.tyut.himusic.activity.SearchActivity;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.adapter.MainHimusicManAdapter;
import com.tyut.himusic.bean.MainHimusicListData;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


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
    @Bind(R.id.main_top_music_running)
    ImageView goToMusicRunning;
    @Bind(R.id.main_suggestion_searchView)
    ImageView goTosearch;
    private MainHimusicManAdapter adapter;
    private List<MainHimusicListData> datasman;
    private List<MainHimusicListData> datasman2;

    private HiMusicMainFragment hiMusicMainFragment;
    private HiMusicManFragment hiMusicManFragment;

    private List<String> himusicImageIdList;

    public static MainHiMusicFragment getInstance()
    {
        return new MainHiMusicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        datasman = new ArrayList<MainHimusicListData>();
        datasman2 = new ArrayList<MainHimusicListData>();
        datasman.add(new MainHimusicListData("热门",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhongguofeng.png", "中国风",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhiqingchun.png", "致青春",
                "http://7xv7ag.com1.z0.glb.clouddn.com/tianxin.png", "甜心",
                "http://7xv7ag.com1.z0.glb.clouddn.com/jiezouyuwenrou.png", "节奏与温柔",
                "http://7xv7ag.com1.z0.glb.clouddn.com/gexiaoleng.png", "戈小冷"));
        datasman.add(new MainHimusicListData("心情",
                "http://7xv7ag.com1.z0.glb.clouddn.com/wuyan.png", "无言",
                "http://7xv7ag.com1.z0.glb.clouddn.com/chengmo.png", "沉默",
                "http://7xv7ag.com1.z0.glb.clouddn.com/yangguang.png", "阳光",
                "http://7xv7ag.com1.z0.glb.clouddn.com/nanguo.png", "难过",
                "http://7xv7ag.com1.z0.glb.clouddn.com/love.png", "love"));
        datasman.add(new MainHimusicListData("主题",
                "http://7xv7ag.com1.z0.glb.clouddn.com/landiao.png", "蓝调",
                "http://7xv7ag.com1.z0.glb.clouddn.com/rmb.png", "RMB",
                "http://7xv7ag.com1.z0.glb.clouddn.com/qingyinyue.png", "轻音乐",
                "http://7xv7ag.com1.z0.glb.clouddn.com/disco.png", "disco",
                "http://7xv7ag.com1.z0.glb.clouddn.com/yaogun.png", "摇滚"));
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
        datasman2.add(new MainHimusicListData("原创歌手",
                "http://7xv7ag.com1.z0.glb.clouddn.com/qiaoyang.png", "乔洋",
                "http://7xv7ag.com1.z0.glb.clouddn.com/xiaojian.png", "小贱",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhengguofeng.png", "郑国锋",
                "http://7xv7ag.com1.z0.glb.clouddn.com/xuliang.png", "徐良",
                "http://7xv7ag.com1.z0.glb.clouddn.com/jiajia.png", "家家"));
        datasman2.add(new MainHimusicListData("华语男歌手",
                "http://7xv7ag.com1.z0.glb.clouddn.com/jayzhou.jpg", "周杰伦",
                "http://7xv7ag.com1.z0.glb.clouddn.com/eason.jpg", "陈奕迅",
                "http://7xv7ag.com1.z0.glb.clouddn.com/linjj.jpg", "林俊杰",
                "http://7xv7ag.com1.z0.glb.clouddn.com/xuanzhiqian.jpg", "薛之谦",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhangxueyou.jpg", "张学友"));
        datasman2.add(new MainHimusicListData("华语女歌手",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhanghuimei.jpg", "张惠妹",
                "http://7xv7ag.com1.z0.glb.clouddn.com/caiyiling.jpg", "蔡依林",
                "http://7xv7ag.com1.z0.glb.clouddn.com/nayin.jpg", "那英",
                "http://7xv7ag.com1.z0.glb.clouddn.com/dengziqi.jpg", "邓紫棋",
                "http://7xv7ag.com1.z0.glb.clouddn.com/zhangliangyin.jpg", "张靓颖"));
        datasman2.add(new MainHimusicListData("日韩潮流",
                "http://7xv7ag.com1.z0.glb.clouddn.com/bigbang.jpg", "BIGBANG",
                "http://7xv7ag.com1.z0.glb.clouddn.com/lovelive.jpg", "邪教",
                "http://7xv7ag.com1.z0.glb.clouddn.com/TARA.jpg", "T-ARA",
                "http://7xv7ag.com1.z0.glb.clouddn.com/akb48.jpg", "AKB48",
                "http://7xv7ag.com1.z0.glb.clouddn.com/xiangcai.jpg", "兵库北"));
        datasman2.add(new MainHimusicListData("英美飚榜",
                "http://7xv7ag.com1.z0.glb.clouddn.com/Maroon%205.jpg", "Maroon 5",
                "http://7xv7ag.com1.z0.glb.clouddn.com/Rihanna.jpg", "Rihanna",
                "http://7xv7ag.com1.z0.glb.clouddn.com/Christopher.jpg", "Christopher",
                "http://7xv7ag.com1.z0.glb.clouddn.com/Avril%20Lavigne.jpg", "Avril",
                "http://7xv7ag.com1.z0.glb.clouddn.com/Taylor%20Swift.jpg", "Taylor"));

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
        viewHimusicPager = (AutoScrollViewPager) mListViewHeader.findViewById(R.id.frag_main_himusic_banner);
        fraghimusicMain = (TextView) mListViewHeader.findViewById(R.id.frag_main_himusic_main);
        fraghimusicMain.setOnClickListener(this);
        fraghimusicMan = (TextView) mListViewHeader.findViewById(R.id.frag_main_himusic_man);
        fraghimusicMan.setOnClickListener(this);
        himusicImageIdList = Arrays.asList(ImageUrlTestUtils.getImageUrls3());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new MainHimusicManAdapter(datasman, getContext());
        recyclerView.setAdapter(adapter);
        adapter.setHeaderView(mListViewHeader);
        viewHimusicPager = (AutoScrollViewPager) mListViewHeader.findViewById(R.id.frag_main_himusic_banner);
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
        goTosearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        goToMusicRunning.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), MusicRunningActivity.class));
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
                adapter.setDatas(datasman2);
                adapter.notifyDataSetChanged();

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
