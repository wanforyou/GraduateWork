
package com.tyut.himusic.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleMenuActivity extends AppCompatActivity {
    @Bind(R.id.imageView3)
    ImageView  imageView;

    @Bind(R.id.imageView4)
    ImageView  imageView1;
    @Bind(R.id.imageView5)
    ImageView  imageView2;
    @Bind(R.id.imageView6)
    ImageView  imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);
        ButterKnife.bind(this);
    }
    @OnClick(value = {R.id.imageView3, R.id.imageView4, R.id.imageView6, R.id.imageView5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView3:
                startAnim();
                break;
        }
    }

    /**
     * 开启一个动画
     */
    private void startAnim() {
        ObjectAnimator animator0=ObjectAnimator.ofFloat(imageView,"alpha",1F,0.5F);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(imageView1,"translationY",200F);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(imageView3,"translationX",200F);
        ObjectAnimator animator3=ObjectAnimator.ofFloat(imageView2,"translationX",200F);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(300);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,animator1,animator2,animator3);
        set.start();
        startAnim1();
    }
    private void startAnim1() {
        Animation mAnim1 = AnimationUtils.loadAnimation(this, R.anim.tip1);
        Animation mAnim2 = AnimationUtils.loadAnimation(this, R.anim.tip2);
        LinearInterpolator lin = new LinearInterpolator();
        mAnim1.setInterpolator(lin);
        imageView2.startAnimation(mAnim1);
        imageView3.startAnimation(mAnim2);


    }
}
