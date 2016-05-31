package com.tyut.himusic.events;

/**
 * Created by Administrator on 2016/5/29.
 */
public class MusicListEvents
{

    private Integer mMsg;
    private long mDuration;
    private String mTitle;
    private String mArtist;


    public MusicListEvents( Integer msg,String title ,String artist,Integer currentTime)
    {
        // TODO Auto-generated constructor stub

        mMsg = msg;
        mDuration =currentTime;
        mTitle = title;
        mArtist= artist;


    }


    public Integer getMsg()
    {
        return mMsg;
    }
    public String getTitle()
    {
        return mTitle;
    }
    public String getArtist()
    {
        return mArtist;
    }
    public Long getDuration()
    {
        return mDuration;
    }


}
