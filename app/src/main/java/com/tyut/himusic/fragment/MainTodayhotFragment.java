package com.tyut.himusic.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tyut.himusic.R;
import com.tyut.himusic.activity.MyApplication;
import com.tyut.himusic.adapter.HotAdapter;
import com.tyut.himusic.util.ImageUrlTestUtils;
import com.tyut.himusic.view.SpacesItemDecoration;
import com.tyut.himusic.view.VideoPopWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/5/10.
 */
public class MainTodayhotFragment extends BaseFragment
{


    @Bind(R.id.imageView3)
    ImageView imageView;

    @Bind(R.id.imageView4)
    ImageView imageView1;
    @Bind(R.id.imageView5)
    ImageView imageView2;
    @Bind(R.id.imageView6)
    ImageView imageView3;
    private static final int[] STATE_CHECKED = new int[]{
            android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};
    private boolean buttonIsChecked = false;

    @Bind(R.id.frag_main_todayhot_recyclerview)
    RecyclerView recyclerView;
    private HotAdapter hotAdapter;
    private String[] imgUrls;
    private String[] imgTitles;
    private PopupWindow popupWindow;
    private boolean isSecond = false;

    public static MainTodayhotFragment getInstance()
    {
        return new MainTodayhotFragment();
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
        final View view = inflater.inflate(R.layout.fragment_suggestion_todayhot, container, false);
        ButterKnife.bind(this, view);
        initView();
        imageView3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getPopupWindow(view);
            }
        });
        return view;

    }

    void initView()
    {

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        hotAdapter = new HotAdapter(imgUrls, imgTitles, getContext());
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

    @OnClick(value = {R.id.imageView3, R.id.imageView4, R.id.imageView5})
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.imageView3:
                if (!buttonIsChecked)
                {
                    startAnim();
                    imageView.setImageState(STATE_CHECKED, true);
                    buttonIsChecked = true;
                } else
                {
                    //返回动画
                    cleanAnim();
                    imageView.setImageState(STATE_UNCHECKED, true);

                    buttonIsChecked = false;
                }
                break;
            case R.id.imageView4:
                recyclerView.smoothScrollToPosition(0);
                break;
            case R.id.imageView5:
                if (isSecond)
                {
                    Toast.makeText(MyApplication.getInstance(), "已经是最新的,请续一秒后重试", Toast.LENGTH_SHORT);
                } else
                {
                    hotAdapter.setDatas(放你刷新之后的内容,title和图片);
                    hotAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void getPopupWindow(View view)
    {
        if (null != popupWindow && popupWindow.isShowing())
        {
            popupWindow.dismiss();
            return;
        } else
        {
            initPopuptWindow(view);
        }
    }

    private void initPopuptWindow(View view)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        popupWindow = new VideoPopWindow(getActivity());
        popupWindow.showAtLocation(view.findViewById(R.id.imageView4), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });

    }

    /**
     * 开启一个动画
     */
    private void startAnim()
    {

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView1, "translationX", -220);
        ObjectAnimator animator16 = ObjectAnimator.ofFloat(imageView1, "scaleY", 0.01f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView2, "translationX", -220);
        ObjectAnimator animator17 = ObjectAnimator.ofFloat(imageView2, "scaleY", 0.01f, 1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView3, "translationX", -220);
        ObjectAnimator animator18 = ObjectAnimator.ofFloat(imageView3, "scaleY", 0.01f, 1.0f);
        AnimatorSet set1 = new AnimatorSet();
        set1.setDuration(300);
        set1.playTogether(animator1, animator2, animator3, animator16, animator17, animator18);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageView1, "rotation", 0F, 50F);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(imageView2, "rotation", 0F, 50F);
        AnimatorSet set2 = new AnimatorSet();
        set2.setStartDelay(300);
        set2.setDuration(300);
        set2.playTogether(animator4, animator5);
        AnimatorSet set3 = new AnimatorSet();
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(imageView1, "rotation", 0F, 103F);
        set3.setStartDelay(300);
        set3.setDuration(300);
        set3.play(animator6);
        set1.start();
        imageView1.setPivotX(260);
        imageView2.setPivotX(260);
        imageView1.setPivotY(60);
        imageView2.setPivotY(60);
        set2.start();
        set3.start();

//        startAnim1();
    }

    private void cleanAnim()
    {

        AnimatorSet set4 = new AnimatorSet();
        ObjectAnimator animator7 = ObjectAnimator.ofFloat(imageView1, "rotation", 103F, 0F);
        set4.setDuration(400);
        set4.play(animator7);
        ObjectAnimator animator9 = ObjectAnimator.ofFloat(imageView2, "rotation", 50F, -0F);
        AnimatorSet set5 = new AnimatorSet();
        set5.setStartDelay(180);
        set5.setDuration(200);
        set5.play(animator9);
        ObjectAnimator animator10 = ObjectAnimator.ofFloat(imageView1, "translationX", 0);
        ObjectAnimator animator13 = ObjectAnimator.ofFloat(imageView1, "scaleY", 1.0f, 0.01f);
        ObjectAnimator animator11 = ObjectAnimator.ofFloat(imageView2, "translationX", 0);
        ObjectAnimator animator14 = ObjectAnimator.ofFloat(imageView2, "scaleY", 1.0f, 0.01f);
        ObjectAnimator animator12 = ObjectAnimator.ofFloat(imageView3, "translationX", 0);
        ObjectAnimator animator15 = ObjectAnimator.ofFloat(imageView3, "scaleY", 1.0f, 0.01f);

        AnimatorSet set6 = new AnimatorSet();
        set6.setStartDelay(400);
        set6.setDuration(300);
        set6.playTogether(animator10, animator11, animator12, animator13, animator14, animator15);
        imageView1.setPivotX(260);
        imageView2.setPivotX(260);
        imageView1.setPivotY(60);
        imageView2.setPivotY(60);
        set4.start();
        set5.start();
        set6.start();

    }
}

