package com.tyut.himusic.activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tyut.himusic.R;
import com.tyut.himusic.util.SDCardUtil;

import java.io.File;

public class VideoPlayerActivity extends Activity
{
    VideoView videoView;
    MediaController mController;
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
    }
}
