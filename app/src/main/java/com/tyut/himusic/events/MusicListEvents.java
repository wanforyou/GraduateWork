package com.tyut.himusic.events;

/**
 * Created by Administrator on 2016/5/29.
 */
public class MusicListEvents
{

    private Integer mMsg;
    private Long mDuration;
    private String mTitle;
    private String mArtist;
    private String mUrl;
    private Integer mListPosition;


    public MusicListEvents(Integer listPosition, Integer msg,
                            String url,String title ,String artist,Long duration)
    {
        // TODO Auto-generated constructor stub

        mListPosition = listPosition;
        mMsg = msg;
        mDuration =duration;
        mTitle = title;
        mUrl =url;
        mArtist= artist;


    }


    public Integer getMsg()
    {
        return mMsg;
    }
    public String getUrl()
    {
        return mUrl;
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
    public Integer getListPosition()
    {
        return mListPosition;
    }

}
