package com.tyut.himusic.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.tyut.himusic.R;
import com.tyut.himusic.fragment.MainHiMusicFragment;
import com.tyut.himusic.fragment.MainHiVideoFragment;
import com.tyut.himusic.fragment.MainIngFragment;
import com.tyut.himusic.fragment.MainMineFragment;
import com.tyut.himusic.fragment.MainSuggestionFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BashActivity implements View.OnClickListener
{


    @Bind(R.id.include_bottom_ing)
    Button ing;
    @Bind(R.id.include_bottom_himusic)
    Button himusic;
    @Bind(R.id.include_bottom_suggestion)
    Button suggestion;
    @Bind(R.id.include_bottom_hivideo)
    Button hivideo;
    @Bind(R.id.include_bottom_mine)
    Button mine;

    private MainSuggestionFragment mainSuggestionFragment;
    private MainMineFragment mainMineFragment;
    private MainIngFragment mainIngFragment;
    private MainHiMusicFragment mainHiMusicFragment;
    private MainHiVideoFragment mainHiVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ButterKnife.bind(this);
        initView();
        initData();

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
            ft.add(R.id.act_main_fragment, mainSuggestionFragment);
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }


    @OnClick({R.id.include_bottom_ing, R.id.include_bottom_himusic, R.id.include_bottom_suggestion, R.id.include_bottom_hivideo, R.id.include_bottom_mine})
    public void onClick(View view)
    {
        ing.setTextColor(getColor(R.color.text_color_bottom_grey));
        ing.setBackgroundColor(getColor(R.color.color_text));
        himusic.setTextColor(getColor(R.color.text_color_bottom_grey));
        himusic.setBackgroundColor(getColor(R.color.color_text));
        hivideo.setTextColor(getColor(R.color.text_color_bottom_grey));
        hivideo.setBackgroundColor(getColor(R.color.color_text));
        suggestion.setTextColor(getColor(R.color.text_color_bottom_grey));
        suggestion.setBackgroundColor(getColor(R.color.color_text));
        mine.setTextColor(getColor(R.color.text_color_bottom_grey));
        mine.setBackgroundColor(getColor(R.color.color_text));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mainSuggestionFragment != null)
        {
            ft.hide(mainSuggestionFragment);
        }
        if (mainMineFragment != null)
        {
            ft.hide(mainMineFragment);
        }
        if (mainIngFragment != null)
        {
            ft.hide(mainIngFragment);
        }
        if (mainHiMusicFragment != null)
        {
            ft.hide(mainHiMusicFragment);
        }
        if (mainHiVideoFragment != null)
        {
            ft.hide(mainHiVideoFragment);
        }
        switch (view.getId())
        {
            case R.id.include_bottom_ing:
                ing.setTextColor(getColor(R.color.color_text));
                ing.setBackground(getDrawable(R.drawable.bottommenu7dp));
                if (mainIngFragment != null && mainIngFragment.isAdded())
                {
                    ft.show(mainIngFragment);
                } else
                {
                    mainIngFragment = MainIngFragment.getInstance();
                    ft.add(R.id.act_main_fragment, mainIngFragment);
                }

                break;
            case R.id.include_bottom_himusic:
                himusic.setTextColor(getColor(R.color.color_text));
                himusic.setBackground(getDrawable(R.drawable.bottommenu7dp));
                if (mainHiMusicFragment != null && mainHiMusicFragment.isAdded())
                {
                    ft.show(mainHiMusicFragment);
                } else
                {
                    mainHiMusicFragment = MainHiMusicFragment.getInstance();
                    ft.add(R.id.act_main_fragment, mainHiMusicFragment);
                }
                break;
            case R.id.include_bottom_suggestion:
                suggestion.setTextColor(getColor(R.color.color_text));
                suggestion.setBackground(getDrawable(R.drawable.bottommenu7dp));
                if (mainSuggestionFragment != null && mainSuggestionFragment.isAdded())
                {
                    ft.show(mainSuggestionFragment);
                } else
                {
                    mainSuggestionFragment = MainSuggestionFragment.getInstance();
                    ft.add(R.id.act_main_fragment, mainSuggestionFragment);
                }
                break;
            case R.id.include_bottom_hivideo:
                hivideo.setTextColor(getColor(R.color.color_text));
                hivideo.setBackground(getDrawable(R.drawable.bottommenu7dp));
                if (mainHiVideoFragment != null && mainHiVideoFragment.isAdded())
                {
                    ft.show(mainHiVideoFragment);
                } else
                {
                    mainHiVideoFragment = MainHiVideoFragment.getInstance();
                    ft.add(R.id.act_main_fragment, mainHiVideoFragment);
                }
                break;
            case R.id.include_bottom_mine:
                mine.setTextColor(getColor(R.color.color_text));
                mine.setBackground(getDrawable(R.drawable.bottommenu7dp));
                if (mainMineFragment != null && mainMineFragment.isAdded())
                {
                    ft.show(mainMineFragment);
                } else
                {
                    mainMineFragment = MainMineFragment.getInstance();
                    ft.add(R.id.act_main_fragment, mainMineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }
}

