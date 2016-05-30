package com.tyut.himusic.activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.tyut.himusic.R;
import com.tyut.himusic.util.SDCardUtil;

import java.io.File;


public class VideoPlayerActivity extends Activity
{
    VideoView videoView;
    MediaController mController;
    DanmakuView mDanmakuView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_video_player);
        // 获取界面上VideoView组件
        videoView = (VideoView) findViewById(R.id.act_video_player_videoview);

        // 创建MediaController对象
        mController = new MediaController(this);
        File video;
        video = new File(SDCardUtil.getVideoPath()+"/ceshi.mp4");
        if(video.exists())
        {
            videoView.setVideoPath(video.getAbsolutePath());  //①
            // 设置videoView与mController建立关联
            videoView.setMediaController(mController);  //②
            // 设置mController与videoView建立关联
            mController.setMediaPlayer(videoView);  //③
            // 让VideoView获取焦点
            videoView.requestFocus();
        }
        mDanmakuView = (DanmakuView) findViewById(R.id.danmakuView);

        // add danmaku items:
        mDanmakuView.addItem(new DanmakuItem(this, "2333333333333333333333333333", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "我从未见过如此厚颜无耻之人", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "前排", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "250个方丈你们好!", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "看见王司徒就滚进来了", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "前方高能", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "来不及了快上车", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "打卡", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "恭喜你发现镇站之宝", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "Hello World", mDanmakuView.getWidth()));

        //显示弹幕
        mDanmakuView.show();

    //方丈,你自己加按钮来控制弹幕显示和隐藏,添加弹幕发送的时候直接调用上面的additem即可。

        //隐藏弹幕
//        mDanmakuView.hide();

        //清空弹幕
//        mDanmakuView.clear();
    }
}
