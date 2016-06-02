package com.tyut.himusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.domain.AppConstant;
import com.tyut.himusic.domain.Mp3Info;
import com.tyut.himusic.events.MusicControlEvents;
import com.tyut.himusic.events.MusicEvents;
import com.tyut.himusic.events.MusicListEvents;
import com.tyut.himusic.service.PlayerService;
import com.tyut.himusic.util.MediaUtil;
import com.tyut.himusic.view.SharePopWindow;

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
    TextView musicTitle;
    @Bind(R.id.music_artist)
    TextView musicArtist;

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
    Integer MusicWay =0;


    private PopupWindow popupWindow;

    private String title; // 歌曲标题
    private String artist; // 歌曲艺术家
    private String url; // 歌曲路径
    private int listPosition; // 播放歌曲在mp3Infos的位置
    private int currentTime; // 当前歌曲播放时间
    private String duration; // 歌曲长度
    private int flag; // 播放标识
    private boolean isFirstTime = true;
    private boolean isFirstTimeChangeWay = true;


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
                if (isFirstTimeChangeWay) {
                    MusicWay =2;
                    isFirstTimeChangeWay = false;
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
                }else {
                if (MusicWay.equals(1)){
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_loop_white_24dp));
                    MusicWay = 2;
                    shuffleMusic();

                }else if (MusicWay.equals(2)){
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_white_24dp));
                    MusicWay = 3;
                    repeat_all();
                }else {
                    musicPlayWay.setImageDrawable(getDrawable(R.drawable.ic_repeat_one_white_24dp));
                    MusicWay = 1;
                    repeat_one();
                }
                }
                break;
            case  R.id.music_download:
//                在子线程中模拟耗时操作完成下载动画，或播放loading动画
                Toast.makeText(MusicRunningActivity.this,"下载完成", Toast.LENGTH_LONG).show();
                break;
            case  R.id.music_share :
                getPopupWindow();
                break;
            case R.id.music_setting:
                startActivity(new Intent(this,MusicSettingActivity.class));
                break;
            case R.id.play_music :
                if (isFirstTime) {
                    isPause = false;
                    musicplay.setImageDrawable(getDrawable(R.drawable.ic_pause_circle_outline_white_48dp));
                    startAnimation(musicPic);

                }else {
                    Intent intent1 = new Intent(MusicRunningActivity.this, PlayerService.class);
                    if (!isPause) {
                        musicplay.setImageDrawable(getDrawable(R.drawable.ic_play_circle_outline_white_48dp));
                        isPause = true;
                        intent1.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
                        intent1.putExtra("MSG", AppConstant.PlayerMsg.PAUSE_MSG);
                        startService(intent1);
                        musicPic.clearAnimation();
                    } else {
                        musicplay.setImageDrawable(getDrawable(R.drawable.ic_pause_circle_outline_white_48dp));
                        isPause = false;
                        intent1.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
                        intent1.putExtra("MSG", AppConstant.PlayerMsg.PAUSE_MSG);
                        startService(intent1);
                        startAnimation(musicPic);
                    }
                }
                break;
            case R.id.previous_music :
//                上一首歌逻辑
                previous_music();
                break;
            case R.id.next_music:
//                下一首歌逻辑
                next_music();
                break;

        }
    }
    public void onDestroy(){
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }


    private class SeekBarChangeListener implements OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            audioTrackChange(progress); // 用户控制进度的改变
        }



        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    }

    private void getPopupWindow()
    {
        if (null != popupWindow && popupWindow.isShowing())
        {
            popupWindow.dismiss();
            return;
        } else
        {
            initPopuptWindow();
        }
    }
    private void initPopuptWindow()
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        popupWindow = new SharePopWindow(this);
        popupWindow.showAtLocation(this.findViewById(R.id.current_progress), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });

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
    public void onEventMainThread(MusicListEvents event) {
        title = (event.getTitle());
        musicTitle.setText(title);
        artist = (event.getArtist());
        musicArtist.setText(artist);
        url = event.getUrl();
        listPosition =event.getListPosition();
        flag =event.getMsg();
        duration= MediaUtil.formatTime(event.getDuration());
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("PlayerActivity has paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("PlayerActivity has onResume");
    }
//上一首逻辑
    public void previous_music() {
    listPosition = listPosition - 1;
    if (listPosition >= 0) {
        Mp3Info mp3Info = mp3Infos.get(listPosition); // 上一首MP3
        musicTitle.setText(mp3Info.getTitle());
        musicArtist.setText(mp3Info.getArtist());
        url = mp3Info.getUrl();
        Intent intent = new Intent();
        intent.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
        intent.putExtra("url", mp3Info.getUrl());
        intent.putExtra("listPosition", listPosition);
        intent.putExtra("MSG", AppConstant.PlayerMsg.PRIVIOUS_MSG);
        startService(intent);

    } else {
        listPosition = 0;
        Toast.makeText(MusicRunningActivity.this, "没有上一首了", Toast.LENGTH_SHORT)
                .show();
    }
}
//下一首逻辑
    public void next_music() {
        listPosition = listPosition + 1;
        if (listPosition <= mp3Infos.size() - 1) {
            Mp3Info mp3Info = mp3Infos.get(listPosition);
            url = mp3Info.getUrl();
            musicTitle.setText(mp3Info.getTitle());
            musicArtist.setText(mp3Info.getArtist());
            Intent intent = new Intent();
            intent.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
            intent.putExtra("url", mp3Info.getUrl());
            intent.putExtra("listPosition", listPosition);
            intent.putExtra("MSG", AppConstant.PlayerMsg.NEXT_MSG);
            startService(intent);

        } else {
            listPosition = mp3Infos.size() - 1;
            Toast.makeText(MusicRunningActivity.this, "没有下一首了", Toast.LENGTH_SHORT)
                    .show();
        }
    }

//    随机播放
    public void shuffleMusic() {
        EventBus.getDefault().post(
                new MusicControlEvents(CTL_ACTION, 1));
    }


//      播放进度改变

    public void audioTrackChange(int progress) {
        Intent intent = new Intent();
        intent.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
        intent.putExtra("url", url);
        intent.putExtra("listPosition", listPosition);
        intent.putExtra("MSG", AppConstant.PlayerMsg.PROGRESS_CHANGE);
        intent.putExtra("progress", progress);
        startService(intent);
    }

//    单曲循环
    public void repeat_one() {
        EventBus.getDefault().post(
                new MusicControlEvents(CTL_ACTION, 3));
    }

//    全部循环

    public void repeat_all() {
        EventBus.getDefault().post(
                new MusicControlEvents(CTL_ACTION, 2));
    }

private void startAnimation(View view){
    Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
    LinearInterpolator lin = new LinearInterpolator();
    operatingAnim.setInterpolator(lin);


}

    }