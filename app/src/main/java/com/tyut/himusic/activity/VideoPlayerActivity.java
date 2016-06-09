package com.tyut.himusic.activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.tyut.himusic.R;
import com.tyut.himusic.util.SDCardUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VideoPlayerActivity extends Activity {
    VideoView videoView;
    MediaController mController;
    DanmakuView mDanmakuView;
    private boolean danMuopen = true;
    private String tuCao;
    Button danMu;
    ImageView back;
    Button downLoadVideo;
    Button danMuFaSong;
    EditText danMuSms;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_video_player);
        // 获取界面上VideoView组件
        videoView = (VideoView) findViewById(R.id.act_video_player_videoview);
        danMu = (Button) findViewById(R.id.danmu);
        back = (ImageView)findViewById(R.id.title_bar_back);
        downLoadVideo = (Button) findViewById(R.id.download);
        danMuFaSong = (Button) findViewById(R.id.danmu_fasong);
        danMuSms = (EditText) findViewById(R.id.editText);
        danMu.setOnClickListener(onclick);
        back.setOnClickListener(onclick);
        downLoadVideo.setOnClickListener(onclick);
        danMuFaSong.setOnClickListener(onclick);

        // 创建MediaController对象
        mController = new MediaController(this);
        File video;
        video = new File(SDCardUtil.getVideoPath() + "/ceshi.mp4");
        if (video.exists()) {
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
        mDanmakuView.addItem(new DanmakuItem(this, "250个儿子你们好!", mDanmakuView.getWidth()));
        mDanmakuView.addItem(new DanmakuItem(this, "看见嫖老师就滚进来了", mDanmakuView.getWidth()));
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

    View.OnClickListener onclick = new View.OnClickListener() {
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.danmu:
                    if (danMuopen) {
                        danMuopen = false;
                        mDanmakuView.hide();
                    } else {
                        danMuopen = true;
                        mDanmakuView.show();
                    }
                    break;
                case R.id.download:
                    Toast.makeText(VideoPlayerActivity.this, "下载完成", Toast.LENGTH_LONG).show();
                    break;
                case R.id.danmu_fasong:
                    tuCao = danMuSms.getText().toString();
                    mDanmakuView.addItem(new DanmakuItem(getApplicationContext(), tuCao, mDanmakuView.getWidth()));
                    mDanmakuView.show();
                    break;
                case R.id.title_bar_back:

                    VideoPlayerActivity.this.finish();

            }
        }
    };
}
