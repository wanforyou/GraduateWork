package com.tyut.himusic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;

import butterknife.ButterKnife;


public class MyvideoFragment extends Fragment
{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myvideo, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

