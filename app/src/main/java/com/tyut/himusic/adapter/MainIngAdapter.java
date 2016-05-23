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
import com.tyut.himusic.bean.MainIngListData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author luqingchuan on 16/5/22 22:29
 * @email luqingchuan@foxmail.com
 */
public class MainIngAdapter extends RecyclerView.Adapter<MainIngAdapter.ViewHolder>
{

    private List<MainIngListData> datas;
    private Context context;

    public MainIngAdapter(List<MainIngListData> datas, Context context)
    {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<MainIngListData> datas)
    {
        this.datas = datas;
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
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
        viewHolder.txtTitle.setText(datas.get(position).getTitle());
        viewHolder.imgPisture.setImageURI(Uri.parse(datas.get(position).getImgurl()));
        viewHolder.txtDate.setText(datas.get(position).getData());
        viewHolder.txtInformation.setText(datas.get(position).getIntroduction());

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

