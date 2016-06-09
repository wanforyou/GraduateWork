package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;

import butterknife.Bind;


public class HiMusicManFragment extends BaseFragment
{


    public static HiMusicManFragment getInstance()
    {
        return new HiMusicManFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_hivideo_man, container, false);
        return view;

    }
}
