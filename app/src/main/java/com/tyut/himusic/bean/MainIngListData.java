package com.tyut.himusic.bean;

/**
 * @author luqingchuan on 16/5/23 10:33
 * @email luqingchuan@foxmail.com
 */
public class MainIngListData
{
    private String title;
    private String imgurl;
    private String data;
    private String introduction;
    private Integer dianzan;

    public MainIngListData(String title, String imgurl,
                           String data, String introduction, Integer dianzan)
    {
        this.title = title;
        this.imgurl = imgurl;
        this.data = data;
        this.introduction = introduction;
        this.dianzan = dianzan;
    }

    public Integer getDianzan()
    {
        return dianzan;
    }

    public void setDianzan(Integer dianzan)
    {
        this.dianzan = dianzan;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getImgurl()
    {
        return imgurl;
    }

    public void setImgurl(String imgurl)
    {
        this.imgurl = imgurl;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
