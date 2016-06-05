package com.tyut.himusic.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tyut.himusic.R;
import com.tyut.himusic.activity.CircleMenuActivity;
import com.tyut.himusic.activity.MusicRunningActivity;
import com.tyut.himusic.adapter.BannerAdapter;
import com.tyut.himusic.fragment.BaseFragment;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.AutoScrollViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JAYZHOUFragment extends BaseFragment {


    @Bind(R.id.btn_music)
    Button jayMusic;
    @Bind(R.id.btn_mv)
    Button jayMv;
    @Bind(R.id.btn_music_special)
    Button jayMpecific;
    @Bind(R.id.btn_music_imformation)
    Button jayImf;



    private JayZhouMusicFragment jayZhouMusicFragment;
//    private JayZhouMvFragment jayZhouMvFragment;
//    private JayZhouSpecificFragment jayZhouSpecificFragment;
//    private JayZhouImfFragment jayZhouImfFragment;



    private List<String> imageIdList;

    public static JayZhouMusicFragment getInstance()
    {
        return new JayZhouMusicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    protected void initView()
    {
    }

    protected void initData()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_jayzhou, container, false);
        ButterKnife.bind(this, view);
        initView();
        setDefaultFragment();
        return view;

    }


    private void setDefaultFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (jayZhouMusicFragment != null && jayZhouMusicFragment.isAdded())
        {
            ft.show(jayZhouMusicFragment);
        } else
        {
            // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
            jayZhouMusicFragment = JayZhouMusicFragment.getInstance();
//
            ft.add(R.id.frag_jayzhou, jayZhouMusicFragment);
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_music, R.id.btn_mv, R.id.btn_music_special,R.id.btn_music_imformation})
    public void onClick(View view)
    {

        jayMusic.setBackgroundColor(getContext().getColor(R.color.color_org1));
        jayMusic.setElevation(10);
        jayMv.setBackgroundColor(getContext().getColor(R.color.color_org2));
        jayMv.setElevation(10);
        jayMpecific.setBackgroundColor(getContext().getColor(R.color.color_org2));
        jayMpecific.setElevation(10);
        jayImf.setBackgroundColor(getContext().getColor(R.color.color_org2));
        jayImf.setElevation(10);



        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        if (jayZhouMusicFragment != null)
        {
            ft.hide(jayZhouMusicFragment);
        }
//        if (jayZhouMvFragment != null)
//        {
//            ft.hide(jayZhouMvFragment);
//        }
//        if (jayZhouSpecificFragment != null)
//        {
//            ft.hide(jayZhouSpecificFragment);
//        }
//        if (jayZhouImfFragment != null)
//        {
//            ft.hide(jayZhouImfFragment);
//        }
        switch (view.getId())
        {
            case R.id.btn_music:
                jayMusic.setTextColor(getContext().getColor(R.color.color_org1));

                jayMusic.setElevation(3);
                if (jayZhouMusicFragment != null && jayZhouMusicFragment.isAdded())
                {
                    ft.show(jayZhouMusicFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    jayZhouMusicFragment = JayZhouMusicFragment.getInstance();
                    ft.add(R.id.frag_jayzhou, jayZhouMusicFragment);
                }
                break;
//            case R.id.btn_mv:
//                jayMv.setTextColor(getContext().getColor(R.color.color_org1));
//
//                jayMv.setElevation(3);
//                if (jayZhouMvFragment != null && jayZhouMvFragment.isAdded())
//                {
//                    ft.show(jayZhouMvFragment);
//                } else
//                {
//                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
//                    jayZhouMvFragment = JayZhouMusicFragment.getInstance();
//                    ft.add(R.id.frag_jayzhou, jayZhouMvFragment);
//                }
//                break;case R.id.btn_music_special:
//            jayMpecific.setTextColor(getContext().getColor(R.color.color_org1));
//
//            jayMpecific.setElevation(3);
//            if (jayZhouSpecificFragment != null && jayZhouSpecificFragment.isAdded())
//            {
//                ft.show(jayZhouSpecificFragment);
//            } else
//            {
//                // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
//                jayZhouSpecificFragment = JayZhouMusicFragment.getInstance();
//                ft.add(R.id.frag_jayzhou, jayZhouSpecificFragment);
//            }
//            break;case R.id.btn_music_imformation:
//            jayImf.setTextColor(getContext().getColor(R.color.color_org1));
//
//            jayImf.setElevation(3);
//            if (jayZhouImfFragment != null && jayZhouImfFragment.isAdded())
//            {
//                ft.show(jayZhouImfFragment);
//            } else
//            {
//                // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
//                jayZhouImfFragment = JayZhouMusicFragment.getInstance();
//                ft.add(R.id.frag_jayzhou, jayZhouImfFragment);
//            }
//            break;


        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}
