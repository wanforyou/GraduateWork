package com.tyut.himusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicRunningActivity extends AppCompatActivity {
    @Bind(R.id.muisc_running_back)
    ImageView MusicBack;
    @Bind(R.id.muisc_list)
    ImageView MusicList;
    @Bind(R.id.music_pan_pic)
    SimpleDraweeView MusicPic;
    @Bind(R.id.music_running_zan)
    ImageView MusicZan;
    @Bind(R.id.music_running_repeatState)
    ImageView MusicPlayWay;
    @Bind(R.id.music_download)
    ImageView MusicDownLoad;
    @Bind(R.id.music_share)
    ImageView MusicShare;
    @Bind(R.id.music_setting)
    ImageView MusicSetting;
    @Bind(R.id.play_music)
    ImageView Musicplay;
    @Bind(R.id.previous_music)
    ImageView PreviousMusic;
    @Bind(R.id.next_music)
    ImageView NextMusic;

    Integer ZanNumber;
    Integer MusicWay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_running);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.muisc_running_back,R.id.muisc_list,R.id.music_pan_pic,
            R.id.music_running_zan,R.id.music_running_repeatState,R.id.music_download,R.id.music_share,R.id.music_setting,
            R.id.play_music,R.id.previous_music,R.id.next_music  })
    public void onClick(View view){
        MusicZan.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_white_24dp));
        MusicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
        Musicplay.setImageDrawable(getDrawable(R.drawable.ic_pause_circle_outline_white_48dp));
        MusicWay = 0;
        switch (view.getId()) {
            case R.id.muisc_running_back:
                MusicRunningActivity.this.finish();
                break;
            case R.id.muisc_list:
                startActivity(new Intent(this,MusicListActivity.class));
                break;
            case   R.id.music_pan_pic:
//                转为歌词界面
            break;
            case  R.id.music_running_zan :
                MusicZan.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_black_24dp));
                ZanNumber = ZanNumber + 1;
            break;
            case  R.id.music_running_repeatState :
//                零表示随机播放，1表示列表播放，2表示单曲循环
                if (MusicWay.equals(0)){
                    MusicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
                    MusicWay = 1;

                }else if (MusicWay.equals(1)){
                    MusicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_white_24dp));
                    MusicWay = 2;

                }else {
                    MusicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_one_white_24dp));
                    MusicWay = 2;

                }
            break;
            case  R.id.music_download:
//                在子线程中模拟耗时操作完成下载动画，或播放loading动画
                Toast.makeText(MusicRunningActivity.this,"下载完成", Toast.LENGTH_LONG).show();
            break;
            case  R.id.music_share :
//                分享的业务逻辑
            break;
            case R.id.music_setting:
                startActivity(new Intent(this,MusicSettingActivity.class));
            break;
            case R.id.play_music :
                Musicplay.setImageDrawable(getDrawable(R.drawable.ic_play_circle_outline_white_48dp));
            break;
            case R.id.previous_music :
//                上一首歌逻辑
            break;
            case R.id.next_music:
//                下一首歌逻辑
            break;

        }

    }

}
