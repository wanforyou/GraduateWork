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

public class MainMonthhotFragment extends BaseFragment
{

    @Bind(R.id.frag_jayzhou_mv)
    RecyclerView recyclerView;
    private HotAdapter hotAdapter;
    private String[] imgUrls;
    private String[] imgTitles;

    public static MainMonthhotFragment getInstance()
    {
        return new MainMonthhotFragment();
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
        View view = inflater.inflate(R.layout.fragment_suggestion_monthhot, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;

    }

    void initView()
    {

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        hotAdapter = new HotAdapter(imgUrls,imgTitles, getContext());
        recyclerView.setAdapter(hotAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        log.d("");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

