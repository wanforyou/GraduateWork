package com.tyut.himusic.view;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tyut.himusic.R;

/**
 * Created by Administrator on 2016/5/7.
 */

public class TopBarTest extends Activity {

    private TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);
        // 获得我们创建的topbar
        mTopbar = (TopBar) findViewById(R.id.topBar);
        // 为topbar注册监听事件，传入定义的接口
        // 并以匿名类的方式实现接口内的方法
        mTopbar.setOnTopbarClickListener(
                new TopBar.topbarClickListener() {

                    @Override
                    public void searchClick() {
                        Toast.makeText(TopBarTest.this,
                                "right", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void runningClick() {
                        Toast.makeText(TopBarTest.this,
                                "left", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}

