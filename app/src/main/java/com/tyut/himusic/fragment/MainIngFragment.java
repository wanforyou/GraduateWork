/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.adapter.MainIngAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainIngFragment extends BaseFragment
{
    @Bind(R.id.frag_main_ing_picture)
    SimpleDraweeView imgPicture;
    @Bind(R.id.frag_main_ing_recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.frag_ing_activity)
    Button fragIngActivity;
    @Bind(R.id.frag_ing_new)
    Button fragIngNew;
    @Bind(R.id.frag_ing_hot)
    Button fragIngHot;


    private MainIngAdapter adapter;
    private String[] imgUrls;

    public static MainIngFragment getInstance()
    {
        return new MainIngFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        imgUrls = ImageUrlTestUtils.getImageUrls();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_ing, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;

    }

    void initView()
    {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        adapter = new MainIngAdapter(imgUrls, getContext());
        recyclerview.setAdapter(adapter);
        imgPicture.setImageURI(Uri.parse(imgUrls[0]));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.frag_ing_activity, R.id.frag_ing_new, R.id.frag_ing_hot})
    public void onClick(View view)
    {
        fragIngActivity.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngActivity.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        fragIngActivity.setElevation(7);
        fragIngNew.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngNew.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        fragIngNew.setElevation(7);
        fragIngHot.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        fragIngHot.setBackgroundColor(getContext().getColor(R.color.bottom_white));
        fragIngHot.setElevation(7);
        switch (view.getId())
        {
            case R.id.frag_ing_activity:
                fragIngActivity.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngActivity.setTextColor(getContext().getColor(R.color.bottom_white));
                fragIngActivity.setElevation(2);
                break;
            case R.id.frag_ing_new:
                fragIngNew.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngNew.setTextColor(getContext().getColor(R.color.bottom_white));
                fragIngNew.setElevation(2);
                break;
            case R.id.frag_ing_hot:
                fragIngHot.setBackground(getContext().getDrawable(R.drawable.bottom7dp));
                fragIngHot.setTextColor(getContext().getColor(R.color.bottom_white));
                fragIngHot.setElevation(2);
                break;
        }
    }
}
