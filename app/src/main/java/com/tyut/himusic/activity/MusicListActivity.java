package com.tyut.himusic.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tyut.himusic.R;
import com.tyut.himusic.adapter.MusicListAdapter;
import com.tyut.himusic.domain.AppConstant;
import com.tyut.himusic.domain.Mp3Info;
import com.tyut.himusic.events.MusicListEvents;
import com.tyut.himusic.util.MediaUtil;
import com.tyut.himusic.util.MyLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MusicListActivity extends AppCompatActivity
{
    private int listPosition = 0;
    private ListView mMusiclist; // 音乐列表
    MusicListAdapter listAdapter;
    private List<Mp3Info> mp3Infos = null;

    private String musicAction;
    private Integer musicMsg;
    private String musicArtist;
    private String musicTitle;
    private long musicDuration;
    private String musicUrl;
    private String url;

    @Bind(R.id.music_title_list)
    TextView mTitle;

    @Bind(R.id.textView5)
    TextView mArtist;

    @Bind(R.id.music_little_pic)
    Button backToRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        mMusiclist = (ListView) findViewById(R.id.music_list);

        mMusiclist.setOnItemClickListener(new MusicListItemClickListener());
        backToRunning.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                MusicListActivity.this.finish();
            }

        });

        mp3Infos = MediaUtil.getMp3Infos(MusicListActivity.this);

        listAdapter = new MusicListAdapter(this, mp3Infos);
        mMusiclist.setAdapter(listAdapter);
    }

    private class MusicListItemClickListener implements AdapterView.OnItemClickListener
    {

//        点击列表播放音乐

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            listPosition = position; // 获取列表点击的位置

            playMusic(listPosition); // 播放音乐
        }

        public void playMusic(int listPosition)
        {
            if (mp3Infos != null)
            {
                Mp3Info mp3Info = mp3Infos.get(listPosition);
                mTitle.setText(mp3Info.getTitle());
                mArtist.setText(mp3Info.getArtist());
                // 添加一系列要传递的数据
                musicTitle = mp3Info.getTitle();
                musicArtist = mp3Info.getArtist();
                musicDuration = mp3Info.getDuration();
                musicUrl = mp3Info.getUrl();
                musicMsg =AppConstant.PlayerMsg.PLAY_MSG;
                Intent intent = new Intent();
                intent.setAction("com.tyut.himusic.media.MUSIC_SERVICE");
                intent.putExtra("url", musicUrl);
                intent.setPackage(getPackageName());
                intent.putExtra("listPosition", listPosition);
                intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
                startService(intent);
                EventBus.getDefault().post(
                        new MusicListEvents(listPosition,musicMsg,musicUrl,musicTitle,musicArtist,musicDuration));

            }
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();

    }

}




