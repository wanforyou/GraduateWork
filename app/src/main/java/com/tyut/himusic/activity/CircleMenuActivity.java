
package com.tyut.himusic.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleMenuActivity extends AppCompatActivity
{
    @Bind(R.id.imageView3)
    ImageView imageView;

    @Bind(R.id.imageView4)
    ImageView imageView1;
    @Bind(R.id.imageView5)
    ImageView imageView2;
    @Bind(R.id.imageView6)
    ImageView imageView3;
    private boolean buttonIsChecked = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);
        ButterKnife.bind(this);
    }

    @OnClick(value = {R.id.imageView3, R.id.imageView4, R.id.imageView6, R.id.imageView5})
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.imageView3:
                if (!buttonIsChecked)
                {
                    startAnim();
                    buttonIsChecked = true;
                } else
                {
                    //返回动画
                    imageView.clearAnimation();
                    imageView1.clearAnimation();
                    imageView2.clearAnimation();
                    imageView3.clearAnimation();

                    buttonIsChecked = false;
                }
                break;
        }
    }

    /**
     * 开启一个动画
     */
    private void startAnim()
    {

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView1, "translationX", -200);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView2, "translationX", -200);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView3, "translationX", -200);
        AnimatorSet set1 = new AnimatorSet();
        set1.setDuration(200);
        set1.playTogether(animator1, animator2, animator3);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageView1, "rotation", 0F, 90F);
        ObjectAnimator animator5= ObjectAnimator.ofFloat(imageView2, "rotation", 0F, 90F);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(animator4, animator5);
        AnimatorSet set3 = new AnimatorSet();
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(imageView1, "rotation", 0F, 30F);
        set3.play(animator6);
        set1.start();
        imageView1.setPivotX(168);
        imageView2.setPivotX(168);
        imageView1.setPivotY(168);
        imageView2.setPivotY(168);
        set2.start();
        set3.start();

//        startAnim1();
    }

    private void startAnim1()
    {
        Animation mAnim1 = AnimationUtils.loadAnimation(this, R.anim.tip1);
        Animation mAnim2 = AnimationUtils.loadAnimation(this, R.anim.tip2);
        imageView2.startAnimation(mAnim1);
        imageView3.startAnimation(mAnim2);


    }
}
