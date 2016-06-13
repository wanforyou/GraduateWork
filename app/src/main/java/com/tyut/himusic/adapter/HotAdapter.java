/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.activity.MusicListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author luqingchuan on 16/5/13 15:41
 * @email luqingchuan@foxmail.com
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder>
{
    private String[] imgUrls;
    private String[] imgTitles;
    private Context context;


    public HotAdapter(String[] imgUrls,String[] imgTitles, Context context)
    {
        super();
        this.imgUrls = imgUrls;
        this.imgTitles = imgTitles;
        this.context = context;
    }

    @Override
    public int getItemCount()
    {
        return imgUrls.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_hot, viewGroup, false);
        return new ViewHolder(v);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position)
    {
        // 建立起ViewHolder中视图与数据的关联
        String imgUrl = imgUrls[position];
        String imgTitle = imgTitles[position];
        viewHolder.imgPisture.setImageURI(Uri.parse(imgUrl));
        viewHolder.txtTitle.setText(imgTitle);
        if (mOnItemClickLitener != null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, pos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(viewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @Bind(R.id.item_hot_picture)
        SimpleDraweeView imgPisture;
        @Bind(R.id.item_hot_title)
        TextView txtTitle;

        public ViewHolder(View convertView)
        {
            super(convertView);
            convertView.setOnClickListener(this);
            ButterKnife.bind(this, convertView);
        }

        // 通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(final View v)
        {

        }
    }


}
