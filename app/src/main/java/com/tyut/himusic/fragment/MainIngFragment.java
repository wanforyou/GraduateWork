/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.activity.SearchActivity;
import com.tyut.himusic.activity.VideoPlayerActivity;
import com.tyut.himusic.adapter.MainIngAdapter;
import com.tyut.himusic.bean.MainIngListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainIngFragment extends BaseFragment implements View.OnClickListener
{
    SimpleDraweeView imgPicture;
    @Bind(R.id.frag_main_ing_recyclerview)
    RecyclerView recyclerview;
    Button fragIngActivity;
    Button fragIngNew;
    Button fragIngHot;

    ImageView search;
    private View mListViewHeader;


    private MainIngAdapter adapter;
    private List<MainIngListData> datasActivity;
    private List<MainIngListData> datasNew;
    private List<MainIngListData> datasHot;

    public static MainIngFragment getInstance()
    {
        return new MainIngFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        datasActivity = new ArrayList<MainIngListData>();
        datasNew = new ArrayList<MainIngListData>();
        datasHot = new ArrayList<MainIngListData>();
        datasActivity.add(new MainIngListData("标题1",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍1", 45));
        datasActivity.add(new MainIngListData("标题2",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍1", 33));
        datasActivity.add(new MainIngListData("标题3",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍1", 435));
        datasActivity.add(new MainIngListData("标题4",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍1", 1));
        datasActivity.add(new MainIngListData("标题5",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍1", 4));
        datasNew.add(new MainIngListData("标题11",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍2", 145));
        datasNew.add(new MainIngListData("标题12",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍2", 133));
        datasNew.add(new MainIngListData("标题13",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍2", 1435));
        datasNew.add(new MainIngListData("标题14",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍2", 11));
        datasNew.add(new MainIngListData("标题15",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍2", 14));
        datasHot.add(new MainIngListData("标题21",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍3", 345));
        datasHot.add(new MainIngListData("标题22",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍3", 33));
        datasHot.add(new MainIngListData("标题23",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍3", 3435));
        datasHot.add(new MainIngListData("标题24",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍3", 31));
        datasHot.add(new MainIngListData("标题25",
                "http://7xqgf6.com2.z0.glb.qiniucdn.com/FmGVOUrfaIb3w0dyoodWCtT_6YC3", "今天", "介绍3", 34));
        mListViewHeader = getActivity().getLayoutInflater().inflate(R.layout.main_ing_frag_headview, null);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        search = (ImageView)findViewById(R.id.title_bar_back);
        search.setOnClickListener(this);

        View view = inflater.inflate(R.layout.fragment_main_ing, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;

    }

    void initView()
    {
        imgPicture = (SimpleDraweeView) mListViewHeader.findViewById(R.id.frag_main_ing_picture);
        fragIngActivity = (Button) mListViewHeader.findViewById(R.id.frag_ing_activity);
        fragIngActivity.setOnClickListener(this);
        fragIngNew = (Button) mListViewHeader.findViewById(R.id.frag_ing_new);
        fragIngNew.setOnClickListener(this);
        fragIngHot = (Button) mListViewHeader.findViewById(R.id.frag_ing_hot);
        fragIngHot.setOnClickListener(this);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setHasFixedSize(true);
        adapter = new MainIngAdapter(datasActivity, getContext());
        recyclerview.setAdapter(adapter);
        adapter.setHeaderView(mListViewHeader);
        imgPicture.setImageURI(Uri.parse(datasActivity.get(0).getImgurl()));



    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onClick(View view)
    {
        fragIngActivity.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngActivity.setBackgroundColor(getContext().getColor(R.color.color_text));
        fragIngActivity.setElevation(7);
        fragIngNew.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngNew.setBackgroundColor(getContext().getColor(R.color.color_text));
        fragIngNew.setElevation(7);
        fragIngHot.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngHot.setBackgroundColor(getContext().getColor(R.color.color_text));
        fragIngHot.setElevation(7);
        switch (view.getId())
        {
            case R.id.frag_ing_activity:
                fragIngActivity.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngActivity.setTextColor(getContext().getColor(R.color.color_text));
                fragIngActivity.setElevation(2);
                adapter.setDatas(datasActivity);
                adapter.notifyDataSetChanged();
                break;
            case R.id.frag_ing_new:
                fragIngNew.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngNew.setTextColor(getContext().getColor(R.color.color_text));
                fragIngNew.setElevation(2);
                adapter.setDatas(datasNew);
                adapter.notifyDataSetChanged();
                break;
            case R.id.frag_ing_hot:
                fragIngHot.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngHot.setTextColor(getContext().getColor(R.color.color_text));
                fragIngHot.setElevation(2);
                adapter.setDatas(datasHot);
                adapter.notifyDataSetChanged();
                break;
            case R.id.title_bar_back:
                startActivity(new Intent(getContext(), SearchActivity.class));
        }
    }
}
