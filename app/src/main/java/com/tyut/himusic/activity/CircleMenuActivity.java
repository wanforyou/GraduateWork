
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
    private static final int[] STATE_CHECKED = new int[]{
            android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};
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
        }
    }

    /**
     * 开启一个动画
     */
    private void startAnim()
    {

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView1, "translationX", -220);
        ObjectAnimator animator16 = ObjectAnimator.ofFloat(imageView1, "scaleY",0.01f,1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView2, "translationX", -220);
        ObjectAnimator animator17 = ObjectAnimator.ofFloat(imageView2, "scaleY",0.01f,1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView3, "translationX", -220);
        ObjectAnimator animator18 = ObjectAnimator.ofFloat(imageView3, "scaleY",0.01f,1.0f);
        AnimatorSet set1 = new AnimatorSet();
        set1.setDuration(300);
        set1.playTogether(animator1, animator2, animator3,animator16,animator17,animator18);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageView1, "rotation", 0F, 50F);
        ObjectAnimator animator5= ObjectAnimator.ofFloat(imageView2, "rotation", 0F, 50F);
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
        imageView1.setPivotX(250);
        imageView2.setPivotX(250);
        imageView1.setPivotY(70);
        imageView2.setPivotY(70);
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
        ObjectAnimator animator9= ObjectAnimator.ofFloat(imageView2, "rotation", 50F, -0F);
        AnimatorSet set5 = new AnimatorSet();
        set5.setStartDelay(180);
        set5.setDuration(200);
        set5.play(animator9);
        ObjectAnimator animator10 = ObjectAnimator.ofFloat(imageView1, "translationX", 0);
        ObjectAnimator animator13 = ObjectAnimator.ofFloat(imageView1, "scaleY",1.0f,0.01f);
        ObjectAnimator animator11 = ObjectAnimator.ofFloat(imageView2, "translationX", 0);
        ObjectAnimator animator14 = ObjectAnimator.ofFloat(imageView2, "scaleY",1.0f,0.01f);
        ObjectAnimator animator12 = ObjectAnimator.ofFloat(imageView3, "translationX", 0);
        ObjectAnimator animator15 = ObjectAnimator.ofFloat(imageView3, "scaleY",1.0f,0.01f);

        AnimatorSet set6 = new AnimatorSet();
        set6.setStartDelay(400);
        set6.setDuration(300);
        set6.playTogether(animator10, animator11, animator12, animator13, animator14, animator15);
        imageView1.setPivotX(250);
        imageView2.setPivotX(250);
        imageView1.setPivotY(70);
        imageView2.setPivotY(70);
        set4.start();
        set5.start();
        set6.start();

    }
}
