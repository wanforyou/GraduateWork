package com.tyut.himusic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HiVideoMainFragment extends BaseFragment
{

    @Bind(R.id.hivideo_hot)
    TextView hiVideoHot;
    @Bind(R.id.hivideo_new)
    TextView hiVideoNew;
    private HiVideoMainHotFragment hiVideoMainHotFragment;
    private HiVideoMainNewFragment hiVideoMainNewFragment;

    public static HiVideoMainFragment getInstance()
    {
        return new HiVideoMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setDefaultFragment();
        log.d("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_hivideo_main, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    private void setDefaultFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (hiVideoMainHotFragment != null && hiVideoMainHotFragment.isAdded())
        {
            ft.show(hiVideoMainHotFragment);
        } else
        {

            hiVideoMainHotFragment = HiVideoMainHotFragment.getInstance();

            ft.add(R.id.frag_hivideo_mine_hotandnew, hiVideoMainHotFragment);
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }

    protected void initData()
    {
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.hivideo_hot, R.id.hivideo_new})
    public void onClick(View view)
    {
        hiVideoHot.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoHot.setBackground(getContext().getDrawable(R.drawable.shape1_1));
        hiVideoNew.setTextColor(getContext().getColor(R.color.text_color_bottom_grey));
        hiVideoNew.setBackground(getContext().getDrawable(R.drawable.shape2));
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (hiVideoMainHotFragment != null)
        {
            ft.hide(hiVideoMainHotFragment);
        }
        if (hiVideoMainNewFragment != null)
        {
            ft.hide(hiVideoMainNewFragment);
        }
        switch (view.getId())
        {
            case R.id.hivideo_hot:
                hiVideoHot.setTextColor(getContext().getColor(R.color.color_text));
                hiVideoHot.setBackground(getContext().getDrawable(R.drawable.shape1));
                if (hiVideoMainHotFragment != null && hiVideoMainHotFragment.isAdded())
                {
                    ft.show(hiVideoMainHotFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMainHotFragment = HiVideoMainHotFragment.getInstance();
                    ft.add(R.id.frag_hivideo_mine_hotandnew, hiVideoMainHotFragment);
                }
                break;
            case R.id.hivideo_new:
                hiVideoNew.setTextColor(getContext().getColor(R.color.color_text));
                hiVideoNew.setBackground(getContext().getDrawable(R.drawable.shape2_2));
                if (hiVideoMainNewFragment != null && hiVideoMainNewFragment.isAdded())
                {
                    ft.show(hiVideoMainNewFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    hiVideoMainNewFragment = HiVideoMainNewFragment.getInstance();
                    ft.add(R.id.frag_hivideo_mine_hotandnew, hiVideoMainNewFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }
}
