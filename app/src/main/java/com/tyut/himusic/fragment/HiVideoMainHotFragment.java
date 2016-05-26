package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.HotAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.SpacesItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HiVideoMainHotFragment extends BaseFragment
{
    @Bind(R.id.frag_hivideo_hot_recyclerview)
    RecyclerView hiVideoHotRecyclerView;

    private HotAdapter videoHotAdapter;
    private String[] imgUrls;

    public static HiVideoMainHotFragment getInstance()
    {
        return new HiVideoMainHotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        imgUrls = ImageUrlTestUtils.getImageUrls();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hivideo_main_hot, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    void initView()
    {

        hiVideoHotRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        hiVideoHotRecyclerView.setHasFixedSize(true);
        videoHotAdapter=new HotAdapter(imgUrls, getContext());
        hiVideoHotRecyclerView.setAdapter(videoHotAdapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        hiVideoHotRecyclerView.addItemDecoration(decoration);
        log.d("");}

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

