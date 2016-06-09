/*
 * Copyright (c) 2016.
 * author:wangjiawei
 * school:tyut
 * Hi-Music
 */

package com.tyut.himusic.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder>
{
    private String[] imgUrls;
    private Context context;
    private String[] imgTitles;

    public NewAdapter(String[] imgUrls,String[] imgTitles, Context context)
    {
        super();
        this.imgUrls = imgUrls;
        this.context = context;
        this.imgTitles = imgTitles;
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
                R.layout.item_new, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        // 建立起ViewHolder中视图与数据的关联
        String imgUrl = imgUrls[position];
        viewHolder.imgPisture.setImageURI(Uri.parse(imgUrl));
        String imgTitle = imgTitles[position];


        viewHolder.txtTitle.setText(imgTitle);

    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @Bind(R.id.item_new_picture)
        SimpleDraweeView imgPisture;
        @Bind(R.id.item_new_title)
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
