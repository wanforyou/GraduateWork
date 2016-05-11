package com.tyut.himusic.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.tyut.himusic.R;
import com.tyut.himusic.fragment.MainMineFragment;
import com.tyut.himusic.fragment.MainSuggestionFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BashActivity implements View.OnClickListener
{


    @Bind(R.id.ing)
    RadioButton ing;
    @Bind(R.id.himusic)
    RadioButton himusic;
    @Bind(R.id.suggestion)
    RadioButton suggestion;
    @Bind(R.id.hivideo)
    RadioButton hivideo;
    @Bind(R.id.mine)
    RadioButton mine;

    private MainSuggestionFragment mainSuggestionFragment;
    private MainMineFragment mainMineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();


        initData();


//        mTodayHot = (Fragment)findViewById(R.layout.main_todayhot_fragment) ;

//        mTabTodayHot.setOnClickListener(this);
//        mTabMonthHot.setOnClickListener(this);
//        mTabWeekHot.setOnClickListener(this);

        // 设置默认的Fragment

    }

    @Override
    protected void initView()
    {
        setDefaultFragment();

    }


    @Override
    protected void initData()
    {

    }

    private void setDefaultFragment()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mainSuggestionFragment != null && mainSuggestionFragment.isAdded())
        {
            ft.show(mainSuggestionFragment);
        } else
        {
            // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
            mainSuggestionFragment = MainSuggestionFragment.getInstance();
//
            ft.add(R.id.act_main_fragment, mainSuggestionFragment);
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }


    @OnClick({R.id.ing, R.id.himusic, R.id.suggestion, R.id.hivideo, R.id.mine})
    public void onClick(View view)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mainSuggestionFragment != null)
        {
            ft.hide(mainSuggestionFragment);
        }
        if (mainMineFragment != null)
        {
            ft.hide(mainMineFragment);
        }
        switch (view.getId())
        {
            case R.id.ing:
                break;
            case R.id.himusic:
                break;
            case R.id.suggestion:
                if (mainSuggestionFragment != null && mainSuggestionFragment.isAdded())
                {
                    ft.show(mainSuggestionFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainSuggestionFragment = MainSuggestionFragment.getInstance();
//
                    ft.add(R.id.act_main_fragment, mainSuggestionFragment);
                }
                break;
            case R.id.hivideo:
                break;
            case R.id.mine:
                if (mainMineFragment != null && mainMineFragment.isAdded())
                {
                    ft.show(mainMineFragment);
                } else
                {
                    // 否则是第一次切换则添加fragment，注意添加后是会显示出来的，replace方法也是先remove后add
                    mainMineFragment = MainMineFragment.getInstance();
//
                    ft.add(R.id.act_main_fragment, mainMineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }
}

