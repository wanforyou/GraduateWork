
package com.tyut.himusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.tyut.himusic.R;


/**
 * 欢迎页
 */
public class WelcomeActivity extends BashActivity
{
    private ImageView imgWelcome;
    private boolean InRecovery = false;
    private boolean isAnimationEnd = false;
    private Integer jump;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    @Override
    protected void initView()
    {
        imgWelcome = (ImageView) findViewById(R.id.act_welcome_img);
        Handler handlerDelay = new Handler();
        handlerDelay.postDelayed(new Runnable()
        {
            public void run()
            {
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1f);
                alphaAnimation.setDuration(2000);
                imgWelcome.setVisibility(View.VISIBLE);
                imgWelcome.startAnimation(alphaAnimation);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {
                        isAnimationEnd = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        isAnimationEnd = true;

                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    }
                });
            }
        }, 500);
    }

    @Override
    protected void initData()
    {

    }
}

