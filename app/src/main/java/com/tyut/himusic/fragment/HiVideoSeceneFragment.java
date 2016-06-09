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

/**
 * Created by Administrator on 2016/5/17.
 */
public class HiVideoSeceneFragment extends BaseFragment
{
    @Bind(R.id.frag_hivideo_scene_recyclerview)
    RecyclerView hiVideoSceneRecyclerView;

    private HotAdapter videoSceneAdapter;
    private String[] imgUrls;
    private String[] imgTitles;

    public static HiVideoSeceneFragment getInstance()
    {
        return new HiVideoSeceneFragment();
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
        View view = inflater.inflate(R.layout.fragment_hivideo_scene, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;

    }
    void initView()
    {
        hiVideoSceneRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        hiVideoSceneRecyclerView.setHasFixedSize(true);
        videoSceneAdapter = new HotAdapter(imgUrls,imgTitles, getContext());
        hiVideoSceneRecyclerView.setAdapter(videoSceneAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        hiVideoSceneRecyclerView.addItemDecoration(decoration);
        log.d("");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
