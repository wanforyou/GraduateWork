package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyut.himusic.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class HiVideoSeceneFragment extends BaseFragment
{
    public static HiVideoSeceneFragment getInstance()
    {
        return new HiVideoSeceneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_ing, container, false);
        return view;

    }
}
