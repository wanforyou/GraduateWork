package com.tyut.himusic.events;

/**
 * Created by Administrator on 2016/5/29.
 */
public class MusicControlEvents
{
    private String maction;
    private Integer mcontrol;

    public MusicControlEvents( String action,Integer control)
    {
        // TODO Auto-generated constructor stub

        mcontrol = control;
        maction = action;


    }



    public Integer getControl()
    {
        return mcontrol;
    }
public String getMaction(){return  maction;}
}
