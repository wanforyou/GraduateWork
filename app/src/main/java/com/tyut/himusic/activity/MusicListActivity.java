package com.tyut.himusic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tyut.himusic.R;
import com.tyut.himusic.adapter.MusicListAdapter;
import com.tyut.himusic.domain.AppConstant;
import com.tyut.himusic.domain.Mp3Info;
import com.tyut.himusic.util.MediaUtil;

import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    private int listPosition = 0;
    private ListView mMusiclist; // 音乐列表
    MusicListAdapter listAdapter;
    private List<Mp3Info> mp3Infos = null;

    private String mAction;
    private Integer mMsg;
    private long mDuration;
    private String mTitle;
    private String mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        mMusiclist = (ListView) findViewById(R.id.music_list);
        mMusiclist.setOnItemClickListener(new MusicListItemClickListener());

        mp3Infos = MediaUtil.getMp3Infos(MusicListActivity.this);

        listAdapter = new MusicListAdapter(this, mp3Infos);
        mMusiclist.setAdapter(listAdapter);
    }

    private class MusicListItemClickListener implements AdapterView.OnItemClickListener {
        /**
         * 点击列表播放音乐
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            listPosition = position; // 获取列表点击的位置
            playMusic(listPosition); // 播放音乐
        }

        public void playMusic(int listPosition) {
            if (mp3Infos != null) {
                Mp3Info mp3Info = mp3Infos.get(listPosition);

                // 添加一系列要传递的数据

                mTitle =  mp3Info.getTitle();
                mArtist = mp3Info.getArtist();
                mDuration =mp3Info.getDuration();
                mMsg =AppConstant.PlayerMsg.PLAY_MSG;

            }
        }
    }
}
