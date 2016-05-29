package com.tyut.himusic.view;

/**
 * Created by Administrator on 2016/5/7.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tyut.himusic.R;

public class TopBar extends RelativeLayout
{

    // 包含topbar上的元素：左按钮、右按钮、标题
    private Button mSearchButton, mRunningButton;
    private TextView mHimusicView;
    private TextView mHivideoView;

    // 布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mSearchParams, mHimusicParams, mHivideoParams, mRunningParams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性

    private Drawable mSerachBackground;

    // 右按钮的属性值，即我们在atts.xml文件中定义的属性

    private Drawable mRunningBackground;

    // 标题HiMusic的属性值，即我们在atts.xml文件中定义的属性
    private float mHimusicTextSize;
    private int mHimusicTextColor;
    private String mHimusic;
    // 标题HiVideo的属性值，即我们在atts.xml文件中定义的属性
    private float mHiVideoTextSize;
    private int mHiVideoTextColor;
    private String mHivideo;

    // 映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public TopBar(Context context)
    {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // 设置topbar的背景
        setBackgroundColor(0x0000FFF);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值

        mSerachBackground = ta.getDrawable(
                R.styleable.TopBar_searchBackground);

        mRunningBackground = ta.getDrawable(
                R.styleable.TopBar_runningBackground);
        mHimusicTextSize = ta.getDimension(
                R.styleable.TopBar_HiMusicTextSize, 10);
        mHimusicTextColor = ta.getColor(
                R.styleable.TopBar_HiMusicTextColor, 0);
        mHimusic = ta.getString(R.styleable.TopBar_HiMusic);

        mHiVideoTextSize = ta.getDimension(
                R.styleable.TopBar_HiVideoTextSize, 10);
        mHiVideoTextColor = ta.getColor(
                R.styleable.TopBar_HiVideoTextColor, 0);
        mHivideo = ta.getString(R.styleable.TopBar_HiVideo);


        // 获取完TypedArray的值后，一般要调用
        // recyle方法来避免重新创建的时候的错误
        ta.recycle();

        mSearchButton = new Button(context);
        mRunningButton = new Button(context);
        mHimusicView = new TextView(context);
        mHivideoView = new TextView(context);


        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值

        mSearchButton.setBackground(mSerachBackground);


        mRunningButton.setBackground(mRunningBackground);


        mHimusicView.setText(mHimusic);
        mHimusicView.setTextColor(mHimusicTextColor);
        mHimusicView.setTextSize(mHimusicTextSize);
        mHimusicView.setGravity(Gravity.CENTER);

        mHivideoView.setText(mHivideo);
        mHivideoView.setTextColor(mHiVideoTextColor);
        mHivideoView.setTextSize(mHiVideoTextSize);
        mHivideoView.setGravity(Gravity.CENTER);

        // 为组件元素设置相应的布局元素
        mSearchParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mSearchParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(mSearchButton, mSearchParams);

        mRunningParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mRunningParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRunningButton, mRunningParams);

        mHimusicParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mHimusicParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mHimusicView, mHimusicParams);

        mHivideoParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mHivideoParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mHivideoView, mHivideoParams);


        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        mSearchButton.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mListener.searchClick();
            }
        });

        mRunningButton.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mListener.runningClick();
            }
        });
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener)
    {
        this.mListener = mListener;
    }

    /**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id, boolean flag)
    {
        if (flag)
        {
            if (id == 0)
            {
                mSearchButton.setVisibility(View.VISIBLE);
            } else
            {
                mSearchButton.setVisibility(View.VISIBLE);
            }
        } else
        {
            if (id == 0)
            {
                mRunningButton.setVisibility(View.GONE);
            } else
            {
                mRunningButton.setVisibility(View.GONE);
            }
        }
    }


    // 接口对象，实现回调机制，在回调方法中
    // 通过映射的接口对象调用接口中的方法
    // 而不用去考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener
    {
        // 左按钮点击事件
        void searchClick();

        // 右按钮点击事件
        void runningClick();
    }
}
