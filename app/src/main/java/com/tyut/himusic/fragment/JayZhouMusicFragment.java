package com.tyut.himusic.fragment;

import android.app.Activity;
import android.widget.ListView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.JayzhouMusicListAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;

/**
 * Created by Administrator on 2016/6/5.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JayZhouMusicFragment extends BaseFragment{
    @Bind(R.id.frag_jayzhou_music)
    ListView lv;
    JayzhouMusicListAdapter listAdapter;

    public static JayZhouMusicFragment getInstance()
    {
        return new JayZhouMusicFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jayzhou_music, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    void initView()
    {
        listAdapter  = new JayzhouMusicListAdapter(getContext());
            lv.setAdapter(listAdapter);
        }


    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    }
