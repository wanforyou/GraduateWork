package com.tyut.himusic.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;

import com.tyut.himusic.domain.AppConstant;
import com.tyut.himusic.domain.Mp3Info;
import com.tyut.himusic.events.MusicEvents;
import com.tyut.himusic.util.MediaUtil;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/5/29.
 */

@SuppressLint("NewApi")
public class PlayerService extends Service
{
    //服务要发送的一些Action
    public static final String UPDATE_ACTION = "UPDATE_ACTION";    //更新动作
    public static final String CTL_ACTION = "CTL_ACTION";        //控制动作
    public static final String MUSIC_CURRENT = "MUSIC_CURRENT";    //当前音乐播放时间更新动作
    public static final String MUSIC_DURATION = ".MUSIC_DURATION";//新音乐长度更新动作
    private MediaPlayer mediaPlayer; // 媒体播放器对象
    private String path;            // 音乐文件路径
    private int msg;                //播放信息
    private boolean isPause;        // 暂停或者暂停
    private int current = 0;        // 记录当前正在播放的音乐
    private List<Mp3Info> mp3Infos;    //存放Mp3Info对象的集合
    private int status = 1;            //播放状态，默认为随机播放
    private MyReceiver myReceiver;    //自定义广播接收器
    private int currentTime;        //当前播放进度
    private int duration;            //歌曲长度
    /**
     * handler用来接收消息，来发送广播更新播放时间
     */

    private EventBus eventBus = new EventBus()
    {
        public void eventsMessage(String action, Integer msg)
        {
            if (mediaPlayer != null)
            {
                // 获取当前音乐播放的位置
                currentTime = mediaPlayer.getCurrentPosition();
                EventBus.getDefault().post(
                        new MusicEvents(MUSIC_CURRENT, currentTime));
//                每秒发送一次未更新
            }
        }
    };


    @Override
    public void onCreate()
    {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mp3Infos = MediaUtil.getMp3Infos(PlayerService.this);


//         设置音乐播放完成时的监听器

        mediaPlayer.setOnCompletionListener(new OnCompletionListener()
        {

            @Override
            public void onCompletion(MediaPlayer mp)
            {
                if (status == 3)
                { // 单曲循环
                    mediaPlayer.start();
                } else if (status == 2)
                { // 顺序播放
                    current++;    //下一首位置
                    if (current <= mp3Infos.size() - 1)
                    {
                        // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                        EventBus.getDefault().post(
                                new MusicEvents(UPDATE_ACTION, current));
                        path = mp3Infos.get(current).getUrl();
                        play(0);
                    } else
                    {
                        mediaPlayer.seekTo(0);
                        current = 0;
                        EventBus.getDefault().post(
                                new MusicEvents(UPDATE_ACTION, current));
                    }
                } else if (status == 1)
                {    //随机播放
                    current = getRandomIndex(mp3Infos.size() - 1);
                    System.out.println("currentIndex ->" + current);
                    // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                    EventBus.getDefault().post(
                            new MusicEvents(UPDATE_ACTION, current));
                    path = mp3Infos.get(current).getUrl();
                    play(0);
                }
            }
        });

//        myReceiver = new MyReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(CTL_ACTION);
//        registerReceiver(myReceiver, filter);
    }

    /**
     * 获取随机位置
     *
     * @param end
     * @return
     */
    protected int getRandomIndex(int end)
    {
        int index = (int) (Math.random() * end);
        return index;
    }

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        path = intent.getStringExtra("url");        //歌曲路径
        current = intent.getIntExtra("listPosition", -1);    //当前播放歌曲的在mp3Infos的位置
        msg = intent.getIntExtra("MSG", 0);            //播放信息
        if (msg == AppConstant.PlayerMsg.PLAY_MSG)
        {    //直接播放音乐
            play(0);
        } else if (msg == AppConstant.PlayerMsg.PAUSE_MSG)
        {    //暂停
            pause();
        } else if (msg == AppConstant.PlayerMsg.STOP_MSG)
        {        //停止
            stop();
        } else if (msg == AppConstant.PlayerMsg.CONTINUE_MSG)
        {    //继续播放
            resume();
        } else if (msg == AppConstant.PlayerMsg.PRIVIOUS_MSG)
        {    //上一首
            previous();
        } else if (msg == AppConstant.PlayerMsg.NEXT_MSG)
        {        //下一首
            next();
        } else if (msg == AppConstant.PlayerMsg.PROGRESS_CHANGE)
        {    //进度更新
            currentTime = intent.getIntExtra("progress", -1);
            play(currentTime);
        } else if (msg == AppConstant.PlayerMsg.PLAYING_MSG)
        {
            EventBus.getDefault().post(
                    new MusicEvents(CTL_ACTION, msg));
        }
        super.onStart(intent, startId);
    }

//    Runnable mRunnable = new Runnable() {
//
//        @Override
//        public void run() {
//            EventBus.getDefault().post(
//                    new MusicEvents(CTL_ACTION,msg));
//
//        }
//    };

    private void play(int currentTime)
    {
        try
        {

            mediaPlayer.reset();// 把各项参数恢复到初始状态
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare(); // 进行缓冲
            mediaPlayer.setOnPreparedListener(new PreparedListener(currentTime));// 注册一个监听器
            EventBus.getDefault().post(
                    new MusicEvents(CTL_ACTION, msg));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 暂停音乐
     */
    private void pause()
    {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    private void resume()
    {
        if (isPause)
        {
            mediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 上一首
     */
    private void previous()
    {
        EventBus.getDefault().post(
                new MusicEvents(UPDATE_ACTION, current));
        play(0);
    }

    /**
     * 下一首
     */
    private void next()
    {
        EventBus.getDefault().post(
                new MusicEvents(UPDATE_ACTION, current));
        play(0);
    }

    /**
     * 停止音乐
     */
    private void stop()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            try
            {
                mediaPlayer.prepare();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        EventBus.getDefault().post(
                new MusicEvents(CTL_ACTION, msg));
    }

    private final class PreparedListener implements OnPreparedListener
    {
        private int currentTime;

        public PreparedListener(int currentTime)
        {
            this.currentTime = currentTime;
        }

        @Override
        public void onPrepared(MediaPlayer mp)
        {
            mediaPlayer.start(); // 开始播放
            if (currentTime > 0)
            { // 如果音乐不是从头播放
                mediaPlayer.seekTo(currentTime);
            }
            //通过Intent来传递歌曲的总长度
            EventBus.getDefault().post(
                    new MusicEvents(MUSIC_DURATION, duration));
        }
    }

    public class MyReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            int control = intent.getIntExtra("control", -1);
            switch (control)
            {
                case 1:
                    status = 1; // 1表示随机播放
                    break;
                case 2:
                    status = 2;    //2表示列表播放
                    break;
                case 3:
                    status = 3;    //3表示单曲循环
                    break;


            }
        }
    }

}
