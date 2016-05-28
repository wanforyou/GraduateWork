package com.tyut.himusic.events;

/**
 * Created by Administrator on 2016/5/29.
 */
public class MusicEvents {
    private String maction;
    private Integer mmsg;

    public MusicEvents(String action, Integer msg) {
        // TODO Auto-generated constructor stub
        maction = action;
        mmsg = msg;


    }
    public String getAction(){
        return maction;
    }
    public Integer getMsg(){
        return mmsg;
    }

}
