package com.tyut.himusic.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author luqingchuan on 16/5/22 22:29
 * @email luqingchuan@foxmail.com
 */
public class MainIngAdapter extends RecyclerView.Adapter<MainIngAdapter.ViewHolder>
{
    private String[] imgUrls;
    private Context context;

    public MainIngAdapter(String[] imgUrls, Context context)
    {
        super();
        this.imgUrls = imgUrls;
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
                R.layout.item_frag_main_ing, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        // 建立起ViewHolder中视图与数据的关联
        String imgUrl = imgUrls[position];
        viewHolder.imgPisture.setImageURI(Uri.parse(imgUrl));


    }

    static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @Bind(R.id.item_frag_main_ing_title)
        TextView txtTitle;
        @Bind(R.id.item_frag_main_ing_img)
        SimpleDraweeView imgPisture;
        @Bind(R.id.item_frag_main_ing_date)
        TextView txtDate;
        @Bind(R.id.item_frag_main_ing_information)
        TextView txtInformation;
        @Bind(R.id.item_frag_main_ing_share)
        ImageView imgShare;
        @Bind(R.id.item_frag_main_ing_dianzan)
        ImageView imgDianzan;

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

