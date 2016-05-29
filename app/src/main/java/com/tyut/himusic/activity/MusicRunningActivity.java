package com.tyut.himusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.domain.Mp3Info;
import com.tyut.himusic.events.MusicEvents;
import com.tyut.himusic.util.MediaUtil;

import java.util.List;

import de.greenrobot.event.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicRunningActivity extends AppCompatActivity {
    @Bind(R.id.muisc_running_back)
    ImageView musicBack;
    @Bind(R.id.muisc_list)
    ImageView musicList;
    @Bind(R.id.music_pan_pic)
    SimpleDraweeView musicPic;
    @Bind(R.id.music_running_zan)
    ImageView musicZan;
    @Bind(R.id.music_running_repeatState)
    ImageView musicPlayWay;
    @Bind(R.id.music_download)
    ImageView musicDownLoad;
    @Bind(R.id.music_share)
    ImageView musicShare;
    @Bind(R.id.music_setting)
    ImageView musicSetting;
    @Bind(R.id.play_music)
    ImageView musicplay;



    @Bind(R.id.music_title)
    TextView musicArtist;
    @Bind(R.id.music_artist)
    TextView musicTitle;

    @Bind(R.id.previous_music)
    ImageView previousMusic;
    @Bind(R.id.next_music)
    ImageView nextMusic;
    @Bind(R.id.current_progress)
    TextView currentProgress;
    @Bind(R.id.audioTrack)
    SeekBar musicProgressBar;

    @Bind(R.id.final_progress)
    TextView finalProgress;

    Integer ZanNumber;
    Integer MusicWay;

    private String title; // 歌曲标题
    private String artist; // 歌曲艺术家
    private String url; // 歌曲路径
    private int listPosition; // 播放歌曲在mp3Infos的位置
    private int currentTime; // 当前歌曲播放时间
    private int duration; // 歌曲长度

    private List<Mp3Info> mp3Infos;
    private boolean isPause;

    //服务要发送的一些Action
    public static final String UPDATE_ACTION = "UPDATE_ACTION";    //更新动作
    public static final String CTL_ACTION = "CTL_ACTION";        //控制动作
    public static final String MUSIC_CURRENT = "MUSIC_CURRENT";    //当前音乐播放时间更新动作
    public static final String MUSIC_DURATION = ".MUSIC_DURATION";//新音乐长度更新动作



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_running);
        ButterKnife.bind(this);
//        注册eventsbus
        EventBus.getDefault().register(this);

    }
    @OnClick({R.id.muisc_running_back,R.id.muisc_list,R.id.music_pan_pic,
            R.id.music_running_zan,R.id.music_running_repeatState,R.id.music_download,R.id.music_share,R.id.music_setting,
            R.id.play_music,R.id.previous_music,R.id.next_music  })
    public void onClick(View view){
        musicZan.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_white_24dp));
        musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
        musicplay.setImageDrawable(getDrawable(R.drawable.ic_pause_circle_outline_white_48dp));
        isPause = false;
        MusicWay = 0;
        switch (view.getId()) {
            case R.id.muisc_running_back:
                MusicRunningActivity.this.finish();
                break;
            case R.id.muisc_list:
                Intent intent = new Intent(getApplicationContext(),MusicListActivity.class);
                startActivity(intent);
                break;
            case   R.id.music_pan_pic:
//                转为歌词界面
            break;
            case  R.id.music_running_zan :

                musicZan.setImageDrawable(getDrawable(R.drawable.ic_thumb_up_black_24dp));
//                发送赞的广播
                ZanNumber = ZanNumber + 1;
            break;
            case  R.id.music_running_repeatState :
//                1表示随机播放，2表示列表播放，3表示单曲循环
                if (MusicWay.equals(1)){
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
                    MusicWay = 2;

                }else if (MusicWay.equals(2)){
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_white_24dp));
                    MusicWay = 3;

                }else {
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_one_white_24dp));
                    MusicWay = 1;

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
                musicplay.setImageDrawable(getDrawable(R.drawable.ic_play_circle_outline_white_48dp));
            break;
            case R.id.previous_music :
//                上一首歌逻辑
            break;
            case R.id.next_music:
//                下一首歌逻辑
            break;

        }
    }
    public void onDestroy(){
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(MusicEvents event) {
        String action = event.getAction();
        if (action.equals(MUSIC_CURRENT)) {
            currentTime = event.getMsg();
            currentProgress.setText(MediaUtil.formatTime(currentTime));
            musicProgressBar.setProgress(currentTime);
        } else if (action.equals(MUSIC_DURATION)) {
            int duration = event.getMsg();
            musicProgressBar.setMax(duration);
            finalProgress.setText(MediaUtil.formatTime(duration));
        } else if (action.equals(UPDATE_ACTION)) {

            listPosition = event.getMsg();
            url = mp3Infos.get(listPosition).getUrl();
            if (listPosition >= 0) {
                musicTitle.setText(mp3Infos.get(listPosition).getTitle());
                musicArtist.setText(mp3Infos.get(listPosition).getArtist());
            }
            if (listPosition == 0) {
                finalProgress.setText(MediaUtil.formatTime(mp3Infos.get(
                        listPosition).getDuration()));
                musicplay.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
                isPause = true;
            }
        }
    }

    }

