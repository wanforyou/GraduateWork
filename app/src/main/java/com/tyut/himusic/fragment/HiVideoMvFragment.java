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


public class HiVideoMvFragment extends BaseFragment
{
    @Bind(R.id.frag_hivideo_mv_recyclerview)
    RecyclerView hiVideoMVRecyclerView;

    private HotAdapter videoMvAdapter;
    private String[] imgUrls;
    private String[] imgTitles;

    public static HiVideoMvFragment getInstance()
    {
        return new HiVideoMvFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        imgUrls = ImageUrlTestUtils.getImageUrls();
        imgTitles = ImageUrlTestUtils.getImageTitle();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_hivideo_mv, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    void initView()
    {
        hiVideoMVRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        hiVideoMVRecyclerView.setHasFixedSize(true);
        videoMvAdapter = new HotAdapter(imgUrls,imgTitles, getContext());
        hiVideoMVRecyclerView.setAdapter(videoMvAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        hiVideoMVRecyclerView.addItemDecoration(decoration);
        log.d("");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
